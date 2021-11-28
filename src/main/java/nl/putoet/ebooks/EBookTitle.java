package nl.putoet.ebooks;

import java.nio.file.Path;
import java.util.*;

public class EBookTitle {
    public final String key;

    private final List<EBookFile> files;

    public EBookTitle(final EBookFile file) {
        this.key = key(file);
        files = new ArrayList<>();
        files.add(file);
    }

    public boolean add(final EBookFile file) {
        if (key.equals(key(file))) {
            return files.add(file);
        }

        return false;
    }

    public List<EBookFile> getFiles() {
        return Collections.unmodifiableList(files);
    }

    public Format[] getFormats() {
        final List<Format> formats = new ArrayList<>();
        for (EBookFile file : files) {
            formats.add(file.format);
        }
        return formats.toArray(new Format[0]);
    }

    public Path[] getFolders() {
        final List<Path> folders = new ArrayList<>();
        for (EBookFile file : files) {
            if (!folders.contains(file.folder))
                folders.add(file.folder);
        }
        return folders.toArray(new Path[0]);
    }

    public static String key(final EBookFile file) {
        return key(file.getTitle());
    }

    protected static String key(final String title) {
        return removeSubtitle(title.toLowerCase().trim());
    }

    private static String removeSubtitle(final String title) {
        return title.contains(":") ? title.substring(0, title.indexOf(':')) : title;
    }

    @Override
    public String toString() {
        return "{key: " + key + ", files: " + files + "}";
    }
}
