import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Startklasse einer Bomberdroid-Runde.
 * 
 * @author Pierre Schwarz
 * 
 */

public class Game implements KeyListener {

	/**
	 * Gibt an, ob seit dem Start von BomberDroid ein Spiel gespielt wurde. <br>
	 * Falls nein, wird beim Klicken auf "Zurueck zum Hauptmenue" das Spielfeld auch nicht zurueckgesetzt.
	 */
	static boolean played = false;
	
	/**
	 * Initialisierung des Game-Panels.
	 */
	
	public Game() {
		
		Interface.game = new Field();
		if (Init.KI)
			new Paul().start();
		
		new RepaintThread().start();
		
		Interface.game.setFocusable(true);
		Interface.game.addKeyListener(this);

		played = true;
		Interface.frame.add(Interface.game);
		
	}

	public static void main(String[] args) {

		Game fenster = new Game();
	}

	public void keyPressed(KeyEvent e) {
		String Key = Eingabe.Ctrl(e);
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}
}
