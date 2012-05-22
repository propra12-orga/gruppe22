import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Options {

	public static void OptionsMenu() {
		Interface.panel.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.panel.add(Interface.sound, c);
		c.gridy = 1;
		Interface.panel.add(Interface.controls, c);
		c.gridy = 2;
		Interface.panel.add(Interface.backtomain, c);
		Interface.panel.updateUI();

	}

}
