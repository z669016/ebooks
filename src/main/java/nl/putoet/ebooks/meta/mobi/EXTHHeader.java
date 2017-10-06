package nl.putoet.ebooks.meta.mobi;

import java.io.InputStream;

public class EXTHHeader {
    public final int type;
    public final int length;
    public final byte[] data;

    public static EXTHHeader getInstance(final InputStream is) {
        byte[] data;

        data = ByteArrayHelper.getBytes(is, 4);
        final int type = ByteArrayHelper.getIntUnswapped(data);

        data = ByteArrayHelper.getBytes(is, 4);
        final int length = ByteArrayHelper.getIntUnswapped(data);

        data = ByteArrayHelper.getBytes(is, length - 8);
        return new EXTHHeader(type, length, data);
    }

    private EXTHHeader (final int type, final int length, final byte[] data) {
        this.type = type;
        this.length = length;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{type: " + type + ", length: " + length + ", data:" + ByteArrayHelper.asHexString(data) + "}";
    }

    public String getDataAsString() {
        return ByteArrayHelper.getFixedSizeString(data);
    }
}
