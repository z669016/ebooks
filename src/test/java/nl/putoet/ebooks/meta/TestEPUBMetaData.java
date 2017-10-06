package nl.putoet.ebooks.meta;

import org.junit.Test;

import java.io.InputStream;
import java.net.URISyntaxException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestEPUBMetaData {
    @Test
    public void testMetaData() throws URISyntaxException {
        final InputStream is = getClass().getResourceAsStream("/50_Android_Hacks.epub");
        assertNotNull("Could not open resource", is);
        final MetaData metaData = EPUBMetaData.getInstance(is);
        assertEquals("Unexpected filename","50 Android Hacks", metaData.getTitle());
        assertArrayEquals("Unexpected authors", new String[]{"Carlos Sessa"}, metaData.getAuthors().toArray());
    }
}
