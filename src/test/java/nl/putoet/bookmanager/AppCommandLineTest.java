package nl.putoet.bookmanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppCommandLineTest {
    @Test
    public void illegalArguments () {
        assertThrows(IllegalArgumentException.class, () -> AppCommandLine.getInstance(new String[] {"-q"}));
    }

    @Test
    public void searchSystemFoldersShort() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-s"});
        assertTrue(line.searchSystemFolders());
    }

    @Test
    public void searchSystemFoldersLong() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-system"});
        assertTrue(line.searchSystemFolders());
    }

    @Test
    public void allFormatsShort() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-a"});
        assertTrue(line.allFormats());
    }

    @Test
    public void allFormatsLong() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-all"});
        assertTrue(line.allFormats());
    }

    @Test
    public void duplicateFormatsShort() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-d"});
        assertTrue(line.duplicateFormats());
    }

    @Test
    public void duplicateFormatsLong() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-duplicate"});
        assertTrue(line.duplicateFormats());
    }

    @Test
    public void missingFormatsShort() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-m"});
        assertTrue(line.missingFormats());
    }

    @Test
    public void missingFormatsLong() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-missing"});
        assertTrue(line.missingFormats());
    }

    @Test
    public void verboseShort() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-v"});
        assertTrue(line.verbose());
    }

    @Test
    public void verboseLong() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-verbose"});
        assertTrue(line.verbose());
    }

    @Test
    public void recursiveShort() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-r"});
        assertTrue(line.recursive());
    }

    @Test
    public void recursiveLong() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-recursive"});
        assertTrue(line.recursive());
    }

    @Test
    public void helpShort() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-h"});
        assertTrue(line.help());
    }

    @Test
    public void helpLong() {
        final AppCommandLine line = AppCommandLine.getInstance(new String[] {"-help"});
        assertTrue(line.help());
    }

    @Test
    public void commands() {
        final String[] commands = {"list folder"};
        final AppCommandLine line = AppCommandLine.getInstance(commands);
        assertArrayEquals(line.getCommands(), commands);
    }
}
