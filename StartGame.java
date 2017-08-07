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
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class StartGame implements KeyListener, MouseListener, ActionListener{
	
	public RenderPanel renderPanel;
	public JFrame jframe;
	//public Controls controls = new Controls();
	
	public static StartGame startGame;
	public Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	public final int WIDTH = this.dim.width;
	public final int HEIGHT = this.dim.height;

	public int gameMode = 0;
	public int players = 0;
	public int option;
	public int theme = 1;
	public boolean paused;
	public boolean start;
	public boolean menu = true;
	public boolean loading;
	public boolean opening;
	public Random random;
	public static Snake snake;
	
	/////////////////////////////////////
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	public int ticks = 0;
	public int time = 0;
	
	public Timer timer = new Timer(20, this);
	
	
	
	/////////////////////////////////
	
	
	public static void main(String[] args){
		startGame = new StartGame();
	}

	public StartGame() {
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
		snake = new Snake();
		sstartGame();
	}
	
	public void sstartGame() {
		this.loading = false;
		this.opening = true;
		this.timer.start();
	}
	
	public void start() {
		snake.overOne = false;
		snake.overTwo = false;
		this.start = true;
		this.paused = false;
		this.option = 0;
		this.time = 0;
		this.ticks = 0;
		this.random = new Random();

		snake.score = 0;
		snake.tailLength = 10;
		snake.direction = 1;
		snake.head = new Point(0, -1);
		snake.snakeParts.clear();
		snake.headTwo = new Point(0, -1);

		for (int i = 0; i < snake.tailLength; i++) {
			snake.snakeParts.add(new Point(snake.head.x, snake.head.y));
		}

		snake.scoreTwo = 0;
		snake.tailLengthTwo = 10;
		snake.directionTwo = 1;
		snake.snakePartsTwo.clear();

		for (int i = 0; i < snake.tailLengthTwo; i++) {
			snake.snakePartsTwo.add(new Point(snake.headTwo.x, snake.headTwo.y));
		}

		snake.cherry = new Point(1 + this.random.nextInt(this.WIDTH / 10 - 2),
				1 + this.random.nextInt(this.HEIGHT / 10 - 2));
		this.timer.restart();
	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (this.menu) {
			if ((this.players != 0) && (x > this.WIDTH / 2 - 100) && (x < this.WIDTH / 2 + 100)) {
				if ((y > this.HEIGHT / 2 - 25) && (y < this.HEIGHT / 2 + 4)) {
					this.gameMode = 1;
					this.menu = false;
					start();
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

	public void mouseReleased(MouseEvent arg0) {
		
		
	}

	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if ((this.opening) && (!this.loading) && ((i == 27) || (i == 32))) {
			this.loading = true;
			this.opening = false;
		}
		
		if ((this.loading) && (!this.opening) && ((i == 27) || (i == 32))) {
			this.loading = false;
		}

		if ((!this.opening) && (!this.loading)) {
			if (!this.menu) {
				if ((i == 37) && (snake.direction != 3)) {
					snake.direction = 2;
				}
				if ((i == 39) && (snake.direction != 2)) {
					snake.direction = 3;
				}
				if ((i == 38) && (snake.direction != 1)) {
					snake.direction = 0;
				}
				if ((i == 40) && (snake.direction != 0)) {
					snake.direction = 1;
				}

				if (this.players == 2) {
					if ((i == 65) && (snake.directionTwo != 3)) {
						snake.directionTwo = 2;
					}
					if ((i == 68) && (snake.directionTwo != 2)) {
						snake.directionTwo = 3;
					}
					if ((i == 87) && (snake.directionTwo != 1)) {
						snake.directionTwo = 0;
					}
					if ((i == 83) && (snake.directionTwo != 0)) {
						snake.directionTwo = 1;
					}
				}

				if (i == 27) {
					this.players = 0;
					this.option = 0;
					snake.overOne = false;
					snake.overTwo = false;
					this.paused = false;
					this.start = false;
					this.menu = true;
				}

				if (i == 32)
					if ((snake.overOne) || (snake.overTwo)) {
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.renderPanel.repaint();
		this.ticks += 1;
		if ((this.ticks % 2 == 0) && (snake.head != null) && (!this.paused) && (!snake.overOne) && (!snake.overTwo) && (!this.menu)) {
			this.time += 1;
			if (snake.direction == 0)
				if ((snake.head.y > 0) && (snake.noTailAt(snake.head.x, snake.head.y - 1))) {
					snake.head = new Point(snake.head.x, snake.head.y - 1);
				} else
					snake.overOne = true;
			if (snake.direction == 1)
				if ((snake.head.y + 1 < this.HEIGHT / 10) && (snake.noTailAt(snake.head.x, snake.head.y + 1))) {
					snake.head = new Point(snake.head.x, snake.head.y + 1);
				} else
					snake.overOne = true;
			if (snake.direction == 2)
				if ((snake.head.x - 1 >= 0) && (snake.noTailAt(snake.head.x - 1, snake.head.y))) {
					snake.head = new Point(snake.head.x - 1, snake.head.y);
				} else
					snake.overOne = true;
			if (snake.direction == 3)
				if ((snake.head.x + 1 < this.WIDTH / 10) && (snake.noTailAt(snake.head.x + 1, snake.head.y))) {
					snake.head = new Point(snake.head.x + 1, snake.head.y);
				} else
					snake.overOne = true;

			if (this.players == 2) {
				if (snake.directionTwo == 0)
					if ((snake.headTwo.y > 0) && (snake.noTailAtTwo(snake.headTwo.x, snake.headTwo.y - 1))) {
						snake.headTwo = new Point(snake.headTwo.x, snake.headTwo.y - 1);
					} else
						snake.overTwo = true;
				if (snake.directionTwo == 1)
					if ((snake.headTwo.y + 1 < this.HEIGHT / 10) && (snake.noTailAtTwo(snake.headTwo.x, snake.headTwo.y + 1))) {
						snake.headTwo = new Point(snake.headTwo.x, snake.headTwo.y + 1);
					} else
						snake.overTwo = true;
				if (snake.directionTwo == 2)
					if ((snake.headTwo.x - 1 >= 0) && (snake.noTailAtTwo(snake.headTwo.x - 1, snake.headTwo.y))) {
						snake.headTwo = new Point(snake.headTwo.x - 1, snake.headTwo.y);
					} else
						snake.overTwo = true;
				if (snake.directionTwo == 3)
					if ((snake.headTwo.x + 1 < this.WIDTH / 10) && (snake.noTailAtTwo(snake.headTwo.x + 1, snake.headTwo.y))) {
						snake.headTwo = new Point(snake.headTwo.x + 1, snake.headTwo.y);
					} else
						snake.overTwo = true;

				snake.snakePartsTwo.add(new Point(snake.headTwo.x, snake.headTwo.y));
				if (snake.snakePartsTwo.size() > snake.tailLengthTwo) {
					snake.snakePartsTwo.remove(0);
				}
			}

			snake.snakeParts.add(new Point(snake.head.x, snake.head.y));
			if (snake.snakeParts.size() > snake.tailLength)
				snake.snakeParts.remove(0);
			if (snake.cherry != null) {
				if (snake.head.equals(snake.cherry)) {
					if (this.gameMode == 2) {
						snake.tailLength += 1 + (snake.score + snake.scoreTwo) / 10;
						snake.score += 10;
						snake.cherry.setLocation(1 + this.random.nextInt(this.WIDTH / 10 - 2),
								1 + this.random.nextInt(this.HEIGHT / 10 - 2));
					} else {
						snake.tailLength += 1;
						snake.score += 10;
						if ((this.players == 2) && (snake.score >= 300))
							snake.overTwo = true;
						snake.cherry.setLocation(1 + this.random.nextInt(this.WIDTH / 10 - 2),
								1 + this.random.nextInt(this.HEIGHT / 10 - 2));
					}
				}

				if ((this.players == 2) && (snake.headTwo.equals(snake.cherry))) {
					if (this.gameMode == 2) {
						snake.tailLengthTwo += 1 + (snake.score + snake.scoreTwo) / 10;
						snake.scoreTwo += 10;
						snake.cherry.setLocation(1 + this.random.nextInt(this.WIDTH / 10 - 2),
								1 + this.random.nextInt(this.HEIGHT / 10 - 2));
					} else {
						snake.tailLengthTwo += 1;
						snake.scoreTwo += 10;
						if (snake.scoreTwo >= 300)
							snake.overOne = true;
						snake.cherry.setLocation(1 + this.random.nextInt(this.WIDTH / 10 - 2),
								1 + this.random.nextInt(this.HEIGHT / 10 - 2));
					}
				}
			}
		}
	}
}
