package xyz.kpzip.circuitsim.gui.menus.mainmenu;

import static xyz.kpzip.circuitsim.gui.GuiInfo.BACKGROUND_TEXTURE;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import xyz.kpzip.circuitsim.gui.frames.MainWindow;
import xyz.kpzip.circuitsim.gui.frames.PromptWindow;
import xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit.VisualComponent;
import xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit.VisualComponentType;
import xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit.VisualConnectionPoint;
import xyz.kpzip.circuitsim.gui.menus.simulation.SimulationSettings;
import xyz.kpzip.circuitsim.simulator.Circuit;
import xyz.kpzip.circuitsim.simulator.components.Abstract2NodeComponent;
import xyz.kpzip.circuitsim.simulator.components.Component;
import xyz.kpzip.circuitsim.util.SimulationGraph;

public class CircuitBoard extends JPanel implements Serializable {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = -3931746356626980713L;
	
	public transient static final int BOUNDS = 1000;
	
	private transient volatile Point offset = new Point(0, 0);
	private transient volatile Point mousePt;
	
	private transient volatile VisualComponentType selectedComponent = null;
	private transient volatile VisualConnectionPoint firstWireConnectionPoint;
	
	private volatile List<VisualComponent> componentSprites = new ArrayList<VisualComponent>();
	private volatile List<VisualConnectionPoint> connections = new ArrayList<VisualConnectionPoint>();
	
	private volatile SimulationSettings settings = new SimulationSettings();

