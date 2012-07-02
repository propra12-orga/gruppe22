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
			
			try{
				if (!KI.esc)
					KI.checkEnv();
				sleep(750);
			}
			catch(InterruptedException e){
				break;
			}
		}
	}
}
