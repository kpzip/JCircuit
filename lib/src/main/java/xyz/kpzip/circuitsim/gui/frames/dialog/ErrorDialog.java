package xyz.kpzip.circuitsim.gui.frames.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ErrorDialog extends Dialog {
	
	/**
	 * serial id
	 */
	private static final long serialVersionUID = 8009476853148381770L;

	public ErrorDialog(Frame owner, String title, String msg, boolean modal) {
		super(owner, title, modal);
		setLayout(new BorderLayout());
        add("Center", new Label(msg));
        pack();
        setVisible(true);
        this.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) { 
                
            } 
        });
	}

}
