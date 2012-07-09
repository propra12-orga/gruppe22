package Game;
/**
 * 
 * Thread-Klasse fuer die KI.
 * 
 * @author Pierre Schwarz
 *
 */

public class Paul extends Thread {
	
	/**
	 * Der Thread fuer die KI.
	 */
	static Paul kiThread = new Paul();
	
	/**
	 * Konstruktor: <br>
	 * Initialisiert den kiThread und ueberschreibt das KI.kiPl-Objekt durch das Init.Player2-Objekt.
	 * Der Thread greift dann, sofern die KI sich nicht grade auf der Flucht befindet,
	 * alle 750ms auf die Basis-Methode der KI zu (KI.checkEnv()).
	 */
	public Paul(){
		super();
		kiThread = this;
		if (!Load.chosen)
			Player.initKI(Init.Player2);
	}
	
	public void run(){
		boolean kiActive = true;
		while(kiActive){
			synchronized(this){
				try{
					if (Bomb.gameOver)
						break;
					
					if (KI.esc) wait();
					
					if(Init.Player2.rad <= Init.Player1.rad)
						Init.Player2.checkRad = Init.Player1.rad;
					else Init.Player2.checkRad = Init.Player2.rad;
					
					if(!Bomb.gameOver)
						KI.setDet();
					if(!Bomb.gameOver)
						KI.checkEnemy();
					if(!Bomb.gameOver)
						KI.checkBoxes();
					
					if (!Init.Player2.danger && !Bomb.gameOver)
						KI.chooseDir();
					
					for (int i = 0; i < 10; i++){
						sleep(75);
						if (Bomb.gameOver)
							kiActive = false;
						if(Interface.isPause)
							wait();
					}
				} catch(InterruptedException e){
					kiActive = false;
				}
			}
		}
	}
	
	public static void resetKI(){
		KI.danger = KI.initDangerArray();
		KI.cnt = 0;
		KI.l = false; 
		KI.r = false;
		KI.o = false;
		KI.u = false;
		KI.noBomb = false;
		KI.esc = false;
		KI.hasMoved = false;
	}
}
