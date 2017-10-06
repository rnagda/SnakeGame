
public class Snake {
	
	private int xPos;
	private int yPos;
	private boolean head;
	
	public Snake(int x, int y, boolean head) {
		xPos = x;
		yPos = y;
		this.head = head;
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public void setX(int increment) {
		xPos += increment;
	}
	
	public void setY(int increment) {
		yPos += increment;
	}
	
	public boolean isHead() {
		return head;
	}

}
