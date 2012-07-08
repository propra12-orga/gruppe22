package Game;

import Client.Client;
import Menues.GameOverPic;

public class Movement {

	/**
	 * Methode wird nach einem Tastendruck aufgerufen. Es findet eine
	 * �berpr�fung statt ob der jeweilige Spieler in diese richtung gehen kann,
	 * wenn ja dann wird dies in der paintComponent der Klasse Field vermerkt.
	 * Danach wird das Spielfeld neu gezeichnet.
	 * 
	 * @param pl
	 */
	
	static Sound powerUp;
	public static Bomb crtBomb;

	
	public static void getMovement(Player pl) {
		
		if (pl.ctrl == "Oben") {
			if (checkMove(pl.x, pl.y - 1, pl)) {
				goUp(pl.x, pl.y, pl);
				playerToField(pl);
				
				//Netzwerk senden
				if (!Interface.offline)
					Client.Send();
			}
		} else if (pl.ctrl == "Unten") {
			if (checkMove(pl.x, pl.y + 1, pl)) {
				goDown(pl.x, pl.y, pl);
				playerToField(pl);
				
				//Netzwerk senden
				if (!Interface.offline)
					Client.Send();
			}
		} else if (pl.ctrl == "Links") {
			if (checkMove(pl.x - 1, pl.y, pl)) {
				goLeft(pl.x, pl.y, pl);
				playerToField(pl);
				
				//Netzwerk senden
				if (!Interface.offline)
					Client.Send();
			}
		} else if (pl.ctrl == "Rechts") {
			if (checkMove(pl.x + 1, pl.y, pl)) {
				goRight(pl.x, pl.y, pl);
				playerToField(pl);
				
				//Netzwerk senden
				if (!Interface.offline)
					Client.Send();
			}
		} else if (pl.ctrl == "Bombe") {
			if (pl.bCnt > 0){
				crtBomb = new Bomb(pl);
				startBombThread(pl, crtBomb);
			
			}
		}

		/* hier erweitern mit if-Bedinungen f�r weitere Spieler */
	}
	
	public static void startBombThread(Player pl, Bomb bomb){
			if (bomb.num == 0){
				Bomb.placeBomb(bomb, pl);
				Carl.bomb0 = new Carl(bomb, pl);
				Carl.bomb0.start();
			} else if (bomb.num == 1){
				Bomb.placeBomb(bomb, pl);
				Carl.bomb1 = new Carl(bomb, pl);
				Carl.bomb1.start();
			} else if (bomb.num == 2){
				Bomb.placeBomb(bomb, pl);
				Carl.bomb2 = new Carl(bomb, pl);
				Carl.bomb2.start();
			} else if (bomb.num == 3){
				Bomb.placeBomb(bomb, pl);
				Carl.bomb3 = new Carl(bomb, pl);		
				Carl.bomb3.start();
			} else if (bomb.num == 4){
				Bomb.placeBomb(bomb, pl);
				Carl.bomb4 = new Carl(bomb, pl);
				Carl.bomb4.start();
			} else if (bomb.num == 5){
				Bomb.placeBomb(bomb, pl);
				Carl.bomb5 = new Carl(bomb, pl);
				Carl.bomb5.start();
			}
			if (!Interface.offline)
				Client.Send();
	}

