import java.awt.Color;
import java.awt.Graphics;

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
	
	public static void placeBomb() {
		Field.isBomb = true;
		Field.x = Interface.Player1.x;
		Field.y = Interface.Player1.y;
		Field.f = new Field();
		Field.f.newPaint();
	}
	
	public static void detonate() {
		int x = Field.x;
		int y = Field.y;
		
		for (int gor = 1; gor <= r; gor++) {
			isGameOver(Field.fieldNumbers[x + gor][y]);
		}
		for (int gol = 1; gol <= l; gol++) {
			isGameOver(Field.fieldNumbers[x - gol][y]);
		}
		for (int goo = 1; goo <= o; goo++) {
			isGameOver(Field.fieldNumbers[x][y - goo]);
		}
		for (int gou = 1; gou <= r; gou++) {
			isGameOver(Field.fieldNumbers[x][y + gou]);
		}
		
		Field.fieldNumbers[x][y] = 8;
		if (isDestructable(Field.fieldNumbers[x + 1][y])) Field.fieldNumbers[x + 1][y] = 8;
		if (isDestructable(Field.fieldNumbers[x - 1][y])) Field.fieldNumbers[x - 1][y] = 8;
		if (isDestructable(Field.fieldNumbers[x][y + 1])) Field.fieldNumbers[x][y + 1] = 8;
		if (isDestructable(Field.fieldNumbers[x][y - 1])) Field.fieldNumbers[x][y - 1] = 8;
	}
	
	public static void endDetonation(){
		int x = Field.x;
		int y = Field.y;
		
		Field.fieldNumbers[x][y] = 0;
		if (isDestructable(Field.fieldNumbers[x + 1][y])) Field.fieldNumbers[x + 1][y] = 0;
		if (isDestructable(Field.fieldNumbers[x - 1][y])) Field.fieldNumbers[x - 1][y] = 0;
		if (isDestructable(Field.fieldNumbers[x][y + 1])) Field.fieldNumbers[x][y + 1] = 0;
		if (isDestructable(Field.fieldNumbers[x][y - 1])) Field.fieldNumbers[x][y - 1] = 0;
	}
	
	public static boolean isDestructable(int coord) {
		if (coord == 1)
			return false;
		else
			return true;
	}
	
	public static void isGameOver(int coord){
		if (coord == 3) {
			Init.reset();
			Interface.closeGameOpenMenu();
			GameEnd.end();
		}
	}
	
	public static void radCheck(Player crtP) {
		ob = true;
		ub = true;
		lb = true;
		rb = true;

		int x = crtP.x;
		int y = crtP.y;

		for (int i = 1; i <= crtP.rad; i++) {
			/* nach rechts überprüfen */
			if (isDestructable(Field.fieldNumbers[x + i][y]) && rb) {
				r = i;
				if (Field.fieldNumbers[x + 1][y] == 2) {
					rb = false;
					// System.out.println("Kiste ist rechts nach " + r +
					// " stellen erreicht, Boolean rb = " + rb);
				}
			}
			/* nach links überprüfen */
			if (isDestructable(Field.fieldNumbers[x - i][y]) && lb) {
				l = i;
				if (Field.fieldNumbers[x - 1][y] == 2) {
					lb = false;
					// System.out.println("Kiste ist links nach " + l +
					// " stellen erreicht, Boolean lb = " + lb);
				}
			}
			/* nach unten überprüfen */
			if (isDestructable(Field.fieldNumbers[x][y + 1]) && ub) {
				u = i;
				if (Field.fieldNumbers[x][y + 1] == 2) {
					ub = false;
					// System.out.println("Kiste ist unten nach " + u +
					// " stellen erreicht, Boolean ub = " + ub);
				}
			}
			/* nach oben überprüfen */
			if (isDestructable(Field.fieldNumbers[x][y - i]) && ob) {
				o = i;
				if (Field.fieldNumbers[x][y - i] == 2) {
					ob = false;
					// System.out.println("Kiste ist oben nach " + o +
					// " stellen erreicht, Boolean ob = " + ob);
				}
			}

		}

	}
}
