package xyz.kpzip.circuitsim.gui.menus.simulation;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import xyz.kpzip.circuitsim.gui.frames.simulation.SimulationSettingsFrame;

public class SimulationMenu extends JMenu {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = -3711119056257226207L;
	
	public SimulationMenu() {
		super("Simulation");
		add(new SimulationSettingsMenuItem());
		add(new RunSimulationMenuItem());
	}
	
	private static class SimulationSettingsMenuItem extends JMenuItem {

		/**
		 * serial id
		 */
		private static final long serialVersionUID = 3187381618574987013L;
		
		public SimulationSettingsMenuItem() {
			super("Simulation Settings");
			addActionListener((actionlistener) -> {
				new SimulationSettingsFrame();
			});
		}
		
	}
	
	private static class RunSimulationMenuItem extends JMenuItem {

		/**
		 * serial id
		 */
		private static final long serialVersionUID = 3187381618574987013L;
		
		public RunSimulationMenuItem() {
			super("Run Simulation");
			addActionListener((actionlistener) -> {
				//TODO start simulation
			});
		}
		
	}
	

}
