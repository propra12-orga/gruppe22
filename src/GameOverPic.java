import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameOverPic {

	public static Boolean picOn = false;
	static BufferedImage endGame;
	static int pic;

	public static void LoadPic() {
		
		if(pic==1){
		try {
			endGame = ImageIO.read(new File("src/Pictures/game_over2.jpg"));
		} catch (IOException e) {
			System.out.println("Game_over2.jpg konnte nicht gemalt werden");
		}
		} else if(pic==2){
			try {
				endGame = ImageIO.read(new File("src/Pictures/bd_exit2_2.jpg"));
			} catch (IOException e) {
				System.out.println("bd_exit2_2.jpg konnte nicht gemalt werden");
			}
			}

	}

	public static void DrawPic() {
		LoadPic();
		picOn = true;
		Menue.MainMenu();
		Interface.endPic.setFocusable(true);
		Interface.frame.add(Interface.endPic);
		Interface.game.setVisible(false);
		Interface.endPic.setVisible(true);

	}

}
