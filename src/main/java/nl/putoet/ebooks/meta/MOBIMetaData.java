package nl.putoet.ebooks.meta;

import nl.putoet.ebooks.meta.mobi.PalmDatabase;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class MOBIMetaData implements MetaData {
    public static MetaData getInstance(final InputStream is) {
        return new MOBIMetaData(PalmDatabase.getInstance(is));
    }

    public static MetaData getInstance(final Path path) {
        return new MOBIMetaData(PalmDatabase.getInstance(path));
    }

    private final String title;
    private final List<String> authors;


    private MOBIMetaData(final PalmDatabase mobi) {
        this.title = mobi.getMobiHeader().getTitle();
        this.authors = Arrays.asList(mobi.getMobiHeader().getAuthor());
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<String> getAuthors() {
        return authors;
    }
}
