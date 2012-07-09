package Client;
import java.io.IOException;

public class ClientGameReader extends Thread {
	static boolean gameOn = false;
	int x;
	int y;
	int num;
	int bCnt;
	int rad;
	int bx;
	int by;
	boolean act;

	public void run() {

		System.out.println("gameReader gestartet.");
		try {
			while (true) {
				if (Client.streamReader.available() >= 47) {
					gameOn = true;
					x = Client.streamReader.readInt();
					y = Client.streamReader.readInt();
					num = Client.streamReader.readInt();
					bCnt = Client.streamReader.readInt();
					rad = Client.streamReader.readInt();
					Update.player(x, y, num, bCnt, rad);
					if(num==1){
						for (int i = 0; i < 3; i++) {
							bx = Client.streamReader.readInt();
							by = Client.streamReader.readInt();
							act = Client.streamReader.readBoolean();
							Update.bomb(bx, by, i, act);
						}
					} else if(num == 2){
						for (int i = 3; i < 6; i++) {
							bx = Client.streamReader.readInt();
							by = Client.streamReader.readInt();
							act = Client.streamReader.readBoolean();
							Update.bomb(bx, by, i, act);
						}
					}
				}

			}

		} catch (IOException e) {
			System.out.println("Fehler beim lesen.");
			e.printStackTrace();
		}
	} // run schließen
} // innere Klasse EingehendReader schließen