package nl.putoet.bookmanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PathFilterTest {
    @Test
    public void testEmpty() {
        assertTrue(new PathFilter().test(null));
    }

    @Test
    public void testCombination() {
        final int[] count = {0};
        final PathFilter filter = new PathFilter().add(path -> {
            count[0]++;
            return true;
        }).add(path -> {
            count[0]++;
            return true;
        }).add(path -> {
            count[0]++;
            return true;
        });
        assertTrue(filter.test(null));
        assertEquals(3, count[0]);
    }

    @Test
    public void testCombinationFalse() {
        final int[] count = {0};
        final PathFilter filter = new PathFilter().add(path -> {
            count[0]++;
            return true;
        }).add(path -> {
            count[0]++;
            return false;
        }).add(path -> {
            count[0]++;
            return true;
        });
        assertFalse(filter.test(null));
        assertEquals(2, count[0]);
    }
}
