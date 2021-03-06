package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetAddress;

import javax.swing.*;

import Menues.Control;
import Menues.GameMode;
import Menues.GameOverPic;
import Menues.IngameMenu;
import Menues.InitComponents;
import Menues.Menue;
import Menues.OnlineMenu;
import Menues.Options;
import Menues.RandomMapMenu;
import Menues.SoundMenu;
import Client.Client;


/**
 * Bomberdroid Startklasse
 * 
 * @author Bastian Siefen
 * 
 */
public class Interface implements KeyListener, ActionListener {
	public static JFrame frame = new JFrame();
	public static JButton join, soundOn, soundOff, online,single, multi, options, exit, sound, controls, controls2,
			backtomain, save, backtooptions, rndMapSingle, rndMapMulti,
			constMap, startGame, startGame2, backtosingle, continueGame,
			restart, backtomulti, saveGame;
	public static JLabel ipLabel, ctrlmenu, player1, player2, up1, down1, right1, left1, bomb1,
			up2, down2, right2, left2, bomb2, boxNumber, saveAs, saved, wrongName;
	public static boolean isPause, isWrong = false;
	public static boolean isSound = true;
	public static JComboBox getBoxNumber;
	public static JPanel menu;
	public static JPanel game = new JPanel();
	public static JPanel endPic = new JPanel() {

		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {

			g.drawImage(GameOverPic.endGame, 0, 0, null);
		}
	};

	public static JTextArea getUp1, getUp2, getDown1, getDown2, getRight1, getRight2,
			getLeft1, getLeft2, getBomb1, getBomb2;
	public static JTextField saveName, getIp;
	public static String[] amountBoxes = { "10", "20", "30", "40", "50", "60", "70",
			"80", "90", "100" };
	public static Dimension dim = new Dimension(200, 25);
	public static Image img = new ImageIcon("src/Pictures/menubg.jpg").getImage();
	public static Image Button = new ImageIcon("src/Pictures/button.png").getImage();
	public static boolean offline, ctrlP2, ctrlP1;
	public static String mapName;
	String Key;
	/**
	 * Konstruktor initialisiert alle Widgets, addet KeyListener und
	 * ActionListener, ruft Menue.MainMenu() auf
	 */
	public Interface() {

		/* Frame initialisieren */
		frame.setTitle("BomberDroid!");
		frame.setSize(800, 674);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		menu = new JPanel(new GridBagLayout());
		join = new JButton("Einem Spiel beitreten");
		soundOn = new JButton("Sound an");
		soundOff = new JButton("Sound aus");
		online = new JButton("Online");
		single = new JButton("Singleplayer");
		multi = new JButton("Multiplayer");
		options = new JButton("Optionen");
		exit = new JButton("Beenden");
		controls = new JButton("Steuerung");
		controls2 = new JButton("Steuerung");
		backtosingle = new JButton("Zuruck zum Singleplayer");
		backtomulti = new JButton("Zurueck");
		rndMapSingle = new JButton("Zufallsmap");
		constMap = new JButton("Laden");
		startGame = new JButton("Spiel starten");
		saveGame = new JButton("Speichern");
		rndMapMulti = new JButton("Zufallsmap");
		startGame2 = new JButton("Spiel starten");
		continueGame = new JButton("Spiel fortsetzen");
		restart = new JButton("Nochmal spielen?");
		getBoxNumber = new JComboBox(Interface.amountBoxes);
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
		saveName = new JTextField();
		getIp = new JTextField(20);
		ipLabel = new JLabel("IP-Adresse eingeben");
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
		saveAs = new JLabel("Name fuer Spielstand festlegen");
		saved = new JLabel("'" + mapName + ".txt' gespeichert" ); 
		wrongName = new JLabel("Unzulaessiger Dateiname: Bitte nur a-z, 0-9, -, _");
		backtooptions = new JButton("Zurueck");
		save = new JButton("Speichern");
		backtomain = new JButton("Zurueck zum Hauptmenue");
		sound = new JButton("Sound");
		controls = new JButton("Steuerung");
		InitComponents.MainMenuComponents();
		InitComponents.SingleComponents();
		InitComponents.MultiComponents();
		InitComponents.OptionsComponents();
		InitComponents.IngameMenuComponents();
		join.addActionListener(this);
		soundOn.addActionListener(this);
		soundOff.addActionListener(this);
		online.addActionListener(this);
		single.addActionListener(this);
		multi.addActionListener(this);
		options.addActionListener(this);
		exit.addActionListener(this);
		sound.addActionListener(this);
		controls.addActionListener(this);
		controls2.addActionListener(this);
		backtomain.addActionListener(this);
		save.addActionListener(this);
		backtooptions.addActionListener(this);
		rndMapSingle.addActionListener(this);
		rndMapMulti.addActionListener(this);
		constMap.addActionListener(this);
		startGame.addActionListener(this);
		startGame2.addActionListener(this);
		saveGame.addActionListener(this);
		backtosingle.addActionListener(this);
		continueGame.addActionListener(this);
		restart.addActionListener(this);
		backtomulti.addActionListener(this);
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
		saveName.addKeyListener(this);
		game.addKeyListener(this);

		Menue.MainMenu();
		frame.add(menu);
	}

