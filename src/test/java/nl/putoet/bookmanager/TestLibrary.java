package nl.putoet.bookmanager;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestLibrary {
    final static String USER_HOME = "/Users/renevanputten";
    final static String MANNING = "Dropbox/Books/Manning Books";

    @BeforeEach
    public void setup() {
        System.setProperty("user.home", USER_HOME);
    }

    @Test
    public void testRoot() {
        assertEquals(Paths.get(USER_HOME), Library.root());
    }

    @Test
    public void testLibraryFolder() {
        Library library = new Library(MANNING);
        assertEquals(Paths.get(USER_HOME, MANNING), library.getLibrary());
    }

    @Test
    public void testLibraryPath() {
        Library library = new Library(MANNING);
        File file = library.getFile("50 Android Hacks", "50_Android_Hacks.epub");
        assertNotNull(file);
    }

    @Test
    public void testLibraryFile() {
        Library library = new Library(MANNING);
        File file = library.getFile("50 Android Hacks", "50_Android_Hacks.epub");
        assertNotNull(file);
    }

    @Test
    public void testLibraryFileInputStream() throws FileNotFoundException {
        Library library = new Library(MANNING);
        InputStream fis = library.getFileInputStream("50 Android Hacks", "50_Android_Hacks.epub");
    }
}
