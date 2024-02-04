package xyz.kpzip.circuitsim.gui.menus;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import xyz.kpzip.circuitsim.gui.menus.file.FileMenu;
import xyz.kpzip.circuitsim.gui.menus.simulation.SimulationMenu;

public class MainMenuBar extends JMenuBar {
	
	/**
	 * Serializeable ids
	 */
	private static final long serialVersionUID = 1L;

	public MainMenuBar() {
		
		add(new FileMenu());
		add(new JMenu("Settings"));
		add(new SimulationMenu());
	}
	
}
