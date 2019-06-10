package nl.putoet.ebooks;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class TestEBookTitleList {
    public static final String ROOT = "/Users/ER21JQ/Dropbox/Books/Manning Books/";
    private EBookFile androidHacksEPUB = new EBookFile(Paths.get(ROOT + "50 Android Hacks/50_Android_Hacks.epub"));
    private EBookFile androidHacksPDF = new EBookFile(Paths.get(ROOT + "50 Android Hacks/50_Android_Hacks.pdf"));
    private EBookFile androidHacksMOBI = new EBookFile(Paths.get(ROOT + "50 Android Hacks/50_Android_Hacks.mobi"));

    @Test
    public void testConstructor() {
        final EBookTitleList list = new EBookTitleList(Paths.get(ROOT + "50 Android Hacks"));
        assertNotNull("object", list);
        assertEquals("root", ROOT + "50 Android Hacks", list.root.toString().replace("\\", "/"));
        assertEquals("files size", 0, list.getTitles().size());
    }

    @Test
    public void TestAdd() {
        final Path root = Paths.get(ROOT + "50 Android Hacks");
        final EBookTitleList list = new EBookTitleList(root);
        assertTrue("add android epub", list.add(androidHacksEPUB));

        final EBookTitleList secondList = new EBookTitleList(Paths.get("."));
        assertTrue(secondList.add(Paths.get(ROOT + "50 Android Hacks/Web_Performance_in_A.epub")));

        assertTrue(list.addAll(secondList));
        assertEquals("root", list.root, root);
        assertEquals("titles size", 2, list.getTitles().size());

        final EBookTitleList filteredList = list.filter(title -> title.key.startsWith("web"));
        assertEquals("filteredList", 1, filteredList.getTitles().size());
    }

    @Test
    public void TestFilter() {
        final Path root = Paths.get(ROOT + "50 Android Hacks");
        final EBookTitleList list = new EBookTitleList(root);
        list.add(androidHacksEPUB);
        list.add(androidHacksPDF);
        list.add(androidHacksMOBI);
        list.add(Paths.get(ROOT + "Web Performance in Action/Web_Performance_in_A.epub"));

        final EBookTitleList duplicatesList = list.filter(EBookTitleList.duplicates);
        assertNull("duplicate title", duplicatesList.titles.get("50 android hacks"));

        final EBookTitleList missingList = list.filter(EBookTitleList.missingFormats);
        assertEquals("missingList", 1, missingList.getTitles().size());
        assertNotNull("missing title", missingList.titles.get("web performance in action"));
    }

    @Test
    public void testToString() {
        final EBookTitleList list = new EBookTitleList(Paths.get("./target/test-classes"));
        assertEquals("{root: ./target/test-classes, titles: []}", list.toString().replace("\\", "/"));
    }

}
