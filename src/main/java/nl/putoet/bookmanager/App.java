package nl.putoet.bookmanager;

import nl.putoet.ebooks.EBookTitle;
import nl.putoet.ebooks.EBookTitleList;
import nl.putoet.ebooks.Format;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.function.Predicate;

public class App {
    final AppCommandLine commandLine;
    final PathFilter filter = new PathFilter();

    final Predicate<Path> filterSystemFolders = path -> {
        final File file = path.toFile();
        return !file.isDirectory() || (file.isDirectory() && !file.getName().startsWith("."));
    };

    App(final AppCommandLine commandLine) {
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
                        if (commandLine.recursive())
                        list.addAll(list(entry));
                    } else {
                        if (Format.isValidEbook(entry)) {
                            verboseProgressNext();
                            list.add(entry);
                        }
                    }
                }
            }
        } catch (IOException exc) {
            System.out.println("IO Error during parsing of folder '" + path + "' " + exc.getMessage());
        } catch (DirectoryIteratorException exc) {
            System.out.println("Error during parsing of folder '" + path + "' " + exc.getMessage());
        }

        return list;
    }

    private void verboseProgressNext() {
        if (commandLine.verbose())
            System.out.print(".");
    }

    void print(final EBookTitleList list) {
        if (commandLine.verbose())
            System.out.println();

        System.out.println("EBook title list for " + list.root);

        if (commandLine.allFormats()) {
            System.out.println("All titles:");
            System.out.println("-----------");
            printTitles(list);
        }

        if (commandLine.missingFormats()) {
            System.out.println("Missing formats:");
            System.out.println("----------------");
            printTitles(list.filter(EBookTitleList.missingFormats));
        }

        if (commandLine.duplicateFormats()) {
            System.out.println("Duplicate formats:");
            System.out.println("------------------");
            printTitles(list.filter(EBookTitleList.duplicates));
        }
    }

    private void printTitles(final EBookTitleList list) {
        for (EBookTitle title : list.getTitles()) {
            System.out.println(title);
        }
    }
}
