import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Save {
	
	public static String s;
	public static boolean choose = true;
	public static boolean chosen = false;
	public static File file;
	
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
				if(Init.MP){
					writer.write(Init.Player2.bCnt + "_");
					writer.write(Init.Player2.rad + "_");
				}
				else {
					writer.write("0_");
					writer.write("0_");
				}
				if(Init.KI){
					writer.write(KI.kiPl.bCnt + "_");
					writer.write(KI.kiPl.rad + "_");
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
