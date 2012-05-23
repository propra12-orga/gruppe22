import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

//nur zum Testen...

public class Game implements KeyListener{

	private static final long serialVersionUID = 1L;

	public Game() {
		
		Interface.game = new Field();
		
		Interface.game.setFocusable(true); 
		Interface.game.addKeyListener(this);
		
		Interface.frame.add(Interface.game);
		
		Interface.game.setVisible(true);
	}

	public static void main(String[] args) {

		Game fenster = new Game();
	}

	public void keyPressed(KeyEvent e) {
		String Key = Eingabe.Ctrl(e);	
	}

	public void keyReleased(KeyEvent e) {
		
		
	}

	
	public void keyTyped(KeyEvent e) {
		
		
	}
}
