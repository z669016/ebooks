package nl.putoet.ebooks;

import nl.putoet.ebooks.meta.EPUBMetaData;
import nl.putoet.ebooks.meta.MOBIMetaData;
import nl.putoet.ebooks.meta.MetaData;
import nl.putoet.ebooks.meta.PDFMetaData;
import org.apache.commons.io.FilenameUtils;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class EBookFile implements MetaData {
    public final String name;
    public final Path folder;
    public final Format format;

    private final MetaData metaData;

    public EBookFile(final Path path) {
        final String fileName = path.toFile().getName();

        this.folder = path.getParent();
        this.name = FilenameUtils.getBaseName(fileName);
        this.format = Format.from(fileName);

        this.metaData = getMetaData(format, path);
    }

    private static MetaData getMetaData(final Format format, final Path path) {
        try {
            switch (format) {
                case PDF:
                    return PDFMetaData.getInstance(path);
                case EPUB:
                    return EPUBMetaData.getInstance(path);
                case MOBI:
                    return MOBIMetaData.getInstance(path);
            }
        } catch (IllegalArgumentException exc) { /* intentinally empry */ }

        System.err.println("No meta data available for " + path);
        System.err.flush();
        return null;
    }

    @Override
    public String toString() {
        return "{name:" + name + ", folder:" + folder + ", format:" + format + "}";
    }

    @Override
    public String getTitle() {
        if (metaData != null)
            return metaData.getTitle();

        return name;
    }

    @Override
    public List<String> getAuthors() {
        if (metaData != null)
            return metaData.getAuthors();

        return Arrays.asList("<authors unknown>");
    }
}
