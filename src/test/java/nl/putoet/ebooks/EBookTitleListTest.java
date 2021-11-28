package nl.putoet.ebooks;

import nl.putoet.bookmanager.LibraryTest;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class EBookTitleListTest {
    public static final String ROOT = LibraryTest.MANNING;
    private final EBookFile androidHacksEPUB = new EBookFile(Paths.get(LibraryTest.ANDROID_HACKS_EPUB));
    private final EBookFile androidHacksPDF = new EBookFile(Paths.get(LibraryTest.ANDROID_HACKS_PDF));
    private final EBookFile androidHacksMOBI = new EBookFile(Paths.get(LibraryTest.ANDROID_HACKS_MOBI));

    @Test
    void constructor() {
        final EBookTitleList list = new EBookTitleList(Paths.get(LibraryTest.ANDROID_HACKS));
        assertNotNull(list);
        assertEquals(LibraryTest.ANDROID_HACKS, list.root.toString().replace("\\", "/"));
        assertEquals(0, list.getTitles().size());
    }

    @Test
    void add() {
        final Path root = Paths.get(LibraryTest.ANDROID_HACKS);
        final EBookTitleList list = new EBookTitleList(root);
        assertTrue(list.add(androidHacksEPUB));

        final EBookTitleList secondList = new EBookTitleList(Paths.get("."));
        assertTrue(secondList.add(Paths.get(ROOT + "/Web Performance In Action/Web_Performance_in_A.epub")));

        assertTrue(list.addAll(secondList));
        assertEquals(list.root, root);
        assertEquals(2, list.getTitles().size());

        final EBookTitleList filteredList = list.filter(title -> title.key.startsWith("web"));
        assertEquals(1, filteredList.getTitles().size());
    }

    @Test
    void filter() {
        final Path root = Paths.get(LibraryTest.ANDROID_HACKS);
        final EBookTitleList list = new EBookTitleList(root);
        list.add(androidHacksEPUB);
        list.add(androidHacksPDF);
        list.add(androidHacksMOBI);
        list.add(Paths.get(ROOT + "/Web Performance in Action/Web_Performance_in_A.epub"));

        final EBookTitleList duplicatesList = list.filter(EBookTitleList.duplicates);
        assertNull(duplicatesList.titles.get("50 android hacks"));

        final EBookTitleList missingList = list.filter(EBookTitleList.missingFormats);
        assertEquals(1, missingList.getTitles().size());
        assertNotNull(missingList.titles.get("web performance in action"));
    }

    @Test
    void toStringTest() {
        final EBookTitleList list = new EBookTitleList(Paths.get("./target/test-classes"));
        assertEquals("{root: ./target/test-classes, titles: []}", list.toString().replace("\\", "/"));
    }
}
