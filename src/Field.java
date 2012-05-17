import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

//Hier wird das Feld gezeichnet...

public class Field extends JPanel {

	private static final long serialVersionUID = 1L;

	public static int[][] basicField = Init.basicField();
	public static int[][] fieldNumbers = Init.fieldContent(basicField);
	public static Field f;

	public Field() {
		super();
	}

	// Zeichnen:
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setSize(640, 480);

		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++) {
				g.setColor(getColor(fieldNumbers[i][j]));
				g.fillRect(i * (getWidth() / 21), j * (getHeight() / 18),
						(i + 1) * (getWidth() / 21), (j + 1)
								* (getHeight() / 18));
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
		Color fieldcolor;

		if (coord == 1)
			fieldcolor = Color.GRAY; // GRAU = Unzerstörbare Mauer
		else if (coord == 2)
			fieldcolor = Color.YELLOW; // GELB = Kiste
		else if (coord == 3)
			fieldcolor = Color.BLACK; // SCHWARZ = Spieler
		else if (coord == 4)
			fieldcolor = Color.RED; // ROT = Bombe
		// else if (coord == 5) fieldcolor = ....; //??? = Detonation
		else if (coord == 6)
			fieldcolor = Color.WHITE; // WEISS = Ausgang
		else
			fieldcolor = Color.WHITE; // WEISS = Frei begehbares Feld

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
		else
			return false;
	}

	// /////////////////////////////////////////////////////////////////////////////////////

	public static void movement(String s) {

		if (s == "Oben") {
			if (checkMove(fieldNumbers[Player.player_x][Player.player_y - 1])) {
				Player.goUp(Player.player_x, Player.player_y);
				System.out.println();
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
			// noch leer
		} else if (s == "Esc") {
			// noch leer
		}
		
		System.out.println(s);
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