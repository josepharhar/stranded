package audio;

import java.io.IOException;

import gui.StrandedApplet;
import net.beadsproject.beads.core.*;
import net.beadsproject.beads.data.Sample;
import net.beadsproject.beads.data.SampleManager;
import net.beadsproject.beads.ugens.*;

public class Audio {
    
    AudioContext ac;
    String mainSource;
    SamplePlayer mainPlayer;
    Gain g;
    
    private StrandedApplet applet;

    public Audio(StrandedApplet applet) {
        this.applet = applet;
        //this.ac = new AudioContext();
        this.mainSource = "Stranded_Theme_Ext.wav";
        
        
    }
    
    public void testAudio() throws IOException {
        AudioContext ac;

        ac = new AudioContext();
        
        String audioFile = "Stranded_Theme_Ext.wav";
        SamplePlayer player = new SamplePlayer(ac, new Sample(audioFile));
        ac.out.addInput(player);
        ac.start();
        
    }

    public void startMainAudio() throws IOException {
        ac = new AudioContext();
        mainPlayer = new SamplePlayer(ac, new Sample(mainSource));
        //mainPlayer.setToLoopStart();
        //mainPlayer.setKillOnEnd(false);
        //mainPlayer.start();
        g = new Gain(ac, 2, 2);
        g.addInput(mainPlayer);
        ac.out.addInput(mainPlayer);
        ac.start();
    }
    
    public void updateBeep() {
        SamplePlayer beep = new SamplePlayer(ac, SampleManager.sample("Status_Update.wav"));
        ac.out.addInput(beep);
    }
    
    public void sideBeep() {
        SamplePlayer Sbeep = new SamplePlayer(ac, SampleManager.sample("Menu_Side_Beep.wav"));
        ac.out.addInput(Sbeep);
    }
    
    public void centerBeep() {
        SamplePlayer Mbeep = new SamplePlayer(ac, SampleManager.sample("Menu_Center_Beep.wav"));
        ac.out.addInput(Mbeep);
    }
}