	/**
	 * Diese Methode �berpr�ft ob die Koordinaten auf die der Spieler gehen will
	 * begehbar sind oder nicht. Es wir ein boolean aufgerufen und dieser zur�ck
	 * gegeben. Es wir ebenfalls �berpr�ft ob der Spieler, mit dem Schritt den
	 * er vorhat, durch den Ausgang geht .
	 * 
	 * @param coord
	 * @return Boolean
	 */
	public static boolean checkMove(int x, int y, Player pl) {
		if (Field.fieldNumbers[x][y] == 0 && !Field.bombPos[x][y])
			return true;
		else if (Field.fieldNumbers[x][y] == 9) {
			Bomb.gameOver = true;
			if (Init.KI) Paul.kiThread.interrupt();
			Init.reset();
			GameOverPic.pic = 2;
			new GameOverPic();
		} else if (Field.fieldNumbers[x][y] == 8 && pl != KI.kiPl) {
			Bomb.gameOver = true;
			if (Init.KI)Paul.kiThread.interrupt();
			Init.reset();
			GameOverPic.pic = 1;
			new GameOverPic();
		} else if (Field.fieldNumbers[x][y] == 42) {
			if (pl == KI.kiPl) KI.noBomb = true;
			pl.bCnt++;
			Field.fieldNumbers[x][y] = 0;
			Init.powerUps[x][y] = 0;
			if (Interface.isSound)
				powerUp = new Sound("src/Sounds/powerUp.wav");
			return true;
		} else if (Field.fieldNumbers[x][y] == 41) {
			if (pl == KI.kiPl) KI.noBomb = true;
			pl.rad++;
			Field.fieldNumbers[x][y] = 0;
			Init.powerUps[x][y] = 0;
			if (Interface.isSound)
				powerUp = new Sound("src/Sounds/PowerUp.wav");
			return true;
		}
		return false;
	}

	/**
	 * Diese Methode kriegt, nach der �berpr�fung ob der Spieler seinen Zug den
	 * er vorhat machen kann, die aktuellen Koordinaten des jeweiligen Spielers
	 * �bergeben speichert diese als alte Position des Spielers ab und zieht der
	 * Y-Koordinate den Wert 1 ab (dies ist die Bewegung nach oben).
	 * 
	 * @param x
	 * @param y
	 * @param pl
	 */

	public static void goUp(int x, int y, Player pl) {
		pl.xo = x;
		pl.yo = y;
		pl.x = x;
		pl.y = y - 1;

	}

	/**
	 * Diese Methode kriegt, nach der �berpr�fung ob der Spieler seinen Zug den
	 * er vorhat machen kann, die aktuellen Koordinaten des jeweiligen Spielers
	 * �bergeben speichert diese als alte Position des Spielers ab und addiert
	 * der Y-Koordinate den Wert 1 dazu (dies ist die Bewegung nach unten).
	 * 
	 * @param x
	 * @param y
	 * @param pl
	 */

	public static void goDown(int x, int y, Player pl) {
		pl.xo = x;
		pl.yo = y;
		pl.x = x;
		pl.y = y + 1;

	}

	/**
	 * Diese Methode kriegt, nach der �berpr�fung ob der Spieler seinen Zug den
	 * er vorhat machen kann, die aktuellen Koordinaten des jeweiligen Spielers
	 * �bergeben speichert diese als alte Position des Spielers ab und addiert
	 * der X-Koordinate den Wert 1 dazu (dies ist die Bewegung nach rechts).
	 * 
	 * @param x
	 * @param y
	 * @param pl
	 */

	public static void goRight(int x, int y, Player pl) {
		pl.xo = x;
		pl.yo = y;
		pl.x = x + 1;
		pl.y = y;

	}

	/**
	 * Diese Methode kriegt, nach der �berpr�fung ob der Spieler seinen Zug den
	 * er vorhat machen kann, die aktuellen Koordinaten des jeweiligen Spielers
	 * �bergeben speichert diese als alte Position des Spielers ab und zieht der
	 * X-Koordinate den Wert 1 ab (dies ist die Bewegung nach links).
	 * 
	 * @param x
	 * @param y
	 * @param pl
	 */

	public static void goLeft(int x, int y, Player pl) {
		pl.xo = x;
		pl.yo = y;
		pl.x = x - 1;
		pl.y = y;

	}

	/**
	 * Methode kriegt den Spieler der seine Position ver�ndert �bergeben und
	 * l�scht seine Darstellung auf dem alten Feld um ihn auf dem n�chsten Feld
	 * darzustellen.
	 * 
	 * @param pl
	 */

	public static void playerToField(Player pl) {
		Field.fieldNumbers[pl.xo][pl.yo] = 0;
		if (pl.num == 1)
			Field.fieldNumbers[pl.x][pl.y] = 3;
		else
			Field.fieldNumbers[pl.x][pl.y] = 4;
	}

}
