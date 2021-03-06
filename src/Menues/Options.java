package Menues;
import java.awt.*;

import Game.Interface;
/**
 * Unterklasse fuer OptionsMenue
 * @author Bastian Siefen
 *
 */
public class Options {
	/**
	 * Cleart Panel und befuellt es mit OptionsMenue Widgets
	 */
	public static void OptionsMenu() {
		Interface.menu.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.menu.add(Interface.sound, c);
		c.gridy = 1;
		Interface.menu.add(Interface.controls, c);
		c.gridy = 2;
		Interface.menu.add(Interface.backtomain, c);
		Interface.menu.updateUI();

	}

}
