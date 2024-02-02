package xyz.kpzip.circuitsim.gui.frames.menus.mainmenu;

import java.awt.Menu;
import java.awt.MenuBar;

public class MainMenuBar extends MenuBar {
	
	/**
	 * Serializeable ids
	 */
	private static final long serialVersionUID = 1L;

	public MainMenuBar() {
		
		add(new FileMenu());
		add(new Menu("Settings"));
		add(new Menu("Simulation"));
	}
	
}
