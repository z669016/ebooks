package nl.putoet.ebooks.meta.mobi;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class TestPalmDocHeader {
    @Test
    public void testHeader() {
        final byte data[] = {0x00, 0x02, 0x00, 0x00, 0x00, 0x08, 0x46, 0x1e, 0x00, (byte) 0x85, 0x10, 0x00, 0x00, 0x00, 0x00, 0x00};
        final InputStream is = new ByteArrayInputStream(data);
        final PalmDocHeader header = PalmDocHeader.getInstance(is);

        System.out.println(header);
        assertEquals("Compression", 0x0002, header.compression);
        assertEquals("TextLength", 0x461e0008, header.textLength);
        assertEquals("RecordCount", 0x0085, header.recordCount);
        assertEquals("RecordSize", 0x1000, header.recordSize);
        assertEquals("CurrentPosition", 0x00000000, header.currentPosition);

    }
}
