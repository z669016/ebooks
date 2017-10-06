package nl.putoet.ebooks;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class TestEBookTitleList {
    private EBookFile androidHacksEPUB = new EBookFile(Paths.get("./target/test-classes/50_Android_Hacks.epub"));

    @Test
    public void testConstructor() {
        final EBookTitleList list = new EBookTitleList(Paths.get("./target/test-classes"));
        assertNotNull("object", list);
        assertEquals("root", "./target/test-classes", list.root.toString().replace("\\", "/"));
        assertEquals("files size", 0, list.getTitles().size());
    }

    @Test
    public void TestAdd() {
        final Path root = Paths.get("./target/test-classes");
        final EBookTitleList list = new EBookTitleList(root);
        assertTrue("add android epub", list.add(androidHacksEPUB));

        final EBookTitleList secondList = new EBookTitleList(Paths.get("."));
        assertTrue(secondList.add(Paths.get("./target/test-classes/Web_Performance_in_A.epub")));

        assertTrue(list.addAll(secondList));
        assertEquals("root", list.root, root);
        assertEquals("titles size", 2, list.getTitles().size());
    }

    @Test
    public void testToString() {
        final EBookTitleList list = new EBookTitleList(Paths.get("./target/test-classes"));
        assertEquals("{root: ./target/test-classes, titles: []}", list.toString().replace("\\", "/"));
    }

}
