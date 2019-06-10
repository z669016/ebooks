package nl.putoet.ebooks;

import nl.putoet.bookmanager.Library;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class TestEBookTitle {
    private static final Path ANDROID_MOBI = Library.getManning().getPath("50 Android Hacks", "50_Android_Hacks.mobi");
    private static final Path ANDROID_EPUB = Library.getManning().getPath("50 Android Hacks", "50_Android_Hacks.epub");
    private static final Path ANDROID_PDF = Library.getManning().getPath("50 Android Hacks", "50_Android_Hacks.pdf");
    private static final Path WEB_EPUB = Library.getManning().getPath("Web Performance in Action", "Web_Performance_in_A.epub");


    private EBookFile androidHacksEPUB = new EBookFile(ANDROID_EPUB);
    private EBookFile androidHacksPDF = new EBookFile(ANDROID_PDF);
    private EBookFile androidHacksMOBI = new EBookFile(ANDROID_MOBI);
    private EBookFile webPerformanceInActionEPUB = new EBookFile(WEB_EPUB);

    @Test
    public void testConstructor() {
        final EBookTitle title = new EBookTitle(androidHacksEPUB);
        assertNotNull("object", title);
        assertEquals("key", title.key, "50 android hacks");
        assertEquals("files size", 1, title.getFiles().size());
        assertArrayEquals("files", new EBookFile[] {androidHacksEPUB}, title.getFiles().toArray());
        assertArrayEquals("formats", new Format[] {Format.EPUB}, title.getFormats());
    }

    @Test
    public void TestMultipleTitles() {
        final EBookTitle title = new EBookTitle(androidHacksEPUB);
        assertTrue("add mobi", title.add(androidHacksMOBI));
        assertTrue("add pdf", title.add(androidHacksPDF));
        assertFalse("add invalid", title.add(webPerformanceInActionEPUB));
        assertEquals("files size", 3, title.getFiles().size());
        assertArrayEquals("formats", new Format[] {Format.EPUB, Format.MOBI, Format.PDF}, title.getFormats());
        assertArrayEquals("folders", new Path[] {Paths.get("/Users/ER21JQ/Dropbox/Books/Manning Books/50 Android Hacks")}, title.getFolders());
    }

    @Test
    public void testToString() {
        final EBookTitle title = new EBookTitle(androidHacksEPUB);
        title.add(androidHacksPDF);

        assertEquals("{key: 50 android hacks, files: [{name:50_Android_Hacks, folder:/Users/ER21JQ/Dropbox/Books/Manning Books/50 Android Hacks, format:EPUB}, {name:50_Android_Hacks, folder:/Users/ER21JQ/Dropbox/Books/Manning Books/50 Android Hacks, format:PDF}]}", title.toString().replace("\\", "/"));
    }

    @Test
    public void testKey() {
        assertEquals("plain", "abc", EBookTitle.key("ABC"));
        assertEquals("trimmed", "abc", EBookTitle.key(" ABC "));
        assertEquals("sub", "abc", EBookTitle.key("ABC: DEF"));
    }

}
