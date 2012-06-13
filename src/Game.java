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
	 * Initialisierung des Game-Panels.
	 */
	public Game() {

		Interface.game = new Field();

		Interface.game.setFocusable(true);
		Interface.game.addKeyListener(this);

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
