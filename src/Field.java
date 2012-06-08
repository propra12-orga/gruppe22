import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//Hier wird das Feld gezeichnet...

public class Field extends JPanel {

	private static final long serialVersionUID = 1L;

	public static int[][] basicField;
	public static int[][] fieldNumbers;
	public static boolean[][] bombPos, expPos;
	static Bomb crtBomb;
	
	public static Field f;
	public static int bombCnt = 1;

	private BufferedImage kiste;
	private BufferedImage boden;
	private BufferedImage stein;
	private BufferedImage bomberman;
	private BufferedImage bombe;
	private BufferedImage exit;

	private BufferedImage exp_v;
	private BufferedImage exp_m;
	private BufferedImage exp_h;
	
	// Zeichnen:
	protected void paintComponent(Graphics g) {
		setSize(800, 662);

		try {
			kiste = ImageIO.read(new File("src/Pictures/Kiste.png"));
		} catch (IOException e) {
		}
		try {
			bomberman = ImageIO.read(new File("src/Pictures/player_green.png"));
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

		g.drawImage(boden,1 * (getWidth() / 21),1
				* (getHeight() / 17), null);
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++) {

				if (fieldNumbers[i][j] == 0) {
					g.drawImage(boden, i * (getWidth() / 21), j
							* (getHeight() / 17), null);

				} else if (fieldNumbers[i][j] == 1) {
					g.drawImage(stein, i * (getWidth() / 21), j
							* (getHeight() / 17), null);

				} else if (fieldNumbers[i][j] == 2) {
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

		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++)
				if (bombPos[i][j]) {

					g.drawImage(bombe, i * (getWidth() / 21), j * (getHeight() / 17),
					null);
		}
		
			for (int i = 0; i < Bomb.bombs.length; i++){
				if (Bomb.bombs[i].det) {
					for (int dr = 0; dr <= Bomb.bombs[i].r; dr++) {
						g.drawImage(exp_h, (Bomb.bombs[i].x + dr) * (getWidth() / 21), Bomb.bombs[i].y
								* (getHeight() / 17), null);
					}

					for (int dl = 0; dl <= Bomb.bombs[i].l; dl++) {
						g.drawImage(exp_h, (Bomb.bombs[i].x - dl) * (getWidth() / 21), Bomb.bombs[i].y
								* (getHeight() / 17), null);
					}

					for (int du = 0; du <= Bomb.bombs[i].u; du++) {
						g.drawImage(exp_v, Bomb.bombs[i].x * (getWidth() / 21), (Bomb.bombs[i].y + du)
								* (getHeight() / 17), null);
					}

					for (int dob = 0; dob <= Bomb.bombs[i].o; dob++) {
						g.drawImage(exp_v, Bomb.bombs[i].x * (getWidth() / 21), (Bomb.bombs[i].y - dob)
								* (getHeight() / 17), null);
					}

					g.drawImage(exp_m, Bomb.bombs[i].x * (getWidth() / 21), Bomb.bombs[i].y * (getHeight() / 17),
							null);
				}

		}
			g.drawImage(bomberman, Init.Player1.x * (getWidth() / 21),
					Init.Player1.y * (getHeight() / 17), null);

	}

	// /////////////////////////////////////////////////////////////////////////////////////


	public void newPaint() {
		Interface.game.repaint();
	}

	// /////////////////////////////////////////////////////////////////////////////////////


}