package Snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener, MouseListener {

	public static void main(String[] args) {
		snake = new Snake();
	}

	public static Snake snake;
	public Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	public final int WIDTH = this.dim.width;
	public final int HEIGHT = this.dim.height;

	public JFrame jframe;
	public RenderPanel renderPanel;
	public Timer timer = new Timer(20, this);

	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	public ArrayList<Point> snakePartsTwo = new ArrayList<Point>();

	public static final int SCALE = 10;
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	public int ticks = 0;
	public int time = 0;
	public int direction = 1;
	public int score;
	public int tailLength;
	public int gameMode = 0;
	public int players = 0;
	public int option;
	public int directionTwo = 0;
	public int scoreTwo;
	public int tailLengthTwo;
	public int theme = 1;
	public Point head;
	public Point headTwo;
	public Point cherry;
	public Random random;
	public boolean overOne;
	public boolean overTwo;
	public boolean paused;
	public boolean start;
	public boolean menu = true;
	public boolean loading = false;
	public boolean opening = true;

	public Snake() {
		this.renderPanel = new RenderPanel();
		this.jframe = new JFrame("Snake");
		this.jframe.add(this.renderPanel);
		this.jframe.setExtendedState(6);
		this.jframe.setUndecorated(true);
		this.jframe.setResizable(false);
		this.jframe.setLocationRelativeTo(null);
		this.jframe.setVisible(true);
		this.jframe.setDefaultCloseOperation(3);
		this.jframe.addKeyListener(this);
		this.jframe.addMouseListener(this);
		startGame();
	}

	private void startGame() {
		this.loading = false;
		this.opening = true;
		this.timer.start();
	}

	public void start() {
		this.overOne = false;
		this.overTwo = false;
		this.start = true;
		this.paused = false;
		this.option = 0;
		this.time = 0;
		this.ticks = 0;
		this.random = new Random();

		this.score = 0;
		this.tailLength = 10;
		this.direction = 1;
		this.head = new Point(0, -1);
		this.snakeParts.clear();
		this.headTwo = new Point(this.WIDTH / 10 - 1, this.HEIGHT / 10 + 1);

		for (int i = 0; i < this.tailLength; i++) {
			this.snakeParts.add(new Point(this.head.x, this.head.y));
		}

		this.scoreTwo = 0;
		this.tailLengthTwo = 10;
		this.directionTwo = 0;
		this.snakePartsTwo.clear();

		for (int i = 0; i < this.tailLengthTwo; i++) {
			this.snakePartsTwo.add(new Point(this.headTwo.x, this.headTwo.y));
		}

		this.cherry = new Point(1 + this.random.nextInt(this.WIDTH / 10 - 2),
				1 + this.random.nextInt(this.HEIGHT / 10 - 2));
		this.timer.restart();
	}

	public void actionPerformed(ActionEvent e) {
		this.renderPanel.repaint();
		this.ticks += 1;
		if ((this.ticks % 2 == 0) && (this.head != null) && (!this.paused) && (!this.overOne) && (!this.overTwo)
				&& (!this.menu)) {
			this.time += 1;
			if (this.direction == 0)
				if ((this.head.y > 0) && (noTailAt(this.head.x, this.head.y - 1))) {
					this.head = new Point(this.head.x, this.head.y - 1);
				} else
					this.overOne = true;
			if (this.direction == 1)
				if ((this.head.y + 1 < this.HEIGHT / 10) && (noTailAt(this.head.x, this.head.y + 1))) {
					this.head = new Point(this.head.x, this.head.y + 1);
				} else
					this.overOne = true;
			if (this.direction == 2)
				if ((this.head.x - 1 >= 0) && (noTailAt(this.head.x - 1, this.head.y))) {
					this.head = new Point(this.head.x - 1, this.head.y);
				} else
					this.overOne = true;
			if (this.direction == 3)
				if ((this.head.x + 1 < this.WIDTH / 10) && (noTailAt(this.head.x + 1, this.head.y))) {
					this.head = new Point(this.head.x + 1, this.head.y);
				} else
					this.overOne = true;

			if (this.players == 2) {
				if (this.directionTwo == 0)
					if ((this.headTwo.y > 0) && (noTailAtTwo(this.headTwo.x, this.headTwo.y - 1))) {
						this.headTwo = new Point(this.headTwo.x, this.headTwo.y - 1);
					} else
						this.overTwo = true;
				if (this.directionTwo == 1)
					if ((this.headTwo.y + 1 < this.HEIGHT / 10) && (noTailAtTwo(this.headTwo.x, this.headTwo.y + 1))) {
						this.headTwo = new Point(this.headTwo.x, this.headTwo.y + 1);
					} else
						this.overTwo = true;
				if (this.directionTwo == 2)
					if ((this.headTwo.x - 1 >= 0) && (noTailAtTwo(this.headTwo.x - 1, this.headTwo.y))) {
						this.headTwo = new Point(this.headTwo.x - 1, this.headTwo.y);
					} else
						this.overTwo = true;
				if (this.directionTwo == 3)
					if ((this.headTwo.x + 1 < this.WIDTH / 10) && (noTailAtTwo(this.headTwo.x + 1, this.headTwo.y))) {
						this.headTwo = new Point(this.headTwo.x + 1, this.headTwo.y);
					} else
						this.overTwo = true;

				this.snakePartsTwo.add(new Point(this.headTwo.x, this.headTwo.y));
				if (this.snakePartsTwo.size() > this.tailLengthTwo) {
					this.snakePartsTwo.remove(0);
				}
			}

			this.snakeParts.add(new Point(this.head.x, this.head.y));
			if (this.snakeParts.size() > this.tailLength)
				this.snakeParts.remove(0);
			if (this.cherry != null) {
				if (this.head.equals(this.cherry)) {
					if (this.gameMode == 2) {
						this.tailLength += 1 + (this.score + this.scoreTwo) / 10;
						this.score += 10;
						this.cherry.setLocation(1 + this.random.nextInt(this.WIDTH / 10 - 2),
								1 + this.random.nextInt(this.HEIGHT / 10 - 2));
					} else {
						this.tailLength += 1;
						this.score += 10;
						if ((this.players == 2) && (this.score >= 300))
							this.overTwo = true;
						this.cherry.setLocation(1 + this.random.nextInt(this.WIDTH / 10 - 2),
								1 + this.random.nextInt(this.HEIGHT / 10 - 2));
					}
				}

				if ((this.players == 2) && (this.headTwo.equals(this.cherry))) {
					if (this.gameMode == 2) {
						this.tailLengthTwo += 1 + (this.score + this.scoreTwo) / 10;
						this.scoreTwo += 10;
						this.cherry.setLocation(1 + this.random.nextInt(this.WIDTH / 10 - 2),
								1 + this.random.nextInt(this.HEIGHT / 10 - 2));
					} else {
						this.tailLengthTwo += 1;
						this.scoreTwo += 10;
						if (this.scoreTwo >= 300)
							this.overOne = true;
						this.cherry.setLocation(1 + this.random.nextInt(this.WIDTH / 10 - 2),
								1 + this.random.nextInt(this.HEIGHT / 10 - 2));
					}
				}
			}
		}
	}

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

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		if ((this.loading) && (!this.opening) && ((i == 27) || (i == 32))) {
			this.loading = false;
		}

		if ((this.opening) && (!this.loading) && ((i == 27) || (i == 32))) {
			this.loading = true;
			this.opening = false;
		}

		if ((!this.opening) && (!this.loading)) {
			if (!this.menu) {
				if ((i == 37) && (this.direction != 3)) {
					this.direction = 2;
				}
				if ((i == 39) && (this.direction != 2)) {
					this.direction = 3;
				}
				if ((i == 38) && (this.direction != 1)) {
					this.direction = 0;
				}
				if ((i == 40) && (this.direction != 0)) {
					this.direction = 1;
				}

				if (this.players == 2) {
					if ((i == 65) && (this.directionTwo != 3)) {
						this.directionTwo = 2;
					}
					if ((i == 68) && (this.directionTwo != 2)) {
						this.directionTwo = 3;
					}
					if ((i == 87) && (this.directionTwo != 1)) {
						this.directionTwo = 0;
					}
					if ((i == 83) && (this.directionTwo != 0)) {
						this.directionTwo = 1;
					}
				}

				if (i == 27) {
					this.players = 0;
					this.option = 0;
					this.overOne = false;
					this.overTwo = false;
					this.paused = false;
					this.start = false;
					this.menu = true;
				}

				if (i == 32)
					if ((this.overOne) || (this.overTwo)) {
						start();
					} else if (this.gameMode > 0)
						this.paused = (!this.paused);
			} else {
				if ((i == 38) && (this.option > 1)) {
					this.option -= 1;
				}

				if ((i == 40) && (this.option < 3)) {
					this.option += 1;
				}

				if (i == 37) {
					this.players = 1;
				}

				if (i == 39) {
					this.players = 2;
				}

				if ((i == 10) || (i == 32)) {
					if (this.players != 0) {
						if (this.option == 1) {
							this.gameMode = 1;
							this.menu = false;
							start();
						}
						if (this.option == 2) {
							this.gameMode = 2;
							this.menu = false;
							start();
						}
					}
					if (this.option == 3) {
						System.exit(0);
					}
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (this.menu) {
			if ((this.players != 0) && (x > this.WIDTH / 2 - 100) && (x < this.WIDTH / 2 + 100)) {
				if ((y > this.HEIGHT / 2 - 25) && (y < this.HEIGHT / 2 + 4)) {
					this.gameMode = 1;
					this.menu = false;
				}
				if ((y > this.HEIGHT / 2 + 6) && (y < this.HEIGHT / 2 + 35)) {
					this.gameMode = 2;
					this.menu = false;
					start();
				}
			}
			if ((x > this.WIDTH / 2 - 100) && (x < this.WIDTH / 2 + 100) && (y > this.HEIGHT / 2 + 37)
					&& (y < this.HEIGHT / 2 + 66)) {
				System.exit(0);
			}
			if ((x < this.WIDTH / 2 - 150) && (y < this.HEIGHT / 2 + 150)) {
				this.players = 1;
			}
			if ((x > this.WIDTH / 2 + 150) && (y < this.HEIGHT / 2 + 150)) {
				this.players = 2;
			}
			if ((x > this.WIDTH / 2 - 250) && (x < this.WIDTH / 2 - 30) && (y > this.HEIGHT / 2 + 150)
					&& (y < this.HEIGHT / 2 + 300)) {
				this.theme = 1;
			}
			if ((x > this.WIDTH / 2 + 30) && (x < this.WIDTH / 2 + 250) && (y > this.HEIGHT / 2 + 150)
					&& (y < this.HEIGHT / 2 + 300)) {
				this.theme = 2;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
