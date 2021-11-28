package nl.putoet.ebooks.meta.mobi;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EXTHHeaderTest {
    private static final byte[] bytes = {0x00, 0x00, 0x00, 0x64,
            0x00, 0x00, 0x00, 0x14,
            0x43, 0x61, 0x72, 0x6c, 0x6f, 0x73, 0x20, 0x53, 0x65, 0x73, 0x73, 0x61};

    @Test
    void getInstance() {
        final InputStream is = new ByteArrayInputStream(bytes);
        final EXTHHeader header = EXTHHeader.getInstance(is);
        assertEquals(100, header.type);
        assertEquals(20, header.length);
        assertArrayEquals(new byte[] {0x43, 0x61, 0x72, 0x6c, 0x6f, 0x73, 0x20, 0x53, 0x65, 0x73, 0x73, 0x61}, header.data);
        assertEquals("Carlos Sessa", header.getDataAsString());
        assertEquals("{type: 100, length: 20, data: 0x43 0x61 0x72 0x6c 0x6f 0x73 0x20 0x53 0x65 0x73 0x73 0x61}", header.toString());
    }
}
