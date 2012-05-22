import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface extends JFrame implements KeyListener, ActionListener {
	static JButton single, multi, options, exit, sound, controls, backtomain,
			save, backtooptions;
	static JLabel ctrlmenu, player1, player2, up1, down1, right1, left1, bomb1,
			up2, down2, right2, left2, bomb2;
	static JTextField getUp1, getUp2, getDown1, getDown2, getRight1, getRight2,
			getLeft1, getLeft2, getBomb1, getBomb2;
	static JPanel panel, game;

	public Interface() {

		this.setTitle("Bomberman!");
		this.setSize(800, 662);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		panel = new JPanel(new GridBagLayout());
		game = new JPanel();
		game.setFocusable(true);
		game.addKeyListener(this);
		single = new JButton("Singleplayer");
		single.addActionListener(this);
		multi = new JButton("  Multiplayer ");
		multi.addActionListener(this);
		options = new JButton("   Optionen    ");
		options.addActionListener(this);
		exit = new JButton("    Beenden    ");
		exit.addActionListener(this);
		controls = new JButton("               Steuerung               ");
		controls.addActionListener(this);
		sound = new JButton("                   Sound                   ");
		backtomain = new JButton("Zurueck zum Hauptmenue");
		backtomain.addActionListener(this);
		save = new JButton("Speichern");
		save.addActionListener(this);
		backtooptions = new JButton("Zurueck");
		backtooptions.addActionListener(this);
		ctrlmenu = new JLabel("Steuerung");
		player1 = new JLabel("Spieler 1");
		player2 = new JLabel("Spieler 2");
		up1 = new JLabel("Oben :");
		down1 = new JLabel("Unten :");
		right1 = new JLabel("Rechts :");
		left1 = new JLabel("Links :");
		bomb1 = new JLabel("Bombe legen :");
		up2 = new JLabel("Oben :");
		down2 = new JLabel("Unten :");
		right2 = new JLabel("Rechts :");
		left2 = new JLabel("Links :");
		bomb2 = new JLabel("Bombe legen :");
		getUp1 = new JTextField(6);
		getDown1 = new JTextField(6);
		getRight1 = new JTextField(6);
		getLeft1 = new JTextField(6);
		getBomb1 = new JTextField(6);
		getUp2 = new JTextField(6);
		getDown2 = new JTextField(6);
		getRight2 = new JTextField(6);
		getLeft2 = new JTextField(6);
		getBomb2 = new JTextField(6);

		/* Sp�ter rausnehmen */
		getUp1.setEditable(false);
		getUp2.setEditable(false);
		getDown1.setEditable(false);
		getDown2.setEditable(false);
		getRight1.setEditable(false);
		getRight2.setEditable(false);
		getLeft1.setEditable(false);
		getLeft2.setEditable(false);
		getBomb1.setEditable(false);
		getBomb2.setEditable(false);

		panel.setFocusable(true);
		panel.addKeyListener(this);
		Menue.MainMenu();
		this.add(panel);
	}

	public static void main(String[] args) {

		Eingabe.CtrlReader();

		Interface Menu = new Interface();

		Menu.setVisible(true);
		displayCtrl();

	}

	public void actionPerformed(ActionEvent e) {
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		if (e.getSource() == this.exit) {
			System.exit(0);
		} else if (e.getSource() == this.options) {
			Options.OptionsMenu();
		} else if (e.getSource() == this.backtomain) {
			Menue.MainMenu();
		} else if (e.getSource() == this.controls) {
			Control.ControlMenu();
		} else if (e.getSource() == this.backtooptions) {
			Options.OptionsMenu();
		} else if (e.getSource() == this.single) {
			panel.setVisible(false);
			Game.main(null);

		}

	}

	public static void displayCtrl() {
		getUp1.setText(Eingabe.CtrlArray[0]);
		getDown1.setText(Eingabe.CtrlArray[1]);
		getRight1.setText(Eingabe.CtrlArray[2]);
		getLeft1.setText(Eingabe.CtrlArray[3]);
		getBomb1.setText(Eingabe.CtrlArray[4]);

	}

	public void keyPressed(KeyEvent e) {
		// String Key = Eingabe.Ctrl(e);
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

}