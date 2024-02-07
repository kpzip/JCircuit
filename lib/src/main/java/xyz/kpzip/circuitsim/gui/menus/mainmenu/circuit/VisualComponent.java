package xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Arrays;

import javax.swing.JButton;

import xyz.kpzip.circuitsim.gui.frames.PromptWindow;
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
		for (int i = 0; i < this.connectionPoints.length; i++) this.connectionPoints[i] = new VisualConnectionPoint(i == 0 ? new Point(position.x, position.y + type.getImage().getWidth()/2) : new Point(position.x - type.getImage().getHeight(), position.y + type.getImage().getWidth()/2));
	}
	
	public VisualComponent(Point position, VisualComponentType type, double value, int connectionPoints, VisualConnectionPoint... visualConnectionPoints) {
		this.position = position;
		this.type = type;
		this.value = value;
		this.connectionPoints = new VisualConnectionPoint[connectionPoints];
		System.arraycopy(visualConnectionPoints, 0, this.connectionPoints, 0, visualConnectionPoints.length);
	}
	
	public void draw(Graphics graphics, CircuitBoard board) {
		if (type == VisualComponentType.WIRE) {
			graphics.setColor(new Color(0, 0, 255));
			graphics.drawLine(connectionPoints[0].getPosition().x + board.getOffset().x + 32, connectionPoints[0].getPosition().y + board.getOffset().y - 32, connectionPoints[1].getPosition().x + board.getOffset().x + 32, connectionPoints[1].getPosition().y + board.getOffset().y - 32);
			return;
		}
		BufferedImage img = type.getImage();
		graphics.drawImage(img, position.x + board.getOffset().x, position.y + board.getOffset().y, board);
		
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
		
		VisualConnectionPoint clickedon = pos.y > position.y - (type.getImage().getHeight()/2) ? connectionPoints[0] : connectionPoints[1];
		if (board.getSelectedComponent() == VisualComponentType.WIRE) {
			
			if (board.getFirstWireConnectionPoint() == null) {
				board.setFirstWireConnectionPoint(clickedon);
				return;
			}
			board.addComponent(0, VisualComponentType.WIRE, clickedon, board.getFirstWireConnectionPoint());
			board.setFirstWireConnectionPoint(null);
			return;
		}
		
		
		
		
		
		
	}
	
	public Component getCircuitComponent(Circuit c) {
		Component co;
		c.addComponent(co = type.createComponent(Arrays.stream(connectionPoints).map((p) -> p.getCachedConnectionPoint()).toArray((len) -> new Circuit.ConnectionPoint[len]), value, c));
		return co;
	}
	
	public boolean isInside(Point test) {
		return test.y < position.y && test.y > position.y - type.getImage().getHeight() &&
				test.x < position.x && test.x > position.x - type.getImage().getWidth();
	}
	

}
