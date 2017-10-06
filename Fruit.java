
public class Fruit {
	private int[][] possibilities = new int[2][50];
	
	public Fruit() {
		build();
	}

	public void build() {
		for (int i = 0; i < possibilities.length; i++) {
			for (int j = 0; j < possibilities[0].length; j++) {
				possibilities[i][j] = (j * 10) + ((i) * 65);
			}
		}
	}
	public int getX(int x) {
		int xApple = possibilities[0][x];
		return xApple;
	}
	
	public int getY(int y) {
		int yApple = possibilities[1][y];
		return yApple;
	}
	
	public int checkX(int x, String direction) {
		if (direction.equals("up")) {
			return x;
			//snake.put(snake.size(), new Snake(x, y + 13, false));
		} else if (direction.equals("down")) {
			return x;
			//snake.put(snake.size(), new Snake(x, y - 13, false));
		} else if (direction.equals("right")) {
			return (x - 13);
			//snake.put(snake.size(), new Snake(x - 13, y, false));
		} else {
			return (x + 13);
			//snake.put(snake.size(), new Snake(x + 13, y, false));
		}
	}
	
	public int checkY(int y, String direction) {
		if (direction.equals("up")) {
			return (y + 13);
		} else if (direction.equals("down")) {
			return (y - 13);
		} else if (direction.equals("right")) {
			return y;
		} else {
			return y;
		}
	}
}
