import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Control {

	public static void ControlMenu() {
		Interface.panel.removeAll();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		Interface.panel.add(Interface.player1, c);
		c.gridy = 1;
		Interface.panel.add(Interface.up1, c);
		c.gridy = 2;
		Interface.panel.add(Interface.down1, c);
		c.gridy = 3;
		Interface.panel.add(Interface.right1, c);
		c.gridy = 4;
		Interface.panel.add(Interface.left1, c);
		c.gridy = 5;
		Interface.panel.add(Interface.bomb1, c);
		c.gridx = 1;
		c.gridy = 1;
		Interface.panel.add(Interface.getUp1, c);
		c.gridy = 2;
		Interface.panel.add(Interface.getDown1, c);
		c.gridy = 3;
		Interface.panel.add(Interface.getRight1, c);
		c.gridy = 4;
		Interface.panel.add(Interface.getLeft1, c);
		c.gridy = 5;
		Interface.panel.add(Interface.getBomb1, c);
		c.gridx = 2;
		c.gridy = 0;
		Interface.panel.add(Interface.player2, c);
		c.gridy = 1;
		Interface.panel.add(Interface.up2, c);
		c.gridy = 2;
		Interface.panel.add(Interface.down2, c);
		c.gridy = 3;
		Interface.panel.add(Interface.right2, c);
		c.gridy = 4;
		Interface.panel.add(Interface.left2, c);
		c.gridy = 5;
		Interface.panel.add(Interface.bomb2, c);
		c.gridx = 3;
		c.gridy = 1;
		Interface.panel.add(Interface.getUp2, c);
		c.gridy = 2;
		Interface.panel.add(Interface.getDown2, c);
		c.gridy = 3;
		Interface.panel.add(Interface.getRight2, c);
		c.gridy = 4;
		Interface.panel.add(Interface.getLeft2, c);
		c.gridy = 5;
		Interface.panel.add(Interface.getBomb2, c);
		c.gridx = 1;
		c.gridy = 6;
		Interface.panel.add(Interface.save, c);
		c.gridx = 2;
		Interface.panel.add(Interface.backtooptions, c);
		Interface.panel.updateUI();

	}

}
