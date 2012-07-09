package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Client.Client;
/** 
 * @author Jan Reckfort
 * @author Bastian Siefen
 *
 */
public class Server {

	public static int[][] basicField;
	public static int[][] fieldNumbers;
	public static int[] playerInfo= new int[5];
	public static int [] bombInfoInt = new int[6];
	public static boolean [] bombInfoBoolean = new boolean[3];
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
		getHostIp();
		basicField = ServerFieldInit.basicField();
		fieldNumbers = ServerFieldInit.fieldContent(basicField);
		bombPos=ServerFieldInit.bombPos(bombPos);
		new Server().go();
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

	public void go() {

		try {
			ServerSocket serverSock = new ServerSocket(5001);

			while (max == true) {
				Socket clientSocket = serverSock.accept();

				maxx++;

				if (maxx == 1) {
					client1 = clientSocket;
				} else {
					client2 = clientSocket;
				}
				if (maxx == 2) {
					max = false;
					
					iOStreams1(client1);
					iOStreams2(client2);

					Thread t1 = new Thread(new waitClient1());
					t1.start();
					sendClient1();

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

	public void iOStreams1(Socket clientSocket) {
		try {
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
	public void iOStreams2(Socket clientSocket) {

		try {
			Socket sock = clientSocket;
			streamReader2 = new DataInputStream(sock.getInputStream());
			streamWriter2 = new DataOutputStream(sock.getOutputStream());

		} catch (Exception ex) {
			System.out
					.println("Server: Es gab Probleme die Streams für CLient2 zu öffnen.");
			ex.printStackTrace();
		}

	}
	
	/**
	 * Senden der fieldNumbers und powerUp Felder an den Klienten 1.
	 */

	public void sendClient1() {
	
		try {
			for (int i = 0; i < 17; i++) {
				for (int j = 0; j < 21; j++) {
					streamWriter1.writeInt(fieldNumbers[j][i]);
					streamWriter1.writeInt(powerUps[j][i]);
				}
			}
		} catch (Exception ex) {
			System.out.println("Server: Probleme in SendClient1.");
			ex.printStackTrace();
		}
		
	}

	/**
	 * Senden der fieldNumbers und powerUp Felder an den Klienten 2.
	 */
	
	public static void sendClient2() {
		
		try {
			for (int i = 0; i < 17; i++) {
				for (int j = 0; j < 21; j++) {
					streamWriter2.writeInt(fieldNumbers[j][i]);
					streamWriter2.writeInt(powerUps[j][i]);
				}
			}
		} catch (Exception ex) {
			System.out.println("Server: Probleme in SendClient2.");
			ex.printStackTrace();
		}
	}
	
	/**
	 * Weitergabe der erhaltenen Daten die den Spieler und die Bombe angeht an beide Klienten.
	 */

	public static void sendToAll() {

		try {
			for(int i=0;i<5;i++){
				streamWriter1.writeInt(playerInfo[i]);
				streamWriter2.writeInt(playerInfo[i]);
			}
			for(int i=0;i<3;i++){
				streamWriter1.writeInt(bombInfoInt[(i*2)]);
				streamWriter2.writeInt(bombInfoInt[(i*2)]);
				streamWriter1.writeInt(bombInfoInt[(i*2+1)]);
				streamWriter2.writeInt(bombInfoInt[(i*2+1)]);
				streamWriter1.writeBoolean(bombInfoBoolean[i]);
				streamWriter2.writeBoolean(bombInfoBoolean[i]);
			}


		} catch (Exception ex) {
			System.out
					.println("Server: Versuche es weiter zusagen geht nicht.");
			ex.printStackTrace();
		}

	} // esAllenWeitersagen schließen

	/**
	 * Erhält die Ipaddresse des Host Rechners.
	 */
	private static void getHostIp() {
		try{
			InetAddress thisIp = InetAddress.getLocalHost();
			System.out.println("Host-IP: "+ thisIp.getHostAddress());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
} // Klasse schließen
