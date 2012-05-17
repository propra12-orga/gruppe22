import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Bomb extends JPanel {
	
	public Bomb(){
		super();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setSize(640, 480);

		g.setColor(Color.RED);
		g.fillOval(Player.player_x * (getWidth() / 21) + 15 , Player.player_y * (getHeight() / 18)+ 27, 20, 20);

	}

	
	public static void placeBomb(){
		
	}
	
	public static void detonate(){
		
	}
}
