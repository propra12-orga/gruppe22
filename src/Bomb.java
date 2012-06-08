import javax.swing.JPanel;

public class Bomb extends JPanel {

	Boolean ob, ub, lb, rb, det;
	int x, y, o, u, l, r, num;
	static Bomb[] bombs = Init.bombs();
	
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
		this.det = false;
		this.num = 0;
	}

	public static void placeBomb(Bomb bomb, Player player) {
		Field.bombPos[bomb.x][bomb.y] = true;
		Field.f = new Field();
		Field.f.newPaint();
		
		if (Init.MP){
			if (player == Init.Player1){
				bombs[player.bP] = bomb;
			}
			else
				bombs[player.bP + 3] = bomb;
		}
		else
			bombs[player.bP] = bomb;
		
		bomb.num = player.bP;
		player.bP += 1;
	}

	public static void detonate(Bomb bomb, Player player) {

		bomb.det = true;
		bombs[bomb.num] = bomb;
		for (int gor = 1; gor <= bomb.r; gor++) {
			isGameOver(Field.fieldNumbers[bomb.x + gor][bomb.y]);
			Field.fieldNumbers[bomb.x + gor][bomb.y] = 8;
			if (Field.bombPos[bomb.x + gor][bomb.y]){
				Field.expPos[bomb.x + gor][bomb.y] = true;
			}
		}
		for (int gol = 1; gol <= bomb.l; gol++) {
			isGameOver(Field.fieldNumbers[bomb.x - gol][bomb.y]);
			Field.fieldNumbers[bomb.x - gol][bomb.y] = 8;
			if (Field.bombPos[bomb.x + gol][bomb.y]){
				Field.expPos[bomb.x + gol][bomb.y] = true;
			}
		}
		for (int goo = 1; goo <= bomb.o; goo++) {
			isGameOver(Field.fieldNumbers[bomb.x][bomb.y - goo]);
			Field.fieldNumbers[bomb.x][bomb.y - goo] = 8;
			if (Field.bombPos[bomb.x][bomb.y - goo]){
				Field.expPos[bomb.x][bomb.y - goo] = true;
			}
		}
		for (int gou = 1; gou <= bomb.u; gou++) {
			isGameOver(Field.fieldNumbers[bomb.x][bomb.y + gou]);
			Field.fieldNumbers[bomb.x][bomb.y + gou] = 8;
			if (Field.bombPos[bomb.x][bomb.y + gou]){
				Field.expPos[bomb.x][bomb.y + gou] = true;
			}
		}
		isGameOver(Field.fieldNumbers[bomb.x][bomb.y]);
		Field.fieldNumbers[bomb.x][bomb.y] = 8;
		Field.bombPos[bomb.x][bomb.y] = false;
		player.bP -= 1;
	}

	

	public static void endDetonation(Bomb bomb) {
				
		bomb.det = false;
		bombs[bomb.num] = bomb;
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
		
		if (Field.fieldNumbers[Init.ex][Init.ey] == 0) {
			Field.fieldNumbers[Init.ex][Init.ey] = 9;
		}

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
			System.out.println("Spieler 2 siegt");
			Init.reset();
			Interface.closeGameOpenMenu();
			Menue.MainMenu();
		} else 		if (coord == 4) {
			System.out.println("Spieler 1 siegt");
			Init.reset();
			Interface.closeGameOpenMenu();
			Menue.MainMenu();
		}
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
