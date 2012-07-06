/**
 * 
 * Thread fuer die KI.
 * 
 * @author Pierre Schwarz
 *
 */

public class Paul extends Thread {
	
	/**
	 * Der Thread fuer die KI.
	 */
	static Paul kiThread;
	
	/**
	 * Konstruktor: <br>
	 * Initialisiert den kiThread und ueberschreibt das KI.kiPl-Objekt durch das Init.Player2-Objekt.
	 * Der Thread greift dann, sofern die KI sich nicht grade auf der Flucht befindet,
	 * alle 750ms auf die Basis-Methode der KI zu (KI.checkEnv()).
	 */
	public Paul(){
		super();
		kiThread = this;
		KI.kiPl = Init.Player2;
	}
	
	public void run(){
		while(true){
			
			try{		
				if (!KI.esc)
					KI.checkEnv();
				sleep(750);
			}
			catch(InterruptedException e){
				if (Interface.isPause)
					synchronized(this){
						try{
							this.wait();
						}
						catch (InterruptedException f){
							
						}
					}
				break;
			}
		}
	}
	
	/**
	 * Nach Pause: <br>
	 * Startet die KI wieder.
	 */
	public void resumeKI(){
		synchronized(this){
			this.notify();
			this.interrupt();
		}
	}
}
