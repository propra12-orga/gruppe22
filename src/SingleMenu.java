import java.awt.GridBagConstraints;
import java.awt.Insets;

public class SingleMenu {

	public static void SingleMenu() {
		Interface.menu.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.menu.add(Interface.rndMap, c);
		c.gridy = 1;
		Interface.menu.add(Interface.constMap, c);
		c.gridy = 2;
		Interface.menu.add(Interface.backtomain, c);
		Interface.menu.updateUI();
		
	}
	
}