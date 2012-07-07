package Game;
public class Player {
	
	/**
	 * X-Koordinate des Spielers
	 */
	public int x;
	/**
	 * Y-Koordinate des Spielers
	 */
	public int y;
	/**
	 * Maximale Bombenanzahl des Spielers
	 */
	public int bCnt;
	/**
	 * Bombenradius des Spielers
	 */
	public int rad;
	/**
	 * Vorhergegangene X-Koordinate des Spielers
	 */
	public int xo;
	/**
	 * Vorhergegangene Y-Koordinate des Spielers
	 */
	public int yo;
	/**
	 * Spielernummer
	 */
	public int num;
	/**
	 * zZ aktive Bomben des Spielers auf dem Spielfeld
	 */
	public int bP; 
	public String ctrl;
	
	/**
	 * Radius des Computergegners zum Berechnen seiner Sicherheit bzgl. der Bomben auf dem Spielfeld
	 */
	public int checkRad;
	/**
	 * Gibt an, ob sich der Computergegner in Gefahr befindet.
	 */
	public boolean danger;
	
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
