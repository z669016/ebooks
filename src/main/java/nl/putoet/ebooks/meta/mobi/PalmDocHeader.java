package nl.putoet.ebooks.meta.mobi;

import java.io.InputStream;

public class PalmDocHeader {
    public final int compression;
    public final int textLength;
    public final int recordCount;
    public final int recordSize;
    public final int currentPosition;

    private static final int SIZE = 0x10;

    public static final PalmDocHeader getInstance(final InputStream is) {
        final byte[] data = ByteArrayHelper.getBytes(is, SIZE);
        return extractPalmDocHeader(data);
    }

    private static PalmDocHeader extractPalmDocHeader(final byte[] data) {
        final int compression = ByteArrayHelper.getShort(data);
        final int textLength = ByteArrayHelper.getInt(data, 4);
        final int recordCount = ByteArrayHelper.getShort(data, 8);
        final int recordSize = ByteArrayHelper.getShort(data, 10);
        final int currentPosition = ByteArrayHelper.getInt(data, 12);

        return new PalmDocHeader(compression, textLength, recordCount, recordSize, currentPosition);
    }

    private PalmDocHeader(final int compression, final int textLength, final int recordCount, final int recordSize, final int currentPosition) {
        this.compression = compression;
        this.textLength = textLength;
        this.recordCount = recordCount;
        this.recordSize = recordSize;
        this.currentPosition = currentPosition;
    }

    @Override
    public String toString() {
        return "{compression: " + compression + ", testLength: " + textLength + ", recordCount: " + recordCount +
                ", recordSize: " + recordSize + ", currentPosition: " + currentPosition + "}";
    }

}
