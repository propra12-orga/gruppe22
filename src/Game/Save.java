package Game;
import java.io.*;

/**
 * Klasse zum Speichern eines Spielstandes
 * @author Pierre Schwarz
 *
 */

public class Save {
	
	/**
	 * Name der zu speichernden Textdatei.
	 */
	public static String s;
	
	/**
	 * Zu speichernde Datei
	 */
	public static File file;
	
	/**
	 * Methode zum Speichern eines Spielstandes: <br>
	 * Erhaelt den Namen der zu speichernden Textdatei. Diese wird erzeugt und beschrieben. <br> <br>
	 * 
	 * <u>Zeile 1-17:</u> <br>
	 * Field.fieldNumbers<br><br>
	 * 
	 * <u>Zeile 18:</u><br>
	 * Die ersten beiden +/- geben an, ob ein Spielmodus aktiv ist (MP/KI)<br>
	 * Die weiteren 4 Informationen, die gespeichert werden, beziehen sich auf den Bombenradius und die Bombenanzahl
	 * der jeweiligen Spielerobjekte: Init.Player1, Init.Player2<br><br>
	 * 
	 * <u>Zeile 19-24:</u><br>
	 * Informationen bzgl. der einzelnen Bomben: Detonationsradius in alle 4 Richtungen <br>
	 * Das letzte +/- gibt an, ob die jeweilige Bombe aktiv ist. 
	 *
	 * @param s
	 */
	
	public static void saveMap(String s){
			try{
				File file = new File("src/Maps/" + s + ".txt");
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				
				for(int i = 0; i < 21; i++)
					writer.write("1" + "_");
				
				for (int j = 1; j < 17; j++){
					writer.newLine();
					for (int i = 0; i < 21; i++)
						writer.write(Field.fieldNumbers[i][j] + "_");
				}
				writer.newLine();
				if (Init.MP) writer.write("+" + "_");
				else writer.write("-" + "_");
				if (Init.KI) writer.write("+" + "_");
				else writer.write("-" + "_");
				writer.write(Init.Player1.bCnt + "_");
				writer.write(Init.Player1.rad + "_");
				if(Init.MP || Init.KI){
					writer.write(Init.Player2.bCnt + "_");
					writer.write(Init.Player2.rad + "_");
				}
				else {
					writer.write("0_");
					writer.write("0_");
				}
				
				writer.newLine();
				for (int i = 0; i < 6; i++){
					writer.write(Bomb.bombs[i].x + "_");
					writer.write(Bomb.bombs[i].y + "_");
					writer.write(Bomb.bombs[i].o + "_");
					writer.write(Bomb.bombs[i].u + "_");
					writer.write(Bomb.bombs[i].l + "_");
					writer.write(Bomb.bombs[i].r + "_");
					if (Bomb.bombs[i].active)
						writer.write("+_");
					else
						writer.write("-_");
					writer.newLine();
				}
						
				
				writer.flush();
				writer.close();
			}
			catch( IOException e )
			{
				e.printStackTrace();
			}
		
	}
}
