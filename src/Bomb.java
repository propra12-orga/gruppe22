import javax.swing.JPanel;

public class Bomb extends JPanel {

	static Boolean ob;
	static Boolean ub;
	static Boolean lb;
	static Boolean rb;

	static int o;
	static int u;
	static int l;
	static int r;
	int x, y;
	
	public Bomb(Player player){
		this.x = player.x;
		this.y = player.y;
	}

	public static void placeBomb(int x, int y) {
		Field.bombPos[x][y] = true;
		Field.f = new Field();
		Field.f.newPaint();
	}

	public static void detonate(int x, int y) {

		for (int gor = 1; gor <= r; gor++) {
			isGameOver(Field.fieldNumbers[x + gor][y]);
			Field.fieldNumbers[x + gor][y] = 8;
		}
		for (int gol = 1; gol <= l; gol++) {
			isGameOver(Field.fieldNumbers[x - gol][y]);
			Field.fieldNumbers[x - gol][y] = 8;
		}
		for (int goo = 1; goo <= o; goo++) {
			isGameOver(Field.fieldNumbers[x][y - goo]);
			Field.fieldNumbers[x][y - goo] = 8;
		}
		for (int gou = 1; gou <= u; gou++) {
			isGameOver(Field.fieldNumbers[x][y + gou]);
			Field.fieldNumbers[x][y + gou] = 8;
		}
		isGameOver(Field.fieldNumbers[x][y]);
		Field.fieldNumbers[x][y] = 8;
		Field.bombPos[x][y] = false;
	}


	public static void endDetonation(int x, int y) {
				
		for (int gor = 1; gor <= r; gor++) {
			Field.fieldNumbers[x + gor][y] = 0;
		}
		for (int gol = 1; gol <= l; gol++) {
			Field.fieldNumbers[x - gol][y] = 0;
		}
		for (int goo = 1; goo <= o; goo++) {
			Field.fieldNumbers[x][y - goo] = 0;
		}
		for (int gou = 1; gou <= u; gou++) {
			Field.fieldNumbers[x][y + gou] = 0;
		}
		isGameOver(Field.fieldNumbers[x][y]);
		Field.fieldNumbers[x][y] = 0;

	}

	public static boolean isDestructable(int coord) {
		if (coord == 1) {
			return false;
		} else {
			return true;
		}
	}

	public static void isGameOver(int coord) {
		if (coord == 3 || coord == 4) {
			Init.reset();
			Interface.closeGameOpenMenu();
			Menue.MainMenu();
		}
	}

	public static void radCheck(Player crtP) {
		ob = true;
		ub = true;
		lb = true;
		rb = true;

		int x = crtP.x;
		int y = crtP.y;

		r = 0;
		l = 0;
		o = 0;
		u = 0;

		for (int i = 0; i <= crtP.rad; i++) {
			/* nach rechts überprüfen */
			if (rb) {
				r = i;
				if (Field.fieldNumbers[x + i][y] == 2) {
					rb = false;
				}
				if (!isDestructable(Field.fieldNumbers[x + (i + 1)][y])) {
					rb = false;
				}
			}

			/* nach links überprüfen */
			if (lb) {
				l = i;
				if (Field.fieldNumbers[x - i][y] == 2) {
					lb = false;
				}
				if (!isDestructable(Field.fieldNumbers[x - (i + 1)][y])) {
					lb = false;
				}
			}
			/* nach unten überprüfen */
			if (ub) {
				u = i;
				if (Field.fieldNumbers[x][y + i] == 2) {
					ub = false;
				}
				if (!isDestructable(Field.fieldNumbers[x][y + (i + 1)])) {
					ub = false;
				}
			}
			/* nach oben überprüfen */
			if (ob) {
				o = i;
				if (Field.fieldNumbers[x][y - i] == 2) {
					ob = false;
				}
				if (!isDestructable(Field.fieldNumbers[x][y - (i + 1)])) {
					ob = false;
				}
			}
		}
	}
}
