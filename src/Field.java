import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Field extends JPanel {

	private static final long serialVersionUID = 1L;

	public static int[][] basicField = Init.basicField();
	public static int[][] fieldNumbers = Init.fieldContent(basicField);

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
	 * getColor-Methode: Das Feldarray �bergibt einen Wert an diese Methode. Mit
	 * einer simplen If-Abfrage wird die entsprechende Feldfarbe ermittelt und
	 * zur�ckgegeben.
	 * 
	 * DETONATION DER BOMBE FEHLT NOCH!!!
	 */

	public Color getColor(int coord) {
		Color fieldcolor;

		if (coord == 1)
			fieldcolor = Color.GRAY; // GRAU = Unzerst�rbare Mauer
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

	public static void main(String[] args) {
		// (noch?) leer
	}

}