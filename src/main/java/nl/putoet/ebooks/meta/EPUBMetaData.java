package nl.putoet.ebooks.meta;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.CreatorContributor;
import nl.siegmann.epublib.epub.EpubReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class EPUBMetaData implements MetaData {
    public static MetaData getInstance(final InputStream is) {
        try {
            final EpubReader epubReader = new EpubReader();
            final Book book = epubReader.readEpub(is);
            return new EPUBMetaData(book);
        } catch (IOException exc) {
            throw new IllegalArgumentException(exc.getMessage());
        }
    }

    public static MetaData getInstance(final Path path)  {
        try (final InputStream is = new FileInputStream(path.toFile())) {
            return getInstance(is);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private final String title;
    private final List<String> authors;

    private EPUBMetaData(final Book book) {
        this.title = book.getTitle();

        final List<CreatorContributor> authors = book.getMetadata().getAuthors();
        this.authors = authors.stream().map(author -> author.getFirstname() + " " + author.getLastname()).collect(Collectors.toList());
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
