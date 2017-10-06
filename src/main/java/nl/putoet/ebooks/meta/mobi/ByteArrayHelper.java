package nl.putoet.ebooks.meta.mobi;

import org.apache.pdfbox.util.Charsets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

class ByteArrayHelper {
    static int getInt(final byte[] data) {
        return getInt(data, 0);
    }

    static int getInt(final byte[] data, final int offset) {
        int lowWord = getShort(data, offset) & 0xffff;
        int highWord = getShort(data, offset + 2) & 0xffff;

        return (highWord << 16) + lowWord;
    }

    static int getIntUnswapped(final byte[] data) {
        return getIntUnswapped(data, 0);
    }

    static int getIntUnswapped(final byte[] data, final int offset) {
        int highWord = getShort(data, offset) & 0xffff;
        int lowWord = getShort(data, offset + 2) & 0xffff;

        return (highWord << 16) + lowWord;
    }

    static int getShort(final byte[] data) {
        return getShort(data, 0);
    }

    static int getShort(final byte[] data, final int offset) {
        int byte0 = data[offset] & 0xff;
        int byte1 = data[offset + 1] & 0xff;
        return (byte0 << 8) + byte1;
    }

    static String getZeroTerminatedString(final byte[] data, final int maxLen) {
        return getZeroTerminatedString(data, 0, maxLen);
    }

    static String getZeroTerminatedString(final byte[] data, final int offset, final int maxLen) {
        final byte string[] = new byte[maxLen];
        final ByteBuffer buffer = ByteBuffer.wrap(data, offset, maxLen);
        buffer.get(string);
        return new String(string, 0, zeroPos(string), Charsets.UTF_8);
    }

    static String getFixedSizeString(final byte[] data) {
        return getFixedSizeString(data, 0, data.length);
    }

    static String getFixedSizeString(final byte[] data, final int offset, final int len) {
        final byte string[] = new byte[len];
        final ByteBuffer buffer = ByteBuffer.wrap(data, offset, len);
        buffer.get(string);
        return new String(string, Charsets.UTF_8);
    }

    private static int zeroPos(final byte[] fileNameBytes) {
        int idx = 0;
        while (fileNameBytes[idx] != 0) idx++;
        return idx;
    }

    static byte[] getBytes(final InputStream fis, final int size) {
        try {
            final byte data[] = new byte[size];
            if (fis.read(data) < size)
                throw new IllegalArgumentException("Unexpected end of stream (less than " + size + " bytes available)");

            return data;
        } catch (IOException exc) {
            throw new IllegalArgumentException(exc.getMessage());
        }
    }

    static String asHexString(final byte[] data) {
        final StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format(" 0x%02x", b));
        }

        return sb.toString();
    }
}
