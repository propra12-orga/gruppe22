package Game;
/**
 * Carl ist unser Bombenthread.
 * @author Pierre Schwarz
 *
 */


public class Carl extends Thread {

	public Player player;
	public boolean end = false;
	public boolean act = false;
	Sound boom;
	
	public static Carl bomb0 = new Carl(Bomb.bombs[0],Init.Player1);
	public static Carl bomb1 = new Carl(Bomb.bombs[1],Init.Player1);
	public static Carl bomb2 = new Carl(Bomb.bombs[2],Init.Player1);
	public static Carl bomb3 = new Carl(Bomb.bombs[3],Init.Player2);
	public static Carl bomb4 = new Carl(Bomb.bombs[4],Init.Player2);
	public static Carl bomb5 = new Carl(Bomb.bombs[5],Init.Player2);
	
	/**
	 * Wichtig fuer geladene Spiele oder von fremden Clienten uebermittelte Informationen.
	 * Wurde die Bombe zuvor schon gesetzt, bekommt der Thread die Bombe im 2. Konstruktor 
	 * als Parameter, wodurch diese in der run()-Methode nichtmehr ueberschrieben wird.
	 */
	Boolean isSet = false;
	Bomb crtBomb, bomb;
	
	/**
	 * Carl-Kontruktor: <br>
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
	}

	public void run() {
		
		player.bCnt -= 1;
		end = false;
		bomb = crtBomb;
		setActivation(bomb);
		
		Bomb.radCheck(bomb, player);
		if (Init.KI) KI.setDanger(bomb);
		
		try {
			for (int i = 0; i < 30; i++){
				sleep(100);
				while(Interface.isPause){
					sleep(10);
				}
				if (bomb.det){
					interrupt();
					end = true;
				}
			}
		} catch (InterruptedException e) {
			interrupt();
		}
		
		if (!isInterrupted() || end){
			Bomb.detonate(bomb, player);
		
			if (Interface.isSound)
				boom = new Sound("src/Sounds/bomb.wav");
			
			// Netzwerk senden
		
		try {
			for (int i = 0; i < 10; i++){
				sleep(100);
				while(Interface.isPause)
					sleep(10);
			}
		} catch (InterruptedException e) {
			interrupt();
		}
		
		if (!isInterrupted() || end){
			Bomb.endDetonation(bomb, player);
			if(Init.KI) KI.clearDanger(bomb);
		
			// Netzwerk senden
		

		try {
			sleep(50);
		} catch (InterruptedException e) {

		}}}
		remActivation(bomb);
		inactive(bomb);
		player.bCnt += 1;
	}
	
	public void inactive(Bomb bomb){
		bomb.active=false;
	}

	public void setActivation(Bomb bomb){
		if (bomb.num == 0)
			bomb0.act = true;
		else if (bomb.num == 1)
			bomb1.act = true;
		else if (bomb.num == 2)
			bomb2.act = true;
		else if (bomb.num == 3)
			bomb3.act = true;
		else if (bomb.num == 4)
			bomb4.act = true;
		else if (bomb.num == 5)
			bomb5.act = true;
	}
	
	public void remActivation(Bomb bomb){
		if (bomb.num == 0)
			bomb0.act = false;
		else if (bomb.num == 1)
			bomb1.act = false;
		else if (bomb.num == 2)
			bomb2.act = false;
		else if (bomb.num == 3)
			bomb3.act = false;
		else if (bomb.num == 4)
			bomb4.act = false;
		else if (bomb.num == 5)
			bomb5.act = false;
	}

}
