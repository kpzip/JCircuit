package xyz.kpzip.circuitsim.gui.frames;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import xyz.kpzip.circuitsim.gui.GuiInfo;

public class MainWindow extends Frame {

	/**
	 * serializeable id
	 */
	private static final long serialVersionUID = 2465230380698728324L;
	
	public volatile boolean isClosed = false;

	public MainWindow() {
		
		this.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) 
            { 
                isClosed = true;
            } 
        });
		
		setIconImage(GuiInfo.ICON);
		setLayout(null);
		setSize(1280, 720);
		setTitle("Java Circuit Simulator");
		setVisible(true);
	}

}
