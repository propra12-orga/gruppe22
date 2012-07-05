import java.io.IOException;

public class gameReader extends Thread {

	public void run() {

		try {
			System.out.println("gameReader: gestartet");
			Server.esAllenWeitersagen();
			while (true) {

				if (Server.streamReader1.available() >= 3262) {
					ReadClient1();
				}
				if (Server.streamReader2.available() >= 3262) {
					ReadClient2();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("bla2");
		}

	} // run schlieﬂen
	
	public void ReadClient1(){
		try{
			System.out.println("Stream1 hat: "
				+ Server.streamReader1.available());
			for (int i1 = 0; i1 <= 16; i1++) {
				for (int j1 = 0; j1 <= 20; j1++) {
					Server.fieldNumbers[j1][i1] = Server.streamReader1
						.readInt();
			}
		}
			for (int i1 = 0; i1 <= 16; i1++) {
				for (int j1 = 0; j1 <= 20; j1++) {
					Server.powerUps[j1][i1] = Server.streamReader1
						.readInt();
			}
		}
			for (int i1 = 0; i1 <= 16; i1++) {
				for (int j1 = 0; j1 <= 20; j1++) {
					Server.bombPos[j1][i1] = Server.streamReader1
						.readBoolean();
			}
		}
			for(int i=0;i<=23;i++){
				Server.bomb[i]=Server.streamReader1.readInt();
			}
	
		Server.esAllenWeitersagen();
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("bla2");
		}
	}
	
	public void ReadClient2(){
		try{
			System.out.println("Stream2 hat: "
				+ Server.streamReader2.available());
			for (int i1 = 0; i1 <= 16; i1++) {
				for (int j1 = 0; j1 <= 20; j1++) {
					Server.fieldNumbers[j1][i1] = Server.streamReader2
						.readInt();
			}
		}
			for (int i1 = 0; i1 <= 16; i1++) {
				for (int j1 = 0; j1 <= 20; j1++) {
					Server.powerUps[j1][i1] = Server.streamReader2
						.readInt();
			}
		}
			for (int i1 = 0; i1 <= 16; i1++) {
				for (int j1 = 0; j1 <= 20; j1++) {
					Server.bombPos[j1][i1] = Server.streamReader2
						.readBoolean();
			}
		}
			for(int i=0;i<=23;i++){
				Server.bomb[i]=Server.streamReader2.readInt();
			}
		Server.esAllenWeitersagen();
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("bla2");
		}
	}

}