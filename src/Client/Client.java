package Client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Game.Bomb;
import Game.Field;
import Game.Init;
import Game.Interface;

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
	} // los schieﬂen

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
	} // netzwerkEinrichten schlieﬂen

	public static void Send() {
		try {
			if( Interface.ctrlP1){
				streamWriter.writeInt(Init.Player1.x);
				streamWriter.writeInt(Init.Player1.y);
				streamWriter.writeInt(Init.Player1.num);
				streamWriter.writeInt(Init.Player1.bCnt);
				streamWriter.writeInt(Init.Player1.rad);
			}else {
				streamWriter.writeInt(Init.Player2.x);
				streamWriter.writeInt(Init.Player2.y);
				streamWriter.writeInt(Init.Player2.num);
				streamWriter.writeInt(Init.Player2.bCnt);
				streamWriter.writeInt(Init.Player2.rad);
			}
			
			if (Interface.ctrlP1){
				for (int i = 0; i < 3; i++) {
					streamWriter.writeInt(Bomb.bombs[i].x);
					streamWriter.writeInt(Bomb.bombs[i].y);
				}
			} else {
				for (int i = 3; i < 6; i++) {
					streamWriter.writeInt(Bomb.bombs[i].x);
					streamWriter.writeInt(Bomb.bombs[i].y);
				}
			}
				
			if (Interface.ctrlP1){
				for (int i = 0; i < 3; i++) {
					streamWriter.writeBoolean(Bomb.bombs[i].active);
				}
			} else {
				for (int i = 3; i < 6; i++) {
					streamWriter.writeBoolean(Bomb.bombs[i].active);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

} // ‰uﬂere Klasse schlieﬂen

