import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

//nur zum Testen...

public class Game extends JFrame implements KeyListener{

	private static final long serialVersionUID = 1L;
	public static JPanel panel = new JPanel();

	public Game() {
		this.setTitle("Bomberman!");
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panel = new Field();
		
		panel.setFocusable(true); 
		panel.addKeyListener(this);
		
		this.add(panel);
		this.setVisible(true);
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
