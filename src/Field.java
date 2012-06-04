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

	private BufferedImage hintergrund;
	private BufferedImage kiste;
	private BufferedImage bomberman;
	private BufferedImage bombe;

	private BufferedImage exp_v;
	private BufferedImage exp_m;
	private BufferedImage exp_h;

	public Field(Bomb bomb){
		crtBomb = bomb;
	}
	
	// Zeichnen:
	protected void paintComponent(Graphics g) {
		setSize(800, 662);

		try {
			hintergrund = ImageIO
					.read(new File("src/Pictures/Hintergrund.jpg"));
		} catch (IOException e) {
		}
		try {
			kiste = ImageIO.read(new File("src/Pictures/Kiste.jpg"));
		} catch (IOException e) {
		}
		try {
			bomberman = ImageIO.read(new File("src/Pictures/BM.png"));
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

		g.drawImage(hintergrund, 0, 0, null);

		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++) {

				if (fieldNumbers[i][j] == 2) {
					g.drawImage(kiste, i * (getWidth() / 21), j
							* (getHeight() / 17), null);

				} else if (fieldNumbers[i][j] == 3) {
					g.drawImage(bomberman, i * (getWidth() / 21), j
							* (getHeight() / 17), null);
				}
				else if (fieldNumbers[i][j] == 4) {
					g.drawImage(bomberman, i * (getWidth() / 21), j
							* (getHeight() / 17), null);
				}

			}

		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++)
				if (bombPos[i][j]) {

					g.drawImage(bombe, i * (getWidth() / 21), j * (getHeight() / 17),
					null);
		}

			if (crtBomb != null){
				if (expPos[crtBomb.x][crtBomb.y]) {
					for (int dr = 0; dr <= crtBomb.r; dr++) {
						g.drawImage(exp_h, (crtBomb.x + dr) * (getWidth() / 21), crtBomb.y
								* (getHeight() / 17), null);
					}

					for (int dl = 0; dl <= crtBomb.l; dl++) {
						g.drawImage(exp_h, (crtBomb.x - dl) * (getWidth() / 21), crtBomb.y
								* (getHeight() / 17), null);
					}

					for (int du = 0; du <= crtBomb.u; du++) {
						g.drawImage(exp_v, crtBomb.x * (getWidth() / 21), (crtBomb.y + du)
								* (getHeight() / 17), null);
					}

					for (int dob = 0; dob <= crtBomb.o; dob++) {
						g.drawImage(exp_v, crtBomb.x * (getWidth() / 21), (crtBomb.y - dob)
								* (getHeight() / 17), null);
					}

					g.drawImage(exp_m, crtBomb.x * (getWidth() / 21), crtBomb.y * (getHeight() / 17),
							null);
				}

		}

	}

	// /////////////////////////////////////////////////////////////////////////////////////

	/*
	 * Methode zur Abfrage der Begehbarkeit eines Feldes true : Begehbar false :
	 * Nicht begehbar
	 */

	public static boolean checkMove(int coord) {
		if (coord == 0)
			return true;
		else if (coord == 9) {
			Interface.closeGameOpenMenu();
			IngameMenu.ingame();
		}
		return false;
	}

	// /////////////////////////////////////////////////////////////////////////////////////
	
	
	// /////////////////////////////////////////////////////////////////////////////////////

	public void newPaint() {
		Interface.game.repaint();
	}

	// /////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		// (noch?) leer
	}

}