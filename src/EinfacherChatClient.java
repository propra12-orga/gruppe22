import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EinfacherChatClient {

	JTextArea eingehend;
	JTextField ausgehend;
	Socket sock;

	static DataInputStream streamReader;
	static DataOutputStream streamWriter;

	public static void start() {
		EinfacherChatClient client = new EinfacherChatClient();
		client.los();
	}

	public void los() {

		netzwerkEinrichten();

		Thread readerThread = new Thread(new StartReader());
		readerThread.start();
	} // los schieﬂen

	private void netzwerkEinrichten() {

		try {
			sock = new Socket("192.168.123.107", 5001);
			streamReader = new DataInputStream(sock.getInputStream());
			streamWriter = new DataOutputStream(sock.getOutputStream());
			System.out.println("Netzwerkverbindung steht");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	} // netzwerkEinrichten schlieﬂen

	public static class Senden {

		public static void Send() {
			try {
				System.out.println("Client sendet");
				for (int i = 0; i < 17; i++) {
					for (int j = 0; j < 21; j++) {
						streamWriter.writeInt(Field.fieldNumbers[j][i]);
					}
				}
				for (int i = 0; i < 17; i++) {
					for (int j = 0; j < 21; j++) {
						streamWriter.writeInt(Init.powerUps[j][i]);
					}
				}
				for (int i = 0; i < 17; i++) {
					for (int j = 0; j < 21; j++) {
						streamWriter.writeBoolean(Field.bombPos[j][i]);
					}
				}
				for (int i = 0; i < 6; i++) {
					streamWriter.writeInt(Bomb.bombs[i].o);
					streamWriter.writeInt(Bomb.bombs[i].u);
					streamWriter.writeInt(Bomb.bombs[i].l);
					streamWriter.writeInt(Bomb.bombs[i].r);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	} // innere Klasse SendenButton schlieﬂen

	public class StartReader extends Thread {
		boolean complet = false;

		public void Losgehts() {
			//Hier Player Initieren ? o.0 

//			Interface.Boxes();
			Field.basicField = Init.basicField();
			Init.MP = true;
			Bomb.bombs = Init.bombs();
			Thread game = new Thread(new ClientGameReader());
			game.start();
			Senden.Send();
			Thread waitForReady = new Thread(new WaitBothPlayers());
			waitForReady.start();

		}

		public void run() {
			try {
				while (complet == false) {
					if (streamReader.available() != 0) {
						System.out.println(streamReader.available());
					}
					if (streamReader.available() >= 1782) {
						IOfieldNumbers();
						IObombPos();
						complet = true;
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			Losgehts();

		} // run schlieﬂen

		public void IOfieldNumbers() {
			System.out
					.println("EinfacherChatClient: Versuche fieldNumbers zu lesen.");
			Field.fieldNumbers = new int[21][17];
			for (int i = 0; i <= 16; i++) {
				for (int j = 0; j <= 20; j++) {
					try {
						Field.fieldNumbers[j][i] = streamReader.readInt();
					} catch (IOException e) {
						System.out
								.println("EinfacherChatClient: Fehler beim auslesen von fieldNumbers.");
						e.printStackTrace();
					}
				}
			}

		}

		public void IObombPos() {
			System.out
					.println("EinfacherChatClient: Versuche bombPos zu lesen.");
			Field.bombPos = new boolean[21][17];
			for (int i = 0; i <= 16; i++) {
				for (int j = 0; j <= 20; j++) {
					try {
						Field.bombPos[j][i] = streamReader.readBoolean();
					} catch (IOException e) {
						System.out
								.println("EinfacherChatClient: Fehler beim auslesen von bombPos.");
						e.printStackTrace();
					}
				}
			}
		}
	} // innere Klasse EingehendReader schlieﬂen

} // ‰uﬂere Klasse schlieﬂen
