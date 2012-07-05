import java.io.IOException;

public class waitClient2 implements Runnable {

	public void run() {

		boolean check = true;
		try {
			System.out.println("waitClient2: gestartet.");
			while (check == true) {
				if (Server.streamReader2.available() >= 3261) {

					for (int i1 = 0; i1 <= 16; i1++) {
						for (int j1 = 0; j1 <= 20; j1++) {
							Server.fieldNumbers[j1][i1] = Server.streamReader2
								.readInt();
					}
				}
					for (int i1 = 0; i1 <= 16; i1++) {
						for (int j1 = 0; j1 <= 20; j1++) {
							Server.powerUps[j1][i1] = Server.streamReader2
								.readInt();
					}
				}
					for (int i1 = 0; i1 <= 16; i1++) {
						for (int j1 = 0; j1 <= 20; j1++) {
							Server.bombPos[j1][i1] = Server.streamReader2
								.readBoolean();
							
					}
				}	
					for(int i=0;i<=23;i++){
						Server.bomb[i]=Server.streamReader2.readInt();
						if(i==23){
							check=false;
						}
					}
				}
			}
			System.out.println("waitClient2: beenden.");
			Thread t3 = new Thread(new gameReader());
			t3.start();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("waitClient2: Konnte nichts empfangen.");
		}

	} // run schließen
} // innere Klasse schließen