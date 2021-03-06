package Server;

import java.io.IOException;

/**
 * Thread der w�hrend eines Onlinespiels dauerhaft auf Daten der Klienten wartet.
 * 
 * @author Jan Reckfort
 * @author Bastian Siefen
 */
public class gameReader extends Thread {

	public void run() {

		try {
			System.out.println("gameReader: gestartet");
			Server.sendToAll();
			while (true) {

				if (Server.streamReader1.available() >= 47) {
					ReadClient1();
				}
				if (Server.streamReader2.available() >= 47) {
					ReadClient2();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("bla2");
		}

	} // run schlie�en
	/**
	 * Auslesen des Daten vom Klienten 1.
	 */
	public void ReadClient1(){
		try{
			for(int i=0;i<5;i++){
				Server.playerInfo[i]=Server.streamReader1.readInt();	
			}
			for(int i = 0;i<6;i++){
				Server.bombInfoInt[i]=Server.streamReader1.readInt();
			}
			for(int i=0;i<3;i++){
				Server.bombInfoBoolean[i]=Server.streamReader1.readBoolean();
			}
	
		Server.sendToAll();
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("bla2");
		}
	}
	/**
	 * Auslesen des Daten vom Klienten 2.
	 */
	public void ReadClient2(){
		try{
			for(int i=0;i<5;i++){
				Server.playerInfo[i]=Server.streamReader2.readInt();	
			}
			for(int i = 0;i<6;i++){
				Server.bombInfoInt[i]=Server.streamReader2.readInt();
			}
			for(int i=0;i<3;i++){
				Server.bombInfoBoolean[i]=Server.streamReader2.readBoolean();
			}
		Server.sendToAll();
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("bla2");
		}
	}

}