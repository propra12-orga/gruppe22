package Game;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Verarbeitung des Tastatureingabe während das Menue geoeffnet ist.
 * 
 * @author Bastian Siefen
 *
 */
public class MenuInput {
	static String[] CtrlArray = new String[10];


	public static String Ctrl(KeyEvent e) {

		int code = e.getKeyCode(); 

		String KeyId = KeyEvent.getKeyText(code); 

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
		return KeyId;

	}

	public static void CtrlReader() { /* 0815 Reader */
		String Ctrl = "";

		try {
			BufferedReader in = new BufferedReader(new FileReader("Test.TXT"));
			Ctrl = in.readLine();
		} catch (IOException e) {
		}
		CtrlArray = Ctrl.split("_");
	}

	
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

