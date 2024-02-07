package xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit;

import java.awt.Point;
import java.io.Serializable;

import xyz.kpzip.circuitsim.simulator.Circuit;

public class VisualConnectionPoint implements Serializable {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 430784584636811874L;
	
	private static int idCounter = 0;
	
	private int id;
	private Point position;
	
	private transient Circuit.ConnectionPoint connectionPointCache = null;
	
	public VisualConnectionPoint(Point p) {
		id = idCounter++;
		this.position = p;
	}
	
	public Circuit.ConnectionPoint getConnectionPoint(Circuit c) {
		return this.connectionPointCache = c.createConnectionPoint();
	}
	
	public Circuit.ConnectionPoint getCachedConnectionPoint() {
		return connectionPointCache;
	}
	
	public boolean hasId(int id) {
		return this.id == id;
	}
	
	public Point getPosition() {
		return position;
	}

}
