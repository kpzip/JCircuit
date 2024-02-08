package xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;

import xyz.kpzip.circuitsim.gui.frames.PromptWindow;
import xyz.kpzip.circuitsim.gui.menus.mainmenu.CircuitBoard;
import xyz.kpzip.circuitsim.simulator.Circuit;
import xyz.kpzip.circuitsim.simulator.components.Component;
import xyz.kpzip.circuitsim.util.SimulationGraph;

public class VisualComponent implements Serializable {
	
	/**
	 * serial id
	 */
	private static final long serialVersionUID = 1378036663200112612L;
	
	private transient Component comp = null;
	private transient SimulationGraph currentData = null;
	
	private Point position;
	private VisualComponentType type;
	private double value;
	
	private VisualConnectionPoint[] connectionPoints;
	
	private transient volatile Color color = new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat());
	
	public VisualComponent(Point position, VisualComponentType type, double value, int connectionPoints, List<VisualConnectionPoint> l) {
		this.position = position;
		this.type = type;
		this.value = value;
		this.connectionPoints = new VisualConnectionPoint[connectionPoints];
		for (int i = 0; i < this.connectionPoints.length; i++) this.connectionPoints[i] = new VisualConnectionPoint(i == 0 ? new Point(position.x + type.getImage().getWidth()/2, position.y) : new Point(position.x + type.getImage().getWidth()/2, position.y + type.getImage().getHeight()), l);
	}
	
	public VisualComponent(Point position, VisualComponentType type, double value, int connectionPoints, List<VisualConnectionPoint> l, VisualConnectionPoint... visualConnectionPoints) {
		this.position = position;
		this.type = type;
		this.value = value;
		this.connectionPoints = new VisualConnectionPoint[connectionPoints];
		System.arraycopy(visualConnectionPoints, 0, this.connectionPoints, 0, visualConnectionPoints.length);
	}
	
	public void draw(Graphics graphics, CircuitBoard board) {
		graphics.setColor(new Color(0, 0, 255));
		if (type == VisualComponentType.WIRE) {
			graphics.drawLine(connectionPoints[0].getPosition().x + board.getOffset().x, connectionPoints[0].getPosition().y + board.getOffset().y, connectionPoints[1].getPosition().x + board.getOffset().x, connectionPoints[1].getPosition().y + board.getOffset().y);
			return;
		}
		BufferedImage img = type.getImage();
		graphics.drawImage(img, position.x + board.getOffset().x, position.y + board.getOffset().y, board);
		if (type.hasValue()) {
			graphics.drawString(value + " " + type.getUnitSymbol(), position.x + type.getImage().getWidth() + board.getOffset().x + 10, position.y + type.getImage().getWidth()/2 + board.getOffset().y);
		}
		
	}
	
	public void onClick(CircuitBoard board, Point pos) {
		if (board.getSelectedComponent() == null && type.hasValue()) {
			new PromptWindow("Enter a new value for component \"" + type.getName() + "\": ", "Change value", (e) -> {
				PromptWindow window = (PromptWindow) ((JButton) e.getSource()).getParent().getParent().getParent().getParent();
				try {
    				double value = Double.parseDouble(window.getText());
    				this.value = value;
    			}
    			catch(NumberFormatException n) {
    				
    			}
				window.setVisible(false);
				window.dispose();
			});
			return;
		}
		
		VisualConnectionPoint clickedon = pos.y < position.y + (type.getImage().getHeight()/2) ? connectionPoints[0] : connectionPoints[1];
		if (board.getSelectedComponent() == VisualComponentType.WIRE) {
			
			if (board.getFirstWireConnectionPoint() == null || board.getFirstWireConnectionPoint() == clickedon) {
				board.setFirstWireConnectionPoint(clickedon);
				return;
			}
			
			if (board.containsComponent(clickedon, board.getFirstWireConnectionPoint())) {
				board.setFirstWireConnectionPoint(null);
				return;
			}
			board.addComponent(0, VisualComponentType.WIRE, clickedon, board.getFirstWireConnectionPoint());
			board.setFirstWireConnectionPoint(null);
			return;
		}
		
	}
	
	public Component getCircuitComponent(Circuit c) {
		c.addComponent(comp = type.createComponent(Arrays.stream(connectionPoints).map((p) -> p.getCachedConnectionPoint()).toArray((len) -> new Circuit.ConnectionPoint[len]), value, c));
		return comp;
	}
	
	public Component getCircuitComponentCache() {
		return comp;
	}
	
	public boolean isInside(Point test) {
		return test.y > position.y && test.y < position.y + type.getImage().getHeight() &&
				test.x > position.x && test.x < position.x + type.getImage().getWidth();
	}
	
	public SimulationGraph getCurrentData() {
		return currentData;
	}
	
	public void setCurrentData(SimulationGraph currentData) {
		this.currentData = currentData;
	}
	
	public VisualConnectionPoint[] getConnectionPoints() {
		return connectionPoints;
	}
	
	public VisualComponentType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "Type: " + type.getName() + " Connects: " + (type.getNumConnectionPoints() == 2 ? connectionPoints[0].toString() + " " + connectionPoints[1].toString() : connectionPoints[0].toString());
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean contains(VisualConnectionPoint p) {
		return Arrays.asList(connectionPoints).contains(p);
	}
	
	public boolean contains(VisualConnectionPoint[] p) {
		for (VisualConnectionPoint vp : p) {
			if (contains(vp)) return true;
		}
		return false;
	}
	
	public List<VisualConnectionPoint> commonConnectionPoints(VisualComponent other) {
		List<VisualConnectionPoint> ret = new ArrayList<VisualConnectionPoint>();
		for (VisualConnectionPoint vp : connectionPoints) {
			if (other.contains(vp)) {
				ret.add(vp);
			}
		}
		
		
		return ret;
	}
	
}
