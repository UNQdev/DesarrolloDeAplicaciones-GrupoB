package ar.edu.unq.desapp.grupob;

import static org.junit.Assert.*;
import org.junit.Test;

public class ShiftTest {

    @Test
    public void testShiftBeforenoon () {
        Shift shift = Shift.Beforenoon;
        
        assertEquals(shift.getTimeOfDay(), "Mañana");
    }
    
    @Test
    public void testShiftAfternoon () {
        Shift shift = Shift.Afternoon;
        
        assertEquals(shift.getTimeOfDay(), "Noche");
    }
}
