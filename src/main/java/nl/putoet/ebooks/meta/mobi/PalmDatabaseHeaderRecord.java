package nl.putoet.ebooks.meta.mobi;

import java.io.InputStream;

public class PalmDatabaseHeaderRecord {
    public final int offset;
    public final int uniqueId;

    private static final int SIZE = 0x08;

    static PalmDatabaseHeaderRecord getInstance(final InputStream fis) {
        final byte[] data = ByteArrayHelper.getBytes(fis, SIZE);
        return extractPalmDatabaseHeaderRecord(data);
    }

    static PalmDatabaseHeaderRecord extractPalmDatabaseHeaderRecord(byte[] data) {
        final int offset = ByteArrayHelper.getInt(data);
        final int numberOfRecords = ByteArrayHelper.getInt(data, 4);

        return new PalmDatabaseHeaderRecord(offset, numberOfRecords);
    }

    private PalmDatabaseHeaderRecord(final int offset, final int uniqueId) {
        this.offset = offset;
        this.uniqueId = uniqueId;
    }

    @Override
    public String toString() {
        return String.format("{offset: 0x%08x, uniqueId: 0x%08x}", offset, uniqueId);
    }
}
