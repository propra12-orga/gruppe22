package Game;

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
