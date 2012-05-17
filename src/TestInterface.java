import javax.swing.JFrame;
import javax.swing.JPanel;

//nur zum Testen

public class TestInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	public static JPanel panel = new JPanel();

	public TestInterface() {
		this.setTitle("Bomberman!");
		this.setSize(640, 480);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new Field();
		this.add(panel);
		this.setVisible(true);
	}

	public static void main(String[] args) {

		TestInterface fenster = new TestInterface();
	}
}
