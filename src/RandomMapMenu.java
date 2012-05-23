import java.awt.GridBagConstraints;
import java.awt.Insets;


public class RandomMapMenu {
	
	public static void RandomMap() {
		Interface.panel.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.panel.add(Interface.boxNumber, c);
		c.gridx = 2;
		Interface.panel.add(Interface.getBoxNumber, c);
		c.gridx = 1;
		c.gridy = 1;
		Interface.panel.add(Interface.startGame, c);
		c.gridy = 2;
		Interface.panel.add(Interface.backtosingle, c);
		Interface.panel.updateUI();
	}
}
