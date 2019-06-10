package nl.putoet.ebooks.meta;

import nl.putoet.bookmanager.Library;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestEPUBMetaData {
    @Test
    public void testMetaData() {
        Library manning = Library.getManning();
        try (InputStream is = manning.getFileInputStream("50 Android Hacks", "50_Android_Hacks.epub")) {
            assertNotNull("Could not open resource", is);
            final MetaData metaData = EPUBMetaData.getInstance(is);
            assertEquals("Unexpected filename", "50 Android Hacks", metaData.getTitle());
            assertArrayEquals("Unexpected authors", new String[]{"Carlos Sessa"}, metaData.getAuthors().toArray());
        } catch (IOException exc) {
            Assert.fail(exc.getMessage());
        }
    }
}
