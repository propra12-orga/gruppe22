package Client;
import java.io.IOException;

import Game.Init;

/**
 * <u>ClientGameReader</u><br>
 *Thread zur dauerhaften Auslesung der Informationen die vom Server an den Client gesendet werden.
 *Nachdem ein Datenblock der fuer den Spieler verantwortlich ist ausgelesen wurde werden die Daten
 *an die Funktion Update.player weiter gegeben um diese Daten weiter zuverarbeiten.<br>
 *Es folgt das Auslesen und Weiterverarbeiten der Informationen für die Bombe.<br>
 *Nach jeden Datenpacket wird die Position des gerade ausgelesenen Spielers mit der Position der
 *Powerups verglichen und ggf. werden die Powerups aktualisiert.
 *
 *@author Jan Reckfort
 *@author Bastian Siefen
 */
public class ClientGameReader extends Thread {
	static boolean gameOn = false;
	int x;
	int y;
	int num;
	int bCnt;
	int rad;
	int bx;
	int by;
	boolean act;
	
	public void run() {

		try {
			while (true) {
				if (Client.streamReader.available() >= 47) {
					gameOn = true;
					x = Client.streamReader.readInt();
					y = Client.streamReader.readInt();
					num = Client.streamReader.readInt();
					bCnt = Client.streamReader.readInt();
					rad = Client.streamReader.readInt();
					Update.player(x, y, num, bCnt, rad);
					if(num==1){
						for (int i = 0; i < 3; i++) {
							bx = Client.streamReader.readInt();
							by = Client.streamReader.readInt();
							act = Client.streamReader.readBoolean();
							Update.bomb(bx, by, i, act);
						}
					} else if(num == 2){
						for (int i = 3; i < 6; i++) {
							bx = Client.streamReader.readInt();
							by = Client.streamReader.readInt();
							act = Client.streamReader.readBoolean();
							Update.bomb(bx, by, i, act);
						}
					}
				}
				checkPowerUp(x,y);
			}

		} catch (IOException e) {
			System.out.println("Fehler beim lesen.");
			e.printStackTrace();
		}
	} // run schließen
	
	/**
	 * <u>checkPowerUp</u><br>
	 *Vergleicht die Position des Spielers der ausgelesen wurde und aktualisiert 
	 *ggf das Init.powerUps.
	 */
	private void checkPowerUp(int x, int y){
		if (Init.powerUps[x][y]== 41 || Init.powerUps[x][y]== 42){
			Init.powerUps[x][y]=0;
		}
		
	}
} // innere Klasse EingehendReader schließen