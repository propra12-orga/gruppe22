package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static int[][] basicField;
	public static int[][] fieldNumbers;
	static int[][] powerUps = new int[21][17];
	static boolean[][] bombPos;
	static int[] bomb = new int[24];
	 public static DataInputStream streamReader1;
	 public static DataInputStream streamReader2;
	 public static DataOutputStream streamWriter1;
	 public static DataOutputStream streamWriter2;

	public static void main(String[] args) {
		basicField = ServerFieldInit.basicField();
		bomb=ServerFieldInit.Bombrad(bomb);
		
	
	//Nachfolgender Befehl verändert basicField. basicFiel=fieldNumbers
		fieldNumbers = ServerFieldInit.fieldContent(basicField);
		bombPos=ServerFieldInit.bombPos(bombPos);
		System.out.println("Server: Feld wurde erstellt");
		new Server().los();
	}

	boolean max = true;
	int maxx = 0;
	static Socket client1;
	static Socket client2;

	public void los() {

		try {
			ServerSocket serverSock = new ServerSocket(5001);

			while (max == true) {
				Socket clientSocket = serverSock.accept();

				maxx++;

				if (maxx == 1) {
					client1 = clientSocket;
					System.out.println("Server: Habe 1 Clienten.");
				} else {
					client2 = clientSocket;
					System.out.println("Server: Habe 2 Clienten.");
				}
				if (maxx >= 2) {
					max = false;

					

					IOStreams1(client1);
					IOStreams2(client2);

					Thread t1 = new Thread(new waitClient1());
					t1.start();
					SendClient1();

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void IOStreams1(Socket clientSocket) {
		try {
			System.out.println("Server: Stream fuer Client 1 wird erstellt.");
			Socket sock = clientSocket;
			streamReader1 = new DataInputStream(sock.getInputStream());
			streamWriter1 = new DataOutputStream(sock.getOutputStream());

		} catch (Exception ex) {
			System.out
					.println("Server: Es gab Probleme die Streams für CLient1 zu öffnen.");
			ex.printStackTrace();
		}

	}

	public void IOStreams2(Socket clientSocket) {

		try {
			System.out.println("Server: Stream fuer Client 2 wird erstellt.");
			Socket sock = clientSocket;
			streamReader2 = new DataInputStream(sock.getInputStream());
			streamWriter2 = new DataOutputStream(sock.getOutputStream());

		} catch (Exception ex) {
			System.out
					.println("Server: Es gab Probleme die Streams für CLient2 zu öffnen.");
			ex.printStackTrace();
		}

	}

	public void SendClient1() {

	
		try {
			for (int i = 0; i < 17; i++) {
				for (int j = 0; j < 21; j++) {
					streamWriter1.writeInt(fieldNumbers[j][i]);
				}
			}
			System.out.println("Server: fieldNumbers an Client 1 gesendet.");
		} catch (Exception ex) {
			System.out.println("Server: Probleme in SendClient1.");
			ex.printStackTrace();
		}
		
		try {
			for (int i = 0; i < 17; i++) {
				for (int j = 0; j < 21; j++) {
					streamWriter1.writeBoolean(bombPos[j][i]);
				}
			}
			System.out.println("Server: bombPos an Client 1 gesendet.");
		} catch (Exception ex) {
			System.out.println("Server: Probleme in SendClient1.");
			ex.printStackTrace();
		}
	}

	public static void SendClient2() {
		
		try {
			for (int i = 0; i < 17; i++) {
				for (int j = 0; j < 21; j++) {
					streamWriter2.writeInt(fieldNumbers[j][i]);
				}
			}
			System.out.println("Server: fieldNumbers an Client 2 gesendet.");
		} catch (Exception ex) {
			System.out.println("Server: Probleme in SendClient2.");
			ex.printStackTrace();
		}
		
		try {
			for (int i = 0; i < 17; i++) {
				for (int j = 0; j < 21; j++) {
					streamWriter2.writeBoolean(bombPos[j][i]);
				}
			}
			System.out.println("Server: bombPos an Client 2 gesendet.");
		} catch (Exception ex) {
			System.out.println("Server: Probleme in SendClient2.");
			ex.printStackTrace();
		}
	}

	public static void esAllenWeitersagen() {

		try {
			System.out
					.println("Server: Versuche es weiter zusagen an Client1&2.");
			for (int i = 0; i < 17; i++) {
				for (int j = 0; j < 21; j++) {
					streamWriter1.writeInt(fieldNumbers[j][i]);
					streamWriter2.writeInt(fieldNumbers[j][i]);
				}
			}
			
			for (int i = 0; i < 17; i++){
				for (int j = 0; j < 21; j++){
					streamWriter1.writeInt(powerUps[j][i]);
					streamWriter2.writeInt(powerUps[j][i]);
				}
			}
				
			for (int i = 0; i < 17; i++) {
				for (int j = 0; j < 21; j++) {
					streamWriter1.writeBoolean(bombPos[j][i]);
					streamWriter2.writeBoolean(bombPos[j][i]);
				}
			}
			for (int i=0;i<=23;i++){
				streamWriter1.writeInt(bomb[i]);
				streamWriter2.writeInt(bomb[i]);
			}

		} catch (Exception ex) {
			System.out
					.println("Server: Versuche es weiter zusagen geht nicht.");
			ex.printStackTrace();
		}

	} // esAllenWeitersagen schließen

} // Klasse schließen
