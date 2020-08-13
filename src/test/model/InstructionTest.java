package model;

import exceptions.NoTimerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.jupiter.api.Assertions.*;

public class InstructionTest {
    Boolean thrown;
    Instruction instruction1;
    Instruction instruction2;
    Instruction instruction3;

    @BeforeEach
    public void setup() {
        thrown = false;
        instruction1 = new Instruction(1, "Mix Eggs", 0);
        instruction2 = new Instruction(99, "Cook Eggs", 5);
        instruction3 = new Instruction(2, "Smash Eggs", 0);
    }

    @Test
    public void getStepStageTest() {
        assertEquals(1, instruction1.getInstructionStage());
        assertEquals(99, instruction2.getInstructionStage());
        assertEquals(2, instruction3.getInstructionStage());
    }

    @Test
    public void getStepInsructionTest() {
        assertEquals("Mix Eggs", instruction1.getInstruction());
        assertEquals("Cook Eggs", instruction2.getInstruction());
        assertEquals("Smash Eggs", instruction3.getInstruction());

    }

    @Test
    public void containsTimerTest() {
        assertFalse(instruction1.containsTimer());
        assertTrue(instruction2.containsTimer());
        assertFalse(instruction3.containsTimer());
    }

    @Test
    public void displayTimerTest() throws NoTimerException {
        assertEquals(5, instruction2.displayTimer());
    }

    @Test
    public void startTimerTest() throws NoTimerException {
        assertEquals("Your 5 second timer has expired!", instruction2.beginOvenTimer());
    }

    @Test
    public void startTimerTestExpectException() {
        try {
            instruction1.beginOvenTimer();
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void displayTimerTestException() {
        try {
            instruction1.displayTimer();
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void displayTimerTestNoException() {
        try {
            instruction2.displayTimer();
            assertEquals(instruction2.displayTimer(), 5);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void startTimerTestNoException() {
        try {
            instruction2.beginOvenTimer();
        } catch (Exception e) {
            thrown = true;
            fail();
        }
        assertFalse(thrown);
    }

}
