package Client;

import Game.Game;
import Game.Interface;
import Game.RepaintThread;
/**
 * <u>WaitBothPlayers</u><br>
 * Startet die Spielmechanismen erst wenn best�tigt ist das alle Daten die ben�tigt werden vorhanden sind.
 * 
 * @author Jan Reckfort
 * @author Bastian Siefen
 */
public class WaitBothPlayers extends Thread {

	public void run() {
		boolean wait = true;
		while (wait == true) {
			if (ClientGameReader.gameOn == true) {
				wait = false;
				Game.main(null);
				Interface.closeMenuOpenGame();

				new RepaintThread().start();

			}
			try {
				sleep(100);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

}
