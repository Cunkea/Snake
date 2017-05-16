package Snake;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {

	public static Snake snake;

	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	public ArrayList<Point> snakePartsTwo = new ArrayList<Point>();

	public int direction;
	public int score;
	public int tailLength;
	public int directionTwo;
	public int scoreTwo;
	public int tailLengthTwo;
	public Point head;
	public Point headTwo;
	public Point cherry;
	public boolean overOne = false;
	public boolean overTwo = false;
	
	public Snake(){
		this.direction = 1;
		this.directionTwo = 0;
	}

	//collision
	public boolean noTailAt(int x, int y) {
		for (Point point : this.snakeParts) {
			if (point.equals(new Point(x, y))) {
				return false;
			}
		}
		return true;
	}

	public boolean noTailAtTwo(int x, int y) {
		for (Point point : this.snakePartsTwo) {
			if (point.equals(new Point(x, y))) {
				return false;
			}
		}
		return true;
	}
}