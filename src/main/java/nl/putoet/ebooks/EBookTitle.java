package nl.putoet.ebooks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        return formats.toArray(new Format[formats.size()]);
    }

    public static String key(final EBookFile file) {
        return file.getTitle();
    }

    @Override
    public String toString() {
        return "{key: " + key + ", files: " + files + "}";
    }
}
