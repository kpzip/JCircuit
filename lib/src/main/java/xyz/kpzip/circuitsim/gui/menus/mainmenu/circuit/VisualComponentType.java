package xyz.kpzip.circuitsim.gui.menus.mainmenu.circuit;

import java.awt.image.BufferedImage;

import xyz.kpzip.circuitsim.gui.GuiInfo;
import xyz.kpzip.circuitsim.simulator.Circuit;
import xyz.kpzip.circuitsim.simulator.components.Component;
import xyz.kpzip.circuitsim.simulator.components.passive.Resistor;
import xyz.kpzip.circuitsim.simulator.components.source.Battery;
import xyz.kpzip.circuitsim.simulator.components.switches.SPSTSwitch;

public enum VisualComponentType {
	RESISTOR((points, value, c) -> {
		return new Resistor(points[0], points[1], value);
	}, 2, true, "Resistor", GuiInfo.RESISTOR_TEXTURE, "Ohm"),
	WIRE((points, value, c) -> {
		return new SPSTSwitch(points[0], points[1], true);
	}, 2, false, "Wire", GuiInfo.WIRE_TEXTURE, ""),
	BATTERY((points, value, c) -> {
		return new Battery(points[0], points[1], value);
	}, 2, true, "Battery", GuiInfo.BATTERY_TEXTURE, "V"),
	GROUND((points, value, c) -> {
		return new SPSTSwitch(points[0], c.getGround(), true);
	}, 1, false, "Ground Connection", GuiInfo.GND_TEXTURE, ""),
	DELETE(null, 0, false, null, null, null);
	
	private volatile ComponentFactory<?> factory;
	private volatile int connectionPoints;
	private volatile boolean hasvalue;
	private volatile String name;
	private volatile BufferedImage img;
	private volatile String unitSymbol;
	
	VisualComponentType(ComponentFactory<?> factory, int connectionPoints, boolean hasvalue, String name, BufferedImage display, String unitSymbol) {
		this.factory = factory;
		this.connectionPoints = connectionPoints;
		this.hasvalue = hasvalue;
		this.name = name;
		this.img = display;
		this.unitSymbol = unitSymbol;
	}

	public Component createComponent(Circuit.ConnectionPoint[] points, double value, Circuit c) {
		return factory.createComponent(points, value, c);
	}
	
	public int getNumConnectionPoints() {
		return this.connectionPoints;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean hasValue() {
		return hasvalue;
	}
	
	public BufferedImage getImage() {
		return img;
	}
	
	public String getUnitSymbol() {
		return unitSymbol;
	}
	
	@FunctionalInterface
	public interface ComponentFactory<T extends Component> {
		
		Component createComponent(Circuit.ConnectionPoint[] points, double value, Circuit c);
		
	}

}
