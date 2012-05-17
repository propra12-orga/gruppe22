import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Bomb extends JPanel {
	
	public static void placeBomb(){
		Field.isBomb = true;
		Field.x = Player.player_x;
		Field.y = Player.player_y;
		Field.f = new Field();
		Field.f.newPaint();
	}
	
	public static void detonate(){
		
	}
}
