package nl.putoet.bookmanager;

import nl.putoet.ebooks.EBookTitle;
import nl.putoet.ebooks.EBookTitleList;
import nl.putoet.ebooks.Format;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.function.Predicate;

public class App {
    protected final AppCommandLine commandLine;
    protected final PathFilter filter = new PathFilter();

    private final Predicate<Path> filterSystemFolders = path -> {
        final File file = path.toFile();
        return !file.isDirectory() || (file.isDirectory() && !file.getName().startsWith("."));
    };

    public App(final AppCommandLine commandLine) {
        this.commandLine = commandLine;

        if (!commandLine.searchSystemFolders()) {
            filter.add(filterSystemFolders);
        }
    }

    public EBookTitleList list(final Path path) {
        final EBookTitleList list = new EBookTitleList(path);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                if (filter.test(entry)) {
                    if (entry.toFile().isDirectory()) {
                        list.addAll(list(entry));
                    } else {
                        if (Format.isValidEbook(entry))
                            list.add(entry);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("IO Error during parsing of folder '" + path + "' " + ex.getMessage());
        } catch (DirectoryIteratorException ex) {
            System.out.println("Error during parsing of folder '" + path + "' " + ex.getMessage());
        }

        return list;
    }

    public void print(final EBookTitleList list) {
        System.out.println("EBook title list for " + list.root);

        for (EBookTitle title : list.getTitles()) {
            if (commandLine.missingFormats()) {
                final Format[] missing = Format.missing(title.getFormats());
                if (missing.length > 0)
                    System.out.println(title);
            } else {
                System.out.println(title);
            }
        }
    }
}
