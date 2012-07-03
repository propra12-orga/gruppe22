public class Movement {

	/**
	 * Methode wird nach einem Tastendruck aufgerufen. Es findet eine
	 * �berpr�fung statt ob der jeweilige Spieler in diese richtung gehen kann,
	 * wenn ja dann wird dies in der paintComponent der Klasse Field vermerkt.
	 * Danach wird das Spielfeld neu gezeichnet.
	 * 
	 * @param pl
	 */

	public static void getMovement(Player pl) {

		if (pl.ctrl == "Oben") {
			if (checkMove(pl.x, pl.y - 1, pl)) {
				goUp(pl.x, pl.y, pl);
				playerToField(pl);
				
				//Netzwerk senden
			}
		} else if (pl.ctrl == "Unten") {
			if (checkMove(pl.x, pl.y + 1, pl)) {
				goDown(pl.x, pl.y, pl);
				playerToField(pl);
				
				//Netzwerk senden
			}
		} else if (pl.ctrl == "Links") {
			if (checkMove(pl.x - 1, pl.y, pl)) {
				goLeft(pl.x, pl.y, pl);
				playerToField(pl);
				
				//Netzwerk senden
			}
		} else if (pl.ctrl == "Rechts") {
			if (checkMove(pl.x + 1, pl.y, pl)) {
				goRight(pl.x, pl.y, pl);
				playerToField(pl);
				
				//Netzwerk senden
			}
		} else if (pl.ctrl == "Bombe") {
			if (pl.bCnt > 0)
				new Carl(pl).start();

		}

		/* hier erweitern mit if-Bedinungen f�r weitere Spieler */
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
			Paul.kiThread.interrupt();
			Init.reset();
			GameOverPic.pic = 2;
			GameOverPic.DrawPic();
		} else if (Field.fieldNumbers[x][y] == 8 && pl != KI.kiPl) {
			Bomb.gameOver = true;
			Paul.kiThread.interrupt();
			Init.reset();
			GameOverPic.pic = 1;
			GameOverPic.DrawPic();
		} else if (Field.fieldNumbers[x][y] == 42) {
			if (pl == KI.kiPl) KI.noBomb = true;
			pl.bCnt++;
			Field.fieldNumbers[x][y] = 0;
			Init.powerUps[x][y] = 0;
			return true;
		} else if (Field.fieldNumbers[x][y] == 41) {
			if (pl == KI.kiPl) KI.noBomb = true;
			pl.rad++;
			Field.fieldNumbers[x][y] = 0;
			Init.powerUps[x][y] = 0;
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
