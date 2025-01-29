import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.decide.Decide;

class LAUNCHTest {
    @Test
    void testLAUNCHtrue() {
        boolean[] FUV = {true, true, true, true}; 
        assertTrue(Decide.LAUNCH(FUV));
    }

    @Test
    void testLAUNCHfalse() {
        boolean[] FUV = {true, true, false, true}; 
        assertFalse(Decide.LAUNCH(FUV));
    }
}