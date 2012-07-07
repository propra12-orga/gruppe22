package Client;
import java.io.IOException;

import Game.Bomb;
import Game.Field;
import Game.Init;

public class ClientGameReader extends Thread {
	static boolean Stift = false;

	public void run() {

		System.out.println("gameReader gestartet.");
		try {
			while (true) {

				if (EinfacherChatClient.streamReader.available() >= 3309) {
					for (int i = 0; i <= 16; i++) {
						for (int j = 0; j <= 20; j++) {
							Field.fieldNumbers[j][i] = EinfacherChatClient.streamReader
									.readInt();
							if (Field.fieldNumbers[j][i] == 4) {
								Init.Player2.x = j;
								Init.Player2.y = i;
								Stift = true;
								}

							if (Field.fieldNumbers[j][i] == 3) {
								Init.Player1.x = j;
								Init.Player1.y = i;
							}
						}

					}
					for (int i = 0; i <= 16; i++) {
						for (int j = 0; j <= 20; j++) {
							Init.powerUps[j][i] = EinfacherChatClient.streamReader
									.readInt();
						}
					}
					for (int i = 0; i <= 16; i++) {
						for (int j = 0; j <= 20; j++) {
							Field.bombPos[j][i] = EinfacherChatClient.streamReader
									.readBoolean();
						}
					}
					for (int i = 0; i < 6; i++) {
						Bomb.bombs[i].o = EinfacherChatClient.streamReader
								.readInt();
						Bomb.bombs[i].u = EinfacherChatClient.streamReader
								.readInt();
						Bomb.bombs[i].l = EinfacherChatClient.streamReader
								.readInt();
						Bomb.bombs[i].r = EinfacherChatClient.streamReader
								.readInt();

					}

				}
			}

		} catch (IOException e) {
			System.out.println("Fehler beim lesen.");
			e.printStackTrace();
		}
	} // run schließen
} // innere Klasse EingehendReader schließen