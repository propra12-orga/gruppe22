import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Interface implements KeyListener, ActionListener {
	static JFrame frame = new JFrame();
	static JButton single, multi, options, exit, sound, controls, controls2, backtomain,
			save, backtooptions, rndMapSingle, rndMapMulti, constMap, startGame, startGame2, backtosingle,
			continueGame, restart, backtomulti;
	static JLabel ctrlmenu, player1, player2, up1, down1, right1, left1, bomb1,
			up2, down2, right2, left2, bomb2, boxNumber;
	static JComboBox getBoxNumber;
	static JPanel menu;
	public static JPanel game = new JPanel();

	static JTextArea getUp1, getUp2, getDown1, getDown2, getRight1, getRight2,
			getLeft1, getLeft2, getBomb1, getBomb2;
	static String[] amountBoxes = {"10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
	static Player Player1 = new Player();
	static Player Player2;
	static Dimension dim = new Dimension(200, 25);

	public Interface() {

		frame.setTitle("Bomberman!");
		frame.setSize(800, 662);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		menu = new JPanel(new GridBagLayout());
		single = new JButton("Singleplayer");
		single.setPreferredSize(dim);
		single.addActionListener(this);
		multi = new JButton("Multiplayer");
		multi.setPreferredSize(dim);
		multi.addActionListener(this);
		options = new JButton("Optionen");
		options.setPreferredSize(dim);
		options.addActionListener(this);
		exit = new JButton("Beenden");
		exit.setPreferredSize(dim);
		exit.addActionListener(this);
		controls = new JButton("Steuerung");
		controls.setPreferredSize(dim);
		controls.addActionListener(this);
		controls2 = new JButton("Steuerung");
		controls2.setPreferredSize(dim);
		controls2.addActionListener(this);
		sound = new JButton("Sound");
		sound.setPreferredSize(dim);
		backtomain = new JButton("Zurueck zum Hauptmenue");
		backtomain.setPreferredSize(dim);
		backtomain.addActionListener(this);
		save = new JButton("Speichern");
		save.setPreferredSize(dim);
		save.addActionListener(this);
		backtooptions = new JButton("Zurueck");
		backtooptions.setPreferredSize(dim);
		backtooptions.addActionListener(this);
		rndMapSingle = new JButton("Zufallsmap");
		rndMapSingle.setPreferredSize(dim);
		rndMapSingle.addActionListener(this);
		rndMapMulti = new JButton("Zufallsmap");
		rndMapMulti.setPreferredSize(dim);
		rndMapMulti.addActionListener(this);
		constMap = new JButton("Map aus Datei");
		constMap.setPreferredSize(dim);
		constMap.addActionListener(this);
		startGame = new JButton("Spiel starten");
		startGame.setPreferredSize(dim);
		startGame.addActionListener(this);
		startGame2 = new JButton("Spiel starten");
		startGame2.setPreferredSize(dim);
		startGame2.addActionListener(this);
		backtosingle = new JButton("Zuruck zum Singleplayer");
		backtosingle.setPreferredSize(dim);
		backtosingle.addActionListener(this);
		continueGame = new JButton("Spiel fortsetzen");
		continueGame.setPreferredSize(dim);
		continueGame.addActionListener(this);
		restart = new JButton("Nochmal spielen?");
		restart.setPreferredSize(dim);
		restart.addActionListener(this);
		backtomulti = new JButton("Zurueck");
		backtomulti.setPreferredSize(dim);
		backtomulti.addActionListener(this);
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
		boxNumber = new JLabel("Kistenanzahl : ");
		getUp1 = new JTextArea(1, 6);
		getDown1 = new JTextArea(1, 6);
		getRight1 = new JTextArea(1, 6);
		getLeft1 = new JTextArea(1, 6);
		getBomb1 = new JTextArea(1, 6);
		getUp2 = new JTextArea(1, 6);
		getDown2 = new JTextArea(1, 6);
		getRight2 = new JTextArea(1, 6);
		getLeft2 = new JTextArea(1, 6);
		getBomb2 = new JTextArea(1, 6);
		getBoxNumber = new JComboBox(amountBoxes);

		getUp1.addKeyListener(this);
		getUp2.addKeyListener(this);
		getDown1.addKeyListener(this);
		getDown2.addKeyListener(this);
		getRight1.addKeyListener(this);
		getRight2.addKeyListener(this);
		getLeft1.addKeyListener(this);
		getLeft2.addKeyListener(this);
		getBomb1.addKeyListener(this);
		getBomb2.addKeyListener(this);

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

		game.setFocusable(true);
		game.addKeyListener(this);
		menu.setFocusable(true);
		Menue.MainMenu();
		frame.add(menu);
	}

	public static void main(String[] args) {

		Interface Menu = new Interface();

		frame.setVisible(true);

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
			Eingabe.CtrlReader();
			displayCtrl();
			Control.ControlMenu();
		} else if (e.getSource() == this.backtooptions) {
			Options.OptionsMenu();
		} else if (e.getSource() == this.single) {
			Eingabe.CtrlReader();
			GameMode.SingleMenu();
		} else if (e.getSource() == this.rndMapSingle) {
			RandomMapMenu.RandomMapSingle();
		} else if (e.getSource() == this.backtosingle) {
			GameMode.SingleMenu();
		} else if (e.getSource() == this.startGame) {
			Boxes();
			Field.basicField = Init.basicField();
			Field.fieldNumbers = Init.fieldContent(Field.basicField);
			Field.bombPos = Init.bombs();
			Field.expPos = Init.bombs();
			Game.main(null);
			closeMenuOpenGame();
		} else if (e.getSource() == this.continueGame) {
			Game.main(null);
			closeMenuOpenGame();
			Field.f.newPaint();
		} else if (e.getSource() == this.restart) {
			Game.main(null);
			closeMenuOpenGame();
			Field.f.newPaint();
		} else if (e.getSource() == this.save) {
			Eingabe.CtrlWrite();
		} else if (e.getSource() == this.constMap) {
			Field.basicField = Init.basicField();
			Field.fieldNumbers = Init.constMap();
			Field.bombPos = Init.bombs();
			Field.expPos = Init.bombs();
			Player2 = new Player();
			Player.getStartPos2(Player2);
			Game.main(null);
			closeMenuOpenGame();
		} else if (e.getSource() == this.multi) {
			GameMode.MultiMenu();
		} else if (e.getSource() == this.backtomulti) {
			GameMode.MultiMenu();
		} else if (e.getSource() == this.controls2) {
			Eingabe.CtrlReader();
			displayCtrl();
			Control.ControlMenu2();
		} else if (e.getSource() == this.startGame2) {
			Boxes();
			Field.basicField = Init.basicField();
			Field.fieldNumbers = Init.fieldContent(Field.basicField);
			Field.bombPos = Init.bombs();
			Field.expPos = Init.bombs();
			Player2 = new Player();
			Player.getStartPos2(Player2);
			Game.main(null);
			closeMenuOpenGame();
		} else if (e.getSource() == rndMapMulti) {
			RandomMapMenu.RandomMapMulti();
		}

	}
	


	private void Boxes() {
		if (getBoxNumber.getSelectedItem()== "10"){
			Init.maxKisten = 10;
		} else if (getBoxNumber.getSelectedItem()== "20"){
			Init.maxKisten = 20;
		} else if (getBoxNumber.getSelectedItem()== "30"){
			Init.maxKisten = 30;
		} else if (getBoxNumber.getSelectedItem()== "40"){
			Init.maxKisten = 40;
		} else if (getBoxNumber.getSelectedItem()== "50"){
			Init.maxKisten = 50;
		} else if (getBoxNumber.getSelectedItem()== "60"){
			Init.maxKisten = 60;
		} else if (getBoxNumber.getSelectedItem()== "70"){
			Init.maxKisten = 70;
		} else if (getBoxNumber.getSelectedItem()== "80"){
			Init.maxKisten = 80;
		} else if (getBoxNumber.getSelectedItem()== "90"){
			Init.maxKisten = 90;
		} else if (getBoxNumber.getSelectedItem()== "100"){
			Init.maxKisten = 100;
		}
		
		
	}

	public static void displayCtrl() {
		getUp1.setText(Eingabe.CtrlArray[0]);
		getDown1.setText(Eingabe.CtrlArray[1]);
		getRight1.setText(Eingabe.CtrlArray[2]);
		getLeft1.setText(Eingabe.CtrlArray[3]);
		getBomb1.setText(Eingabe.CtrlArray[4]);
		getUp2.setText(Eingabe.CtrlArray[5]);
		getDown2.setText(Eingabe.CtrlArray[6]);
		getRight2.setText(Eingabe.CtrlArray[7]);
		getLeft2.setText(Eingabe.CtrlArray[8]);
		getBomb2.setText(Eingabe.CtrlArray[9]);

	}

	public static void closeGameOpenMenu() {
		game.setVisible(false);
		menu.setVisible(true);
	}

	public static void closeMenuOpenGame() {
		menu.setVisible(false);
		game.setVisible(true);
	}

	public void keyPressed(KeyEvent e) {
		String Key = Eingabe.Ctrl(e);
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

}