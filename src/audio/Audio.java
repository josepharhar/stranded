package audio;

import gui.StrandedApplet;

import net.beadsproject.beads.core.*;
import net.beadsproject.beads.data.SampleManager;
import net.beadsproject.beads.ugens.*;

public class Audio {
    
    AudioContext ac;
    String mainSource;
    SamplePlayer mainPlayer;
    Gain g;
    Glide gainValue;
    
    private StrandedApplet applet;

    public Audio(StrandedApplet applet) {
        this.applet = applet;
        this.g = new Gain(ac, 2, 0.2f);
        this.mainSource = "resources/Stranded_Theme.wav";
        
        
    }

    public void startMainAudio() {
        mainPlayer = new SamplePlayer(ac, SampleManager.sample(mainSource));
        gainValue.setValue((float)1/(float)1);
        mainPlayer.setToLoopStart();
        mainPlayer.setKillOnEnd(false);
        ac.out.addInput(mainPlayer);
        ac.start();
    }
}
