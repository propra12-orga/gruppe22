import java.awt.*;
/**
 * Unterklasse fuer das ZufallsMapMenue
 * @author Bastian Siefen
 *
 */
public class RandomMapMenu {
	/**
	 * Wird ausgefuehrt wenn im Singleplayer auf ZufallsMap gedrueckt wurde,
	 * cleart das Panel und befuellt es mit den Zufallsmap Widgets neu
	 */
	public static void RandomMapSingle() {
		Interface.menu.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.menu.add(Interface.boxNumber, c);
		c.gridx = 2;
		Interface.menu.add(Interface.getBoxNumber, c);
		c.gridx = 1;
		c.gridy = 1;
		Interface.menu.add(Interface.startGame, c);
		c.gridy = 2;
		Interface.menu.add(Interface.backtosingle, c);
		Interface.menu.updateUI();
	}
	/**
	 * Wird ausgefuehrt wenn im Multieplayer auf ZufallsMap gedrueckt wurde,
	 * cleart das Panel und befuellt es mit den Zufallsmap Widgets neu
	 */
	public static void RandomMapMulti() {
		Interface.menu.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.menu.add(Interface.boxNumber, c);
		c.gridx = 2;
		Interface.menu.add(Interface.getBoxNumber, c);
		c.gridx = 1;
		c.gridy = 1;
		Interface.menu.add(Interface.startGame2, c);
		c.gridy = 2;
		Interface.menu.add(Interface.backtomulti, c);
		Interface.menu.updateUI();
	}
}
