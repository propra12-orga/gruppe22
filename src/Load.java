import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Oeffnet ein Fenster zur Auswahl der Map-Textdatei.
 * @author Pierre Schwarz
 *
 */

public class Load{
	
	public static String s;
	
	public static String loadMap(){
		JFileChooser fc = new JFileChooser("src/Maps/");
		FileFilter filter = new FileNameExtensionFilter("Textdatei" , "txt");
		fc.setFileFilter( filter );

		switch ( fc.showOpenDialog( null ) ){
			case JFileChooser.APPROVE_OPTION:
				File file = fc.getSelectedFile();
				s = "src/Maps/" + file.getName();
				break;
				default:
				System.out.println( "Auswahl abgebrochen" );
		}
		return s;
	}
}
