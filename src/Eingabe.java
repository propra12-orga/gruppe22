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
	 * Diese Methode kriegt das KeyEvent übergeben das beim Tastendruck
	 * ausgelöst wurde übergeben. Es gleicht den KeyText mit den
	 * eingespeicherten Steuerungs-Optionen ab und übergibt falls diese
	 * übereinstimmen den Befehl fürs Bewegen usw. an die Funktion weiter die
	 * Bomberman auf der GUI bewegt weiter
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
		/*
		  Vergleichen der Steuerungs-Optionen mit dem KeyText
		*/
		if (KeyId.equals(CtrlArray[0])) { 
			Field.movement("Oben");
		} else if (KeyId.equals(CtrlArray[1])) {
			Field.movement("Unten");
		} else if (KeyId.equals(CtrlArray[2])) {
			Field.movement("Rechts");
		} else if (KeyId.equals(CtrlArray[3])) {
			Field.movement("Links");
		} else if (KeyId.equals(CtrlArray[4])) {
			Field.movement("Bombe");
		} else if (KeyId.equals(CtrlArray[5])) {
			Field.movement("Esc");
		}

		return KeyId;
	}

	/*
	 * Diese Funktion liest die zuletzt gespeicherten Steuerungs-Optionen aus
	 * der Datei Test.TXT aus. Zusätzlich legt sie diese im Feld CtrlArray fest.
	 */

	public static void CtrlReader() { /* 0815 Reader */
		String Ctrl = "";

		try {
			BufferedReader in = new BufferedReader(new FileReader("Test.TXT"));
			Ctrl = in.readLine();
		} catch (IOException e) {
		}

		System.out.println(Ctrl);

		CtrlArray = Ctrl.split("_"); /*
									 * Splitten des gelesenen Strings am "_" und
									 * füllen den Feldes was zum vergleichen mit
									 * der gedrückten Taste nötig ist
									 */
	}

	/*
	 * Diese Funktion schreibt die im Menue fest gelegten Steuerungs-Optionen in
	 * die Datei Test.TXT.(Name noch ändern)
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
