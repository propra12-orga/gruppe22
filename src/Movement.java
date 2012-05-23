public class Movement {

	public static void getMovement(Paul Player) {

		if (Player.playerNumber == 1) {

			if (Player.ctrl == "Oben") {
				if (checkMove(Field.fieldNumbers[Interface.Player1.x][Interface.Player1.y - 1])) {
					goUp(Interface.Player1.x, Interface.Player1.y);
					playerToField();
					Field.f = new Field();
					Field.f.newPaint();
				}
			} else if (Player.ctrl == "Unten") {
				if (checkMove(Field.fieldNumbers[Interface.Player1.x][Interface.Player1.y + 1])) {
					goDown(Interface.Player1.x, Interface.Player1.y);
					playerToField();
					Field.f = new Field();
					Field.f.newPaint();
				}
			} else if (Player.ctrl == "Links") {
				if (checkMove(Field.fieldNumbers[Interface.Player1.x - 1][Interface.Player1.y])) {
					goLeft(Interface.Player1.x, Interface.Player1.y);
					playerToField();
					Field.f = new Field();
					Field.f.newPaint();
				}
			} else if (Player.ctrl == "Rechts") {
				if (checkMove(Field.fieldNumbers[Interface.Player1.x + 1][Interface.Player1.y])) {
					goRight(Interface.Player1.x, Interface.Player1.y);
					playerToField();
					Field.f = new Field();
					Field.f.newPaint();
				}
			} else if (Player.ctrl == "Bombe") {
				if (Interface.Player1.bCnt > 0)
					new Carl("Bomb").start();

			} else if (Player.ctrl == "Esc") {
				Init.reset();
				Interface.closeGameOpenMenu();
				IngameMenu.ingame();
				
			}
		}
		/* hier erweitern mit if-Bedinungen für weitere Spieler */
	}

	public static boolean checkMove(int coord) {
		if (coord == 0)
			return true;
		else if (coord == 9) {
			Init.reset();
			Interface.closeGameOpenMenu();
			GameEnd.end();
		}
		return false;
	}

	public static void goUp(int x, int y) {
		Interface.Player1.xo = x;
		Interface.Player1.yo = y;
		Interface.Player1.x = x;
		Interface.Player1.y = y - 1;

	}

	public static void goDown(int x, int y) {
		Interface.Player1.xo = x;
		Interface.Player1.yo = y;
		Interface.Player1.x = x;
		Interface.Player1.y = y + 1;

	}

	public static void goRight(int x, int y) {
		Interface.Player1.xo = x;
		Interface.Player1.yo = y;
		Interface.Player1.x = x + 1;
		Interface.Player1.y = y;

	}

	public static void goLeft(int x, int y) {
		Interface.Player1.xo = x;
		Interface.Player1.yo = y;
		Interface.Player1.x = x - 1;
		Interface.Player1.y = y;

	}

	public static void playerToField() {
		Field.fieldNumbers[Interface.Player1.xo][Interface.Player1.yo] = 0;
		Field.fieldNumbers[Interface.Player1.x][Interface.Player1.y] = 3;
	}

}
