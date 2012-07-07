package Server;
import java.io.IOException;

public class waitClient1 implements Runnable {

	public void run() {

		boolean check = true;
		try {
			System.out.println("waitClient1: gestartet.");
			while (check == true) {
				if(Server.streamReader1.available()!=0){
					System.out.println(Server.streamReader1.available());
					}
				if (Server.streamReader1.available() >= 3261) {
				
						for (int i1 = 0; i1 <= 16; i1++) {
							for (int j1 = 0; j1 <= 20; j1++) {
								Server.fieldNumbers[j1][i1] = Server.streamReader1
									.readInt();
						}
					}
						for (int i1 = 0; i1 <= 16; i1++) {
							for (int j1 = 0; j1 <= 20; j1++) {
								Server.powerUps[j1][i1] = Server.streamReader1
									.readInt();
						}
					}
						for (int i1 = 0; i1 <= 16; i1++) {
							for (int j1 = 0; j1 <= 20; j1++) {
								Server.bombPos[j1][i1] = Server.streamReader1
									.readBoolean();
								
						}
							
					
					}
						for(int i=0;i<=23;i++){
							Server.bomb[i]=Server.streamReader1.readInt();
							if(i==23){
								check=false;
							}
						}
				}
			}
			System.out.println("waitClient1: beenden.");
			Thread t2 = new Thread(new waitClient2());
			t2.start();
			Server.SendClient2();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("waitClient1: Konnte nichts empfangen.");
		}

	} // run schließen
} // innere Klasse schließen

