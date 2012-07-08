
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static int[][] basicField;
	public static int[][] fieldNumbers;
	public static int[] playerInfo= new int[6];
	public static int [] bombInfoInt = new int[12];
	public static boolean [] bombInfoBoolean = new boolean[6];
	static int[][] powerUps = new int[21][17];
	static boolean[][] bombPos;
	public static DataInputStream streamReader1;
	public static DataInputStream streamReader2;	
	public static DataOutputStream streamWriter1;
	public static DataOutputStream streamWriter2;
	boolean max = true;
	int maxx = 0;
	static Socket client1;
	static Socket client2;

	/**
	 * <u>Main-Methode des Servers:</u><br>
	 * Initialisiert das <b>basicField</b> um auf dieser Basis <b>fieldNumbers</b>
	 * zu initaliesieren. Nach der Initialisierung von fieldNumbers
	 * wird das <b>powerUps</b> Feld gefüllt.<br>
	 * Danach wird das feld <b>bombPos</b> Initialisiert.
	 * Es folg der Aufruf der Methode <b>Go()</b>.
	 * 
	 * @param args
	 * 
	 */
	
	public static void main(String[] args) {
		basicField = ServerFieldInit.basicField();
		fieldNumbers = ServerFieldInit.fieldContent(basicField);
		bombPos=ServerFieldInit.bombPos(bombPos);
		System.out.println("Server: Feld wurde erstellt");
		new Server().Go();
	}
	
	/**
	 * <u>go():</u><br>
	 * Der Port des Servers ist 5001.<br>
	 * Es wird in einer Schleife gewartet bis zwei Klienten
	 * sich mit dem Server verbunden haben.<br> verbindet sich ein 
	 * Klient mit dem Server wird sein Socket gespeichert.<br>
	 * Ist dies der Fall werden die Methoden <b>IOStreams1</b> 
	 * und <b>IOStreams2</b> aufgerufen welche die Streams fuer
	 *  die Klienten oeffnen.<br>
	 * Daraufhin wird der Thread <b>waitClient1</b> gestartet.<br>
	 * Es wird die Methode <b>SendClient1()</b> aufgerufen.
	 * 
	 * 
	 */

	public void Go() {

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
				if (maxx == 2) {
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
	
	/**
	 * <u>IOStreams1:</u><br>
	 * Mit dem übergebenen Socket wird der Inputstream (<b>streamReader1</b>) geöffnet.<br>
	 *  Mit dem übergebenen Socket wird der Outputstream (<b>streamWriter1</b>) geöffnet.<br>
	 * @param clientSocket
	 */

	public void IOStreams1(Socket clientSocket) {
		try {
			System.out.println("Server: Stream fuer Client 1 wird erstellt.");
			streamReader1 = new DataInputStream(clientSocket.getInputStream());
			streamWriter1 = new DataOutputStream(clientSocket.getOutputStream());

		} catch (Exception ex) {
			System.out
					.println("Server: Es gab Probleme die Streams für CLient1 zu öffnen.");
			ex.printStackTrace();
		}

	}
	
	/**
	 * <u>IOStreams1:</u><br>
	 * Mit dem übergebenen Socket wird der Inputstream (<b>streamReader2</b>) geöffnet.<br>
	 *  Mit dem übergebenen Socket wird der Outputstream (<b>streamWriter2</b>) geöffnet.<br>
	 * @param clientSocket
	 */
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
					streamWriter1.writeInt(powerUps[j][i]);
				}
			}
			System.out.println("Server: fieldNumbers und powerUps an Client 1 gesendet.");
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
					streamWriter2.writeInt(powerUps[j][i]);
				}
			}
			System.out.println("Server: fieldNumbers und powerUps an Client 2 gesendet.");
		} catch (Exception ex) {
			System.out.println("Server: Probleme in SendClient2.");
			ex.printStackTrace();
		}
	}

	public static void SendToAll() {

		try {
			System.out
					.println("Server: Versuche es weiter zusagen an Client1&2.");
			
			for(int i=0;i<6;i++){
				streamWriter1.writeInt(playerInfo[i]);
				streamWriter2.writeInt(playerInfo[i]);
			}
			for(int i=0;i<6;i++){
				streamWriter1.writeInt(bombInfoInt[(i*2)]);
				streamWriter2.writeInt(bombInfoInt[(i*2)]);
				streamWriter1.writeInt(bombInfoInt[(i*2+1)]);
				streamWriter2.writeInt(bombInfoInt[(i*2+1)]);
				streamWriter1.writeBoolean(bombInfoBoolean[i]);
				streamWriter2.writeBoolean(bombInfoBoolean[i]);
			}

			
			
			
			
			
//			for (int i = 0; i < 17; i++) {
//				for (int j = 0; j < 21; j++) {
//					streamWriter1.writeInt(fieldNumbers[j][i]);
//					streamWriter2.writeInt(fieldNumbers[j][i]);
//				}
//			}
//			
//			for (int i = 0; i < 17; i++){
//				for (int j = 0; j < 21; j++){
//					streamWriter1.writeInt(powerUps[j][i]);
//					streamWriter2.writeInt(powerUps[j][i]);
//				}
//			}
//				
//			for (int i = 0; i < 17; i++) {
//				for (int j = 0; j < 21; j++) {
//					streamWriter1.writeBoolean(bombPos[j][i]);
//					streamWriter2.writeBoolean(bombPos[j][i]);
//				}
//			}

		} catch (Exception ex) {
			System.out
					.println("Server: Versuche es weiter zusagen geht nicht.");
			ex.printStackTrace();
		}

	} // esAllenWeitersagen schließen

} // Klasse schließen
