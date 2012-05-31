import java.awt.event.KeyEvent;
import java.io.*;

public class Eingabe {

	static String[] CtrlArray = new String[10]; /*
												 * Feld zum speichern der
												 * Steuerung
												 */

	/*
	 * Diese Methode kriegt das KeyEvent �bergeben das beim Tastendruck
	 * ausgel�st wurde �bergeben. Es gleicht den KeyText mit den
	 * eingespeicherten Steuerungs-Optionen ab und �bergibt falls diese
	 * �bereinstimmen den Befehl f�rs Bewegen usw. an die Funktion weiter die
	 * Bomberman auf der GUI bewegt weiter
	 */

	public static String Ctrl(KeyEvent e) { /* erh�lt KeyEvent */

		Paul Player = new Paul();

		int code = e.getKeyCode(); /*
									 * Umwandelung des KeyEvents in die passende
									 * KeyCode
									 */
		String KeyId = KeyEvent.getKeyText(code); /*
												 * Umwandelung des KeyCodes in
												 * den KeyText
												 */
		if (e.getSource() == Interface.getUp1) {
			Interface.getUp1.setText(KeyId);
			CtrlArray[0] = KeyId;
		} else if (e.getSource() == Interface.getUp2) {
			Interface.getUp2.setText(KeyId);
			CtrlArray[5] = KeyId;
		} else if (e.getSource() == Interface.getDown1) {
			Interface.getDown1.setText(KeyId);
			CtrlArray[1] = KeyId;
		} else if (e.getSource() == Interface.getDown2) {
			Interface.getDown2.setText(KeyId);
			CtrlArray[6] = KeyId;
		} else if (e.getSource() == Interface.getRight1) {
			Interface.getRight1.setText(KeyId);
			CtrlArray[2] = KeyId;
		} else if (e.getSource() == Interface.getRight2) {
			Interface.getRight2.setText(KeyId);
			CtrlArray[7] = KeyId;
		} else if (e.getSource() == Interface.getLeft1) {
			Interface.getLeft1.setText(KeyId);
			CtrlArray[3] = KeyId;
		} else if (e.getSource() == Interface.getLeft2) {
			Interface.getLeft2.setText(KeyId);
			CtrlArray[8] = KeyId;
		} else if (e.getSource() == Interface.getBomb1) {
			Interface.getBomb1.setText(KeyId);
			CtrlArray[4] = KeyId;
		} else if (e.getSource() == Interface.getBomb2) {
			Interface.getBomb2.setText(KeyId);
			CtrlArray[9] = KeyId;
		}

		/*
		 * Vergleichen der Steuerungs-Optionen mit dem KeyText
		 */
		if (KeyId.equals(CtrlArray[0])) {
			Player.ctrl = "Oben";
			Player.playerNumber = 1;
			Movement.getMovement(Player);
		} else if (KeyId.equals(CtrlArray[1])) {
			Player.ctrl = "Unten";
			Player.playerNumber = 1;
			Movement.getMovement(Player);
		} else if (KeyId.equals(CtrlArray[2])) {
			Player.ctrl = "Rechts";
			Player.playerNumber = 1;
			Movement.getMovement(Player);
		} else if (KeyId.equals(CtrlArray[3])) {
			Player.ctrl = "Links";
			Player.playerNumber = 1;
			Movement.getMovement(Player);
		} else if (KeyId.equals(CtrlArray[4])) {
			Player.ctrl = "Bombe";
			Player.playerNumber = 1;
			Movement.getMovement(Player);
		} else if (KeyId.equals(CtrlArray[5])) {
			Player.ctrl = "Oben";
			Player.playerNumber = 2;
			Movement.getMovement(Player);
		} else if (KeyId.equals(CtrlArray[6])) {
			Player.ctrl = "Unten";
			Player.playerNumber = 2;
			Movement.getMovement(Player);
		} else if (KeyId.equals(CtrlArray[7])) {
			Player.ctrl = "Links";
			Player.playerNumber = 2;
			Movement.getMovement(Player);
		} else if (KeyId.equals(CtrlArray[8])) {
			Player.ctrl = "Rechts";
			Player.playerNumber = 2;
			Movement.getMovement(Player);
		} else if (KeyId.equals(CtrlArray[9])) {
			Player.ctrl = "Bombe";
			Player.playerNumber = 2;
			Movement.getMovement(Player);
		} else if (KeyId.equals("NumPad +")) {
			Interface.Player1.rad++;
		} else if (KeyId.equals("NumPad -")) {
			Interface.Player1.rad--;
		} else if (KeyId.equals("ESC")) {
			Interface.closeGameOpenMenu();
			IngameMenu.ingame();
		}

		return KeyId;
	}

	/*
	 * Diese Funktion liest die zuletzt gespeicherten Steuerungs-Optionen aus
	 * der Datei Test.TXT aus. Zus�tzlich legt sie diese im Feld CtrlArray fest.
	 */

	public static void CtrlReader() { /* 0815 Reader */
		String Ctrl = "";

		try {
			BufferedReader in = new BufferedReader(new FileReader("Test.TXT"));
			Ctrl = in.readLine();
		} catch (IOException e) {
		}
		CtrlArray = Ctrl.split("_"); /*
									 * Splitten des gelesenen Strings am "_" und
									 * f�llen den Feldes was zum vergleichen mit
									 * der gedr�ckten Taste n�tig ist
									 */
	}

	/*
	 * Diese Funktion schreibt die im Menue fest gelegten Steuerungs-Optionen in
	 * die Datei Test.TXT.(Name noch �ndern)
	 */

	public static void CtrlWrite() { /* 0815 Writer */

		String CtrlString = "";
		for (int i = 0; i <= 9; i++) {
			CtrlString += CtrlArray[i] + "_";
		}

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Test.TXT"));
			out.write(CtrlString);
			out.close();
		} catch (IOException e) {
		}

	}

}
