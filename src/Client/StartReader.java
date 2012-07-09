package Client;

import java.io.IOException;
import Game.Bomb;
import Game.Field;
import Game.Init;
import Game.Interface;

/**
 * Thread der auf die ersten Daten des Servers wartet.
 * 
 * @author Jan Reckfort
 * @author Bastian Siefen
 */
public class StartReader extends Thread {
	boolean complete = false;

	/**
	 * <u>run:</u><br>
	 * Es wird eine Schleife gestartet die darauf wartet, dass vom Server das
	 * Packet ankommt welches <b/>fieldNumbers</b> und <b>powerUps</b>
	 * beinhaltet. Ist dieses Packet komplett wird es ausgelesen.<br>
	 * Ist das Auslesen beendet wird die Methode <b>DoIt</b> aufgerufen.
	 */

	public void run() {
		try {
			while (complete == false) {
				if (Client.streamReader.available() == 2856) {
					iOfields();
					complete = true;
				}
			}
		} catch (IOException e) {
			System.out.println("Fehler");
			e.printStackTrace();
		}
		doIt();
	} // run schließen

	/**
	 * <u>DoIt:</u><br>
	 * Klient ueberprüft ob ein Spieler an der Startposition gesetzt ist, falls
	 * nicht, setzt er den ersten Spieler.<br>
	 * Falls der erste Spieler bereits gesetzt wurde , setzt der Klient den
	 * zweiten Spieler. <br>
	 * Dies passiert ueber die Methode <b>Init.InitPlayer1</b> und
	 * <b>Init.Initplayer2</b><br>
	 * Falls der zweite Spieler gesetzt wird von diesem Klienten noch der
	 * boolean <b>Init.MP</b> auf <i>true</> gesetzt genau so wie auch der
	 * boolean <b>Interface.ctrlP2</b>. Diese booleans aktivieren den
	 * <i>Multiplayer</i> und die Steuerung fuer den jeweiligen Spieler.
	 */
	public void doIt() {
		if (Field.fieldNumbers[1][1] == 3) {
			Interface.ctrlP2 = true;
			System.out.println("Spieler 2 wird gesetzt.");
		} else {
			Interface.ctrlP1 = true;
			Init.InitPlayer1(Field.fieldNumbers);
			System.out.println("Spieler 1 wird gesetzt.");
		}
		Init.InitPlayer2(Field.fieldNumbers);
		Field.basicField = Init.basicField();
		Init.MP = true;
		Bomb.bombs = Init.bombs();
		Field.bombPos = Init.bombPos();
		Thread game = new Thread(new ClientGameReader());
		game.start();
		SendFirstTime();
		Thread waitForReady = new Thread(new WaitBothPlayers());
		waitForReady.start();

	}

	/**
	 * <u>SendfieldNumbers:</u><br>
	 * Durchlaeuft eine Schleife, die <b>Field.fieldNumbers</b> durchgeht und
	 * jedes mal den jeweiligen Wert verschickt. Ebenfalls werden auch die entsprechenden
	 * Bomben Informationen gesendet.
	 */
	public void SendFirstTime() {
		try {
			for (int i = 0; i < 17; i++) {
				for (int j = 0; j < 21; j++) {
					Client.streamWriter.writeInt(Field.fieldNumbers[j][i]);
				}
			}
			System.out.println("StartReader: fieldNumbers an Server gesendet.");
			if (Interface.ctrlP1) {
				Client.streamWriter.writeInt(Init.Player1.x);
				Client.streamWriter.writeInt(Init.Player1.y);
				Client.streamWriter.writeInt(Init.Player1.num);
				Client.streamWriter.writeInt(Init.Player1.bCnt);
				Client.streamWriter.writeInt(Init.Player1.rad);
			} else {
				Client.streamWriter.writeInt(Init.Player2.x);
				Client.streamWriter.writeInt(Init.Player2.y);
				Client.streamWriter.writeInt(Init.Player2.num);
				Client.streamWriter.writeInt(Init.Player2.bCnt);
				Client.streamWriter.writeInt(Init.Player2.rad);
			}
			if (Interface.ctrlP1){
				for (int i = 0; i < 3; i++) {
					Client.streamWriter.writeInt(Bomb.bombs[i].x);
					Client.streamWriter.writeInt(Bomb.bombs[i].y);
				}
			} else {
				for (int i = 3; i < 6; i++) {
					Client.streamWriter.writeInt(Bomb.bombs[i].x);
					Client.streamWriter.writeInt(Bomb.bombs[i].y);
				}
			}
				
			if (Interface.ctrlP1){
				for (int i = 0; i < 3; i++) {
					Client.streamWriter.writeBoolean(Bomb.bombs[i].active);
				}
			} else {
				for (int i = 3; i < 6; i++) {
					Client.streamWriter.writeBoolean(Bomb.bombs[i].active);
				}
			}


		} catch (Exception ex) {
			System.out.println("StartReader: Probleme beim senden.");
			ex.printStackTrace();
		}
	}

	/**
	 * <u>IOfields:</u><br>
	 * Erzeugt ein neues zweidimensionales Array <b>Field.fieldNumbers</b><br>
	 * Durchlaeuft dieses dann in einer geschachtelten Schleife und liest bei
	 * jedem Schritt den jeweiligen Wert fuer <b>Field.fieldNumbers</b> und
	 * <b>Init.powerUps</b> aus und speichert ihn im jeweiligen Array.
	 */
	public void iOfields() {
		System.out
				.println("EinfacherChatClient: Versuche fieldNumbers zu lesen.");
		Field.fieldNumbers = new int[21][17];
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 21; j++) {
				try {
					Field.fieldNumbers[j][i] = Client.streamReader.readInt();
					Init.powerUps[j][i] = Client.streamReader.readInt();
				} catch (IOException e) {
					System.out
							.println("EinfacherChatClient: Fehler beim auslesen von"
									+ " fieldNumbers und powerUps.");
					e.printStackTrace();
				}
			}
		}
		for (int j = 0; j < 17; j++){
			System.out.println();
			for (int i = 0; i < 21; i++)
				System.out.print(Init.powerUps[i][j]);
			}
			System.out.println();
		
		
	} // IOfieldNumbers schließen
} // Klasse schließen