	/**
	 * Neuer Konstruktoraufruf & frame aus Konstruktor sichtbar machen
	 */
	public static void main(String[] args) {
		Field.LoadImg();

		new Interface();

		frame.setVisible(true);

	}

	/**
	 * Hier werden die Klicks auf Buttons etc. verarbeitet Bei Klick auf z.B.
	 * Optionen wird Klassen Options mit der Methode OptionsMenu aufgerufen
	 */
	public void actionPerformed(ActionEvent e) {

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		if (e.getSource() == this.exit) {
			System.exit(0);
		} else if (e.getSource() == this.options) {
			Options.OptionsMenu();
		} else if (e.getSource() == this.backtomain) {
			Menue.MainMenu();
			if (Game.played)
				Init.reset();
		} else if (e.getSource() == this.controls) {
			MenuInput.CtrlReader();
			displayCtrl();
			Control.ControlMenu();
		} else if (e.getSource() == this.backtooptions) {
			Options.OptionsMenu();
		} else if (e.getSource() == this.single) {
			GameMode.SingleMenu();
		} else if (e.getSource() == this.rndMapSingle) {
			RandomMapMenu.RandomMapSingle();
		} else if (e.getSource() == this.backtosingle) {
			GameMode.SingleMenu();
		} else if (e.getSource() == this.startGame) {
			offline = true;
			ctrlP1 = true;
			Boxes();
			Init.KI = true;
			Field.basicField = Init.basicField();
			Field.fieldNumbers = Init.fieldContent(Field.basicField);
			Field.bombPos = Init.bombPos();
			Bomb.bombs = Init.bombs();
			Game.main(null);
			closeMenuOpenGame();
			Field.f = new Field();
		} else if (e.getSource() == this.continueGame) {
			isPause = false;	
			synchronized(Paul.kiThread){
				Paul.kiThread.notify();
			}
			Game.main(null);
			closeMenuOpenGame();
			Field.f.newPaint();
		} else if (e.getSource() == this.restart) {
			Game.main(null);
			closeMenuOpenGame();
			Field.f.newPaint();
		} else if (e.getSource() == this.save) {
			MenuInput.CtrlWrite();
		} else if (e.getSource() == this.constMap) {
			Load.loadMap();
			if(Load.chosen){
				offline = true;
				ctrlP1 = true;
				Field.bombPos = Init.bombPos();
				Bomb.bombs = Init.bombs();
				Field.basicField = Init.basicField();
				Field.fieldNumbers = Init.constMap(Load.s);
				Load.content();
				Game.main(null);
				closeMenuOpenGame();
				Field.f = new Field();
			}
		} else if (e.getSource() == this.multi) {
			GameMode.MultiMenu();
		} else if (e.getSource() == this.backtomulti) {
			GameMode.MultiMenu();
		} else if (e.getSource() == this.controls2) {
			MenuInput.CtrlReader();
			displayCtrl();
			Control.ControlMenu2();
		} else if (e.getSource() == this.startGame2) {
			offline = true;
			ctrlP1 = true;
			ctrlP2 = true;
			Init.MP = true;
			Boxes(); // Anzeige wie viele Boxen auf der Zufallskarte sein sollen
			Field.basicField = Init.basicField();
			Field.fieldNumbers = Init.fieldContent(Field.basicField);
			Field.bombPos = Init.bombPos();
			Bomb.bombs = Init.bombs();
			Game.main(null);
			closeMenuOpenGame();
		} else if (e.getSource() == rndMapMulti) {
			RandomMapMenu.RandomMapMulti();
		} else if (e.getSource() == saveGame) {
			IngameMenu.isSave = true;
			IngameMenu.ingame();
		}else if (e.getSource() == online) {
			OnlineMenu.online();
		} else if (e.getSource() == soundOn) {
			isSound = true;
		} else if (e.getSource() == soundOff) {
			isSound = false;
		} else if (e.getSource() == sound) {
			SoundMenu.Sound();
		} else if (e.getSource() == join) {
			Client.ip = getIp.getText();
			Client.start();
		}


	}

	

