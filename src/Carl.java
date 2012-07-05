/**
 * Carl ist unser Bombenthread.
 * @author Pierre Schwarz
 *
 */


public class Carl extends Thread {

	public Player player;
	Sound boom;
	Boolean isSet = false;
	Bomb crtBomb, bomb;
	
	/**
	 * Der Thread bekommt einen Spieler uebergeben, alles Weitere
	 * ist von diesem abhaengig.
	 * @param crtPlayer
	 */
	
	public Carl(Player crtPlayer) {
		super();
		player = crtPlayer;
		isSet = false;
	}
	
	public Carl(Bomb bomb, Player crtPlayer){
		super();
		player = crtPlayer;
		crtBomb = bomb;
		isSet = true;
	}

	public void run() {

		if(!isSet){
			player.bCnt -= 1;
			bomb = new Bomb(player);
			Bomb.placeBomb(bomb, player);
		}
		else {
			bomb = crtBomb;
			player.bP += 1;
		}
		Bomb.radCheck(bomb, player);
		if (Init.KI) KI.setDanger(bomb);
		try {
			for (int i = 0; i < 30; i++){
				sleep(100);
				while(Interface.isPause){
					sleep(10);
				}
				if (bomb.det)
					interrupt();
			}
		} catch (InterruptedException e) {
			
		}
		
		Bomb.detonate(bomb, player);
		boom = new Sound("src/Sounds/bomb.wav");
			
		// Netzwerk senden
		
		
		
		try {
			sleep(1000);
		} catch (InterruptedException e) {

		}
		
		Bomb.endDetonation(bomb, player);
		if(Init.KI) KI.clearDanger(bomb);
		Field.expPos[bomb.x][bomb.y] = false;
		
		// Netzwerk senden
	

		try {
			sleep(50);
		} catch (InterruptedException e) {

		}
		player.bCnt += 1;
	}

}
