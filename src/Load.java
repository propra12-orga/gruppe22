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
	public static boolean choose = true;
	public static boolean chosen = false;
	
	public static void loadMap(){
		chosen = false;
		choose = true;
		s = "";
		JFileChooser fc = new JFileChooser("src/Maps/");
		fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
		fc.setDialogTitle("Map-Textdatei öffnen");
		fc.setFileFilter(new FileNameExtensionFilter("Textdatei" , "txt"));
		fc.setFileFilter( new FileFilter()
		{
		  @Override public boolean accept( File f )
		  {
		    return f.isFile() &&
		    		(f.getName().endsWith("txt") || f.getName().endsWith("TXT"));
		  }
		  @Override public String getDescription()
		  {
		    return "Map-Textdatei";
		  }
		} );

		while(choose)
			switch ( fc.showOpenDialog( null ) ){
				case JFileChooser.APPROVE_OPTION:
					File file = fc.getSelectedFile();
					s = file.toString();
				
					if(!s.contains("Maps")){
						System.out.println("Invalid Directory");
						break;
					}
					else{
						s = "src/Maps/" + file.getName();
						chosen = true;
						choose = false;
						break;
					}
				
					default:
						System.out.println( "Auswahl abgebrochen" );
						choose = false;
			}
	}
}
