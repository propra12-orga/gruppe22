import java.awt.*;
/**
 * Unterklasse fuer das SteuerungsMenue
 * @author Bastian Siefen
 *
 */
public class Control {
	static GridBagConstraints c = new GridBagConstraints();
	
	/**
	 * Cleart das Panel und befuellt es mit den Steuerungs Widgets neu,
	 * wird ausgefuehrt wenn im OptionsMenue auf Steuerung geklickt wurde.
	 */
	public static void ControlMenu() {
		Interface.menu.removeAll();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		Interface.menu.add(Interface.player1, c);
		c.gridy = 1;
		Interface.menu.add(Interface.up1, c);
		c.gridy = 2;
		Interface.menu.add(Interface.down1, c);
		c.gridy = 3;
		Interface.menu.add(Interface.right1, c);
		c.gridy = 4;
		Interface.menu.add(Interface.left1, c);
		c.gridy = 5;
		Interface.menu.add(Interface.bomb1, c);
		c.gridx = 1;
		c.gridy = 1;
		Interface.menu.add(Interface.getUp1, c);
		c.gridy = 2;
		Interface.menu.add(Interface.getDown1, c);
		c.gridy = 3;
		Interface.menu.add(Interface.getRight1, c);
		c.gridy = 4;
		Interface.menu.add(Interface.getLeft1, c);
		c.gridy = 5;
		Interface.menu.add(Interface.getBomb1, c);
		c.gridx = 2;
		c.gridy = 0;
		Interface.menu.add(Interface.player2, c);
		c.gridy = 1;
		Interface.menu.add(Interface.up2, c);
		c.gridy = 2;
		Interface.menu.add(Interface.down2, c);
		c.gridy = 3;
		Interface.menu.add(Interface.right2, c);
		c.gridy = 4;
		Interface.menu.add(Interface.left2, c);
		c.gridy = 5;
		Interface.menu.add(Interface.bomb2, c);
		c.gridx = 3;
		c.gridy = 1;
		Interface.menu.add(Interface.getUp2, c);
		c.gridy = 2;
		Interface.menu.add(Interface.getDown2, c);
		c.gridy = 3;
		Interface.menu.add(Interface.getRight2, c);
		c.gridy = 4;
		Interface.menu.add(Interface.getLeft2, c);
		c.gridy = 5;
		Interface.menu.add(Interface.getBomb2, c);
		c.gridx = 1;
		c.gridy = 6;
		Interface.menu.add(Interface.save, c);
		c.gridx = 2;
		Interface.menu.add(Interface.backtooptions, c);
		Interface.menu.updateUI();

	}
	
	/**
	 * Cleart das Panel und befuellt es mit den Steuerungs Widgets neu,
	 * wird ausgefuehrt wenn im MultiplayerMenue auf Steuerung geklickt wurde.
	 */
	public static void ControlMenu2() {
		Interface.menu.removeAll();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 0;
		c.gridy = 0;
		Interface.menu.add(Interface.player1, c);
		c.gridy = 1;
		Interface.menu.add(Interface.up1, c);
		c.gridy = 2;
		Interface.menu.add(Interface.down1, c);
		c.gridy = 3;
		Interface.menu.add(Interface.right1, c);
		c.gridy = 4;
		Interface.menu.add(Interface.left1, c);
		c.gridy = 5;
		Interface.menu.add(Interface.bomb1, c);
		c.gridx = 1;
		c.gridy = 1;
		Interface.menu.add(Interface.getUp1, c);
		c.gridy = 2;
		Interface.menu.add(Interface.getDown1, c);
		c.gridy = 3;
		Interface.menu.add(Interface.getRight1, c);
		c.gridy = 4;
		Interface.menu.add(Interface.getLeft1, c);
		c.gridy = 5;
		Interface.menu.add(Interface.getBomb1, c);
		c.gridx = 2;
		c.gridy = 0;
		Interface.menu.add(Interface.player2, c);
		c.gridy = 1;
		Interface.menu.add(Interface.up2, c);
		c.gridy = 2;
		Interface.menu.add(Interface.down2, c);
		c.gridy = 3;
		Interface.menu.add(Interface.right2, c);
		c.gridy = 4;
		Interface.menu.add(Interface.left2, c);
		c.gridy = 5;
		Interface.menu.add(Interface.bomb2, c);
		c.gridx = 3;
		c.gridy = 1;
		Interface.menu.add(Interface.getUp2, c);
		c.gridy = 2;
		Interface.menu.add(Interface.getDown2, c);
		c.gridy = 3;
		Interface.menu.add(Interface.getRight2, c);
		c.gridy = 4;
		Interface.menu.add(Interface.getLeft2, c);
		c.gridy = 5;
		Interface.menu.add(Interface.getBomb2, c);
		c.gridx = 1;
		c.gridy = 6;
		Interface.menu.add(Interface.save, c);
		c.gridx = 2;
		Interface.menu.add(Interface.backtomulti, c);
		Interface.menu.updateUI();

	}

	
}
