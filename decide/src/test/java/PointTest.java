import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.decide.Point;

public class PointTest {

    @Test
    public void testDistance() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        assertEquals(5.0, p1.distance(p2), 0.001);
    }

    @Test
    public void testDirection() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 1);
        assertEquals(Math.PI / 4, p1.direction(p2), 0.001);
    }

    @Test
    public void testDistanceToLine() {
        Point p = new Point(1, 1);
        Point a = new Point(0, 0);
        Point b = new Point(2, 2);
        assertEquals(0.0, p.distanceToLine(a, b), 0.001);
    }

    @Test
    public void testAngle() {
        Point a = new Point(0, 0);
        Point b = new Point(1, 0);
        Point c = new Point(1, 1);
        assertEquals(Math.PI / 2, Point.angle(a, b, c), 0.001);
    }

    @Test
    public void testArea() {
        Point a = new Point(0, 0);
        Point b = new Point(1, 0);
        Point c = new Point(0, 1);
        assertEquals(0.5, Point.area(a, b, c), 0.001);
    }

    @Test
    public void testCircumradius() {
        Point a = new Point(0, 0);
        Point b = new Point(1, 0);
        Point c = new Point(0, 1);
        assertEquals(Math.sqrt(2) / 2, Point.circumradius(a, b, c), 0.001);
    }

    @Test
    public void testToString() {
        Point p = new Point(1, 2);
        assertEquals("(1.0, 2.0)", p.toString());
    }
}
