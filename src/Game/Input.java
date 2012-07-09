package Game;
import Menues.*;
import java.awt.event.KeyEvent;

/**
 * Klasse für die Methoden zur Feststellung der Tastatureingabe
 * 
 * @author Jan Reckfort
 * 
 */
public class Input {

	/*
	 * Diese Methode kriegt das KeyEvent übergeben das beim Tastendruck
	 * ausgelöst wurde übergeben. Es gleicht den KeyText mit den
	 * eingespeicherten Steuerungs-Optionen ab und übergibt falls diese
	 * übereinstimmen den Befehl fürs Bewegen usw. an die Funktion weiter die
	 * Bomberman auf der GUI bewegt weiter
	 */

	/**
	 * Methode regelt das einspeichern der Steuerung die im Menü festgelegt
	 * wird. Sie überprüft zusätzlich die eingabe während des Spiels und ordnet
	 * wenn die gedrückte Taste eine Bewegungstaste eines Spielers ist diese dem
	 * entsprechenden Spieler zu.
	 * 
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

		/*
		 * Vergleichen der Steuerungs-Optionen mit dem KeyText
		 */
		if(Interface.ctrlP1 == true) {
			if (KeyId.equals(MenuInput.CtrlArray[0])) {
				Init.Player1.ctrl = "Oben";

				Movement.getMovement(Init.Player1);
			} else if (KeyId.equals(MenuInput.CtrlArray[1])) {
				Init.Player1.ctrl = "Unten";

				Movement.getMovement(Init.Player1);
			} else if (KeyId.equals(MenuInput.CtrlArray[2])) {
				Init.Player1.ctrl = "Rechts";

				Movement.getMovement(Init.Player1);
			} else if (KeyId.equals(MenuInput.CtrlArray[3])) {
				Init.Player1.ctrl = "Links";

				Movement.getMovement(Init.Player1);
			} else if (KeyId.equals(MenuInput.CtrlArray[4])) {
				Init.Player1.ctrl = "Bombe";

				Movement.getMovement(Init.Player1);
			}
		}

		if (Init.MP == true && Interface.ctrlP2 == true) {
			if (KeyId.equals(MenuInput.CtrlArray[5])) {
				Init.Player2.ctrl = "Oben";

				Movement.getMovement(Init.Player2);
			} else if (KeyId.equals(MenuInput.CtrlArray[6])) {
				Init.Player2.ctrl = "Unten";

				Movement.getMovement(Init.Player2);
			} else if (KeyId.equals(MenuInput.CtrlArray[7])) {
				Init.Player2.ctrl = "Links";

				Movement.getMovement(Init.Player2);
			} else if (KeyId.equals(MenuInput.CtrlArray[8])) {
				Init.Player2.ctrl = "Rechts";

				Movement.getMovement(Init.Player2);
			} else if (KeyId.equals(MenuInput.CtrlArray[9])) {
				Init.Player2.ctrl = "Bombe";

				Movement.getMovement(Init.Player2);
			}
		}
		if (KeyId.equals("NumPad +")) {
		} else if (KeyId.equals("NumPad -")) {
			Init.Player1.rad--;
		} else if (KeyId.equals("ESC")) {
			Interface.isPause = true;
			if (Init.KI)
				Paul.kiThread.interrupt();
			Interface.closeGameOpenMenu();
			IngameMenu.ingame();
		}

		return KeyId;
	}

}
