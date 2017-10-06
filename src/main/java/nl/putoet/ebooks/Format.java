package nl.putoet.ebooks;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Format {
    PDF,
    EPUB,
    MOBI;

    private static List<String> names = Stream.of(values())
            .map(Enum::name)
            .collect(Collectors.toList());

    public static Format from(final String fileName) {
        return valueOf(extension(fileName));
    }

    public static boolean isValidEbook(final String fileName) {
        return names.contains(extension(fileName));
    }

    public static boolean isValidEbook(final Path path) {
        return isValidEbook(path.toString());
    }

    public static boolean isPDF(final String fileName) {
        return PDF.toString().equals(extension(fileName));
    }

    public static boolean isMOBI(final String fileName) {
        return MOBI.toString().equals(extension(fileName));
    }

    public static boolean isEPUB(final String fileName) {
        return EPUB.toString().equals(extension(fileName));
    }

    private static String extension(final String fileName) {
        return FilenameUtils.getExtension(fileName).toUpperCase();
    }

    public static Format[] missing(final Format[] formats) {
        final List<Format> missing = new ArrayList<Format>(Arrays.asList(Format.values()));
        for (Format format : formats) {
            missing.remove(format);
        }
        return missing.toArray(new Format[missing.size()]);
    }
}
