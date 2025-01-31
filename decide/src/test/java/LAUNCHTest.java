import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.decide.Decide;

class LAUNCHTest {
    @Test
    void testLAUNCHtrue() {
        //If all elements in FUV are true, the LAUNCH-method should return true
        boolean[] FUV = {true, true, true, true}; 
        assertTrue(Decide.LAUNCH(FUV));
    }

    @Test
    void testLAUNCHfalse() {
        //If atleast one element in FUV is false, the LAUNCH-method should return false
        boolean[] FUV = {true, true, false, true}; 
        assertFalse(Decide.LAUNCH(FUV));
    }
}