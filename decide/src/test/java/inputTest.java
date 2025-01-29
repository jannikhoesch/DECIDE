import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.decide.Decide;
import com.decide.Parameters;

class InputTest {

    @Test
    void testInputTrue() {
        Parameters validParams = new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0,
        5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0);
        int NUMPOINTS = 10;
        assertTrue(Decide.input_valid(validParams, NUMPOINTS), "Valid input should return true");
    }

    @Test
    void testInvalidInputs() {
        int NUMPOINTS = 10;

        assertThrows(IllegalArgumentException.class, 
            () -> Decide.input_valid(new Parameters(-1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0), NUMPOINTS), 
            "Invalid input for LIC0");

        assertThrows(IllegalArgumentException.class, 
            () -> Decide.input_valid(new Parameters(1.0, -1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0), NUMPOINTS), 
            "Invalid input for LIC1");

        assertThrows(IllegalArgumentException.class, 
            () -> Decide.input_valid(new Parameters(1.0, 1.0, Math.PI, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0), NUMPOINTS), 
            "Invalid input for LIC2");

        assertThrows(IllegalArgumentException.class, 
            () -> Decide.input_valid(new Parameters(1.0, 1.0, Math.PI / 2, -1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0), NUMPOINTS), 
            "Invalid input for LIC3");

        assertThrows(IllegalArgumentException.class, 
            () -> Decide.input_valid(new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 11, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0), NUMPOINTS), 
            "Invalid input for LIC4");

        assertThrows(IllegalArgumentException.class, 
            () -> Decide.input_valid(new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 2, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0), NUMPOINTS), 
            "Invalid input for LIC6");

        assertThrows(IllegalArgumentException.class, 
            () -> Decide.input_valid(new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 9, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0), NUMPOINTS), 
            "Invalid input for LIC7");

        assertThrows(IllegalArgumentException.class, 
            () -> Decide.input_valid(new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 4, 4, 1, 1, 1, 1, 2, 1.0, 1.0, 1.0), NUMPOINTS), 
            "Invalid input for LIC8");

        assertThrows(IllegalArgumentException.class, 
            () -> Decide.input_valid(new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 
            5, 2, 2, 2, -1, 3, 1, 1, 2, 1.0, 1.0, 1.0), NUMPOINTS), 
            "Invalid input for LIC9");

        assertThrows(IllegalArgumentException.class, 
            () -> Decide.input_valid(new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 0, 1.0, 1.0, 1.0), NUMPOINTS), 
            "Invalid input for LIC11");

        assertThrows(IllegalArgumentException.class, 
            () -> Decide.input_valid(new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, -1.0, 1.0, 1.0), NUMPOINTS), 
            "Invalid input for LIC12");

        assertThrows(IllegalArgumentException.class, 
            () -> Decide.input_valid(new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, -1.0, 1.0), NUMPOINTS), 
            "Invalid input for LIC13");

        assertThrows(IllegalArgumentException.class, 
            () -> Decide.input_valid(new Parameters(1.0, 1.0, Math.PI / 2, 1.0, 3, 2, 1.0, 5, 2, 2, 2, 1, 1, 1, 1, 2, 1.0, 1.0, -1.0), NUMPOINTS), 
            "Invalid input for LIC14");
    }
}
