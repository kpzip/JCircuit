package xyz.kpzip.circuitsim.gui.menus.mainmenu;

import static xyz.kpzip.circuitsim.gui.GuiInfo.BACKGROUND_TEXTURE;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import xyz.kpzip.circuitsim.gui.frames.PromptWindow;
import xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit.VisualComponent;
import xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit.VisualComponentType;

public class CircuitBoard extends JPanel implements Serializable {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = -3931746356626980713L;
	
	public transient static final int BOUNDS = 1000;
	
	private transient volatile Point offset = new Point(0, 0);
	private transient volatile Point mousePt;
	
	private transient volatile VisualComponentType selectedComponent = null;
	
	private volatile List<VisualComponent> componentSprites = new ArrayList<VisualComponent>();

	public CircuitBoard(Dimension size) {
		setDoubleBuffered(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		setSize(size);
		
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	
                mousePt = e.getPoint();
                
                if (selectedComponent != null) {
                	if (selectedComponent.hasValue()) {
                		new PromptWindow("Enter a value for component \"" + selectedComponent + "\": ", "Enter value");
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
                if (offset.x > BOUNDS) offset.setLocation(BOUNDS, offset.y);
                if (offset.y > BOUNDS) offset.setLocation(offset.x, BOUNDS);
                if (offset.x < -BOUNDS) offset.setLocation(-BOUNDS, offset.y);
                if (offset.y < -BOUNDS) offset.setLocation(offset.x, -BOUNDS);
                mousePt = e.getPoint();
                repaint();
            }
        });
        setVisible(true);
	}
	
	public void addComponent(double value) {
		Point pos = (Point) mousePt.clone();
		pos.translate(offset.x, offset.y);
		componentSprites.add(new VisualComponent(pos, selectedComponent, value, selectedComponent.getNumConnectionPoints()));
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
	
	public void setComponentSelectionType(VisualComponentType type) {
		this.selectedComponent = type;
	}
	
	public Point getOffset() {
		return offset;
	}

}
