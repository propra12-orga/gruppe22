public class Player {
	
	/**
	 * X-Koordinate des Spielers
	 */
	int x;
	/**
	 * Y-Koordinate des Spielers
	 */
	int y;
	/**
	 * Maximale Bombenanzahl des Spielers
	 */
	int bCnt;
	/**
	 * Bombenradius des Spielers
	 */
	int rad;
	/**
	 * Vorhergegangene X-Koordinate des Spielers
	 */
	int xo;
	/**
	 * Vorhergegangene Y-Koordinate des Spielers
	 */
	int yo;
	/**
	 * Spielernummer
	 */
	int num;
	/**
	 * zZ aktive Bomben des Spielers auf dem Spielfeld
	 */
	int bP; 
	String ctrl;
	
	/**
	 * Radius des Computergegners zum Berechnen seiner Sicherheit bzgl. der Bomben auf dem Spielfeld
	 */
	int checkRad;
	/**
	 * Gibt an, ob sich der Computergegner in Gefahr befindet.
	 */
	boolean danger;
	
	/**
	 * Konstruktor: <br>
	 * Erzeugt ein neues Spielerobjekt mit entsprechenden Variablen, die zunaechst auf Standard gesetzt sind.
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
