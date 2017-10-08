package nl.putoet.ebooks;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class TestEBookTitle {
    private EBookFile androidHacksEPUB = new EBookFile(Paths.get("./target/test-classes/50_Android_Hacks.epub"));
    private EBookFile androidHacksPDF = new EBookFile(Paths.get("./target/test-classes/50_Android_Hacks.pdf"));
    private EBookFile androidHacksMOBI = new EBookFile(Paths.get("./target/test-classes/50_Android_Hacks.mobi"));
    private EBookFile webPerformanceInActionEPUB = new EBookFile(Paths.get("./target/test-classes/Web_Performance_in_A.epub"));

    @Test
    public void testConstructor() {
        final EBookTitle title = new EBookTitle(androidHacksEPUB);
        assertNotNull("object", title);
        assertEquals("key", title.key, "50 Android Hacks");
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
        assertArrayEquals("folders", new Path[] {Paths.get("./target/test-classes")}, title.getFolders());
    }

    @Test
    public void testToString() {
        final EBookTitle title = new EBookTitle(androidHacksEPUB);
        title.add(androidHacksPDF);

        assertEquals("{key: 50 Android Hacks, files: [{name:50_Android_Hacks, folder:./target/test-classes, format:EPUB}, {name:50_Android_Hacks, folder:./target/test-classes, format:PDF}]}", title.toString().replace("\\", "/"));
    }

}
