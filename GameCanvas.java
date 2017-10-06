import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameCanvas extends JPanel implements ActionListener, KeyListener {
	private String name = "";
	private Color color = Color.WHITE;
	private Timer time;
	private String direction = "";
	private int delay = 100;
	private boolean startOfGame = true;
	private boolean gameOver = false;
	private Random random = new Random();
	private int a = random.nextInt(50);
	private int b = random.nextInt(50);
	private int length = 3;
	private int speedChange = 1;
	private int colorChange;
	private int score = 0;
	BufferedImage image;
	BufferedWriter writer = null;
	boolean showInstructions = true;

	Apple apple = new Apple();
	Pear pear = new Pear();
	Watermelon watermelon = new Watermelon();
	

	String filename = apple.fileName();
	
	private Map<Integer, Snake> snake = new TreeMap<Integer, Snake>();

	public GameCanvas() {
		addKeyListener(this);
		time = new Timer(delay, this);
		time.start();
	}

	public void setName(String n) {
		name = n;
	}

	public void setColor(String c) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
			SecurityException, ClassNotFoundException {
		Field field = Class.forName("java.awt.Color").getField(c.toUpperCase());
		color = (Color) field.get(null);
	}

	public void initializeSnake() {
		snake.put(0, new Snake(250, 250, true));
		snake.put(1, new Snake(237, 250, false));
		snake.put(2, new Snake(224, 250, false));
	}

	public boolean gameOver() {
		return gameOver;
	}

	public void setGameState(boolean gameOver) {
		this.gameOver = gameOver;
	}
	@Override
	public void paintComponent(Graphics gc) {
		Font f = new Font("Score", Font.PLAIN, 13);
		Font fT = new Font("Title", Font.BOLD, 34);
		
		gc.setColor(Color.BLACK);
		gc.fillRect(0, 0, getWidth(), getHeight());

		gc.setColor(Color.WHITE);
		gc.drawRect(395, 5, 100, 60);

		gc.setFont(fT);
		gc.drawRect(5, 5, 389, 60);
		gc.setColor(Color.ORANGE);
		gc.drawString("The Snake Game!", 50, 50);
		
		gc.setColor(color);
		gc.setFont(f);
		gc.drawString(name, 403, 22);
		gc.drawString("Score: " + score, 403, 40);
		gc.drawString("Length: " + length, 403, 58);

		if (startOfGame) {
			initializeSnake();
			startOfGame = false;
		}
		if (snake.get(0).getX() < 0 || snake.get(0).getX() > 500
				|| snake.get(0).getY() < 65 || snake.get(0).getY() > 565) {
			gameOver = true;
			break;
		}

		for (int i = 0; i < snake.size(); i++) {
			int x = snake.get(i).getX();
			int y = snake.get(i).getY();
			if (snake.get(i).isHead()) {
				gc.drawOval(x, y, 12, 12);
			} else {
				gc.fillOval(x, y, 12, 12);
			}
		}

		// System.out.println("Snake head is at: (" + snake.get(0).getX() + ", "
		// +
		// snake.get(0).getY() + ")");
		//
		// System.out.println("apple is at: (" + apple.getX(a) + ", " +
		// apple.getY(b) + ")");

		for (int key : snake.keySet()) {
			if (snake.get(key).getX() == apple.getX(a) &&
					snake.get(key).getY() == apple.getY(b)) {
				a = random.nextInt(50);
				b = random.nextInt(50);
			}
			if (key > 0) {
				if (snake.get(key).getX() == snake.get(0).getX() 
						&& snake.get(key).getY() == snake.get(0).getY()) {
					gameOver = true;
					break;
				}
			}
		}
		
		if (apple.hit(snake.get(0).getX(), snake.get(0).getY(), a, b)){
			int x = snake.get(snake.size() - 1).getX();
			int y = snake.get(snake.size() - 1).getY();
			
			time.setDelay(delay / speedChange);
			
			int newX = apple.checkX(x, direction);
			int newY = apple.checkY(y, direction);
			
			snake.put(snake.size(), new Snake(newX, newY, false));
			
			if (colorChange < 2 && colorChange > 0) {
				color = Color.BLUE;
			}
			
			length++;
			if (length % 15 == 0) {
				speedChange  = watermelon.speedChangeFactor();
				colorChange = watermelon.colorChange();
				filename = watermelon.fileName();
				score += 15;
			} else if (length % 10 == 0) {
				speedChange  = pear.speedChangeFactor();
				colorChange = pear.colorChange();
				filename = pear.fileName();
				score += 13;
			} else {
				speedChange  = apple.speedChangeFactor();
				colorChange = apple.colorChange();
				filename = apple.fileName();
				score += 10;
			}	
			a = random.nextInt(50);
			b = random.nextInt(50);
		}
		
		try {
			image = ImageIO.read(new File(filename));
		} catch (IOException e) {
		}
		
		gc.drawImage(image, apple.getX(a), apple.getY(b), 16, 18, null); 
		
		//gc.fillOval(apple.getX(a), apple.getY(b), 13, 13);
		
		if (gameOver) {
			time.stop();
			try {
				writer = new BufferedWriter(new FileWriter("scores.txt", true));
				writer.write(name + "\t" + score);
			} catch (Exception e) {
	            e.printStackTrace();
			} finally {
				try {
	                writer.close();
	            } catch (Exception e) {
	            }
			}
			gc.setColor(Color.WHITE);
			gc.setFont(new Font("gameOver", Font.BOLD, 40));
			gc.drawString("Game Over!", 140, 290);
			
			gc.setFont(new Font("playAgain", Font.BOLD, 20));
			gc.drawString("Press Space Bar to Play Again", 110, 330);
		}

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 565);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
		if (direction.equals("up")) {
			for (int i = snake.size() - 1; i > 0; i--) {
				int x = snake.get(i - 1).getX();
				int y = snake.get(i - 1).getY();
				snake.put(i, new Snake(x, y, false));
			}
			(snake.get(0)).setY(-13);
			repaint();
		}
		else if (direction.equals("right")) {
			for (int i = snake.size() - 1; i > 0; i--) {
				int x = snake.get(i - 1).getX();
				int y = snake.get(i - 1).getY();
				snake.put(i, new Snake(x, y, false));
			}
			(snake.get(0)).setX(13);
			repaint();
		}
		else if (direction.equals("left")) {
			for (int i = snake.size() - 1; i > 0; i--) {
				int x = snake.get(i - 1).getX();
				int y = snake.get(i - 1).getY();
				snake.put(i, new Snake(x, y, false));
			}
			(snake.get(0)).setX(-13);
			repaint();
		}
		else if (direction.equals("down")) {
			for (int i = snake.size() - 1; i > 0; i--) {
				int x = snake.get(i - 1).getX();
				int y = snake.get(i - 1).getY();
				snake.put(i, new Snake(x, y, false));
			}
			(snake.get(0)).setY(13);
			repaint();
		} 
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_SPACE:
			direction = "";
			score = 0;
			length = 3;
			startOfGame = true;
			gameOver = false;
			speedChange = 1;
			snake.clear();
			time.start();
			colorChange = 0;
			repaint();
		case KeyEvent.VK_UP:
			if (!direction.equals("down")) {
				direction = "up";
				break;
			}
		case KeyEvent.VK_DOWN:
			if (!direction.equals("up")) {
				direction = "down";
				break;
			}
		case KeyEvent.VK_LEFT:
			if (!direction.equals("right")) {
				direction = "left";
				break;
			}
		case KeyEvent.VK_RIGHT:
			if (!direction.equals("left")) {
				direction = "right";
				break;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
