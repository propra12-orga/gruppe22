package Client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Game.Bomb;
import Game.Init;

public class Client {

	Socket sock;
	static DataInputStream streamReader;
	static DataOutputStream streamWriter;

	/**
	 * <u>Start:</u><br>
	 * Erstellt ein neues Objekt vom Typ Client.<br>
	 * Aufrufen der Methode <b>go()</b>
	 */
	public static void start() {
		Client client = new Client();
		client.Go();
	}

	/**
	 * <u>Go:</u><br>
	 * Aufrufen der Methode <b>Network</b>.<br>
	 * Starten des Threads <b>StartReader</b>.
	 */
	public void Go() {

		Network();

		Thread readerThread = new Thread(new StartReader());
		readerThread.start();
	} // los schie�en

	/**
	 * <u>Network:</u><br>
	 * Erstellen des Sockets.<br>
	 * Deklarierung der Input- und Outputstreams: <b>streamReader</b> und
	 * <b>streamWriter</b>.<br>
	 */
	private void Network() {

		try {
			sock = new Socket("192.168.2.111", 5001);
			streamReader = new DataInputStream(sock.getInputStream());
			streamWriter = new DataOutputStream(sock.getOutputStream());
			System.out.println("Netzwerkverbindung steht");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	} // netzwerkEinrichten schlie�en

	public static void Send() {
		try {
			System.out.println("Client sendet");
			streamWriter.writeInt(Init.Player1.x);
			streamWriter.writeInt(Init.Player1.y);
			streamWriter.writeInt(Init.Player1.num);
			streamWriter.writeInt(Init.Player1.bCnt);
			streamWriter.writeInt(Init.Player1.rad);
			streamWriter.writeInt(Init.Player1.bP);
			streamWriter.writeInt(Init.Player2.x);
			streamWriter.writeInt(Init.Player2.y);
			streamWriter.writeInt(Init.Player2.num);
			streamWriter.writeInt(Init.Player2.bCnt);
			streamWriter.writeInt(Init.Player2.rad);
			streamWriter.writeInt(Init.Player2.bP);
			for (int i = 0; i < 6; i++) {
				streamWriter.writeInt(Bomb.bombs[i].x);
				streamWriter.writeInt(Bomb.bombs[i].y);
			}
			for (int i = 0; i < 6; i++) {
				streamWriter.writeBoolean(Bomb.bombs[i].active);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

} // �u�ere Klasse schlie�en

