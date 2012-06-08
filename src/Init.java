import java.io.*;

public class Init {

	static int maxKisten = 90;
	static Boolean MP = false;
	static Player Player1 = new Player();
	static Player Player2 = new Player();
	static BufferedReader in;
	
	static int ex;
	static int ey;

	// /////////////////////////////////////////////////////////////////////////////////////

	/*
	 * Initialisierung des Feldarrays Jede Koordinate des Feldes bekommt einen
	 * Wert: 0) Frei begehbares Feld 1) Unzerst�rbare Mauer 2) Zerst�rbare
	 * Kiste 3) Spieler1 4) Spieler2 5) Spieler3 6) Spieler4 7) Bombe 8)
	 * Detonation 9) Ausgang ......
	 */
	public static void MapReader() { /* 0815 Reader */

		try {
			in = new BufferedReader(new FileReader("Map1.TXT"));

		} catch (IOException e) {
		}

	}

	public static int[][] constMap() {
		String[] bufmap = new String[21];
		String zeile;
		zeile = "";
		int[][] feld = new int[21][17];
		MapReader();

		for (int i = 0; i <= 16; i++) {

			try {
				zeile = in.readLine();
			} catch (IOException e) {

			}
			bufmap = zeile.split("_");
			for (int o = 0; o <= 20; o++) {
				feld[o][i] = Integer.parseInt(bufmap[o]);

			}

		}
		
		SetExit(feld);

		return feld;

	}

	public static int[][] basicField() {
		Eingabe.CtrlReader();
		int[][] fields = new int[21][17];

		// Zun�chst bekommen alle Feldkoordinaten den Wert "0"
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
		 * double randomBox lediglich f�r Math.random() Das Feldarray wird
		 * einmal durchgegangen. Sofern eine frei begehbare Stelle gefunden
		 * wird, wird diese per Zufall (Math.random()) zum Kistenfeld im Array
		 * editiert.
		 */

		double randomBox;
		int value = 0;

		while (value < maxKisten) {
			for (int i = 1; i < 20; i++)
				for (int j = 1; j < 16 && value < maxKisten; j++) {
					if (fields[i][j] == 0) {
						randomBox = Math.random();
						if (randomBox > 0.99) {
							fields[i][j] = 2;
							value++;
						}
					}
				}
		}

		// Setze die 4 Eckpunkte (Startpunkte der Spieler) auf 3
		// und die 2 jeweils angrenzenden Felder auf 0.
		fields[1][1] = 3;
		fields[1][2] = 0;
		fields[2][1] = 0;
		
		if (MP) {
			fields[19][15] = 4;
			fields[18][15] = 0;
			fields[19][14] = 0;
			Player.getStartPos2(Player2);
		}
		
		SetExit(fields);
		
		return fields;
	}

	public static void reset() {

		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++)
				if (Field.fieldNumbers[i][j] != 1
						&& Field.fieldNumbers[i][j] != 9)
					Field.fieldNumbers[i][j] = 0;

		Field.fieldNumbers = fieldContent(Field.basicField);
		Player1.x = 1;
		Player1.y = 1;
		if (MP) {
			Player2.x = 20;
			Player2.y = 16;

			MP = false;
		};
	}
	
	public static boolean[][] bombPos(){
		boolean[][] bombPos = new boolean[21][17];
		
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++)
				bombPos[i][j] = false;
		
		return bombPos;
	}
	
	public static Bomb[] bombs(){
		Bomb[] bombs;
		if (MP){
			bombs = new Bomb[6];
			for (int i = 0; i < 3; i++)
				bombs[i] = new Bomb(Player1);
			for (int i = 3; i < 6; i++)
				bombs[i] = new Bomb(Player2);
		}
		else {
			bombs = new Bomb[3];
			for (int i = 0; i < 3; i++)
				bombs[i] = new Bomb(Player1);
		}
		
		return bombs;
			
	}
	
	public static void SetExit(int[][] fields){
		int value = 0;
		double randomBox;
		
		while (value < 1) {
			for (int i = 1; i < 20; i++)
				for (int j = 1; j < 16 && value < maxKisten; j++) {
					if (fields[i][j] == 2) {
						randomBox = Math.random();
						if (randomBox > 0.99) {
							ex = i;
							ey = j;
							value++;
						}
					}
				}
		}
		
		
	}
	
	
	
}