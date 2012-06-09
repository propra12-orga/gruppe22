public class Movement {
	
	/**
	 * Methode wird nach einem Tastendruck aufgerufen. Es findet eine Überprüfung statt
	 * ob der jeweilige Spieler in diese richtung gehen kann, wenn ja dann wird dies
	 * in der paintComponent der Klasse Field vermerkt. Danach wird das Spielfeld neu
	 * gezeichnet.
	 * @param pl
	 */

	public static void getMovement(Player pl) {

			

			if (pl.ctrl == "Oben") {
				if (checkMove(Field.fieldNumbers[pl.x][pl.y - 1])) {
					goUp(pl.x, pl.y, pl);
					playerToField(pl);
					Field.f = new Field();
					Field.f.newPaint();
				}
			} else if (pl.ctrl == "Unten") {
				if (checkMove(Field.fieldNumbers[pl.x][pl.y + 1])) {
					goDown(pl.x, pl.y, pl);
					playerToField(pl);
					Field.f = new Field();
					Field.f.newPaint();
				}
			} else if (pl.ctrl == "Links") {
				if (checkMove(Field.fieldNumbers[pl.x - 1][pl.y])) {
					goLeft(pl.x, pl.y, pl);
					playerToField(pl);
					Field.f = new Field();
					Field.f.newPaint();
				}
			} else if (pl.ctrl == "Rechts") {
				if (checkMove(Field.fieldNumbers[pl.x + 1][pl.y])) {
					goRight(pl.x, pl.y, pl);
					playerToField(pl);
					Field.f = new Field();
					Field.f.newPaint();
				}
			} else if (pl.ctrl == "Bombe") {
				if (pl.bCnt > 0)
					new Carl(pl).start();
				
			}
		
		/* hier erweitern mit if-Bedinungen für weitere Spieler */
	}
	
	/**
	 * Diese Methode überprüft ob die Koordinaten auf die der Spieler gehen will
	 * begehbar sind oder nicht. Es wir ein boolean aufgerufen und dieser zurück
	 * gegeben. Es wir ebenfalls überprüft ob der Spieler, mit dem Schritt den er
	 *  vorhat, durch den Ausgang geht . 
	 * 
	 * @param coord
	 * @return Boolean
	 */
	public static boolean checkMove(int coord) {
		if (coord == 0)
			return true;
		else if (coord == 9) {
			Init.reset();
			GameOverPic.pic=2;
			GameOverPic.DrawPic();
		}
		return false;
	}
	/**
	 * Diese Methode kriegt, nach der Überprüfung ob der Spieler seinen Zug den er
	 * vorhat machen kann, die aktuellen Koordinaten des jeweiligen Spielers übergeben
	 * speichert diese als alte Position des Spielers ab und zieht der Y-Koordinate
	 * den Wert 1 ab (dies ist die Bewegung nach oben).  
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
	 * Diese Methode kriegt, nach der Überprüfung ob der Spieler seinen Zug den er
	 * vorhat machen kann, die aktuellen Koordinaten des jeweiligen Spielers übergeben
	 * speichert diese als alte Position des Spielers ab und addiert der Y-Koordinate
	 * den Wert 1 dazu (dies ist die Bewegung nach unten).  
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
	 * Diese Methode kriegt, nach der Überprüfung ob der Spieler seinen Zug den er
	 * vorhat machen kann, die aktuellen Koordinaten des jeweiligen Spielers übergeben
	 * speichert diese als alte Position des Spielers ab und addiert der X-Koordinate
	 * den Wert 1 dazu (dies ist die Bewegung nach rechts).  
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
	 * Diese Methode kriegt, nach der Überprüfung ob der Spieler seinen Zug den er
	 * vorhat machen kann, die aktuellen Koordinaten des jeweiligen Spielers übergeben
	 * speichert diese als alte Position des Spielers ab und zieht der X-Koordinate
	 * den Wert 1 ab (dies ist die Bewegung nach links).  
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
	 * Methode kriegt den Spieler der seine Position verändert übergeben und löscht seine
	 * Darstellung auf dem alten Feld um ihn auf dem nächsten Feld darzustellen.
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