	/**
	 * verarbeitet Combobox Auswahl fuer Kistenanzahl bei Random Map
	 */
	public static void Boxes() {
		if (getBoxNumber.getSelectedItem() == "10") {
			Init.maxBoxes = 10;
		} else if (getBoxNumber.getSelectedItem() == "20") {
			Init.maxBoxes = 20;
		} else if (getBoxNumber.getSelectedItem() == "30") {
			Init.maxBoxes = 30;
		} else if (getBoxNumber.getSelectedItem() == "40") {
			Init.maxBoxes = 40;
		} else if (getBoxNumber.getSelectedItem() == "50") {
			Init.maxBoxes = 50;
		} else if (getBoxNumber.getSelectedItem() == "60") {
			Init.maxBoxes = 60;
		} else if (getBoxNumber.getSelectedItem() == "70") {
			Init.maxBoxes = 70;
		} else if (getBoxNumber.getSelectedItem() == "80") {
			Init.maxBoxes = 80;
		} else if (getBoxNumber.getSelectedItem() == "90") {
			Init.maxBoxes = 90;
		} else if (getBoxNumber.getSelectedItem() == "100") {
			Init.maxBoxes = 100;
		}

	}

	public static void displayCtrl() {
		getUp1.setText(MenuInput.CtrlArray[0]);
		getDown1.setText(MenuInput.CtrlArray[1]);
		getRight1.setText(MenuInput.CtrlArray[2]);
		getLeft1.setText(MenuInput.CtrlArray[3]);
		getBomb1.setText(MenuInput.CtrlArray[4]);
		getUp2.setText(MenuInput.CtrlArray[5]);
		getDown2.setText(MenuInput.CtrlArray[6]);
		getRight2.setText(MenuInput.CtrlArray[7]);
		getLeft2.setText(MenuInput.CtrlArray[8]);
		getBomb2.setText(MenuInput.CtrlArray[9]);

	}

	/**
	 * Menu Panel unsichtbar, Game Panel sichtbar
	 */
	public static void closeGameOpenMenu() {
		game.setVisible(false);
		menu.setVisible(true);
	}

	/**
	 * Menu Panel sichtbar, Game Panel unsichtbar
	 */
	public static void closeMenuOpenGame() {
		menu.setVisible(false);
		game.setVisible(true);
	}

	public static void invisPicOpenMenu() {
		endPic.setVisible(false);
		menu.setVisible(true);
		GameOverPic.picOn = false;

	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		Key = MenuInput.Ctrl(e);
		String KeyId = KeyEvent.getKeyText(code);
		if (KeyId.equals("Eingabe") && IngameMenu.isSave){
			if (saveName.getText() != null && saveName.getText() != "" && checkWrong()){

				IngameMenu.isSaved = true;
				mapName = saveName.getText();
				saved = new JLabel("'" + mapName + ".txt' gespeichert" ); 
				Interface.saved.setForeground(Color.red);
				Save.saveMap(mapName);
				IngameMenu.isSave = false;
				IngameMenu.ingame();
			}
			else {
				isWrong = true;
				IngameMenu.ingame();
			}
			isWrong = false;
			IngameMenu.isSaved = false;
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}
	
	public boolean checkWrong(){
		if (!saveName.getText().contains(" ") && !saveName.getText().contains("!")
				&& !saveName.getText().contains("'") && !saveName.getText().contains("�")
				&& !saveName.getText().contains("$") && !saveName.getText().contains("%")
				&& !saveName.getText().contains("[") && !saveName.getText().contains("&")
				&& !saveName.getText().contains("{") && !saveName.getText().contains("/")
				&& !saveName.getText().contains("�") && !saveName.getText().contains("(")
				&& !saveName.getText().contains("�") && !saveName.getText().contains(")")
				&& !saveName.getText().contains("?") && !saveName.getText().contains("=")
				&& !saveName.getText().contains("�") && !saveName.getText().contains("@")
				&& !saveName.getText().contains("]") && !saveName.getText().contains("�")
				&& !saveName.getText().contains("}") && !saveName.getText().contains("`")
				&& !saveName.getText().contains("<") && !saveName.getText().contains(";")
				&& !saveName.getText().contains(">") && !saveName.getText().contains(".")
				&& !saveName.getText().contains("|") && !saveName.getText().contains(":")
				&& !saveName.getText().contains("�") && !saveName.getText().contains("^")
				&& !saveName.getText().contains("*") && !saveName.getText().contains("+")
				&& !saveName.getText().contains("~") && !saveName.getText().contains("#")
				&& !saveName.getText().contains("\\") && !saveName.getText().contains(",")
				&& !saveName.getText().contains("�"))
					return true;
		else return false;
	}

}