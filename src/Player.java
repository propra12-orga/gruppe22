public class Player {
	
	int x;
	int y;
	int bCnt;
	int rad;
	int xo;
	int yo;
	int num;
	int bP; //bombs placed
	String ctrl;
	
	int checkRad;
	boolean danger;
	
	/**
	 * Erzeugung des Spielerobjektes mit den Variablen:
	 * x - X-Koordinate,
	 * y - Y-Koordinate,
	 * xo - X-Koordinate im vorrangehenden Zug,
	 * yo - Y-Koordinate im vorrangehenden Zug,
	 * bCnt - Anzahl der Bomben des jeweiligen Spielers,
	 * rad - Radius der Bomben des Spielers,
	 * ctrl - Richtungsangabe des Spielers in die er geht,
	 * num - Nummer des Spielers,
	 * bP - aktive Bomben des Spielers auf dem Spielfeld
	 * 
	 * checkRad - Ueberpruefungsradius fuer Gefahr bzgl. Bomben (KI)
	 * danger - Boolean bzgl. der Gefahr.
	 */

	public Player() {
		this.x = 1;
		this.y = 1;
		
		this.xo = x;
		this.yo = y;
		this.bCnt = 1;
		this.rad = 1;
		this.ctrl ="";
		this.num = 1;
		this.bP = 0;
	}
	/**
	 * Definiert die Position und die Spielernummer des Spieler 2 (Multiplayer)
	 * 
	 * @param player <br>
	 * Spieler 2 wird uebergeben und seine Parameter entsprechend ueberschrieben.
	 */
	
	public static void getStartPos2(Player player){
		player.x = 19;
		player.y = 15;
		player.num = 2;
		player.bP = 3;
	}
	
	/**
	 * Definiert die Position und die Spielernummer des Spieler 2 
	 * mit zusaetzlichen spielereigenen Parametern (KI)
	 * 
	 * @param player <br>
	 * Spieler 2 wird uebergeben und seine Parameter entsprechend ueberschrieben.
	 */
	
	public static void initKI(Player player){
		player.x = 19;
		player.y = 15;
		player.num = 2;
		player.bP = 3;
		player.checkRad = 1;
		player.danger = false;
	}
	
}
