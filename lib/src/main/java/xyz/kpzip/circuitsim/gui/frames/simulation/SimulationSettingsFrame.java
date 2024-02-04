package xyz.kpzip.circuitsim.gui.frames.simulation;

import static xyz.kpzip.circuitsim.gui.GuiInfo.*;

import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import xyz.kpzip.circuitsim.gui.frames.MainWindow;
import xyz.kpzip.circuitsim.gui.menus.simulation.SimulationSettings;

public class SimulationSettingsFrame extends JFrame {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 2750943814802799936L;
	
	private SimulationSettings settings;

	public SimulationSettingsFrame() {
		settings = new SimulationSettings();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(520, 800);
		setIconImage(ICON);
		setTitle("Simulation Options");
		setLocationRelativeTo(MainWindow.INSTANCE);
		
		setLayout(new GridLayout(20, 1));
		
		JPanel simulTime = new JPanel(new GridLayout(1, 0));
		simulTime.add(new JLabel("Simulation Time (Milliseconds)"));
		
		JFormattedTextField timeText = new JFormattedTextField(NUMBER_FORMATTER);
		timeText.setValue(settings.getSimulationTimeMs());
		simulTime.add(timeText);
		add(simulTime);
		
		
		JPanel maxtimestep = new JPanel(new GridLayout(1, 0));
		maxtimestep.add(new JLabel("Maximum Time Step (Milliseconds)"));
		
		JFormattedTextField maxtimestepText = new JFormattedTextField(NUMBER_FORMATTER);
		maxtimestepText.setValue(settings.getMaxStepMs());
		maxtimestep.add(maxtimestepText);
		add(maxtimestep);
		
		JPanel startat0 = new JPanel(new GridLayout(1, 0));
		startat0.add(new JLabel("Start all sources at 0V?"));
		
		startat0.add(new JCheckBox((Icon)null, settings.isResetUponStart()));
		add(startat0);
		
		
		setVisible(true);
	}

}
