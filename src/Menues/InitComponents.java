package Menues;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Interface;

/**
 * Interface Initialisierungsklasse
 * hier werden alle Widgets die im Programm verwendet werden,
 * initialisiert und weist ihnen Groesse, Farbe etc zu.
 * @author Bastian Siefen
 *
 */
public class InitComponents {
	/**
	 * Hier werden die Widgets fuer das Hauptmenue initialisiert und verarbeitet
	 */
	public static void MainMenuComponents(){
		Interface.menu = new JPanel(new GridBagLayout()) {

			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				g.drawImage(Interface.img, 0, 0, null);
			}
		};
		Interface.single.setPreferredSize(Interface.dim);
		Interface.single.setIcon(new ImageIcon(Interface.Button));
		Interface.single.setHorizontalTextPosition(JButton.CENTER);
		Interface.single.setVerticalTextPosition(JButton.CENTER);
		Interface.single.setForeground(Color.white);
		
		
		Interface.multi.setPreferredSize(Interface.dim);
		Interface.multi.setIcon(new ImageIcon(Interface.Button));
		Interface.multi.setHorizontalTextPosition(JButton.CENTER);
		Interface.multi.setVerticalTextPosition(JButton.CENTER);
		Interface.multi.setForeground(Color.white);
		
		
		Interface.options.setPreferredSize(Interface.dim);
		Interface.options.setIcon(new ImageIcon(Interface.Button));
		Interface.options.setHorizontalTextPosition(JButton.CENTER);
		Interface.options.setVerticalTextPosition(JButton.CENTER);
		Interface.options.setForeground(Color.white);
		
		
		Interface.exit.setPreferredSize(Interface.dim);
		Interface.exit.setIcon(new ImageIcon(Interface.Button));
		Interface.exit.setHorizontalTextPosition(JButton.CENTER);
		Interface.exit.setVerticalTextPosition(JButton.CENTER);
		Interface.exit.setForeground(Color.white);
		
		
		Interface.game.setFocusable(true);
		Interface.menu.setFocusable(true);
		
		
		Interface.backtosingle.setPreferredSize(Interface.dim);
		Interface.backtosingle.setIcon(new ImageIcon(Interface.Button));
		Interface.backtosingle.setHorizontalTextPosition(JButton.CENTER);
		Interface.backtosingle.setVerticalTextPosition(JButton.CENTER);
		Interface.backtosingle.setForeground(Color.white);
		
		
		Interface.backtomulti.setPreferredSize(Interface.dim);
		Interface.backtomulti.setIcon(new ImageIcon(Interface.Button));
		Interface.backtomulti.setHorizontalTextPosition(JButton.CENTER);
		Interface.backtomulti.setVerticalTextPosition(JButton.CENTER);
		Interface.backtomulti.setForeground(Color.white);
	}
	
	/**
	 * Hier werden die Widgets fuer das SingleplayerMenue initialisiert und verarbeitet
	 */
	public static void SingleComponents() {
		Interface.rndMapSingle.setPreferredSize(Interface.dim);
		Interface.rndMapSingle.setIcon(new ImageIcon(Interface.Button));
		Interface.rndMapSingle.setHorizontalTextPosition(JButton.CENTER);
		Interface.rndMapSingle.setVerticalTextPosition(JButton.CENTER);
		Interface.rndMapSingle.setForeground(Color.white);
		
		
		Interface.constMap.setPreferredSize(Interface.dim);
		Interface.constMap.setIcon(new ImageIcon(Interface.Button));
		Interface.constMap.setHorizontalTextPosition(JButton.CENTER);
		Interface.constMap.setVerticalTextPosition(JButton.CENTER);
		Interface.constMap.setForeground(Color.white);
		
		
		Interface.startGame.setPreferredSize(Interface.dim);
		Interface.startGame.setIcon(new ImageIcon(Interface.Button));
		Interface.startGame.setHorizontalTextPosition(JButton.CENTER);
		Interface.startGame.setVerticalTextPosition(JButton.CENTER);
		Interface.startGame.setForeground(Color.white);
	}
	
	/**
	 * Hier werden die Widgets fuer das MultiplayerMenue initialisiert und verarbeitet
	 */
	public static void MultiComponents() {
		Interface.rndMapMulti.setPreferredSize(Interface.dim);
		Interface.rndMapMulti.setIcon(new ImageIcon(Interface.Button));
		Interface.rndMapMulti.setHorizontalTextPosition(JButton.CENTER);
		Interface.rndMapMulti.setVerticalTextPosition(JButton.CENTER);
		Interface.rndMapMulti.setForeground(Color.white);
		
		
		Interface.startGame2.setPreferredSize(Interface.dim);
		Interface.startGame2.setIcon(new ImageIcon(Interface.Button));
		Interface.startGame2.setHorizontalTextPosition(JButton.CENTER);
		Interface.startGame2.setVerticalTextPosition(JButton.CENTER);
		Interface.startGame2.setForeground(Color.white);
		
		Interface.online.setPreferredSize(Interface.dim);
		Interface.online.setIcon(new ImageIcon(Interface.Button));
		Interface.online.setHorizontalTextPosition(JButton.CENTER);
		Interface.online.setVerticalTextPosition(JButton.CENTER);
		Interface.online.setForeground(Color.white);
		
		
		Interface.join.setPreferredSize(Interface.dim);
		Interface.join.setIcon(new ImageIcon(Interface.Button));
		Interface.join.setHorizontalTextPosition(JButton.CENTER);
		Interface.join.setVerticalTextPosition(JButton.CENTER);
		Interface.join.setForeground(Color.white);
		

		Interface.ipLabel.setForeground(Color.white);
	}
	
	/**
	 * Hier werden die Widgets fuer das OptionsMenue initialisiert und verarbeitet
	 */
	public static void OptionsComponents() {
		Interface.controls.setPreferredSize(Interface.dim);
		Interface.controls.setIcon(new ImageIcon(Interface.Button));
		Interface.controls.setHorizontalTextPosition(JButton.CENTER);
		Interface.controls.setVerticalTextPosition(JButton.CENTER);
		Interface.controls.setForeground(Color.white);
		
		Interface.controls2.setPreferredSize(Interface.dim);
		Interface.controls2.setIcon(new ImageIcon(Interface.Button));
		Interface.controls2.setHorizontalTextPosition(JButton.CENTER);
		Interface.controls2.setVerticalTextPosition(JButton.CENTER);
		Interface.controls2.setForeground(Color.white);
		
		
		Interface.sound.setPreferredSize(Interface.dim);
		Interface.sound.setIcon(new ImageIcon(Interface.Button));
		Interface.sound.setHorizontalTextPosition(JButton.CENTER);
		Interface.sound.setVerticalTextPosition(JButton.CENTER);
		Interface.sound.setForeground(Color.white);
		
		Interface.soundOn.setPreferredSize(Interface.dim);
		Interface.soundOn.setIcon(new ImageIcon(Interface.Button));
		Interface.soundOn.setHorizontalTextPosition(JButton.CENTER);
		Interface.soundOn.setVerticalTextPosition(JButton.CENTER);
		Interface.soundOn.setForeground(Color.white);
		
		Interface.soundOff.setPreferredSize(Interface.dim);
		Interface.soundOff.setIcon(new ImageIcon(Interface.Button));
		Interface.soundOff.setHorizontalTextPosition(JButton.CENTER);
		Interface.soundOff.setVerticalTextPosition(JButton.CENTER);
		Interface.soundOff.setForeground(Color.white);
		
		
		Interface.backtomain.setPreferredSize(Interface.dim);
		Interface.backtomain.setIcon(new ImageIcon(Interface.Button));
		Interface.backtomain.setHorizontalTextPosition(JButton.CENTER);
		Interface.backtomain.setVerticalTextPosition(JButton.CENTER);
		Interface.backtomain.setForeground(Color.white);
		
		
		Interface.save.setPreferredSize(Interface.dim);
		Interface.save.setIcon(new ImageIcon(Interface.Button));
		Interface.save.setHorizontalTextPosition(JButton.CENTER);
		Interface.save.setVerticalTextPosition(JButton.CENTER);
		Interface.save.setForeground(Color.white);
		
		
		Interface.backtooptions.setPreferredSize(Interface.dim);
		Interface.backtooptions.setIcon(new ImageIcon(Interface.Button));
		Interface.backtooptions.setHorizontalTextPosition(JButton.CENTER);
		Interface.backtooptions.setVerticalTextPosition(JButton.CENTER);
		Interface.backtooptions.setForeground(Color.white);
		
		
		Interface.player1.setForeground(Color.white);
		Interface.player2.setForeground(Color.white);
		Interface.up1.setForeground(Color.white);
		Interface.up2.setForeground(Color.white);
		Interface.down1.setForeground(Color.white);
		Interface.down2.setForeground(Color.white);
		Interface.right1.setForeground(Color.white);
		Interface.right2.setForeground(Color.white);
		Interface.left1.setForeground(Color.white);
		Interface.left2.setForeground(Color.white);
		Interface.bomb1.setForeground(Color.white);
		Interface.bomb2.setForeground(Color.white);
		Interface.boxNumber.setForeground(Color.white);
		Interface.getUp1.setEditable(false);
		Interface.getUp2.setEditable(false);
		Interface.getDown1.setEditable(false);
		Interface.getDown2.setEditable(false);
		Interface.getRight1.setEditable(false);
		Interface.getRight2.setEditable(false);
		Interface.getLeft1.setEditable(false);
		Interface.getLeft2.setEditable(false);
		Interface.getBomb1.setEditable(false);
		Interface.getBomb2.setEditable(false);
	}
	
	/**
	 * Hier werden die Widgets fuer das IngameMenue initialisiert und verarbeitet
	 */
	public static void IngameMenuComponents() {
		Interface.continueGame.setPreferredSize(Interface.dim);
		Interface.continueGame.setIcon(new ImageIcon(Interface.Button));
		Interface.continueGame.setHorizontalTextPosition(JButton.CENTER);
		Interface.continueGame.setVerticalTextPosition(JButton.CENTER);
		Interface.continueGame.setForeground(Color.white);
		
		Interface.saveGame.setPreferredSize(Interface.dim);
		Interface.saveGame.setIcon(new ImageIcon(Interface.Button));
		Interface.saveGame.setHorizontalTextPosition(JButton.CENTER);
		Interface.saveGame.setVerticalTextPosition(JButton.CENTER);
		Interface.saveGame.setForeground(Color.white);
		
		Interface.saveAs.setForeground(Color.red);
		Interface.wrongName.setForeground(Color.red);
		Interface.saveName.setPreferredSize(Interface.dim);
		
		Interface.restart.setPreferredSize(Interface.dim);
		Interface.restart.setIcon(new ImageIcon(Interface.Button));
		Interface.restart.setHorizontalTextPosition(JButton.CENTER);
		Interface.restart.setVerticalTextPosition(JButton.CENTER);
		Interface.restart.setForeground(Color.white);
		
		
	}
	

}










