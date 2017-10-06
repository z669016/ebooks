package nl.putoet.ebooks.meta;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class TestPDFMetaData {
    @Test
    public void testMetaData() throws IOException {
        final InputStream is = getClass().getResourceAsStream("/50_Android_Hacks.pdf");
        assertNotNull("Could not open resource", is);
        final MetaData metaData = PDFMetaData.getInstance(is);
        assertEquals("Unexpected filename","50 Android Hacks", metaData.getTitle());
        assertArrayEquals("Unexpected authors", new String[]{"Carlos Sessa"}, metaData.getAuthors().toArray());
    }
}
