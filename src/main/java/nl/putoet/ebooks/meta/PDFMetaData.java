package nl.putoet.ebooks.meta;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class PDFMetaData implements MetaData {
    public static MetaData getInstance(final InputStream is) {
        PDDocument document = null;
        try {
            document = PDDocument.load(is);
            return new PDFMetaData(document);
        } catch (IOException exc) {
            throw new IllegalArgumentException(exc);
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
            } catch (IOException exc) { /* intentionally left empty */ }
        }
    }

    public static MetaData getInstance(final Path path) {
        try (final InputStream is = new FileInputStream(path.toFile())) {
            return getInstance(is);
        } catch (IOException exc) {
            throw new IllegalArgumentException(exc);
        }
    }

    private final String title;
    private final List<String> authors;

    private PDFMetaData(final PDDocument document) {
        this.title = document.getDocumentInformation().getTitle();
        this.authors = Arrays.asList(document.getDocumentInformation().getAuthor());
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
