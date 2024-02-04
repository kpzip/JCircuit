package xyz.kpzip.circuitsim.gui.menus.mainmenu;

import static xyz.kpzip.circuitsim.gui.GuiInfo.*;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

public class CircuitBoard extends JPanel {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = -3931746356626980713L;
	
	public static final int BOUNDS = 1000;
	
	private volatile Point offset = new Point(0, 0);
	
	private volatile Point mousePt;

	public CircuitBoard(Dimension size) {
		setDoubleBuffered(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setSize(size);
		
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            	
                mousePt = e.getPoint();
                //repaint();
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
    }

}
