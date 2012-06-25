/**
 * 
 * Thread fuer die KI, greift lediglich alle 750ms auf
 * die Basismethode checkEnv aus der KI-Klasse zu.
 * 
 * @author Pierre Schwarz
 *
 */

public class Paul extends Thread {
	
	public Paul(){
		super();
	}
	
	public void run(){
		while(true){
			KI.checkEnv();
			
			try{
				sleep(750);
			}
			catch(InterruptedException e){
				
			}
		}
	}
}
