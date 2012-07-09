
import java.io.IOException;

public class gameReader extends Thread {

	public void run() {

		try {
			System.out.println("gameReader: gestartet");
			Server.sendToAll();
			while (true) {

				if (Server.streamReader1.available() >= 47) {
					readClient1();
				}
				if (Server.streamReader2.available() >= 47) {
					readClient2();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("bla2");
		}

	} 
	
	public void readClient1(){
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
			System.out.println("gameReader: sendToAll ERROR");
		}
	}
	
	public void readClient2(){
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