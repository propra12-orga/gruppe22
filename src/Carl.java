/**
 * Carl ist unser Bombenthread.
 * @author Pierre Schwarz
 *
 */


public class Carl extends Thread {

	public Player player;
	
	/**
	 * Der Thread bekommt einen Spieler uebergeben, alles Weitere
	 * ist von diesem abhaengig.
	 * @param crtPlayer
	 */
	
	public Carl(Player crtPlayer) {
		super();
		player = crtPlayer;
	}

	public void run() {

		player.bCnt -= 1;
		Bomb bomb = new Bomb(player);
		Bomb.placeBomb(bomb, player);
		Bomb.radCheck(bomb, player);
		try {
			for (int i = 0; i < 30; i++){
				sleep(100);
				if (bomb.det)
					interrupt();
			}
		} catch (InterruptedException e) {
			
		}
		
		Bomb.detonate(bomb, player);
			
//		Netzwerk senden		
		
		try {
			sleep(1000);
		} catch (InterruptedException e) {

		}
		
		Bomb.endDetonation(bomb, player);
		Field.expPos[bomb.x][bomb.y] = false;
		
//		Netzwerk senden	

		try {
			sleep(50);
		} catch (InterruptedException e) {

		}
		player.bCnt += 1;
	}

}
