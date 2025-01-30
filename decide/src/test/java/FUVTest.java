import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import com.decide.Decide;


class FUVTest {
    @Test
    void testFUVOutput() {
        //test 1
        boolean[][] PUM = {
            {false, false},
            {false, false},
            };
        boolean[] PUV = {true, false};
        boolean[] expectedFUV = {false, true};
        assertTrue(Arrays.equals(expectedFUV, Decide.FUV(PUV, PUM)));

        //test 2
        boolean[][] PUM2 = {
            {true, true},
            {true, true},
            };
        boolean[] PUV2 = {true, false};
        boolean[] expectedFUV2 = {true, true};
        assertTrue(Arrays.equals(expectedFUV2, Decide.FUV(PUV2, PUM2)));

        //test 3
        boolean[][] PUM3 = {
            {true, false},
            {true, false},
            };
        boolean[] PUV3 = {true, false};
        boolean[] expectedFUV3 = {false, true};
        assertTrue(Arrays.equals(expectedFUV3, Decide.FUV(PUV3, PUM3)));
    }
}