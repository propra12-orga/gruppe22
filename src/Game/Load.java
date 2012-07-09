package Game;
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
	
	/**
	 * Dateiname der zu ladenden Datei.
	 */
	public static String s;
	
	/**
	 * Gibt an, ob eine Datei zum Laden grade ausgewaehlt wird.
	 */
	public static boolean choose = true;
	
	/**
	 * Gibt an, ob eine Dateiauswahl stattgefunden hat.
	 */
	public static boolean chosen = false;
	
	/**
	 * Laedt eine Textdatei. <br>
	 * Oeffnet einen JFileChooser und setzt diverse Filter (es MUSS sich um den src/Maps-Directory handeln
	 * und die Datei MUSS mit .txt enden)
	 */
	public static void loadMap(){
		chosen = false;
		choose = true;
		s = "";
		JFileChooser fc = new JFileChooser("src/Maps/");
		fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
		fc.setDialogTitle("Spielstand laden");
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
	
	/**
	 * Laedt einen Spielstand anhand der zuvor geladenen und ausgelesenen Textdatei. <br>
	 * Das Bombenarray der Bomb-Klasse, wichtige Player-Parameter und der Spielmodus (KI/MP)
	 * werden entsprechend ueberschrieben.
	 */
	public static void content(){
		
		
		Init.Player1.bCnt = Integer.parseInt(Init.gameInfo[2]);
		Init.Player1.rad = Integer.parseInt(Init.gameInfo[3]);
		Init.Player2.bCnt = Integer.parseInt(Init.gameInfo[4]);
		Init.Player2.rad = Integer.parseInt(Init.gameInfo[5]);
		
		if(Init.gameInfo[0].equals("+"))
			Init.MP = true;

		if(Init.gameInfo[1].equals("+"))
			Init.KI = true;

		for (int i = 0; i < 6; i++){
				Bomb.bombs[i].x = Integer.parseInt(Init.bombInfo[i][0]);
				Bomb.bombs[i].y = Integer.parseInt(Init.bombInfo[i][1]);
				Bomb.bombs[i].o = Integer.parseInt(Init.bombInfo[i][2]);
				Bomb.bombs[i].u = Integer.parseInt(Init.bombInfo[i][3]);
				Bomb.bombs[i].l = Integer.parseInt(Init.bombInfo[i][4]);
				Bomb.bombs[i].r = Integer.parseInt(Init.bombInfo[i][5]);
				Bomb.bombs[i].num = i;
				
				if(Init.bombInfo[i][6].equals("+")){
					if(i == 0){
						Init.Player1.bCnt += 1;
						Bomb.placeBomb(Bomb.bombs[i], Init.Player1);
						Carl.bomb0 = new Carl(Bomb.bombs[i], Init.Player1);
						Carl.bomb0.start();
					} else if (i == 1){
						Init.Player1.bCnt += 1;
						Bomb.placeBomb(Bomb.bombs[i], Init.Player1);
						Carl.bomb1 = new Carl(Bomb.bombs[i], Init.Player1);
						Carl.bomb1.start();
					} else if (i == 2){
						Init.Player1.bCnt += 1;
						Bomb.placeBomb(Bomb.bombs[i], Init.Player1);
						Carl.bomb2 = new Carl(Bomb.bombs[i], Init.Player1);
						Carl.bomb2.start();
					} else if (i == 3){
						Init.Player2.bCnt += 1;
						Bomb.placeBomb(Bomb.bombs[i], Init.Player1);
						Carl.bomb3 = new Carl(Bomb.bombs[i], Init.Player2);
						Carl.bomb3.start();
					} else if (i == 4){
						Init.Player2.bCnt += 1;
						Bomb.placeBomb(Bomb.bombs[i], Init.Player1);
						Carl.bomb4 = new Carl(Bomb.bombs[i], Init.Player2);
						Carl.bomb4.start();
					} else if (i == 5){
						Init.Player2.bCnt += 1;
						Bomb.placeBomb(Bomb.bombs[i], Init.Player1);
						Carl.bomb5 = new Carl(Bomb.bombs[i], Init.Player2);
						Carl.bomb5.start();
					}
						
				}
			}
			startPos();
	}
	
	/**
	 * Laedt die Startpositionen der Spieler.
	 */
	public static void startPos(){
		for (int i = 0; i < 21; i++)
			for (int j = 0; j < 17; j++){
				if(Field.fieldNumbers[i][j] == 3){
					Init.Player1.x = i;
					Init.Player1.xo = i;
					Init.Player1.y = j;
					Init.Player1.yo = j;
				}
				if(Field.fieldNumbers[i][j] == 4 && (Init.MP || Init.KI)){
					Init.Player2.x = i;
					Init.Player2.xo = i;
					Init.Player2.y = j;
					Init.Player2.yo = j;
				}
			}
	}
}
