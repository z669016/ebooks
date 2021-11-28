package nl.putoet.ebooks.meta.mobi;

import nl.putoet.bookmanager.LibraryTest;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestPalmDatabaseHeader {
    @Test
    void mobi() throws IOException {
        try (InputStream is = new FileInputStream(LibraryTest.ANDROID_HACKS_MOBI)) {
            final PalmDatabaseHeader header = PalmDatabaseHeader.getInstance(is);
            assertEquals("50_Android_Hacks", header.name);
            assertEquals(0x013a, header.numberOfRecords);
        }
    }
}
