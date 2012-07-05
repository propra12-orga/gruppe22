import java.awt.*;
/**
 * Unterklasse fuer Menues Singeplayer & Multiplayer
 * @author Bastian Siefen
 *
 */
public class GameMode {

	static GridBagConstraints c = new GridBagConstraints();
	/**
	 * Cleart Panel und befuellt es neu mit Singleplayer Widgets
	 */
	public static void SingleMenu() {
		Interface.menu.removeAll();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.menu.add(Interface.rndMapSingle, c);
		c.gridy = 1;
		Interface.menu.add(Interface.constMap, c);
		c.gridy = 2;
		Interface.menu.add(Interface.backtomain, c);
		Interface.menu.updateUI();
		
	}
	/**
	 * Cleart Panel und befuellt es neu mit Multiplayer Widgets
	 */
	public static void MultiMenu() {
		Interface.menu.removeAll();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.menu.add(Interface.rndMapMulti, c);
		c.gridy = 1;
		Interface.menu.add(Interface.constMap, c);
		c.gridy = 2;
		Interface.menu.add(Interface.controls2, c);
		c.gridy = 3;
		Interface.menu.add(Interface.online, c);
		c.gridy = 4;
		Interface.menu.add(Interface.backtomain, c);
		Interface.menu.updateUI();
	}
	
}