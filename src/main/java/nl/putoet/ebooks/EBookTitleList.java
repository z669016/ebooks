package nl.putoet.ebooks;

import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;

public class EBookTitleList {
    public final Path root;

    Map<String, EBookTitle> titles = new TreeMap<>();

    public static Predicate<EBookTitle> duplicates = title -> {
        final Set<Format> set = new HashSet<>();
        for (Format format : title.getFormats()) {
            if (!set.add(format))
                return true;
        }

        return false;
    };

    public static Predicate<EBookTitle> missingFormats = title -> Format.missing(title.getFormats()).length > 0;

    public EBookTitleList(final Path root) {
        this.root = root;
    }

    public Collection<EBookTitle> getTitles() {
        return titles.values();
    }

    public boolean add(final Path path) {
        try {
            return add(new EBookFile(path));
        } catch (RuntimeException exc) {
            System.err.println("Failed to create/add ebook for " + path);
            throw exc;
        }
    }

    boolean add(final EBookFile file) {
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
        } else
            titles.put(title.key, title);

        return true;
    }

    public EBookTitleList filter(final Predicate<EBookTitle> filter) {
        final EBookTitleList filteredList = new EBookTitleList(root);
        for (EBookTitle title : titles.values()) {
            if (filter.test(title)) {
                filteredList.add(title);
            }
        }

        return filteredList;
    }

    @Override
    public String toString() {
        return "{root: " + root + ", titles: " + getTitles().toString() + "}";
    }
}
