import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game {
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
		JFrame frame = new JFrame();
		GameCanvas game = new GameCanvas();
		
		String name= JOptionPane.showInputDialog("Please enter player name: ");
		game.setName(name);
		
		Object[] colors = {"Cyan", "Orange", "Green", "Pink", "Yellow"};
		String s = (String)JOptionPane.showInputDialog(
		                    frame, "Choose color","Customized Dialog",
		                    JOptionPane.PLAIN_MESSAGE, null, colors, "Cyan");

		if ((s != null) && (s.length() > 0)) {
		    game.setColor(s);
		}
		
		frame.add(game);
		frame.setBackground(Color.BLACK);
		frame.pack();
		frame.addKeyListener(game);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