	public CircuitBoard() {
		setDoubleBuffered(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	
                mousePt = e.getPoint();
                
                //TODO check if pre-existing components were clicked on
                for (int i = 0; i < componentSprites.size(); i++) {
                	VisualComponent c = componentSprites.get(i);
                	Point pos = (Point) mousePt.clone();
                	pos.translate(-offset.x, -offset.y);
                	if(c.isInside(pos)) {
                		if (selectedComponent == VisualComponentType.DELETE) {
                			componentSprites.remove(i);
                			List<VisualConnectionPoint> toRemove = new LinkedList<VisualConnectionPoint>();
                			toRemove.addAll(Arrays.asList(c.getConnectionPoints()));
                			for (int j = 0; j < componentSprites.size(); j++) {
                				VisualComponent c2 = componentSprites.get(j);
                				if (c2.getType() == VisualComponentType.WIRE && c2.contains(c.getConnectionPoints())) {
                					componentSprites.remove(j);
                					j--;
                					continue;
                				}
                				toRemove.removeAll(c2.commonConnectionPoints(c));
               
                			}
                			connections.removeAll(toRemove);
                			return;
                		}
                		c.onClick(CircuitBoard.this, mousePt);
                	}
                }
                if (selectedComponent == VisualComponentType.WIRE || selectedComponent == VisualComponentType.DELETE) {
                	return;
                }
                
                if (selectedComponent != null) {
                	if (selectedComponent.hasValue()) {
                		new PromptWindow("Enter a value for component \"" + selectedComponent + "\": ", "Enter value", (ee) -> {
                			PromptWindow window = (PromptWindow) ((JButton) ee.getSource()).getParent().getParent().getParent().getParent();
                			try {
                				double value = Double.parseDouble(window.getText());
                				MainWindow.INSTANCE.getBoardComponent().addComponent(value);
                			}
                			catch(NumberFormatException n) {
                				
                			}
                			window.setVisible(false);
                			window.dispose();
                		});
                	} else {
                		addComponent(0);
                	}
                }
                
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - mousePt.x;
                int dy = e.getY() - mousePt.y;
                offset.setLocation(offset.x + dx, offset.y + dy);
                if (offset.x > 0) offset.setLocation(0, offset.y);
                if (offset.y > 0) offset.setLocation(offset.x, 0);
                if (offset.x < -BOUNDS) offset.setLocation(-BOUNDS, offset.y);
                if (offset.y < -BOUNDS) offset.setLocation(offset.x, -BOUNDS);
                mousePt = e.getPoint();
                repaint();
            }
        });
        setVisible(true);
	}
	
	public void addComponent(double value) {
		addComponent(value, selectedComponent);
	}
	
	public void addComponent(double value, VisualComponentType type) {
		Point pos = (Point) mousePt.clone();
		pos.translate(-offset.x, -offset.y);
		componentSprites.add(new VisualComponent(pos, type, value, type.getNumConnectionPoints(), connections));
		repaint();
	}
	
	public void addComponent(double value, VisualComponentType type, VisualConnectionPoint... points) {
		Point pos = (Point) mousePt.clone();
		pos.translate(offset.x, offset.y);
		componentSprites.add(new VisualComponent(pos, type, value, type.getNumConnectionPoints(), connections, points));
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
        int width = getWidth();
        int height = getHeight();
        int imageW = BACKGROUND_TEXTURE.getWidth(this);
        int imageH = BACKGROUND_TEXTURE.getHeight(this);
  
        // Tile the image to fill our area.
        for (int x = (int) offset.getX(); x < width; x += imageW) {
            for (int y = (int) offset.getY(); y < height; y += imageH) {
                g.drawImage(BACKGROUND_TEXTURE, x, y, this);
            }
        }
        componentSprites.forEach((c) -> c.draw(g, this));
    }
	
	public VisualComponentType getSelectedComponent() {
		return selectedComponent;
	}
	
	public void setComponentSelectionType(VisualComponentType type) {
		this.selectedComponent = type;
		if (type == null) {
			firstWireConnectionPoint = null;
		}
	}
	
	public Point getOffset() {
		return offset;
	}
	
	public void setFirstWireConnectionPoint(VisualConnectionPoint point) {
		this.firstWireConnectionPoint = point;
	}
	
	public VisualConnectionPoint getFirstWireConnectionPoint() {
		return firstWireConnectionPoint;
	}
	
	public Circuit buildCircuit() {
		Circuit c = new Circuit();
		connections.forEach((con) -> con.getConnectionPoint(c));
		componentSprites.forEach((com) -> com.getCircuitComponent(c));
		return c;
	}
	
	public void simulate() {
		Circuit c = buildCircuit();
		double time_seconds = 0;
		final double time_final1 = time_seconds;
		connections.forEach((con) -> con.setVoltageData(new SimulationGraph()));
		componentSprites.forEach((com) -> com.setCurrentData(new SimulationGraph()));
		if (settings.isResetUponStart()) {
			connections.forEach((con) -> con.getVoltageData().addValue(time_final1, 0));
			componentSprites.forEach((com) -> com.getCurrentData().addValue(time_final1, 0));
		}
		while (time_seconds < settings.getSimulationTimeMs() / 1000.0d) {
			double timestep = settings.getMaxStepMs() / 1000.0d;
			c.simulationStep(timestep);
			time_seconds += timestep;
			final double time_final = time_seconds;
			c.getConnectionPoints().forEach((con) -> {
				VisualConnectionPoint v = getVisualConnectionPoint(con);
				if (v != null) {
					v.getVoltageData().addValue(time_final, con.getVoltage());
				}
			});
			c.getComponents().forEach((com) -> {
				VisualComponent v = getVisualComponent(com);
				v.getCurrentData().addValue(time_final, ((Abstract2NodeComponent) com).getCurrent());
			});
		}
	}
	
	public VisualComponent getVisualComponent(Component c) {
		for (VisualComponent v : componentSprites) {
			if (v.getCircuitComponentCache() == c) {
				return v;
			}
		}
		return null;
	}
	
	public VisualConnectionPoint getVisualConnectionPoint(Circuit.ConnectionPoint point) {
		for (VisualConnectionPoint v : connections) {
			if (v.getCachedConnectionPoint() == point) {
				return v;
			}
		}
		return null;
	}
	
	public SimulationSettings getSettings() {
		return this.settings;
	}
	
	public boolean containsComponent(VisualConnectionPoint c1, VisualConnectionPoint c2) {
		for (VisualComponent p : componentSprites) {
			VisualConnectionPoint[] cpoints = p.getConnectionPoints();
			if (p.getType().getNumConnectionPoints() == 2 && (cpoints[0] == c1 || cpoints[0] == c2) && (cpoints[1] == c1 || cpoints[1] == c2)) {
				return true;
			}
		}
		return false;
	}
	
	public List<VisualComponent> getComponentSprites() {
		return componentSprites;
	}

}
