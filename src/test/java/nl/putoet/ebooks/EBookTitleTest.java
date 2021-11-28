package nl.putoet.ebooks;

import nl.putoet.bookmanager.Library;
import nl.putoet.bookmanager.LibraryTest;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class EBookTitleTest {
    private static final Path ANDROID_MOBI = Library.getManning().getPath("50 Android Hacks", "50_Android_Hacks.mobi");
    private static final Path ANDROID_EPUB = Library.getManning().getPath("50 Android Hacks", "50_Android_Hacks.epub");
    private static final Path ANDROID_PDF = Library.getManning().getPath("50 Android Hacks", "50_Android_Hacks.pdf");
    private static final Path WEB_EPUB = Library.getManning().getPath("Web Performance in Action", "Web_Performance_in_A.epub");

    private final EBookFile androidHacksEPUB = new EBookFile(ANDROID_EPUB);
    private final EBookFile androidHacksPDF = new EBookFile(ANDROID_PDF);
    private final EBookFile androidHacksMOBI = new EBookFile(ANDROID_MOBI);
    private final EBookFile webPerformanceInActionEPUB = new EBookFile(WEB_EPUB);

    @Test
    void constructor() {
        final EBookTitle title = new EBookTitle(androidHacksEPUB);
        assertNotNull(title);
        assertEquals(title.key, "50 android hacks");
        assertEquals(1, title.getFiles().size());
        assertArrayEquals(new EBookFile[] {androidHacksEPUB}, title.getFiles().toArray());
        assertArrayEquals(new Format[] {Format.EPUB}, title.getFormats());
    }

    @Test
    void multipleTitles() {
        final EBookTitle title = new EBookTitle(androidHacksEPUB);
        assertTrue(title.add(androidHacksMOBI));
        assertTrue(title.add(androidHacksPDF));
        assertFalse(title.add(webPerformanceInActionEPUB));
        assertEquals(3, title.getFiles().size());
        assertArrayEquals(new Format[] {Format.EPUB, Format.MOBI, Format.PDF}, title.getFormats());
        assertArrayEquals(new Path[] {Paths.get(LibraryTest.ANDROID_HACKS)}, title.getFolders());
    }

    @Test
    void toStringTest() {
        final EBookTitle title = new EBookTitle(androidHacksEPUB);
        title.add(androidHacksPDF);

        assertEquals("{key: 50 android hacks, files: [{name:50_Android_Hacks, folder:" + LibraryTest.ANDROID_HACKS + ", format:EPUB}, {name:50_Android_Hacks, folder:" + LibraryTest.ANDROID_HACKS + ", format:PDF}]}", title.toString().replace("\\", "/"));
    }

    @Test
    void key() {
        assertEquals("abc", EBookTitle.key("ABC"));
        assertEquals("abc", EBookTitle.key(" ABC "));
        assertEquals("abc", EBookTitle.key("ABC: DEF"));
    }
}
