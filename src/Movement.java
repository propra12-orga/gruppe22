public class Movement {

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
				Bomb.radCheck(pl);
			}
		
		/* hier erweitern mit if-Bedinungen für weitere Spieler */
	}

	public static boolean checkMove(int coord) {
		if (coord == 0)
			return true;
		else if (coord == 9) {
			Init.reset();
			Interface.closeGameOpenMenu();
			Menue.MainMenu();
		}
		return false;
	}

	public static void goUp(int x, int y, Player pl) {
		pl.xo = x;
		pl.yo = y;
		pl.x = x;
		pl.y = y - 1;

	}

	public static void goDown(int x, int y, Player pl) {
		pl.xo = x;
		pl.yo = y;
		pl.x = x;
		pl.y = y + 1;

	}

	public static void goRight(int x, int y, Player pl) {
		pl.xo = x;
		pl.yo = y;
		pl.x = x + 1;
		pl.y = y;

	}

	public static void goLeft(int x, int y, Player pl) {
		pl.xo = x;
		pl.yo = y;
		pl.x = x - 1;
		pl.y = y;

	}

	public static void playerToField(Player pl) {
		Field.fieldNumbers[pl.xo][pl.yo] = 0;
		Field.fieldNumbers[pl.x][pl.y] = 3;
	}

}
