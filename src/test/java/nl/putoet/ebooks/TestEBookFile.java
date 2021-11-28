package nl.putoet.ebooks;

import nl.putoet.bookmanager.Library;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEBookFile {
    private static final Path MOBI = Library.getManning().getPath("50 Android Hacks", "50_Android_Hacks.mobi");
    private static final Path EPUB = Library.getManning().getPath("50 Android Hacks", "50_Android_Hacks.epub");
    private static final Path PDF = Library.getManning().getPath("50 Android Hacks", "50_Android_Hacks.pdf");

    @Test
    public void testMobi() {
        final EBookFile file = new EBookFile(MOBI);
        assertEquals(Format.MOBI, file.format);
        performFileTest(file);
    }

    @Test
    public void testEPUB() {
        final EBookFile file = new EBookFile(EPUB);
        assertEquals(Format.EPUB, file.format);
        performFileTest(file);
    }

    @Test
    public void testPDF() {
        final EBookFile file = new EBookFile(PDF);
        assertEquals(Format.PDF, file.format);
        performFileTest(file);
    }

    private void performFileTest(EBookFile file) {
        assertEquals("50 Android Hacks", file.getTitle());
        assertEquals(List.of("Carlos Sessa"), file.getAuthors());

        assertEquals("50_Android_Hacks", file.name);
        assertEquals("/Users/renevanputten/Dropbox/Books/Manning Books/50 Android Hacks", file.folder.toString().replace("\\", "/"));
    }

}
