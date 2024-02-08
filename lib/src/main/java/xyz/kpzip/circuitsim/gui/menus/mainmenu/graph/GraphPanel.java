package xyz.kpzip.circuitsim.gui.menus.mainmenu.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import xyz.kpzip.circuitsim.gui.frames.MainWindow;
import xyz.kpzip.circuitsim.gui.frames.graph.GraphFrame;
import xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit.VisualComponent;

public class GraphPanel extends JPanel {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Point ORIGIN_OFFSET = new Point(25, 25);
	
	private static final double Y_SCALE = 1;
	
	private static final int RESOLUTION = 100;
	
	private volatile GraphFrame frame;
	
	public GraphPanel(GraphFrame frame) {
		this.frame = frame;
		setDoubleBuffered(true);
		setBackground(new Color(0, 0, 0));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(new Color(127, 127, 127));
		Point origin = getOrigin();
		g.drawLine(origin.x, origin.y, origin.x, origin.y - getGraphHeight());
		g.drawLine(origin.x, origin.y, origin.x + getGraphWidth(), origin.y);
		g.drawString(String.valueOf(Y_SCALE), ORIGIN_OFFSET.x, ORIGIN_OFFSET.y);
		g.drawString(String.valueOf(getXScale()), ORIGIN_OFFSET.x + getGraphWidth(), ORIGIN_OFFSET.y + getGraphHeight());
		for (VisualComponent v : frame.getVisualComponents()) {
			double prevxval = 0;
			double prevyval = v.getCurrentData().sample(prevxval);
			for (int i = 0; i < RESOLUTION; i++) {
				double xval = (((double) i)/RESOLUTION) * getXScale();
				double yval = v.getCurrentData().sample(xval);
				
//				System.out.println("x: " + xval);
//				System.out.println("y: " + yval);
//				
//				System.out.println("x prev: " + prevxval);
//				System.out.println("y prev: " + prevyval);
				
				int x1 = (int) ((prevxval/getXScale()) * getGraphWidth() + ORIGIN_OFFSET.x);
				int y1 = getHeight() - (int) ((prevyval/Y_SCALE) * getGraphHeight() + ORIGIN_OFFSET.y);
				
				int x2 = (int) ((xval/getXScale()) * getGraphWidth() + ORIGIN_OFFSET.x);
				int y2 = getHeight() - (int) ((yval/Y_SCALE) * getGraphHeight() + ORIGIN_OFFSET.y);
				
				g.setColor(/* v.getColor() */ new Color(0, 255, 0));
				if (y1 >= 0 && y2 >= 0) g.drawLine(x1, y1, x2, y2);
				prevxval = xval;
				prevyval = yval;
			}
		}
		
	}
	
	
	private Point getOrigin() {
		Point orig = new Point(0, this.getHeight());
		orig.translate(ORIGIN_OFFSET.x, -ORIGIN_OFFSET.y);
		return orig;
	}
	
	private int getGraphWidth() {
		return this.getWidth() - 2 * ORIGIN_OFFSET.x;
	}
	
	private int getGraphHeight() {
		return this.getHeight() - 2 * ORIGIN_OFFSET.y;
	}
	
	private double getXScale() {
		return MainWindow.INSTANCE.getBoardComponent().getSettings().getSimulationTimeMs() / 1000d;
	}

}
