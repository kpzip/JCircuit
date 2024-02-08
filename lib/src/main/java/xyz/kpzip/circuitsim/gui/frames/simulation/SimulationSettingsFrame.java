package xyz.kpzip.circuitsim.gui.frames.simulation;

import static xyz.kpzip.circuitsim.gui.GuiInfo.ICON;
import static xyz.kpzip.circuitsim.gui.GuiInfo.NUMBER_FORMATTER;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.JButton;
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

	public SimulationSettingsFrame() {
		SimulationSettings settings = MainWindow.INSTANCE.getBoardComponent().getSettings();
		
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
		JCheckBox startat0box;
		startat0.add(startat0box = new JCheckBox((Icon)null, settings.isResetUponStart()));
		add(startat0);
		
		JPanel applyButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton button;
		applyButtonPanel.add(button = new JButton("Apply"));
		button.addActionListener((e) -> {
			settings.setSimulationTimeMs((Long) timeText.getValue());
			settings.setMaxStepMs((Long) maxtimestepText.getValue());
			settings.setResetUponStart(startat0box.isSelected());
		});
		add(applyButtonPanel);
		
		
		setVisible(true);
	}

}
