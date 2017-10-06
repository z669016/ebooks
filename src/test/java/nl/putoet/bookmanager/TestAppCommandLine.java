package nl.putoet.bookmanager;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class TestAppCommandLine {
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArguments () {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-q"});
    }

    @Test
    public void testSearchSystemFoldersShort() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-s"});
        assertTrue(line.searchSystemFolders());
    }

    @Test
    public void testSearchSystemFoldersLong() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-system"});
        assertTrue(line.searchSystemFolders());
    }

    @Test
    public void testMissingFormatsShort() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-m"});
        assertTrue(line.missingFormats());
    }

    @Test
    public void testMissingFormatsLong() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-missing"});
        assertTrue(line.missingFormats());
    }

    @Test
    public void testCommands() {
        final String[] commands = {"list folder"};
        final AppCommandLine line = AppCommandLine.getInstance(commands);
        assertArrayEquals(line.getCommands(), commands);
    }

}
