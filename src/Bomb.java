import javax.swing.JPanel;

public class Bomb extends JPanel {

	Boolean ob, ub, lb, rb;
	int x, y, o, u, l, r;
	static int paintO, paintU, paintL, paintR;
	
	public Bomb(Player player){
		this.x = player.x;
		this.y = player.y;
		this.o = 0;
		this.u = 0;
		this.l = 0;
		this.r = 0;
		this.ob = true;
		this.ub = true;
		this.lb = true;
		this.rb = true;
	}

	public static void placeBomb(int x, int y) {
		Field.bombPos[x][y] = true;
		Field.f = new Field(null);
		Field.f.newPaint();
	}

	public static void detonate(Bomb bomb) {

		for (int gor = 1; gor <= bomb.r; gor++) {
			isGameOver(Field.fieldNumbers[bomb.x + gor][bomb.y]);
			Field.fieldNumbers[bomb.x + gor][bomb.y] = 8;
		}
		for (int gol = 1; gol <= bomb.l; gol++) {
			isGameOver(Field.fieldNumbers[bomb.x - gol][bomb.y]);
			Field.fieldNumbers[bomb.x - gol][bomb.y] = 8;
		}
		for (int goo = 1; goo <= bomb.o; goo++) {
			isGameOver(Field.fieldNumbers[bomb.x][bomb.y - goo]);
			Field.fieldNumbers[bomb.x][bomb.y - goo] = 8;
		}
		for (int gou = 1; gou <= bomb.u; gou++) {
			isGameOver(Field.fieldNumbers[bomb.x][bomb.y + gou]);
			Field.fieldNumbers[bomb.x][bomb.y + gou] = 8;
		}
		isGameOver(Field.fieldNumbers[bomb.x][bomb.y]);
		Field.fieldNumbers[bomb.x][bomb.y] = 8;
		Field.bombPos[bomb.x][bomb.y] = false;
	}


	public static void endDetonation(Bomb bomb) {
				
		for (int gor = 1; gor <= bomb.r; gor++) {
			Field.fieldNumbers[bomb.x + gor][bomb.y] = 0;
		}
		for (int gol = 1; gol <= bomb.l; gol++) {
			Field.fieldNumbers[bomb.x - gol][bomb.y] = 0;
		}
		for (int goo = 1; goo <= bomb.o; goo++) {
			Field.fieldNumbers[bomb.x][bomb.y - goo] = 0;
		}
		for (int gou = 1; gou <= bomb.u; gou++) {
			Field.fieldNumbers[bomb.x][bomb.y + gou] = 0;
		}
		isGameOver(Field.fieldNumbers[bomb.x][bomb.y]);
		Field.fieldNumbers[bomb.x][bomb.y] = 0;

	}

	public static boolean isDestructable(int coord) {
		if (coord == 1) {
			return false;
		} else {
			return true;
		}
	}

	public static void isGameOver(int coord) {
		if (coord == 3) {
			Init.reset();
			Interface.closeGameOpenMenu();
			Menue.MainMenu();
		}
	}
	
	public static void getRad(Bomb bomb){
		paintO = bomb.o;
		paintU = bomb.u;
		paintL = bomb.l;
		paintR = bomb.r;
	}

	public static void radCheck(Bomb bomb, int br) {
		bomb.ob = true;
		bomb.ub = true;
		bomb.lb = true;
		bomb.rb = true;

		int x = bomb.x;
		int y = bomb.y;

		bomb.r = 0;
		bomb.l = 0;
		bomb.o = 0;
		bomb.u = 0;

		for (int i = 0; i <= br; i++) {
			/* nach rechts überprüfen */
			if (bomb.rb) {
				bomb.r = i;
				if (Field.fieldNumbers[x + i][y] == 2) {
					bomb.rb = false;
				}
				if (!isDestructable(Field.fieldNumbers[x + (i + 1)][y])) {
					bomb.rb = false;
				}
			}

			/* nach links überprüfen */
			if (bomb.lb) {
				bomb.l = i;
				if (Field.fieldNumbers[x - i][y] == 2) {
					bomb.lb = false;
				}
				if (!isDestructable(Field.fieldNumbers[x - (i + 1)][y])) {
					bomb.lb = false;
				}
			}
			/* nach unten überprüfen */
			if (bomb.ub) {
				bomb.u = i;
				if (Field.fieldNumbers[x][y + i] == 2) {
					bomb.ub = false;
				}
				if (!isDestructable(Field.fieldNumbers[x][y + (i + 1)])) {
					bomb.ub = false;
				}
			}
			/* nach oben überprüfen */
			if (bomb.ob) {
				bomb.o = i;
				if (Field.fieldNumbers[x][y - i] == 2) {
					bomb.ob = false;
				}
				if (!isDestructable(Field.fieldNumbers[x][y - (i + 1)])) {
					bomb.ob = false;
				}
			}
		}
	}
}
