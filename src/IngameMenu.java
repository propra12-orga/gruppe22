import java.awt.*;
/**
 * Unterklasse fuer das IngameMenue
 * @author Bastian Siefen
 *
 */
public class IngameMenu {
	
	static boolean isSave, isSaved = false;
	
	/**
	 * Wird aufgerufen, wenn waehrend des Spiels ESC gedrueckt wird,
	 * cleart Panel und befuellt es mit IngameMenue Widgets
	 */
	public static void ingame() {
		Interface.menu.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.menu.add(Interface.continueGame, c);
		c.gridy = 1;
		Interface.menu.add(Interface.saveGame, c);
		c.gridy = 2;
		if (isSaved){
			Interface.menu.add(Interface.saved, c);
			c.gridy = 3;
		}
		if (isSave){
			Interface.menu.add(Interface.saveAs, c);
			c.gridy = 3;
			Interface.menu.add(Interface.saveName, c);
			c.gridy = 4;
		}
		Interface.menu.add(Interface.backtomain, c);
		Interface.menu.updateUI();
	}
}
