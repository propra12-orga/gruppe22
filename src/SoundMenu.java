import java.awt.GridBagConstraints;
import java.awt.Insets;


public class SoundMenu {

	static GridBagConstraints c = new GridBagConstraints();
	
	public static void Sound() {
		Interface.menu.removeAll();
		c.insets = new Insets(10, 10, 10, 10);
		c.gridx = 1;
		c.gridy = 0;
		Interface.menu.add(Interface.soundOn, c);
		c.gridy = 1;
		Interface.menu.add(Interface.soundOff, c);
		c.gridy = 2;
		Interface.menu.add(Interface.backtooptions, c);
		Interface.menu.updateUI();
		
	}

	

}
