package nl.putoet.ebooks.meta.mobi;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestByteArrayHelper {
    final static byte data[] = {0x2c, (byte) 0x98, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00};

    @Test
    public void testGetShort() {
        assertEquals(0x2c98, ByteArrayHelper.getShort(data));
    }

    @Test
    public void testGetShortOffset() {
        assertEquals(0x0002, ByteArrayHelper.getShort(data, 4));
    }

    @Test
    public void testGetInt() {
        assertEquals(0x00002c98, ByteArrayHelper.getInt(data));
    }

    @Test
    public void testGetIntUnswapped() {
        assertEquals(0x2c980000, ByteArrayHelper.getIntUnswapped(data));
    }

    @Test
    public void testGetIntOffsetunswapped() {
        assertEquals(0x00020000, ByteArrayHelper.getIntUnswapped(data, 4));
    }

    @Test
    public void testGetZeroterminatedString() {
        final byte[] data = {0x42, 0x4f, 0x4f, 0x4b, 0x4d, 0x4f, 0x42, 0x49, 0x00, 0x00};
        assertEquals("BOOKMOBI", ByteArrayHelper.getZeroTerminatedString(data, 10));
    }

    @Test
    public void testGetZeroterminatedStringOffset() {
        final byte[] data = {0x00, 0x00, 0x42, 0x4f, 0x4f, 0x4b, 0x4d, 0x4f, 0x42, 0x49, 0x00, 0x00};
        assertEquals("BOOKMOBI", ByteArrayHelper.getZeroTerminatedString(data, 2, 10));
    }

    @Test
    public void testGetFixedSizeStringOffset() {
        final byte[] data = {0x00, 0x00, 0x42, 0x4f, 0x4f, 0x4b, 0x4d, 0x4f, 0x42, 0x49};
        assertEquals("BOOKMOBI", ByteArrayHelper.getFixedSizeString(data, 2, 8));
    }

    @Test
    public void testGetFixedSizeString() {
        final byte[] data = {0x42, 0x4f, 0x4f, 0x4b, 0x4d, 0x4f, 0x42, 0x49};
        assertEquals("BOOKMOBI", ByteArrayHelper.getFixedSizeString(data));
    }

    @Test
    public void testGetBytes() {
        final byte[] data = {0x42, 0x4f, 0x4f, 0x4b, 0x4d, 0x4f, 0x42, 0x49};
        final InputStream is = new ByteArrayInputStream(data);

        final byte[] copy = ByteArrayHelper.getBytes(is, 4);
        assertArrayEquals(new byte[] {0x42, 0x4f, 0x4f, 0x4b}, copy);
    }

    @Test
    public void testGetBytesException() {
        final byte[] data = {0x42, 0x4f, 0x4f, 0x4b, 0x4d, 0x4f, 0x42, 0x49};
        final InputStream is = new ByteArrayInputStream(data);

        assertThrows(IllegalArgumentException.class, () -> ByteArrayHelper.getBytes(is, 20));
    }

    @Test
    public void testAsHexString() {
        final byte[] data = {0x42, 0x4f, 0x4f, 0x4b};
        assertEquals(" 0x42 0x4f 0x4f 0x4b", ByteArrayHelper.asHexString(data));
    }
}
