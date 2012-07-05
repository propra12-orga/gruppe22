public class WaitBothPlayers extends Thread {

	public void run() {
		boolean koks = true;
		System.out.println("WaitBothPlayers: Gestartet.");
		while (koks == true) {
			if (ClientGameReader.Stift == true) {
				koks = false;
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
