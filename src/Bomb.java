import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Bomb extends JPanel {
	
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
		
		isGameOver(Field.fieldNumbers[x + 1][y]);
		isGameOver(Field.fieldNumbers[x - 1][y]);
		isGameOver(Field.fieldNumbers[x][y + 1]);
		isGameOver(Field.fieldNumbers[x][y - 1]);
		isGameOver(Field.fieldNumbers[x][y]);
		
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
}
