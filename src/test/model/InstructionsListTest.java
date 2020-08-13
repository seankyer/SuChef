package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InstructionsListTest {

    InstructionList instructionList1;
    InstructionList instructionList2;
    InstructionList instructionList3;
    Instruction instruction1;
    Instruction instruction2;
    Instruction instruction3;

    @BeforeEach
    public void setup() {
        instructionList1 = new InstructionList();
        instructionList2 = new InstructionList();
        instructionList3 = new InstructionList();

        instruction1 = new Instruction(1, "Mix Eggs", 0);
        instruction2 = new Instruction(99, "Cook Eggs", 60);
        instruction3 = new Instruction(2, "Smash Eggs", 0);

    }

    @Test
    public void addInstructionTest() {
        instructionList1.addInstruction(instruction1);
        instructionList1.addInstruction(instruction2);
        instructionList1.addInstruction(instruction3);
        instructionList2.addInstruction(instruction2);

        assertTrue(instructionList1.instructions.contains(instruction1));
        assertTrue(instructionList1.instructions.contains(instruction2));
        assertTrue(instructionList1.instructions.contains(instruction3));
        assertTrue(instructionList2.instructions.contains(instruction2));
        assertFalse(instructionList2.instructions.contains(instruction1));

        assertEquals(0, instructionList1.instructions.indexOf(instruction1));
        assertEquals(1, instructionList1.instructions.indexOf(instruction2));
        assertEquals(2, instructionList1.instructions.indexOf(instruction3));
        assertEquals(0, instructionList2.instructions.indexOf(instruction2));
    }


    @Test
    public void removeInstructionTest() {
        instructionList1.addInstruction(instruction1);
        instructionList1.addInstruction(instruction2);
        instructionList1.addInstruction(instruction3);
        instructionList2.addInstruction(instruction2);

        instructionList1.removeInstruction(instruction2);
        instructionList2.removeInstruction(instruction2);

        assertTrue(instructionList1.instructions.contains(instruction1));
        assertFalse(instructionList1.instructions.contains(instruction2));
        assertTrue(instructionList1.instructions.contains(instruction3));
        assertFalse(instructionList2.instructions.contains(instruction2));
        assertFalse(instructionList2.instructions.contains(instruction1));

        assertEquals(0, instructionList1.instructions.indexOf(instruction1));
        assertEquals(1, instructionList1.instructions.indexOf(instruction3));
    }


    @Test
    public void displayInstructionForStageTest() {
        instructionList1.addInstruction(instruction1);
        instructionList1.addInstruction(instruction2);
        instructionList1.addInstruction(instruction3);
        instructionList2.addInstruction(instruction2);

        assertEquals(instruction1, instructionList1.displayInstructionForStage(1));
        assertEquals(instruction2, instructionList1.displayInstructionForStage(2));
        assertEquals(instruction3, instructionList1.displayInstructionForStage(3));
        assertEquals(instruction2, instructionList2.displayInstructionForStage(1));
    }
}
