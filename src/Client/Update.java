package Client;

import Game.Bomb;
import Game.Carl;
import Game.Field;
import Game.Init;
import Game.Player;

/**
 * Aktualisiert das Spielfeld im Onlinemodus.
 * 
 * @author Pierre Schwarz
 *
 */


public class Update {
	
	/**
	 * Bombe, die aktualisiert werden soll.
	 */
	static Bomb bomb;
	
	/**
	 * Spieler, der aktualisiert werden soll.
	 */
	static Player player;
	
	/**
	 * Gibt an, um welchen Spieler es sich handelt.
	 */
	static boolean pl1, pl2 = false;
	static int xo1 = Init.Player1.x;
	static int yo1 = Init.Player1.y;
	static int xo2 = Init.Player2.x;
	static int yo2 = Init.Player2.y;
	/**
	 * Aktualisiert das Gegenspielerobjekt im Onlinemodus.
	 * 	
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @param num Spielernummer
	 * @param bCnt max. Bombenanzahl
	 * @param rad Spielerradius
	 * @param bP Anzahl gelegter Bomben
	 */
	
	public static void player(int x, int y, int num, int bCnt, int rad){
		
		/*
		 * Initialisiere Spielerobjekt und ueberschreibe die zugehoerigen Parameter
		 * entsprechend der erhaltenen Informationen.
		 */
		
		/*
		 * Ueberschreibe beim Clienten das entsprechende Spielerobjekt
		 */
		if (num == 1){
			Field.fieldNumbers[x][y]=3;
			Field.fieldNumbers[xo1][yo1]=0;
			Init.Player1.x=x;
			Init.Player1.y=y;
			Init.Player1.bCnt=bCnt;
			Init.Player1.rad=rad;
			xo1=x;
			yo1=y;
			
		} else {
			Field.fieldNumbers[x][y]=4;
			Field.fieldNumbers[xo2][yo2]=0;
			Init.Player2.x=x;
			Init.Player2.y=y;
			Init.Player2.bCnt=bCnt;
			Init.Player2.rad=rad;
			xo2=x;
			yo2=y;
		}
	}
	
	/**
	 * Aktualisiert die jeweilige Bombe und uebertraegt sie ins Bomb.bombs-Array.
	 * 
	 * @param x - X-Koordinate
	 * @param y - Y-Koordinate
	 * @param num - Bombennummer
	 * @param act - aktiv/inaktiv
	 */
	
	public static void bomb(int x, int y, int num, boolean act){

		Field.bombPos[x][y] = act;

		Bomb.bombs[num].num = num;
		Bomb.bombs[num].x=x;
		Bomb.bombs[num].y=y;
		Bomb.bombs[num].active=act;
		
		if(num == 0 && Bomb.bombs[num].active && !Carl.bomb0.isAlive()) {
			Carl.bomb0 = new Carl(Bomb.bombs[num], Init.Player1);
			Carl.bomb0.start();
		} else if (num == 1 && Bomb.bombs[num].active && !Carl.bomb1.isAlive()) {
			Carl.bomb1 = new Carl(Bomb.bombs[num], Init.Player1);
			Carl.bomb1.start();
		} else if (num == 2 && Bomb.bombs[num].active && !Carl.bomb2.isAlive()) {
			Carl.bomb2 = new Carl(Bomb.bombs[num], Init.Player1);
			Carl.bomb2.start();
		} else if (num == 3 && Bomb.bombs[num].active && !Carl.bomb3.isAlive()) {
			Carl.bomb3 = new Carl(Bomb.bombs[num], Init.Player2);
			Carl.bomb3.start();
		} else if (num == 4 && Bomb.bombs[num].active && !Carl.bomb4.isAlive()) {
			Carl.bomb4 = new Carl(Bomb.bombs[num], Init.Player2);
			Carl.bomb4.start();
		} else if (num == 5 && Bomb.bombs[num].active && !Carl.bomb5.isAlive()) {
			Carl.bomb5 = new Carl(Bomb.bombs[num], Init.Player2);
			Carl.bomb5.start();
		}			
	}
}
