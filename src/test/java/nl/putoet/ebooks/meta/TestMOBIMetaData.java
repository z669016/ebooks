package nl.putoet.ebooks.meta;

import org.junit.Test;

import java.io.InputStream;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class TestMOBIMetaData {
    @Test
    public void testMetaData() throws URISyntaxException {
        final InputStream is = getClass().getResourceAsStream("/50_Android_Hacks.mobi");
        assertNotNull("Could not open resource", is);
        final MetaData metaData = MOBIMetaData.getInstance(is);
        assertEquals("Unexpected filename","50 Android Hacks", metaData.getTitle());
        assertArrayEquals("Unexpected authors", new String[]{"Carlos Sessa"}, metaData.getAuthors().toArray());
    }
}
