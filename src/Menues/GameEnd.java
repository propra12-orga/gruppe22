package Menues;
import java.awt.*;

import Game.Init;
import Game.Interface;
public class GameEnd {


	public static void end() {
		Init.reset();
		Interface.menu.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.menu.add(Interface.restart, c);
		c.gridy = 1;
		Interface.menu.add(Interface.backtomain, c);
		Interface.menu.updateUI();
	}
}