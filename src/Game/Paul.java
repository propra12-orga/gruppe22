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
		KI.kiPl = Init.Player2;
	}
	
	public void run(){
		while(true){
			synchronized(this){
				try{
				
					if (!KI.esc)
						KI.checkEnv();
					for (int i = 0; i < 10; i++){
						sleep(75);
						if(Interface.isPause)
							wait();
					}
				} catch(InterruptedException e){
					if (isInterrupted())
						break;
				}
			}
		}
	}
}
