package nl.putoet.bookmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Library {
    final static String USER_HOME = System.getProperty("user.home");
    final static String BOOKS = "Dropbox/Books";
    final static String MANNING = BOOKS + "/Manning Books";

    final Path library;

    protected Library() {
        library = root();
    }

    protected Library(String folder) {
        library = Paths.get(USER_HOME, folder);
    }

    public static Library getBooks() {
        return new Library(BOOKS);
    }

    public static Library getManning() {
        return new Library(MANNING);
    }

    public static Path root() {
        return Paths.get(USER_HOME);
    }

    protected Path getLibrary() {
        return library;
    }

    public Path getPath(String folderName, String fileName) {
        return Paths.get(library.toString(), folderName, fileName);
    }

    public File getFile(String folderName, String fileName) {
        return getPath(folderName, fileName).toFile();
    }

    public FileInputStream getFileInputStream(String folderName, String fileName) throws FileNotFoundException {
        return new FileInputStream(getFile(folderName, fileName));
    }
}
