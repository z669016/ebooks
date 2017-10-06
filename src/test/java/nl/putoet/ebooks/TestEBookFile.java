package nl.putoet.ebooks;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TestEBookFile {
    private static final Path MOBI = Paths.get("./target/test-classes/50_Android_Hacks.mobi");
    private static final Path EPUB = Paths.get("./target/test-classes/50_Android_Hacks.epub");
    private static final Path PDF = Paths.get("./target/test-classes/50_Android_Hacks.pdf");

    @Test
    public void testMobi() {
        final EBookFile file = new EBookFile(MOBI);
        assertEquals("format", Format.MOBI, file.format);
        performFileTest(file);
    }

    @Test
    public void testEPUB() {
        final EBookFile file = new EBookFile(EPUB);
        assertEquals("format", Format.EPUB, file.format);
        performFileTest(file);
    }

    @Test
    public void testPDF() {
        final EBookFile file = new EBookFile(PDF);
        assertEquals("format", Format.PDF, file.format);
        performFileTest(file);
    }

    private void performFileTest(EBookFile file) {
        assertEquals("title", "50 Android Hacks", file.getTitle());
        assertEquals("author", Arrays.asList("Carlos Sessa"), file.getAuthors());

        assertEquals("name", "50_Android_Hacks", file.name);
        assertEquals("path", "./target/test-classes", file.folder.toString().replace("\\", "/"));
    }

}
