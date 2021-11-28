package nl.putoet.ebooks.meta.mobi;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPalmDatabaseHeader {
    @Test
    public void testMobi() throws IOException {
        try (InputStream is = new FileInputStream("/Users/renevanputten/Dropbox/Books/Manning Books/50 Android Hacks/50_Android_Hacks.mobi")) {
            final PalmDatabaseHeader header = PalmDatabaseHeader.getInstance(is);
            assertEquals("50_Android_Hacks", header.name);
            assertEquals(0x013a, header.numberOfRecords);
        }
    }
}
