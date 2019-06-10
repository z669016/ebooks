package nl.putoet.ebooks.meta;

import nl.putoet.bookmanager.Library;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class TestPDFMetaData {
    @Test
    public void testMetaData() {
        Library manning = Library.getManning();
        try (InputStream is = manning.getFileInputStream("50 Android Hacks", "50_Android_Hacks.pdf")) {
            assertNotNull("Could not open resource", is);
            final MetaData metaData = PDFMetaData.getInstance(is);
            assertEquals("Unexpected filename","50 Android Hacks", metaData.getTitle());
            assertArrayEquals("Unexpected authors", new String[]{"Carlos Sessa"}, metaData.getAuthors().toArray());
        } catch (IOException exc) {
            Assert.fail(exc.getMessage());
        }
    }
}
