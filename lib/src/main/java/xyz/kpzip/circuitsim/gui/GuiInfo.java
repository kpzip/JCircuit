package xyz.kpzip.circuitsim.gui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.text.NumberFormatter;

public final class GuiInfo {
	
	
	public static final Image ICON = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("assets/icon.png"));  
	
	public static final BufferedImage RESISTOR_TEXTURE = readUnsafe(ClassLoader.getSystemResource("assets/resistor.png"));
	public static final BufferedImage BATTERY_TEXTURE = readUnsafe(ClassLoader.getSystemResource("assets/battery.png"));
	public static final BufferedImage GND_TEXTURE = readUnsafe(ClassLoader.getSystemResource("assets/ground.png"));
	public static final BufferedImage WIRE_TEXTURE = readUnsafe(ClassLoader.getSystemResource("assets/wire.png"));
	public static final BufferedImage BACKGROUND_TEXTURE = readUnsafe(ClassLoader.getSystemResource("assets/bg.png"));
	
	
	public static final NumberFormatter NUMBER_FORMATTER;
	
	static {
		NUMBER_FORMATTER = new NumberFormatter(NumberFormat.getInstance());
		NUMBER_FORMATTER.setValueClass(Long.class);
		NUMBER_FORMATTER.setMinimum(0L);
		NUMBER_FORMATTER.setMaximum(Long.MAX_VALUE);
		NUMBER_FORMATTER.setAllowsInvalid(false);
		NUMBER_FORMATTER.setCommitsOnValidEdit(true);
	}
	
	private GuiInfo() {}
	
	private static BufferedImage readUnsafe(URL url) {
		try {
			return ImageIO.read(url);
		} catch (IOException e) {
			//failing to load resources is fatal
			e.printStackTrace();
			System.exit(-1);
			return null;
		}
	}

}
