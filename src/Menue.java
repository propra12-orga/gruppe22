import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Menue {

	public static void MainMenu() {

		Interface.panel.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.panel.add(Interface.single, c);
		c.gridy = 1;
		Interface.panel.add(Interface.multi, c);
		c.gridy = 2;
		Interface.panel.add(Interface.options, c);
		c.gridy = 3;
		Interface.panel.add(Interface.exit, c);
		Interface.panel.updateUI();
	}

}
