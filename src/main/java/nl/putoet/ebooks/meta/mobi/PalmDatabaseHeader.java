package nl.putoet.ebooks.meta.mobi;

import java.io.InputStream;

public class PalmDatabaseHeader {
    public final String name;
    public final int numberOfRecords;

    private static final int SIZE = 0x50;

    static PalmDatabaseHeader getInstance(final InputStream fis) {
        final byte data[] = ByteArrayHelper.getBytes(fis, SIZE);
        return extractPalmDatabaseHeader(data);
    }

    private static PalmDatabaseHeader extractPalmDatabaseHeader(final byte[] data) {
        final String name = ByteArrayHelper.getZeroTerminatedString(data, 0x20);
        final String type = ByteArrayHelper.getFixedSizeString(data, 0x3c, 8);
        if (!"BOOKMOBI".equals(type))
            throw new IllegalArgumentException(type);
        final int numberOfRecords = ByteArrayHelper.getInt(data, 0x4c);

        return new PalmDatabaseHeader(name, numberOfRecords);
    }

    public PalmDatabaseHeader(final String name, final int numberOfRecords) {
        this.name = name;
        this.numberOfRecords = numberOfRecords;
    }

    @Override
    public String toString() {
        return "{name:" + name + ", numberOfRecords:" + numberOfRecords + "}";
    }
}

