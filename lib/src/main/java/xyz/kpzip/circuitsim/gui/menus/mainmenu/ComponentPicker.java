package xyz.kpzip.circuitsim.gui.menus.mainmenu;

import java.awt.Cursor;

import javax.swing.JPanel;

public class ComponentPicker extends JPanel {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 1L;

	public ComponentPicker() {
		setDoubleBuffered(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		
		
		setVisible(true);
	}

}
