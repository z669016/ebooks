package nl.putoet.ebooks.meta.mobi;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PalmDatabaseHeaderRecordTest {
    @Test
    void record() {
        final byte[] data = {0x2c, (byte) 0x98, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00};
        final InputStream is = new ByteArrayInputStream(data);
        final PalmDatabaseHeaderRecord record = PalmDatabaseHeaderRecord.getInstance(is);

        System.out.println(record);
        assertEquals(0x00002c98, record.offset);
        assertEquals(0x00000002, record.uniqueId);
    }
}
