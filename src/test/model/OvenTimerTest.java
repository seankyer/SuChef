package model;

import model.OvenTimer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OvenTimerTest {

    OvenTimer t1;
    OvenTimer t2;
    OvenTimer t3;
    OvenTimer t4;

    @BeforeEach
    public void setup() {
        t1 = new OvenTimer(3);
        t2 = new OvenTimer(0);
        t3 = new OvenTimer(10);
        t4 = new OvenTimer(1);
    }

    @Test
    public void displayTimeTest() {
        assertEquals(3,t1.displayTime());
        assertEquals(10,t3.displayTime());
        assertEquals(1,t4.displayTime());
    }

    @Test
    public void startTimerTest() {
        assertEquals("Your 3 second timer has expired!", t1.startTimer());
        assertEquals("Your 0 second timer has expired!", t2.startTimer());
        assertEquals("Your 10 second timer has expired!", t3.startTimer());
        assertEquals("Your 1 second timer has expired!", t4.startTimer());
    }

}
