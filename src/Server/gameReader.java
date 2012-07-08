
import java.io.IOException;

public class gameReader extends Thread {

	public void run() {

		try {
			System.out.println("gameReader: gestartet");
			Server.SendToAll();
			while (true) {

				if (Server.streamReader1.available() >= 102) {
					ReadClient1();
				}
				if (Server.streamReader2.available() >= 102) {
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
			for(int i=0;i<12;i++){
				Server.playerInfo[i]=Server.streamReader1.readInt();	
			}
			for(int i = 0;i<12;i++){
				Server.bombInfoInt[i]=Server.streamReader1.readInt();
			}
			for(int i=0;i<6;i++){
				Server.bombInfoBoolean[i]=Server.streamReader1.readBoolean();
			}
	
		Server.SendToAll();
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("bla2");
		}
	}
	
	public void ReadClient2(){
		try{
			for(int i=0;i<12;i++){
				Server.playerInfo[i]=Server.streamReader2.readInt();	
			}
			for(int i = 0;i<12;i++){
				Server.bombInfoInt[i]=Server.streamReader2.readInt();
			}
			for(int i=0;i<6;i++){
				Server.bombInfoBoolean[i]=Server.streamReader2.readBoolean();
			}
		Server.SendToAll();
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("bla2");
		}
	}

}