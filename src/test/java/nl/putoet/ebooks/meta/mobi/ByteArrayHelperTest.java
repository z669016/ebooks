package nl.putoet.ebooks.meta.mobi;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ByteArrayHelperTest {
    final static byte[] data = {0x2c, (byte) 0x98, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00};

    @Test
    void getShort() {
        assertEquals(0x2c98, ByteArrayHelper.getShort(data));
    }

    @Test
    void getShortOffset() {
        assertEquals(0x0002, ByteArrayHelper.getShort(data, 4));
    }

    @Test
    void getInt() {
        assertEquals(0x00002c98, ByteArrayHelper.getInt(data));
    }

    @Test
    void getIntUnswapped() {
        assertEquals(0x2c980000, ByteArrayHelper.getIntUnswapped(data));
    }

    @Test
    void getIntUnswappedOffset() {
        assertEquals(0x00020000, ByteArrayHelper.getIntUnswapped(data, 4));
    }

    @Test
    void getZeroTerminatedString() {
        final byte[] data = {0x42, 0x4f, 0x4f, 0x4b, 0x4d, 0x4f, 0x42, 0x49, 0x00, 0x00};
        assertEquals("BOOKMOBI", ByteArrayHelper.getZeroTerminatedString(data, 10));
    }

    @Test
    void getZeroTerminatedStringOffset() {
        final byte[] data = {0x00, 0x00, 0x42, 0x4f, 0x4f, 0x4b, 0x4d, 0x4f, 0x42, 0x49, 0x00, 0x00};
        assertEquals("BOOKMOBI", ByteArrayHelper.getZeroTerminatedString(data, 2, 10));
    }

    @Test
    void getFixedSizeStringOffset() {
        final byte[] data = {0x00, 0x00, 0x42, 0x4f, 0x4f, 0x4b, 0x4d, 0x4f, 0x42, 0x49};
        assertEquals("BOOKMOBI", ByteArrayHelper.getFixedSizeString(data, 2, 8));
    }

    @Test
    void getFixedSizeString() {
        final byte[] data = {0x42, 0x4f, 0x4f, 0x4b, 0x4d, 0x4f, 0x42, 0x49};
        assertEquals("BOOKMOBI", ByteArrayHelper.getFixedSizeString(data));
    }

    @Test
    void getBytes() {
        final byte[] data = {0x42, 0x4f, 0x4f, 0x4b, 0x4d, 0x4f, 0x42, 0x49};
        final InputStream is = new ByteArrayInputStream(data);

        final byte[] copy = ByteArrayHelper.getBytes(is, 4);
        assertArrayEquals(new byte[] {0x42, 0x4f, 0x4f, 0x4b}, copy);
    }

    @Test
    void getBytesException() {
        final byte[] data = {0x42, 0x4f, 0x4f, 0x4b, 0x4d, 0x4f, 0x42, 0x49};
        final InputStream is = new ByteArrayInputStream(data);

        assertThrows(IllegalArgumentException.class, () -> ByteArrayHelper.getBytes(is, 20));
    }

    @Test
    void asHexString() {
        final byte[] data = {0x42, 0x4f, 0x4f, 0x4b};
        assertEquals(" 0x42 0x4f 0x4f 0x4b", ByteArrayHelper.asHexString(data));
    }
}
