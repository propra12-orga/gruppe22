/**
 * Carl ist unser Bombenthread.
 * @author Pierre Schwarz
 *
 */


public class Carl extends Thread {

	public Player player;
	Sound boom;
	
	/**
	 * Wichtig fuer geladene Spiele oder von fremden Clienten uebermittelte Informationen.
	 * Wurde die Bombe zuvor schon gesetzt, bekommt der Thread die Bombe im 2. Konstruktor 
	 * als Parameter, wodurch diese in der run()-Methode nichtmehr ueberschrieben wird.
	 */
	Boolean isSet = false;
	Bomb crtBomb, bomb;
	
	/**
	 * 1. Carl-Konstruktor: <br>
	 * Der Thread bekommt einen Spieler uebergeben, alles Weitere
	 * ist von diesem abhaengig. Hier wird die entsprechende Bombe
	 * des Threads im Thread selbst initialisiert.
	 * @param Player crtPlayer
	 */
	
	public Carl(Player crtPlayer) {
		super();
		player = crtPlayer;
		isSet = false;
	}
	
	/**
	 * 2. Carl-Kontruktor: <br>
	 * Hier bekommt der Thread sowohl einen Spieler, als auch eine Bombe uebergeben.
	 * Die Bombe wird in der run()-Methode so benutzt, wie sie hier uebergeben wird
	 * und nicht ueberschrieben/neu initialisiert.
	 * @param Bomb bomb
	 * @param Player crtPlayer
	 */
	public Carl(Bomb bomb, Player crtPlayer){
		super();
		player = crtPlayer;
		crtBomb = bomb;
		isSet = true;
	}

	public void run() {
		Interface.isPause = false;
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
