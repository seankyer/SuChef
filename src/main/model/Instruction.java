package model;

import exceptions.NoTimerException;

import java.io.Serializable;

// Represents a singular instruction for an instruction list. An instruction has a stage (step in the cooking process)
//          the actual instruction (string) and if required, an Oven Timer
public class Instruction implements Serializable {

    int stage;
    String instruction;
    OvenTimer timer;

    // REQUIRES: stage > 0 and must be sequential step in CookingSteps. instruction must not be empty.
    // EFFECTS: Constructs a step
    public Instruction(int stage, String instruction, int timer) {
        this.stage = stage;
        this.instruction = instruction;
        if (timer > 0) {
            this.timer = new OvenTimer(timer);
        } else {
            this.timer = null;
        }
    }

    // EFFECTS: returns the instruction stage
    public int getInstructionStage() {
        return this.stage;
    }

    // EFFECTS: returns string instruction
    public String getInstruction() {
        return this.instruction;
    }

    // EFFECTS: Returns true if this.timer is an OvenTimer
    public boolean containsTimer() {
        return timer != null;
    }

    // REQUIRES: instruction must contains an OvenTimer
    // EFFECTS: Shows the value of the timer
    public int displayTimer() throws NoTimerException {
        return this.timer.displayTime();
    }

    // REQUIRES: instruction must contain an OvenTimer
    // EFFECTS: Starts OvenTimer and returns alert when finished
    public String beginOvenTimer() throws NoTimerException {
        return this.timer.startTimer();
    }
}
