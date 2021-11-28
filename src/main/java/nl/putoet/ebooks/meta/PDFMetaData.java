package nl.putoet.ebooks.meta;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public class PDFMetaData implements MetaData {
    public static MetaData getInstance(final InputStream is) {
        try (final PDDocument document = PDDocument.load(is)) {
            return new PDFMetaData(document);
        } catch (IOException exc) {
            throw new IllegalArgumentException(exc);
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
        this.authors = List.of(document.getDocumentInformation().getAuthor());
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
