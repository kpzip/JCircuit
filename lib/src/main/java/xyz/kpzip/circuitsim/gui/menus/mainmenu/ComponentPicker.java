package xyz.kpzip.circuitsim.gui.menus.mainmenu;

import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import xyz.kpzip.circuitsim.gui.frames.MainWindow;
import xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit.VisualComponentType;

public class ComponentPicker extends JPanel {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = 1L;

	public ComponentPicker() {
		setDoubleBuffered(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setLayout(new GridLayout(20, 1));
		
		JButton resistor = new ComponentButton("Resistor", VisualComponentType.RESISTOR);
		JButton wire = new ComponentButton("Wire", VisualComponentType.WIRE);
		JButton battery = new ComponentButton("Battery", VisualComponentType.BATTERY);
		JButton gnd = new ComponentButton("Ground", VisualComponentType.GROUND);
		JButton none = new ComponentButton("Clear Selection", null);
		
		add(resistor);
		add(wire);
		add(battery);
		add(gnd);
		add(none);
		
		setVisible(true);
	}
	
	private static class ComponentButton extends JButton {
		
		/**
		 * serial id
		 */
		private static final long serialVersionUID = -5434754078448829944L;

		public ComponentButton(String name, VisualComponentType type) {
			super(name);
			addActionListener((e) -> {
				MainWindow.INSTANCE.getBoardComponent().setComponentSelectionType(type);
			});
		}
		
	}

}
