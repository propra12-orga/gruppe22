package Game;
/**
 * Verantwortlich f�r das andauernde neu zeichnen des Spielfeldes w�hrend des Spiels.
 * 
 * @author Jan Reckfort
 * 
 */

public class RepaintThread extends Thread {

	public RepaintThread() {
		super();
	}

	public void run() {
		try {

			while (true) {
				sleep(30);
				Interface.game.repaint();
			}
		} catch (InterruptedException e) {

		}

	}

}
