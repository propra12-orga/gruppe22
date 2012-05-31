import java.awt.*;

public class IngameMenu {

	public static void ingame() {
		Interface.menu.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.menu.add(Interface.continueGame, c);
		c.gridy = 1;
		Interface.menu.add(Interface.backtomain, c);
		Interface.menu.updateUI();
	}
}
