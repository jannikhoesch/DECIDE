import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import com.decide.Decide;


class FUVTest {
    @Test
    void testFUVOutput() {
        boolean[][] PUM = {
            {false, true, true},
            {true, false, true},
            {true, true, true},
            };
        
        boolean[] PUV = {false, true, true};

        boolean[] expectedFUV = {true, false, true};

        assertTrue(Arrays.equals(expectedFUV, Decide.FUV(PUV, PUM)));
    }
}