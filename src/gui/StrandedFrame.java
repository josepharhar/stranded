package gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StrandedFrame extends Frame {
    
    public static void main(String[] args) {
        
        StrandedFrame frame = new StrandedFrame();
        
        StrandedApplet applet = new StrandedApplet(frame);
        
        frame.add(applet, BorderLayout.CENTER);
        
        //make close button work
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        //no resizing... for now ;)
        frame.setResizable(false);
        
        //processing initialization
        applet.init();
        
        frame.setVisible(true);
    }
    
    public StrandedFrame() {
        super("Stranded");
    }
}
