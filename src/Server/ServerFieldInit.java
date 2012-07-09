package Server;

/**
 * Serverseitige Initialiesierungsklasse für spielrelevante Felder.
 * 
 * @author Jan Reckfort
 * @author Bastian Siefen
 *
 */

public class ServerFieldInit {

	/**
	 * Initialisierungsmethode eines Standard-Spielfeldes, lediglich bestehend
	 * aus festen Mauerstuecken und frei begehbaren Feldern.
	 * 
	 * @return fields
	 */
	public static int[][] basicField() {
		int[][] fields1 = new int[21][17];

		// Zunï¿½?chst bekommen alle Feldkoordinaten den Wert "0"
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++)
				fields1[i][j] = 0;

		// Die Randfelder des Spielfeldes werden auf "1" gesetzt
		for (int i = 0; i < 21; i++) {
			fields1[i][0] = 1;
			fields1[i][16] = 1;
		}

		for (int j = 0; j < 17; j++) {
			fields1[0][j] = 1;
			fields1[20][j] = 1;
		}

		for (int i = 2; i < 19; i += 2)
			for (int j = 2; j < 15; j += 2)
				fields1[i][j] = 1;

		return fields1;
	}
	/**
	 * Initialisierung des Inhalts eines Spielfeldes (Kisten, Spieler....)
	 * 
	 * @param fields
	 * @return fields
	 */
	public static int[][] fieldContent(int[][] field) {

		/*
		 * double randomBox lediglich fuer Math.random() Das Feldarray wird
		 * einmal durchgegangen. Sofern eine frei begehbare Stelle gefunden
		 * wird, wird diese per Zufall (Math.random()) zum Kistenfeld im Array
		 * editiert.
		 */
		int[][] fields=field;
		
		double randomBox;
		int value = 0;

		while (value < 50) {
			for (int i = 1; i < 20; i++)
				for (int j = 1; j < 16 && value < 50; j++) {
					if (fields[i][j] == 0) {
						randomBox = Math.random();
						if (randomBox > 0.99) {
							fields[i][j] = 2;
							value++;
						}
					}
				}
		}

		 setPowerUps(fields);

		return fields;
	}
	/**
	 * Initialisiert die PowerUps nach dem gleichen Prinzip wie der Ausgang in der
	 * Init.setExit-Methode gesetzt wird.
	 *
	 * @param fields
	 */
	public static void setPowerUps(int[][] fields) {
		int value = 0;
		double randomBox;
		double whichPower;
		int bombCnt = 1;

		while (value < (50 / 2)) {
			for (int i = 1; i < 20; i++)
				for (int j = 1; j < 16 && value < (50 / 2); j++) {
					if (fields[i][j] == 2) {
						randomBox = Math.random();
						if (randomBox > 0.99) {
							whichPower = Math.random();
							if (whichPower > 0.5 && bombCnt <= 3) {
								bombCnt++;
								Server.powerUps[i][j] = 42;
								value++;
							} else if (whichPower < 0.5) {
								Server.powerUps[i][j] = 41;
								value++;
							}
						}
					}
				}
			Server.powerUps[1][1] = 0;
			Server.powerUps[1][2] = 0;
			Server.powerUps[2][1] = 0;
			Server.powerUps[19][15] = 0;
			Server.powerUps[19][14] = 0;
			Server.powerUps[18][15] = 0;
		}
	}
	public static int[] Bombrad(int[]bombrad){
		for (int i=0;i<=23;i++)
		{ 
			bombrad[i]=0;
		}
			return bombrad;
	}
	public static boolean[][] bombPos(boolean[][] inPos) {
		inPos = new boolean[21][17];
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++)
				inPos[i][j] = false;
		return inPos;
	}
}
