package xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Arrays;

import xyz.kpzip.circuitsim.gui.menus.mainmenu.CircuitBoard;
import xyz.kpzip.circuitsim.simulator.Circuit;
import xyz.kpzip.circuitsim.simulator.components.Component;

public class VisualComponent implements Serializable {
	
	/**
	 * serial id
	 */
	private static final long serialVersionUID = 1378036663200112612L;
	
	private Point position;
	private VisualComponentType type;
	private double value;
	
	private VisualConnectionPoint[] connectionPoints;
	
	public VisualComponent(Point position, VisualComponentType type, double value, int connectionPoints) {
		this.position = position;
		this.type = type;
		this.value = value;
		this.connectionPoints = new VisualConnectionPoint[connectionPoints];
		for (int i = 0; i < this.connectionPoints.length; i++) this.connectionPoints[i] = new VisualConnectionPoint();
	}
	
	public VisualComponent(Point position, VisualComponentType type, double value, int connectionPoints, VisualConnectionPoint... visualConnectionPoints) {
		this.position = position;
		this.type = type;
		this.value = value;
		this.connectionPoints = new VisualConnectionPoint[connectionPoints];
		System.arraycopy(visualConnectionPoints, 0, this.connectionPoints, 0, visualConnectionPoints.length);
	}
	
	public void draw(Graphics graphics, CircuitBoard board) {
		BufferedImage img = type.getImage();
		graphics.drawImage(img, position.x + board.getOffset().x, position.y + board.getOffset().y, board);
		
	}
	
	public void onClick() {
		
	}
	
	public Component getCircuitComponent(Circuit c) {
		return type.createComponent(Arrays.stream(connectionPoints).map((p) -> p.getCachedConnectionPoint()).toArray((len) -> new Circuit.ConnectionPoint[len]), value, c);
	}
	

}
