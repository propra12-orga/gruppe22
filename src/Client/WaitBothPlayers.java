package Client;

import Game.Game;
import Game.Interface;
import Game.RepaintThread;

public class WaitBothPlayers extends Thread {

	public void run() {
		boolean wait = true;
		System.out.println("WaitBothPlayers: Gestartet.");
		while (wait == true) {
			if (ClientGameReader.gameOn == true) {
				wait = false;
				Game.main(null);
				Interface.closeMenuOpenGame();

				System.out.println("WaitBothPlayers: Beendet.");

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
