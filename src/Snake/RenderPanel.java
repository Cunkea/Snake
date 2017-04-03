package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class RenderPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	public final int WIDTH = this.dim.width;
	public final int HEIGHT = this.dim.height;

	public int c = 250;
	public boolean transparent = true;
	public String str;

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Snake snake = Snake.snake;
		if (snake.opening) {
			g.setColor(new Color(13286784));
			g.fillRect(0, 0, this.WIDTH, this.HEIGHT);
			g.setColor(new Color(0, 0, 0, this.c));
			g.fillRect(0, 0, this.WIDTH, this.HEIGHT);
			if (this.c >= 2) {
				this.c -= 2;
			} else {
				snake.opening = false;
				snake.loading = true;
				this.c = 0;
			}
		}

		if (snake.loading) {
			g.setColor(new Color(13286784));
			g.fillRect(0, 0, this.WIDTH, this.HEIGHT);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", 1, 100));
			g.drawString("Snake Game", this.WIDTH / 2 - 280, this.HEIGHT / 2);
			if (this.transparent) {
				this.c += 5;
				if (this.c >= 160)
					this.transparent = false;
			} else {
				this.c -= 5;
				if (this.c <= 0)
					this.transparent = true;
			}

			g.setColor(new Color(255, 255, 255, this.c));
			g.setFont(new Font("Arial", 1, 30));
			g.drawString("Pres Space continue", this.WIDTH / 2 - 140, this.HEIGHT / 2 + 30);
		}

		if ((!snake.loading) && (!snake.opening)) {
			g.setColor(new Color(13286784));
			g.fillRect(0, 0, this.WIDTH, this.HEIGHT);

			// crtanje 1. zmije
			if ((snake.players > 0) && (snake.start)) {
				g.setColor(new Color(7783598).darker());
				if (snake.theme == 2) {
					for (Point point : snake.snakeParts) {
						g.fillOval(point.x * 10, point.y * 10, 10, 10);
					}
					g.fillOval(snake.head.x * 10, snake.head.y * 10, 10, 10);
				} else {
					for (Point point : snake.snakeParts) {
						g.fillRect(point.x * 10, point.y * 10, 10, 10);
					}
					g.fillRect(snake.head.x * 10, snake.head.y * 10, 10, 10);
				}
			}

			// crtanje 2. zmije
			if ((snake.players == 2) && (snake.start)) {
				g.setColor(new Color(4788598).darker());
				if (snake.theme == 2) {
					for (Point point : snake.snakePartsTwo) {
						g.fillOval(point.x * 10, point.y * 10, 10, 10);
					}
					g.fillOval(snake.headTwo.x * 10, snake.headTwo.y * 10, 10, 10);
				} else {
					for (Point point : snake.snakePartsTwo) {
						g.fillRect(point.x * 10, point.y * 10, 10, 10);
					}
					g.fillRect(snake.headTwo.x * 10, snake.headTwo.y * 10, 10, 10);
				}

				// mjenja boju kada se preklapaju zmije
				for (Point point : snake.snakeParts) {
					for (Point pointTwo : snake.snakePartsTwo) {
						if (point.equals(pointTwo)) {
							g.setColor(Color.blue.darker());
							if (snake.theme == 2) {
								g.fillOval(point.x * 10, point.y * 10, 10, 10);
							} else {
								g.fillRect(point.x * 10, point.y * 10, 10, 10);
							}
						}
					}
				}
			}

			if (!snake.menu) {
				g.setColor(new Color(14183536).darker());
				g.fillOval(snake.cherry.x * 10, snake.cherry.y * 10, 10, 10);
			}

			if ((snake.gameMode == 2) && (!snake.menu)) {
				g.setColor(Color.BLACK);
				g.setFont(new Font("Arial", 1, 10));
				g.drawString("+ " + (1 + (snake.score + snake.scoreTwo) / 10), snake.cherry.x * 10,
						snake.cherry.y * 10);
			}

			if ((snake.players == 1) && (!snake.menu)) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", 1, 20));
				this.str = ("Score: " + snake.score + ", Time: " + snake.time / 20);
				g.drawString(this.str, getWidth() / 2 - this.str.length() * 6, 30);
			}

			if ((snake.players == 2) && (!snake.menu)) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", 1, 20));
				if (snake.gameMode == 2) {
					this.str = ("Score: " + snake.score + " / 300");
				} else {
					this.str = ("Score: " + snake.score);
				}

				g.drawString(this.str, 10, 30);
				this.str = ("Time: " + snake.time / 20);
				g.drawString(this.str, getWidth() / 2 - this.str.length() * 6, 30);

				if (snake.gameMode == 2) {
					this.str = ("Score: " + snake.scoreTwo + " / 300");
				} else {
					this.str = ("Score: " + snake.scoreTwo);
				}
				g.drawString(this.str, getWidth() - this.str.length() * 10, 30);
			}

			if ((snake.overOne) && (snake.players == 1)) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", 1, 100));
				g.drawString("Game Over!", this.WIDTH / 2 - 280, this.HEIGHT / 2 - 50);
				g.setFont(new Font("Arial", 1, 30));
				g.drawString("Pres Space to start again", this.WIDTH / 2 - 175, this.HEIGHT / 2);
				g.drawString("Pres Esc to exit", this.WIDTH / 2 - 115, this.HEIGHT / 2 + 30);
			}

			if ((snake.overOne) && (snake.players == 2) && (!snake.overTwo)) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", 1, 100));
				g.drawString("Player Two Wins!", this.WIDTH / 2 - 390, this.HEIGHT / 2 - 50);
				g.setFont(new Font("Arial", 1, 30));
				g.drawString("Pres Space to start again", this.WIDTH / 2 - 175, this.HEIGHT / 2);
				g.drawString("Pres Esc to exit", this.WIDTH / 2 - 115, this.HEIGHT / 2 + 30);
			}

			if (snake.paused) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", 1, 100));
				g.drawString("Paused", this.WIDTH / 2 - 190, this.HEIGHT / 2 - 50);
				g.setFont(new Font("Arial", 1, 30));
				g.drawString("Pres Space to continue", this.WIDTH / 2 - 175, this.HEIGHT / 2);
			}

			if ((snake.overTwo) && (snake.players == 2) && (!snake.overOne)) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", 1, 100));
				g.drawString("Player One Wins!", this.WIDTH / 2 - 390, this.HEIGHT / 2 - 50);
				g.setFont(new Font("Arial", 1, 30));
				g.drawString("Pres Space to start again", this.WIDTH / 2 - 175, this.HEIGHT / 2);
				g.drawString("Pres Esc to exit", this.WIDTH / 2 - 115, this.HEIGHT / 2 + 30);
			}

			if ((snake.overTwo) && (snake.players == 2) && (snake.overOne)) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", 1, 100));
				g.drawString("Nobody Wins!", this.WIDTH / 2 - 330, this.HEIGHT / 2 - 50);
				g.setFont(new Font("Arial", 1, 30));
				g.drawString("Pres Space to start again", this.WIDTH / 2 - 175, this.HEIGHT / 2);
				g.drawString("Pres Esc to exit", this.WIDTH / 2 - 115, this.HEIGHT / 2 + 30);
			}

			if (snake.menu) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial", 1, 100));
				g.drawString("Snake Game", this.WIDTH / 2 - 280, this.HEIGHT / 2 - 50);
				g.setFont(new Font("Arial", 1, 25));

				if (snake.menu) {
					if (snake.players == 1) {
						g.setColor(Color.GREEN);
					} else {
						g.setColor(Color.GREEN.darker());
					}

					g.fillOval(this.WIDTH / 2 - 230, this.HEIGHT / 2 - 25, 20, 20);
					g.setColor(Color.WHITE);
					g.drawString("1 Player", this.WIDTH / 2 - 270, this.HEIGHT / 2 + 15);

					if (snake.players == 2) {
						g.setColor(Color.GREEN);
					} else {
						g.setColor(Color.GREEN.darker());
					}

					g.fillOval(this.WIDTH / 2 + 230, this.HEIGHT / 2 - 25, 20, 20);
					g.setColor(Color.WHITE);
					g.drawString("2 Players", this.WIDTH / 2 + 185, this.HEIGHT / 2 + 15);

					if (snake.option == 1) {
						g.setColor(Color.lightGray);
					} else {
						g.setColor(Color.gray);
					}

					g.fillRect(this.WIDTH / 2 - 100, this.HEIGHT / 2 - 25, 200, 29);
					g.setColor(Color.WHITE);
					g.drawString("Normal mode", this.WIDTH / 2 - 78, this.HEIGHT / 2 - 1);

					if (snake.option == 2) {
						g.setColor(Color.lightGray);
					} else {
						g.setColor(Color.gray);
					}

					g.fillRect(this.WIDTH / 2 - 100, this.HEIGHT / 2 + 6, 200, 29);
					g.setColor(Color.WHITE);
					g.drawString("Hard mode", this.WIDTH / 2 - 65, this.HEIGHT / 2 + 30);

					if (snake.option == 3) {
						g.setColor(Color.lightGray);
					} else {
						g.setColor(Color.gray);
					}

					g.fillRect(this.WIDTH / 2 - 100, this.HEIGHT / 2 + 37, 200, 29);
					g.setColor(Color.WHITE);
					g.drawString("Exit Game", this.WIDTH / 2 - 60, this.HEIGHT / 2 + 61);

					if (snake.theme == 1) {
						g.setColor(Color.gray);
					} else {
						g.setColor(Color.lightGray);
					}

					g.fillRect(this.WIDTH / 2 - 250, this.HEIGHT / 2 + 150, 220, 150);
					g.setColor(new Color(13286784));
					g.fillRect(this.WIDTH / 2 - 240, this.HEIGHT / 2 + 160, 200, 130);

					if (snake.theme == 2) {
						g.setColor(Color.gray);
					} else {
						g.setColor(Color.lightGray);
					}

					g.fillRect(this.WIDTH / 2 + 30, this.HEIGHT / 2 + 150, 220, 150);
					g.setColor(new Color(13286784));
					g.fillRect(this.WIDTH / 2 + 40, this.HEIGHT / 2 + 160, 200, 130);
					g.setColor(new Color(7783598).darker());
					g.fillRect(this.WIDTH / 2 - 230, this.HEIGHT / 2 + 220, 180, 10);

					for (int i = 0; i < 18; i++) {
						g.fillOval(this.WIDTH / 2 + 50 + 10 * i, this.HEIGHT / 2 + 220, 10, 10);
					}
				}
			}
		}
	}
}