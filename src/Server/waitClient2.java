package Server;

import java.io.IOException;

public class waitClient2 implements Runnable {
	
	/**
	 * <u>run:</u><br>
	 * Es wird eine Schleife gestartet die auf das <b>fieldNumbers</b> Feld wartet,
	 *  dass vom Klienten 2 gesendet wird. Wenn diese beendet ist wird der
	 *  <b>gameReader</b> Thread gestartet.
	 */
	public void run() {

		boolean check = true;
		try {
			System.out.println("waitClient2: gestartet.");
			while (check == true) {
				if (Server.streamReader2.available() == 1475) {

					for (int i = 0; i < 17; i++) {
						for (int j = 0; j <21; j++) {
							Server.fieldNumbers[j][i] = Server.streamReader2
								.readInt();
						}
					}
					for(int i=0;i<5;i++){
						Server.playerInfo[i]=Server.streamReader2.readInt();	
					}
					for(int i = 0;i<6;i++){
						Server.bombInfoInt[i]=Server.streamReader2.readInt();
					}
					for(int i=0;i<3;i++){
						Server.bombInfoBoolean[i]=Server.streamReader2.readBoolean();
					}
					check = false;
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