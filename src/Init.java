public class Init {

	// /////////////////////////////////////////////////////////////////////////////////////

	/*
	 * Initialisierung des Feldarrays Jede Koordinate des Feldes bekommt einen
	 * Wert: 0) Frei begehbares Feld 1) Unzerstörbare Mauer 2) Zerstörbare Kiste
	 * 3) Spieler1 4) Spieler2 5) Spieler3 6) Spieler4 7) Bombe 8) Detonation 9)
	 * Ausgang ......
	 */

	public static int[][] basicField() {
		int[][] fields = new int[21][17];

		// Zunächst bekommen alle Feldkoordinaten den Wert "0"
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++)
				fields[i][j] = 0;

		// Die Randfelder des Spielfeldes werden auf "1" gesetzt
		for (int i = 0; i < 21; i++) {
			fields[i][0] = 1;
			fields[i][16] = 1;
		}

		for (int j = 0; j < 17; j++) {
			fields[0][j] = 1;
			fields[20][j] = 1;
		}

		for (int i = 2; i < 19; i += 2)
			for (int j = 2; j < 15; j += 2)
				fields[i][j] = 1;

		return fields;
	}

	// /////////////////////////////////////////////////////////////////////////////////////

	public static int[][] fieldContent(int[][] fields) {

		/*
		 * double randomBox lediglich für Math.random() Das Feldarray wird
		 * einmal durchgegangen. Sofern eine frei begehbare Stelle gefunden
		 * wird, wird diese per Zufall (Math.random()) zum Kistenfeld im Array
		 * editiert.
		 */

		double randomBox;
		int value = 0;

		while (value < 100) {
			for (int i = 1; i < 20; i++)
				for (int j = 1; j < 16; j++) {
					if (fields[i][j] == 0) {
						randomBox = Math.random();
						if (randomBox > 0.5) {
							fields[i][j] = 2;
							value++;
						}
					}
				}
		}

		// Setze die 4 Eckpunkte (Startpunkte der Spieler) auf 3
		// und die 2 jeweils angrenzenden Felder auf 0.
		fields[1][1] = 3;
		// fields[1][15] = 4; Zunächst sind Spieler 2-4 auskommentiert,
		// fields[19][1] = 5; reicht ja erstmal den Singleplayer gebacken
		// fields[19][15] = 6; zu bekommen.
		fields[1][2] = 0;
		fields[2][1] = 0;
		// fields[1][14] = 0;
		// fields[2][15] = 0;
		// fields[18][1] = 0;
		// fields[19][2] = 0;
		// fields[18][15] = 0;
		// fields[19][14] = 0;

		return fields;
	}

	// /////////////////////////////////////////////////////////////////////////////////////

	public static boolean isDestructable(int coord) {
		if (coord == 1)
			return false;
		else
			return true;
	}

	// /////////////////////////////////////////////////////////////////////////////////////

	// Boolean-Array zur Kollisionsabfrage

	public static boolean[][] checkArray() {
		boolean[][] checkArray = new boolean[21][17];
		int[][] fieldNumbers = basicField();

		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++)
				checkArray[i][j] = isDestructable(fieldNumbers[i][j]);

		return checkArray;
	}
}