import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Eingabe {

	static String[] CtrlArray = new String[6]; /*
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
		/*
		  Vergleichen der Steuerungs-Optionen mit dem KeyText
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
			Player.ctrl = "Esc";
			Player.playerNumber = 1;
			Movement.getMovement(Player);
		} else if (KeyId.equals("NumPad +")) {
			Interface.Player1.rad ++;
		} else if (KeyId.equals("NumPad -")) {
			Interface.Player1.rad --;
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
		String CtrlString = "Oben_Unten_Rechts_Links_Leertaste_ESC_";

		

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Test.TXT"));
			out.write(CtrlString);
			out.close();
		} catch (IOException e) {
		}

	}

}
