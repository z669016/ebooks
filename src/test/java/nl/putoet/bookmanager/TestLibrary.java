package nl.putoet.bookmanager;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestLibrary {
    final static String USER_HOME = "/Users/ER21JQ";
    final static String MANNING = "Dropbox/Books/Manning Books";

    @Before
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
