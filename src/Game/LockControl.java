package Game;
/**
 * Thread der die Steuerung für eine gewisse Zeit blockiert damit der Spieler nicht zu schnell ist.
 * @author Pierre Schwarz
 *
 */

public class LockControl extends Thread{
	
	int plNum;

	public LockControl (int i){
		super();
		plNum = i;
	}
	
	public void run(){
		if(plNum == 1)
		Interface.ctrlP1 = false;
		else Interface.ctrlP2 = false;
		
		try{
			sleep(40);
		} catch (InterruptedException e){
			
		}
		if(plNum == 1)
		Interface.ctrlP1 = true;
		else Interface.ctrlP2 = true;
	}
}
