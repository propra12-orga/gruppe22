import java.awt.*;
/**
 * Unterklasse fuer das Hauptmenue
 * @author Bastian Siefen
 *
 */
public class Menue {
	/**
	 * Cleart Panel und befuellt es mit den Hauptmenue Widgets
	 */
	public static void MainMenu() {

		Interface.menu.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.menu.add(Interface.single, c);
		c.gridy = 1;
		Interface.menu.add(Interface.multi, c);
		c.gridy = 2;
		Interface.menu.add(Interface.options, c);
		c.gridy = 3;
		Interface.menu.add(Interface.exit, c);
		Interface.menu.updateUI();
	}

}
