
public class Pear extends Fruit{
	public int speedChangeFactor() {
		return 2;
	}
	
	public int colorChange() {
		return 0;
	}
	
	public String fileName() {
		return "pear.png";
	}
	
	public boolean hit(int x, int y, int a, int b) {
		return (getX(a) > (x - 12) && getX(a) < (x + 12)
				&& getY(b) > (y - 12) && getY(b) < (y + 12));
	}
}
