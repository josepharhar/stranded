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
        this.g = new Gain(ac, 2, 2);
        this.mainSource = "Stranded_Theme.wav";
        
        
    }
    
    public void testAudio() throws IOException {
        AudioContext ac;

        ac = new AudioContext();
        /*
         * Here's how to play back a sample.
         * 
         * The first line gives you a way to choose the audio file. The
         * (commented, optional) second line allows you to stream the audio
         * rather than loading it all at once. The third line creates a sample
         * player and loads in the Sample. SampleManager is a utility which
         * keeps track of loaded audio files according to their file names, so
         * you don't have to load them again.
         */
        String audioFile = "Stranded_Theme_Ext.wav";
        // SampleManager.setBufferingRegime(Sample.Regime.newStreamingRegime(1000));
        SamplePlayer player = new SamplePlayer(ac, new Sample(audioFile));
        /*
         * And as before...
         */
        ac.out.addInput(player);
        ac.start();
        
    }

    public void startMainAudio() {
        mainPlayer = new SamplePlayer(ac, SampleManager.sample(mainSource));
        //mainPlayer.setToLoopStart();
        //mainPlayer.setKillOnEnd(false);
        //mainPlayer.start();
        g.addInput(mainPlayer);
        ac.out.addInput(g);
        ac.start();
    }
}
