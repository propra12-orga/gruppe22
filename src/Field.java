import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//Hier wird das Feld gezeichnet...

public class Field extends JPanel {

	public static int[][] basicField;
	public static int[][] fieldNumbers;
	public static boolean[][] bombPos, expPos;
	// static Bomb crtBomb;

	public static Field f;
	// public static int bombCnt = 1;

	private static BufferedImage kiste;
	private static BufferedImage boden;
	private static BufferedImage stein;
	private static BufferedImage bomberman;
	private static BufferedImage bombe;
	private static BufferedImage exit;

	private static BufferedImage exp_v;
	private static BufferedImage exp_m;
	private static BufferedImage exp_h;

	// Zeichnen:
	/**
	 * Laden der Bilder und Zeichnen der Bilder im Spiel.
	 */
	protected void paintComponent(Graphics g) {
		setSize(800, 662);

		PaintGround(g);
		PaintContent(g);
		PaintBombs(g);
		PaintExplosion(g);
		PaintBD(g);

	}

	// /////////////////////////////////////////////////////////////////////////////////////

	public void PaintBombs(Graphics g) {

		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 17; j++) {
				if (bombPos[i][j]) {

					g.drawImage(bombe, i * (getWidth() / 21), j
							* (getHeight() / 17), null);
				}
			}
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////////

	public void PaintExplosion(Graphics g) {

		for (int i = 0; i < Bomb.bombs.length; i++) {
			if (Bomb.bombs[i].det) {
				for (int dr = 0; dr <= Bomb.bombs[i].r; dr++) {
					g.drawImage(exp_h, (Bomb.bombs[i].x + dr)
							* (getWidth() / 21), Bomb.bombs[i].y
							* (getHeight() / 17), null);
				}

				for (int dl = 0; dl <= Bomb.bombs[i].l; dl++) {
					g.drawImage(exp_h, (Bomb.bombs[i].x - dl)
							* (getWidth() / 21), Bomb.bombs[i].y
							* (getHeight() / 17), null);
				}

				for (int du = 0; du <= Bomb.bombs[i].u; du++) {
					g.drawImage(exp_v, Bomb.bombs[i].x * (getWidth() / 21),
							(Bomb.bombs[i].y + du) * (getHeight() / 17), null);
				}

				for (int dob = 0; dob <= Bomb.bombs[i].o; dob++) {
					g.drawImage(exp_v, Bomb.bombs[i].x * (getWidth() / 21),
							(Bomb.bombs[i].y - dob) * (getHeight() / 17), null);
				}

				g.drawImage(exp_m, Bomb.bombs[i].x * (getWidth() / 21),
						Bomb.bombs[i].y * (getHeight() / 17), null);
			}
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////////

	public void PaintContent(Graphics g) {

		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 17; j++) {
				if (basicField[i][j] == 0) {
					g.drawImage(boden, i * (getWidth() / 21), j
							* (getHeight() / 17), null);

				} else if (basicField[i][j] == 1) {
					g.drawImage(stein, i * (getWidth() / 21), j
							* (getHeight() / 17), null);

				}

			}
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////////

	public void PaintGround(Graphics g) {

		for (int i = 0; i < 21; i++) {
			for (int j = 0; j < 17; j++) {
				if (fieldNumbers[i][j] == 2) {
					g.drawImage(kiste, i * (getWidth() / 21), j
							* (getHeight() / 17), null);

				} else if (fieldNumbers[i][j] == 4) {
					g.drawImage(bomberman, i * (getWidth() / 21), j
							* (getHeight() / 17), null);

				} else if (fieldNumbers[i][j] == 9) {
					g.drawImage(exit, i * (getWidth() / 21), j
							* (getHeight() / 17), null);
				}

			}
		}
	}

	// /////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Methode zum neumalen des Inhaltes des Spielfeldes
	 */

	public void newPaint() {
		Interface.game.repaint();
	}

	// /////////////////////////////////////////////////////////////////////////////////////

	public void PaintBD(Graphics g) {
		g.drawImage(bomberman, Init.Player1.x * (getWidth() / 21),
				Init.Player1.y * (getHeight() / 17), null);
	}

	// /////////////////////////////////////////////////////////////////////////////////////

	protected static void LoadImg() {
		try {
			kiste = ImageIO.read(new File("src/Pictures/Kiste.png"));
		} catch (IOException e) {
		}
		try {
			bomberman = ImageIO.read(new File(
					"src/Pictures/silver_skin_front.png"));
		} catch (IOException e) {
		}
		try {
			bombe = ImageIO.read(new File("src/Pictures/Bombe.png"));
		} catch (IOException e) {
		}

		try {
			exp_h = ImageIO.read(new File("src/Pictures/exp_h.jpg"));
		} catch (IOException e) {
		}
		try {
			exp_v = ImageIO.read(new File("src/Pictures/exp_v.jpg"));
		} catch (IOException e) {
		}
		try {
			exp_m = ImageIO.read(new File("src/Pictures/exp_m.jpg"));
		} catch (IOException e) {
		}
		try {
			boden = ImageIO.read(new File("src/Pictures/boden.png"));
		} catch (IOException e) {
		}
		try {
			stein = ImageIO.read(new File("src/Pictures/stein.png"));
		} catch (IOException e) {
		}
		try {
			exit = ImageIO.read(new File("src/Pictures/ausgang.png"));
		} catch (IOException e) {
		}

	}

}