package xyz.kpzip.circuitsim.gui.frames.graph;

import static xyz.kpzip.circuitsim.gui.GuiInfo.ICON;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import xyz.kpzip.circuitsim.gui.frames.MainWindow;
import xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit.VisualComponent;
import xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit.VisualConnectionPoint;
import xyz.kpzip.circuitsim.gui.menus.mainmenu.graph.GraphPanel;

public class GraphFrame extends JFrame {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = -8743452709775552061L;
	
	private List<VisualComponent> components = new ArrayList<VisualComponent>();
	private List<VisualConnectionPoint> connectionPoints = new ArrayList<VisualConnectionPoint>();

	public GraphFrame() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setIconImage(ICON);
		setTitle("Simulation Result");
		setLocationRelativeTo(MainWindow.INSTANCE);
		setBackground(new Color(0, 0, 0));
		setLayout(new BorderLayout());
		add(new GraphPanel(this), BorderLayout.CENTER);
		components.addAll(MainWindow.INSTANCE.getBoardComponent().getComponentSprites());
		
		setVisible(true);
	}
	
	public List<VisualComponent> getVisualComponents() {
		return components;
	}
	
	public List<VisualConnectionPoint> getConnectionPoints() {
		return connectionPoints;
	}

}
