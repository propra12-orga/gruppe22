import java.awt.*;

public class GameMode {

	static GridBagConstraints c = new GridBagConstraints();
	
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
		Interface.menu.add(Interface.backtomain, c);
		Interface.menu.updateUI();
	}
	
}