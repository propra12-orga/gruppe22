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
	 * Definiert die Position und die Spieler Nummer des Spieler 2.
	 */
	
	public static void getStartPos2(Player player){
		player.x = 19;
		player.y = 15;
		player.num = 2;
		player.bP = 3;
	}
	
}
