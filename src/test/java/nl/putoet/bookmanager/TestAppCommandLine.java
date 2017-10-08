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
    public void testAllFormatsShort() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-a"});
        assertTrue(line.allFormats());
    }

    @Test
    public void testAllFormatsLong() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-all"});
        assertTrue(line.allFormats());
    }

    @Test
    public void testDuplicateFormatsShort() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-d"});
        assertTrue(line.duplicateFormats());
    }

    @Test
    public void testDuplicateFormatsLong() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-duplicate"});
        assertTrue(line.duplicateFormats());
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
    public void testVerboseShort() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-v"});
        assertTrue(line.verbose());
    }

    @Test
    public void testVerboseLong() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-verbose"});
        assertTrue(line.verbose());
    }

    @Test
    public void testRecursiveShort() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-r"});
        assertTrue(line.recursive());
    }

    @Test
    public void testRecursiveLong() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-recursive"});
        assertTrue(line.recursive());
    }

    @Test
    public void testHelpShort() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-h"});
        assertTrue(line.help());
    }

    @Test
    public void testHelpLong() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-help"});
        assertTrue(line.help());
    }

    @Test
    public void testCommands() {
        final String[] commands = {"list folder"};
        final AppCommandLine line = AppCommandLine.getInstance(commands);
        assertArrayEquals(line.getCommands(), commands);
    }

}
