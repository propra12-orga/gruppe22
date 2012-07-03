/**
 * 
 * Thread fuer die KI, greift lediglich alle 750ms auf
 * die Basismethode checkEnv aus der KI-Klasse zu.
 * 
 * @author Pierre Schwarz
 *
 */

public class Paul extends Thread {
	
	static Paul kiThread;
	
	public Paul(){
		super();
		kiThread = this;
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
	
	public void resumeKI(){
		synchronized(this){
			this.notify();
			this.interrupt();
		}
	}
}
