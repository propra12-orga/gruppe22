import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//Hier wird das Feld gezeichnet...

public class Field extends JPanel {

	private static final long serialVersionUID = 1L;

	public static int[][] basicField = Init.basicField();
	public static int[][] fieldNumbers = Init.fieldContent(basicField);
	public static Field f;
	public static boolean isBomb = false;
	public static int x, y;
	public static int bombCnt = 1;
	
	private BufferedImage image;

	public Field() {
		super();
	}

	// Zeichnen:
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setSize(800, 600);

		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++) {
				g.setColor(getColor(fieldNumbers[i][j]));
				if (fieldNumbers[i][j] == 0) {
					g.drawImage(image, i * (getWidth() / 21), j
							* (getHeight() / 18), null);
				}
				else if(fieldNumbers[i][j] == 1){g.drawImage(image, i * (getWidth() / 21), j
						* (getHeight() / 18), null);}
				else if(fieldNumbers[i][j] == 2){g.drawImage(image, i * (getWidth() / 21), j
						* (getHeight() / 18), null);} 
				else if(fieldNumbers[i][j] == 3){g.drawImage(image, i * (getWidth() / 21), j
								* (getHeight() / 18), null);}
				else if(fieldNumbers[i][j] == 7){g.drawImage(image, i * (getWidth() / 21), j
						* (getHeight() / 18), null);}  else {
					g.fillRect(i * (getWidth() / 21), j * (getHeight() / 18),
							(i + 1) * (getWidth() / 21), (j + 1)
									* (getHeight() / 18));
				}
			}
		if (isBomb) {
			try {
				image = ImageIO
						.read(new File(
								"src/Pictures/Bombe.jpg"));
			} catch (IOException e) {
			}
			g.drawImage(image, x * (getWidth() / 21), y
					* (getHeight() / 18), null);
		}

	}

	// /////////////////////////////////////////////////////////////////////////////////////

	/*
	 * getColor-Methode: Das Feldarray übergibt einen Wert an diese Methode. Mit
	 * einer simplen If-Abfrage wird die entsprechende Feldfarbe ermittelt und
	 * zurückgegeben.
	 * 
	 * DETONATION DER BOMBE FEHLT NOCH!!!
	 */

	public Color getColor(int coord) {
		Color fieldcolor = Color.WHITE;

		if (coord == 0)
			try {
				image = ImageIO
						.read(new File(
								"src/Pictures/Boden.jpg"));
			} catch (IOException e) {
			}
		else if (coord == 1)
			try {
				image = ImageIO
						.read(new File(
								"src/Pictures/Stein.jpg"));
			} catch (IOException e) {
			}
		else if (coord == 2)
			try {
				image = ImageIO
						.read(new File(
								"src/Pictures/Kiste.jpg"));
			} catch (IOException e) {
			}
		else if (coord == 3)
			try {
				image = ImageIO
						.read(new File(
								"src/Pictures/BM.jpg"));
			} catch (IOException e) {
			}
		else if (coord == 4)
			fieldcolor = Color.BLACK; //Spieler2
		else if (coord == 5)
			fieldcolor = Color.BLACK; //Spieler3
		else if (coord == 6)
			fieldcolor = Color.BLACK; //Spieler4	
		else if (coord == 8)
			fieldcolor = Color.RED; //Detonation
		else if (coord == 9)
			fieldcolor = Color.BLUE; //Ausgang

		return fieldcolor;
	}

	// /////////////////////////////////////////////////////////////////////////////////////

	/*
	 * Methode zur Abfrage der Begehbarkeit eines Feldes true : Begehbar false :
	 * Nicht begehbar
	 */

	public static boolean checkMove(int coord) {
		if (coord == 0)
			return true;
		else if (coord == 9){
			Game.panel.setVisible(false);
			Interface.panel.setVisible(true);
			Interface.main(null);
		}
		return false;
	}

	// /////////////////////////////////////////////////////////////////////////////////////

	public static void movement(String s) {

		if (s == "Oben") {
			if (checkMove(fieldNumbers[Player.player_x][Player.player_y - 1])) {
				Player.goUp(Player.player_x, Player.player_y);
				Player.playerToField();
				f = new Field();
				f.newPaint();
			}
		} else if (s == "Unten") {
			if (checkMove(fieldNumbers[Player.player_x][Player.player_y + 1])) {
				Player.goDown(Player.player_x, Player.player_y);
				Player.playerToField();
				f = new Field();
				f.newPaint();
			}
		} else if (s == "Links") {
			if (checkMove(fieldNumbers[Player.player_x - 1][Player.player_y])) {
				Player.goLeft(Player.player_x, Player.player_y);
				Player.playerToField();
				f = new Field();
				f.newPaint();
			}
		} else if (s == "Rechts") {
			if (checkMove(fieldNumbers[Player.player_x + 1][Player.player_y])) {
				Player.goRight(Player.player_x, Player.player_y);
				Player.playerToField();
				f = new Field();
				f.newPaint();
			}
		} else if (s == "Bombe") {
			if (bombCnt > 0) new Carl("Bomb").start();
		} else if (s == "Esc") {
			// noch leer
		}

	}

	// /////////////////////////////////////////////////////////////////////////////////////

	public void newPaint() {
		Game.panel.repaint();
	}
	

	// /////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		// (noch?) leer
	}

}