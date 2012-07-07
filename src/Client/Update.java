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
	
	public static void player(int x, int y, int num, int bCnt, int rad, int bP){
		
		/*
		 * Initialisiere Spielerobjekt und ueberschreibe die zugehoerigen Parameter
		 * entsprechend der erhaltenen Informationen.
		 */
		
		player = new Player();
		player.x = x;
		player.y = x;
		
		player.xo = x;
		player.yo = y;
		player.num = num;
		player.bCnt = bCnt;
		player.rad = rad;
		player.ctrl ="";
		if (bP >= 1)
			player.bP = bP - 1;
		else player.bP = 0;
		
		/*
		 * Ueberschreibe beim Clienten das entsprechende Spielerobjekt
		 */
		
		if (player.num == 1){
			Init.Player1 = player;
			pl1 = true;
		} else {
			Init.Player2 = player;
			pl2 = true;
		}
		
		/*
		 * Durchlaufe das Spielfeld, suche und setze die Spieler.
		 */
		
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++){
				if (i == player.x && j == player.y && pl1)
					Field.fieldNumbers[i][j] = 3;
				else if (i == player.x && j == player.y && pl2)
					Field.fieldNumbers[i][j] = 4;
			}
		
		pl1 = false;
		pl2 = false;
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
		
		/*
		 * Festlegen, zu welchem Spieler die Bombe gehoert.
		 */
		
		if (num == 0 || num == 1 || num == 2){
			bomb = new Bomb(Init.Player1);
			pl1 = true;
		} else {
			bomb = new Bomb(Init.Player2);
			pl2 = true;
		}
		
		/*
		 * Ueberschreiben wichtige Werte des Bombenobjektes.
		 * Festlegen, ob Bombe aktiv ist oder nicht.
		 */
		bomb.x = x;
		bomb.y = y;
		bomb.num = num;
		bomb.active = act;
		
		/*
		 * Ueberpruefen, ob der Bombenthread beim Clienten zuvor schon gestartet wurde.
		 */
		
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++)
				if ((Field.bombPos[i][j] || Field.fieldNumbers[i][j] == 8) && bomb.x == i && bomb.y == j)
					bomb.isSet = true;
		
		/*
		 * Bomb.bombs-Array entsprechend ueberschreiben
		 */
		
		Bomb.bombs[num] = bomb;
		
		/*
		 * Ist Bombe aktiv und wurde noch nicht gesetzt, starte Bombenthread.
		 */
		
		if (bomb.active && !bomb.isSet && pl1)
			new Carl(bomb, Init.Player1); 
		else if (bomb.active && !bomb.isSet && pl2)
			new Carl(bomb, Init.Player2);
		
		pl1 = false;
		pl2 = false;
			
	}
}
