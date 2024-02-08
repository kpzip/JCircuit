package xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit;

import java.awt.Point;
import java.io.Serializable;
import java.util.List;

import xyz.kpzip.circuitsim.simulator.Circuit;
import xyz.kpzip.circuitsim.util.SimulationGraph;

public class VisualConnectionPoint implements Serializable {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 430784584636811874L;
	
	private static int idCounter = 0;
	
	private final int id;
	private volatile Point position;
	
	private transient Circuit.ConnectionPoint connectionPointCache = null;
	private transient SimulationGraph voltageData = null;
	
	public VisualConnectionPoint(Point p, List<VisualConnectionPoint> l) {
		id = idCounter++;
		this.position = p;
		l.add(this);
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
	
	public SimulationGraph getVoltageData() {
		return voltageData;
	}
	
	public void setVoltageData(SimulationGraph voltageData) {
		this.voltageData = voltageData;
	}
	
	@Override
	public String toString() {
		return "Connection point: " + id;
	}

}
