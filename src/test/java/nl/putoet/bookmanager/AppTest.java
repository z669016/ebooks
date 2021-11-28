package nl.putoet.bookmanager;

import nl.putoet.ebooks.EBookTitleList;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void testConstructorFilters() {
        final String[] args = {"list", LibraryTest.ANDROID_HACKS, "-s", "-r"};
        final AppCommandLine commandLine = AppCommandLine.getInstance(args);
        final App app = new App(commandLine);

        assertEquals(commandLine, app.commandLine);
        assertEquals(0, app.filter.predicates.size());
    }

    @Test
    public void testList() {
        final String[] args = {"list", LibraryTest.ANDROID_HACKS, "-m", "-r"};
        final AppCommandLine commandLine = AppCommandLine.getInstance(args);
        final App app = new App(commandLine);

        assertEquals(commandLine, app.commandLine);
        assertEquals(1, app.filter.predicates.size());

        final EBookTitleList list = app.list(Paths.get(args[1]));
        assertEquals(args[1], list.root.toString().replace("\\", "/"));
        assertEquals(1, list.getTitles().size());
    }
}
