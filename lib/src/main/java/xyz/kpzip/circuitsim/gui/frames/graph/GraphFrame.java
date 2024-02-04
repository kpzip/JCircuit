package xyz.kpzip.circuitsim.gui.frames.graph;

import static xyz.kpzip.circuitsim.gui.GuiInfo.*;

import javax.swing.JFrame;

import xyz.kpzip.circuitsim.gui.frames.MainWindow;

public class GraphFrame extends JFrame {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = -8743452709775552061L;

	public GraphFrame() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setIconImage(ICON);
		setTitle("Simulation Result");
		setLocationRelativeTo(MainWindow.INSTANCE);
		
		setVisible(true);
	}

}
