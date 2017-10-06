
public class Watermelon extends Fruit{
	public int speedChangeFactor() {
		return 1;
	}
	
	public int colorChange() {
		int x = (int) Math.random() * 2;
		return x;
	}
	
	public String fileName() {
		return "watermelon.png";
	}
	
	public boolean hit(int x, int y, int a, int b) {
		return (getX(a) > (x - 12) && getX(a) < (x + 12)
				&& getY(b) > (y - 12) && getY(b) < (y + 12));
	}

}
