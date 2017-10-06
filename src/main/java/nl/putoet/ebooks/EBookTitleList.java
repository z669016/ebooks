package nl.putoet.ebooks;

import java.nio.file.Path;
import java.util.*;

public class EBookTitleList {
    public final Path root;
    private Map<String, EBookTitle> titles = new HashMap<>();

    public EBookTitleList(final Path root) {
        this.root = root;
    }

    public Collection<EBookTitle> getTitles() {
        return titles.values();
    }

    public boolean add(final Path path) {
        return add(new EBookFile(path));
    }

    public boolean add(final EBookFile file) {
        final String key = EBookTitle.key(file);
        final EBookTitle title = titles.get(key);

        if (title != null) {
            return title.add(file);
        }

        titles.put(key, new EBookTitle(file));
        return true;
    }

    public boolean addAll(final EBookTitleList list) {
        for (EBookTitle title : list.getTitles()) {
            add(title);
        }

        return true;
    }

    private boolean add(final EBookTitle title) {
        if (titles.containsKey(title.key)) {
            for (EBookFile file : title.getFiles()) {
                add(file);
            }
        } else {
            titles.put(title.key, title);
        }

        return true;
    }

    @Override
    public String toString() {
        return "{root: " + root + ", titles: " + getTitles().toString() + "}";
    }
}
