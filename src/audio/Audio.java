package audio;

import java.io.IOException;

import gui.StrandedApplet;
import net.beadsproject.beads.core.*;
import net.beadsproject.beads.data.Sample;
import net.beadsproject.beads.data.SampleManager;
import net.beadsproject.beads.ugens.*;
import net.beadsproject.beads.ugens.SamplePlayer.LoopType;

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

    public void startMainAudio() throws IOException {
        ac = new AudioContext();
        mainPlayer = new SamplePlayer(ac, new Sample(mainSource));
        mainPlayer.setLoopType(LoopType.LOOP_FORWARDS);
        mainPlayer.setKillOnEnd(false);
        //mainPlayer.start();
        g = new Gain(ac, 2, 2);
        g.addInput(mainPlayer);
        ac.out.addInput(mainPlayer);
        ac.start();
    }
    
    public void updateBeep() {
        SamplePlayer beep = new SamplePlayer(ac, SampleManager.sample("resources/Status_Update.wav"));
        ac.out.addInput(beep);
    }
    
    public void sideBeep() {
        SamplePlayer Sbeep = new SamplePlayer(ac, SampleManager.sample("resources/Menu_Side_Beep.wav"));
        ac.out.addInput(Sbeep);
    }
    
    public void centerBeep() {
        SamplePlayer Mbeep = new SamplePlayer(ac, SampleManager.sample("resources/Menu_Center_Beep.wav"));
        ac.out.addInput(Mbeep);
    }
    
    public void fightSounds() {
        SamplePlayer fight;
        if(Math.random()*2 > 1) {
           fight = new SamplePlayer(ac, SampleManager.sample("resources/Fight_Sounds.wav"));
        }
        else
           fight = new SamplePlayer(ac, SampleManager.sample("resources/Fight_Sounds_2.wav"));
        ac.out.addInput(fight);
    }
    
    public void failSound() {
        SamplePlayer fail = new SamplePlayer(ac, SampleManager.sample("resources/Task_Time_Out.wav"));
        ac.out.addInput(fail);
    }
    
    public void failJingle() {
        SamplePlayer jing =  new SamplePlayer(ac, SampleManager.sample("resources/Stranded_GameOver"));
        ac.out.clearInputConnections();
        ac.out.addInput(jing);
    }
}
