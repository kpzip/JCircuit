package xyz.kpzip.circuitsim.gui;

import java.awt.Image;
import java.awt.Toolkit;

public final class GuiInfo {

	private GuiInfo() {}
	
	public static final Image ICON = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("assets/icon.png"));  

}
