package nl.putoet.bookmanager;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    public final static String USER_HOME = "/Users/renevanputten";
    public final static String MANNING = USER_HOME + "/Dropbox/Books/Manning Books";
    public static final String ANDROID_HACKS = MANNING + "/50 Android Hacks";
    public static final String ANDROID_HACKS_MOBI = ANDROID_HACKS + "/50_Android_Hacks.mobi";
    public static final String ANDROID_HACKS_EPUB = ANDROID_HACKS + "/50_Android_Hacks.epub";
    public static final String ANDROID_HACKS_PDF = ANDROID_HACKS + "/50_Android_Hacks.pdf";

    @BeforeEach
    void setup() {
        System.setProperty("user.home", USER_HOME);
    }

    @Test
    void root() {
        assertEquals(Paths.get(USER_HOME), Library.root());
    }

    @Test
    void libraryFolder() {
        final Library library = new Library(MANNING);
        assertEquals(Paths.get(MANNING), library.getLibrary());
    }

    @Test
    void libraryPath() {
        final Library library = new Library(MANNING);
        final File file = library.getFile("50 Android Hacks", "50_Android_Hacks.epub");
        assertNotNull(file);
    }

    @Test
    void libraryFile() {
        final Library library = new Library(MANNING);
        final File file = library.getFile("50 Android Hacks", "50_Android_Hacks.epub");
        assertNotNull(file);
    }

    @Test
    void libraryFileInputStream() {
        final Library library = new Library(MANNING);
        try {
            library.getFileInputStream("50 Android Hacks", "50_Android_Hacks.epub");
        } catch (FileNotFoundException exc) {
            fail(exc);
        }
    }
}
