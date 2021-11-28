package nl.putoet.ebooks.meta;

import nl.putoet.bookmanager.Library;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestMOBIMetaData {
    @Test
    public void testMetaData() {
        Library manning = Library.getManning();
        try (InputStream is = manning.getFileInputStream("50 Android Hacks", "50_Android_Hacks.mobi")) {
            assertNotNull(is);
            final MetaData metaData = MOBIMetaData.getInstance(is);
            assertEquals("50 Android Hacks", metaData.getTitle());
            assertArrayEquals(new String[]{"Carlos Sessa"}, metaData.getAuthors().toArray());
        } catch (IOException exc) {
            fail(exc.getMessage());
        }
    }
}
