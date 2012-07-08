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
		
		/*
		 * Festlegen, zu welchem Spieler die Bombe gehoert.
		 */
		Field.bombPos[x][y] = act;
		if (!act){
			Bomb.bombs[num].isSet = false;

		}
	
		
		if(num <3){
			if (act && !Bomb.bombs[num].isSet){
				Bomb.bombs[num].x=x;
				Bomb.bombs[num].y=y;
				Bomb.bombs[num].active=true;
				new Carl(Bomb.bombs[num], Init.Player1).start(); 
				Bomb.bombs[num].isSet=true;
			}
				
		}else{
			if (act && !Bomb.bombs[num].isSet){
				Bomb.bombs[num].x=x;
				Bomb.bombs[num].y=y;
				Bomb.bombs[num].active=true;
				new Carl(Bomb.bombs[num], Init.Player2).start(); 
				Bomb.bombs[num].isSet=true;
			}
			
		}
		
		
		
		
//		if (num == 0 || num == 1 || num == 2){
//			bomb = new Bomb(Init.Player1);
//			pl1 = true;
//		} else {
//			bomb = new Bomb(Init.Player2);
//			pl2 = true;
//		}
//		
//		/*
//		 * Ueberschreiben wichtige Werte des Bombenobjektes.
//		 * Festlegen, ob Bombe aktiv ist oder nicht.
//		 */
//		bomb.x = x;
//		bomb.y = y;
//		bomb.num = num;
//		bomb.active = act;
//		
//		if (bomb.active){
//			Field.bombPos[bomb.x][bomb.y] = true;
//		}
		/*
		 * Ueberpruefen, ob der Bombenthread beim Clienten zuvor schon gestartet wurde.
		 */
		
//		for (int i = 0; i < 21; i++)
//			for (int j = 0; j < 17; j++)
//				if ((Field.bombPos[i][j] || Field.fieldNumbers[i][j] == 8) && bomb.x == i && bomb.y == j)
//					bomb.isSet = true;
//		
//		/*
//		 * Bomb.bombs-Array entsprechend ueberschreiben
//		 */
//		
//		Bomb.bombs[num] = bomb;
//		
//		/*
//		 * Ist Bombe aktiv und wurde noch nicht gesetzt, starte Bombenthread.
//		 */
//		
//		if (bomb.active && !bomb.isSet && pl1)
//			new Carl(bomb, Init.Player1); 
//		else if (bomb.active && !bomb.isSet && pl2)
//			new Carl(bomb, Init.Player2);
//		
//		pl1 = false;
//		pl2 = false;
			
	}
}
