package nl.putoet.ebooks.meta.mobi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PalmDatabase {
    private final PalmDatabaseHeader header;
    private final List<PalmDatabaseHeaderRecord> headerRecords;
    private final PalmDocHeader docHeader;
    private final MOBIHeader mobiHeader;

    public static PalmDatabase getInstance(final InputStream is) {
        final PalmDatabaseHeader header = PalmDatabaseHeader.getInstance(is);
        final List<PalmDatabaseHeaderRecord> headerRecords = new ArrayList<>(header.numberOfRecords);
        for (int idx = 0; idx < header.numberOfRecords; idx++) {
            final PalmDatabaseHeaderRecord record = PalmDatabaseHeaderRecord.getInstance(is);
            headerRecords.add(record);
        }
        final PalmDocHeader docHeader = PalmDocHeader.getInstance(is);
        final MOBIHeader mobiHeader = MOBIHeader.getInstance(is);
        return new PalmDatabase(header, headerRecords, docHeader, mobiHeader);
    }

    public static PalmDatabase getInstance(final Path path) {
        try (final InputStream is = new FileInputStream(path.toFile())) {
            return getInstance(is);
        } catch (FileNotFoundException exc) {
            throw new IllegalArgumentException(exc.getMessage());
        } catch (IOException exc) {
            throw new IllegalArgumentException(exc.getMessage());
        }
    }

    private PalmDatabase(final PalmDatabaseHeader header, final List<PalmDatabaseHeaderRecord> headerRecords,
                         final PalmDocHeader docHeader, final MOBIHeader mobiHeader) {
        this.header = header;
        this.headerRecords = headerRecords;
        this.docHeader = docHeader;
        this.mobiHeader = mobiHeader;
    }

    public PalmDatabaseHeader getHeader() {
        return header;
    }

    public List<PalmDatabaseHeaderRecord> getHeaderRecords() {
        return Collections.unmodifiableList(headerRecords);
    }

    public PalmDocHeader getDocHeader() {
        return docHeader;
    }

    public MOBIHeader getMobiHeader() {
        return mobiHeader;
    }
}

