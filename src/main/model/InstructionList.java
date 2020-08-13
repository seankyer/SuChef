package model;

import java.io.Serializable;
import java.util.ArrayList;

// Represents a list of instructions which comprise another part of a recipe
public class InstructionList implements Serializable {
    // create a list of steps. Add step will add a step to the list of steps.
    // remove step will remove a step of a given index to amend for user error.
    // create timer will be used before the step that requires a timer. It will construct a timer and add it to the
    // list

    ArrayList<Instruction> instructions;

    // EFFECTS: Constructs an InstructionList for recipe
    public InstructionList() {
        this.instructions = new ArrayList<Instruction>();
    }

    // EFFECTS: Returns length of instructions:
    public int getLengthOfInstructions() {
        return this.instructions.size();
    }

    // REQUIRES: instructions must be added in sequential stage order
    // MODIFIES: this
    // EFFECTS: adds Instruction to instructions
    public void addInstruction(Instruction ins) {
        this.instructions.add(ins);
    }

    // REQUIRES: Instruction must be in instructions
    // MODIFIES: this
    // EFFECTS: removes given Instruction from instructions
    public void removeInstruction(Instruction ins) {
        for (int i = 0; i < this.instructions.size(); i++) {
            if (this.instructions.get(i) == ins) {
                this.instructions.remove(i);
            }
        }
    }

    // REQUIRES: 0 < stage < amount of instructions
    // EFFECTS: given a stage (int) return the corresponding Instruction
    public Instruction displayInstructionForStage(int stage) {
        return this.instructions.get(stage - 1);
    }

}
