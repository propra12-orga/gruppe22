import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Klasse für die Methoden zur Feststellung der Tastatureingabe
 * @author Jan Reckfort
 *
 */
public class Eingabe {

	static String[] CtrlArray = new String[10]; /*
												 * Feld zum speichern der
												 * Steuerung
												 */

	/*
	 * Diese Methode kriegt das KeyEvent übergeben das beim Tastendruck
	 * ausgelöst wurde übergeben. Es gleicht den KeyText mit den
	 * eingespeicherten Steuerungs-Optionen ab und übergibt falls diese
	 * übereinstimmen den Befehl fürs Bewegen usw. an die Funktion weiter die
	 * Bomberman auf der GUI bewegt weiter
	 */
	
	/**
	 * Methode regelt das einspeichern der Steuerung die im Menü festgelegt wird. 
	 * Sie überprüft zusätzlich die eingabe während des Spiels und ordnet wenn die 
	 * gedrückte Taste eine Bewegungstaste eines Spielers ist diese dem 
	 * entsprechenden Spieler zu.
	 * @author Jan Reckfort
	 *
	 */

	public static String Ctrl(KeyEvent e) { /* erhält KeyEvent */

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
			Init.Player1.ctrl = "Oben";

			Movement.getMovement(Init.Player1);
		} else if (KeyId.equals(CtrlArray[1])) {
			Init.Player1.ctrl = "Unten";

			Movement.getMovement(Init.Player1);
		} else if (KeyId.equals(CtrlArray[2])) {
			Init.Player1.ctrl = "Rechts";

			Movement.getMovement(Init.Player1);
		} else if (KeyId.equals(CtrlArray[3])) {
			Init.Player1.ctrl = "Links";

			Movement.getMovement(Init.Player1);
		} else if (KeyId.equals(CtrlArray[4])) {
			Init.Player1.ctrl = "Bombe";

			Movement.getMovement(Init.Player1);
		}

		if (Init.MP == true) {
			if (KeyId.equals(CtrlArray[5])) {
				Init.Player2.ctrl = "Oben";

				Movement.getMovement(Init.Player2);
			} else if (KeyId.equals(CtrlArray[6])) {
				Init.Player2.ctrl = "Unten";

				Movement.getMovement(Init.Player2);
			} else if (KeyId.equals(CtrlArray[7])) {
				Init.Player2.ctrl = "Links";

				Movement.getMovement(Init.Player2);
			} else if (KeyId.equals(CtrlArray[8])) {
				Init.Player2.ctrl = "Rechts";

				Movement.getMovement(Init.Player2);
			} else if (KeyId.equals(CtrlArray[9])) {
				Init.Player2.ctrl = "Bombe";

				Movement.getMovement(Init.Player2);
			}
		}
		if (KeyId.equals("NumPad +")) {
			Init.Player1.rad++;
		} else if (KeyId.equals("NumPad -")) {
			Init.Player1.rad--;
		} else if (KeyId.equals("ESC")) {
			Interface.closeGameOpenMenu();
			IngameMenu.ingame();
		}

		return KeyId;
	}

	/*
	 * Diese Funktion liest die zuletzt gespeicherten Steuerungs-Optionen aus
	 * der Datei Test.TXT aus. Zusï¿½tzlich legt sie diese im Feld CtrlArray
	 * fest.
	 */

	/**
	 * Liest die Steuerung aus der Datei aus und spaltet den String an jedem "_".
	 *
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
									 * fï¿½llen den Feldes was zum vergleichen
									 * mit der gedrï¿½ckten Taste nï¿½tig ist
									 */
	}

	/*
	 * Diese Funktion schreibt die im Menue fest gelegten Steuerungs-Optionen in
	 * die Datei Test.TXT.(Name noch ï¿½ndern)
	 */
	/**
	 * Wenn im Menü auf den Button "Speichern" gedrückt wurde schreibt die Methode,
	 * die vom Spieler festgelegte Steuerung in einer Datei damit später wieder auf
	 * diese Steuerung zugegriffen werden kann. 
	 *
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
