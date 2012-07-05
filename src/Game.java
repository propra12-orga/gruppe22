import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Startklasse einer Bomberdroid-Runde.
 * 
 * @author Pierre Schwarz
 * 
 */

public class Game implements KeyListener {

	static boolean played = false;
	/**
	 * Initialisierung des Game-Panels.
	 */
	
	public Game() {

		if (Init.loaded)
			Load.content();
		
		
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
