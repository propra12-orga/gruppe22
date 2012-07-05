import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Init {

	static int maxKisten = 90;
	static Boolean MP = false;
	static Boolean KI = false;
	static Boolean loaded = false;
	static Boolean exitSet = false;
	static Player Player1 = new Player();
	static Player Player2 = new Player();
	static BufferedReader in;
	static String[] gameInfo = new String[8];
	static String[][] bombInfo = new String[6][7];

	static int ex;
	static int ey;
	static int[][] powerUps = new int[21][17];
	
	static int bombCnt;
	static int rad;

	// /////////////////////////////////////////////////////////////////////////////////////

	/*
	 * Initialisierung des Feldarrays Jede Koordinate des Feldes bekommt einen
	 * Wert: 0) Frei begehbares Feld 1) Unzerst�?rbare Mauer 2) Zerst�?rbare
	 * Kiste 3) Spieler1 4) Spieler2 5) Spieler3 6) Spieler4 7) Bombe 8)
	 * Detonation 9) Ausgang ......
	 */

	/**
	 * Methode zum �ffnen des Datenkanals zur Datei welche die Daten f�r die
	 * konstante Karte beinhaltet.
	 */
	public static void MapReader(String s) { /* 0815 Reader */

		try {
			in = new BufferedReader(new FileReader(s));

		} catch (IOException e) {
		}

	}

	/**
	 * Die Methode ruft den BufferedReader der f�r die Karten auslesung
	 * zust�ndig ist auf. Sie liest die Zeilen der Datei einzelt aus und spaltet
	 * den String den sie erh�lt auf die jeweiligen zahlenw erden in ein
	 * zweidimensionales Feld eingespeichert. Dieses bef�llte Feld wird zur�ck
	 * gegeben.
	 * 
	 * @return feld
	 */

	public static int[][] constMap(String s) {
		maxKisten = 0;
		String[] bufmap = new String[21];
		String[] bufPl = new String[8];
		String[] bufB = new String[7];
		String zeile;
		zeile = "";
		int[][] feld = new int[21][17];
		MapReader(s);

		for (int i = 0; i <= 16; i++) {

			try {
				zeile = in.readLine();
			} catch (IOException e) {

			}
			bufmap = zeile.split("_");
			for (int o = 0; o <= 20; o++) {
				feld[o][i] = Integer.parseInt(bufmap[o]);
				if (feld[o][i] == 2)
					maxKisten++;
			}

		}
		
		try {
			zeile = in.readLine();
		} catch (IOException e){
			
		}
		bufPl = zeile.split("_");
		//for (int i = 0; i < 8; i++)
			//System.out.println("bufPl[" + i + "] = " + bufPl[i]);
		
		for (int i = 0; i < 8; i++)
			gameInfo[i] = bufPl[i];
		
		for (int i = 0; i < 6; i++){
			try {
				zeile = in.readLine();
			} catch (IOException e){
				
			}
			bufB = zeile.split("_");
			for (int j = 0; j < 7; j++)
				bombInfo[i][j] = bufB[j];
		}
		
		
		loaded = true;
		
		for (int i = 0; i < 21; i++)
			for(int j = 0; j < 17; j++){
				if(feld[i][j] == 9)
					exitSet = true;
				if(feld[i][j] == 8)
					feld[i][j] = 0;
			}
			
		if (!exitSet)
			SetExit(feld);
		setPowerUps(feld);

		return feld;

	}

	/**
	 * Initialisierungsmethode eines Standard-Spielfeldes, lediglich bestehend
	 * aus festen Mauerstuecken und frei begehbaren Feldern.
	 * 
	 * @return
	 */

	public static int[][] basicField() {
		MenueEingabe.CtrlReader();
		int[][] fields = new int[21][17];

		// Zun�?chst bekommen alle Feldkoordinaten den Wert "0"
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

	/**
	 * Initialisierung des Inhalts eines Spielfeldes (Kisten, Spieler....)
	 * 
	 * @param fields
	 * @return
	 */

	public static int[][] fieldContent(int[][] fields) {

		/*
		 * double randomBox lediglich fuer Math.random() Das Feldarray wird
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
		
		if (KI) {
			fields[19][15] = 4;
			fields[18][15] = 0;
			fields[19][14] = 0;
			Player1.initKI(Player2);
		}

		setPowerUps(fields);
		SetExit(fields);

		return fields;
	}

	/**
	 * Reset-Methode fuer das Spielfeld. Befuellt das Spielfeld neu und setzt
	 * die Spieler wieder auf ihre Startpositionen.
	 */

	public static void reset() {

		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++)
				if (Field.fieldNumbers[i][j] != 1
						&& Field.fieldNumbers[i][j] != 9)
					Field.fieldNumbers[i][j] = 0;

		Field.fieldNumbers = fieldContent(Field.basicField);
		Player1.x = 1;
		Player1.y = 1;
		Bomb.gameOver = false;
		exitSet = false;
		loaded = false;
		
		if (MP || KI) {
			Player2.x = 20;
			Player2.y = 16;

			MP = false;
			KI = false;
		}
		;
	}

	/**
	 * Initialisierung eines Boolean-Arrays fuer die Bomben. Legt spaeter fest,
	 * wo sich Bomben befinden, und von welchen Bomben Detonationen gestartet
	 * werden.
	 * 
	 * @return
	 */

	public static boolean[][] bombPos() {
		boolean[][] bombPos = new boolean[21][17];

		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++)
				bombPos[i][j] = false;

		return bombPos;
	}

	/**
	 * Initialisierung des statischen Bombenarrays der Klasse Bomb, unter der
	 * Beruecksichtigung des Spielmodus.
	 * 
	 * @return
	 */

	public static Bomb[] bombs() {
		Bomb[] bombs;
			bombs = new Bomb[6];
			for (int i = 0; i < 3; i++)
				bombs[i] = new Bomb(Player1);
			for (int i = 3; i < 6; i++)
				bombs[i] = new Bomb(Player2);

		return bombs;

	}

	/**
	 * Die Methode durch l�uft bevor das Spiel beginnt das Spielfeld und
	 * �berpr�ft wo sich Kisten befinden. Wenn eine Kiste vorhanden ist an der
	 * Stelle wo sich die For-Schleifen befinden wird ein zuf�lliger Wert
	 * erzeugt. Bei einem Ergebniss gr��er als 0,99 (bei einem Maximum von 1)
	 * wird der Ausgang gesetzt. Es kann sich maximal ein Ausgang auf dem
	 * Spielfeld befinden.
	 * 
	 * @param fields
	 */

	public static void SetExit(int[][] fields) {
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

	public static void setPowerUps(int[][] fields) {
		int value = 0;
		double randomBox;
		double whichPower;
		int bombCnt = 1;

		while (value < (maxKisten / 2)) {
			for (int i = 1; i < 20; i++)
				for (int j = 1; j < 16 && value < (maxKisten / 2); j++) {
					if (fields[i][j] == 2) {
						randomBox = Math.random();
						if (randomBox > 0.99) {
							whichPower = Math.random();
							if (whichPower > 0.5 && bombCnt <= 3){
								bombCnt++;
								powerUps[i][j] = 42;
								value++;
							} else if (whichPower < 0.5) {
								powerUps[i][j] = 41;
								value++;
							}
						}
					}
				}
		}

	}

}