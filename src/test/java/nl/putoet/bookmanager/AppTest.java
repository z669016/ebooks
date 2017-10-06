package nl.putoet.bookmanager;

import nl.putoet.ebooks.EBookTitleList;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void testConstructorFilters() {
        final String[] args = {"list", "./target/test-classes", "-s"};
        final AppCommandLine commandLine = AppCommandLine.getInstance(args);
        final App app = new App(commandLine);

        assertEquals("commandLine", commandLine, app.commandLine);
        assertEquals("filters", 0, app.filter.predicates.size());
    }

    @Test
    public void testList() {
        final String[] args = {"list", "./target/test-classes", "-m"};
        final AppCommandLine commandLine = AppCommandLine.getInstance(args);
        final App app = new App(commandLine);

        assertEquals("commandLine", commandLine, app.commandLine);
        assertEquals("filters", 1, app.filter.predicates.size());

        final EBookTitleList list = app.list(Paths.get(args[1]));
        assertEquals("root", args[1], list.root.toString().replace("\\", "/"));
        assertEquals("list", 2, list.getTitles().size());

        app.print(list);
    }
}
