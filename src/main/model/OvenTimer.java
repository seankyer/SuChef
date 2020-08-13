package model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.Serializable;

// Represents an oven timer which is connected to an instruction. Oven timer will create a 'ding' noise once completed
public class OvenTimer implements Serializable {

    int timeRemaining;

    // REQUIRES: seconds > 0
    // Initializes OverTimer object
    public OvenTimer(int seconds) {
        this.timeRemaining = seconds;
    }

    public int displayTime() {
        return this.timeRemaining;
    }

    // REQUIRES: timeRemaining > 0 and timeRemaining must be in seconds (not milliseconds)
    // MODIFIES: this
    // EFFECTS: start the OvenTimer
    // Using System.currentTimeMillis() and adding the target time, we loop, checking the timer until it
    //       reaches the target time.
    public String startTimer() {

        long startTime;
        long targetTime;
        long endTime;
        long timeElapsed;
        File clap = new File("./data/OvenTimerSfx.wav");

        targetTime = System.currentTimeMillis() + (this.timeRemaining * 1000);
        startTime = System.currentTimeMillis();
        for (long i = startTime; i < targetTime; i = System.currentTimeMillis()) { }

        endTime = System.currentTimeMillis();
        timeElapsed = (endTime - startTime) / 1000;

        playSound(clap);
        return ("Your " + timeElapsed + " second timer has expired!");
    }

    // https://www.youtube.com/watch?v=QVrxiJyLTqU
    // EFFECTS: Plays sound when timer expires
    static void playSound(File sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
