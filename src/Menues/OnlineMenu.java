package Menues;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import Game.Interface;

/**
 * Vorlage fuer das Online-Menue. 
 * @author Bastian Siefen
 *
 */
public class OnlineMenu {

	static GridBagConstraints c = new GridBagConstraints();
	
	public static void online() {
		
		Interface.menu.removeAll();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.menu.add(Interface.join, c);
		c.gridx = 2;
		c.gridy = 0;
		Interface.menu.add(Interface.ipLabel, c);
		c.gridy = 1;
		Interface.menu.add(Interface.getIp, c);
		c.gridx = 1;
		c.gridy = 2;
		Interface.menu.add(Interface.backtomain, c);
		Interface.menu.updateUI();
		
	}

}
