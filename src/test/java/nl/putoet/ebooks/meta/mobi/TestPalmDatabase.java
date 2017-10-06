package nl.putoet.ebooks.meta.mobi;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestPalmDatabase {
    @Test
    public void testMobi() throws IOException {
        try (final InputStream is = getClass().getResourceAsStream("/50_Android_Hacks.mobi")) {
            final PalmDatabase pdb = PalmDatabase.getInstance(is);
            final List<PalmDatabaseHeaderRecord> headerRecords = pdb.getHeaderRecords();

            assertEquals(pdb.getHeader().numberOfRecords, headerRecords.size());

            assertNotNull(pdb.getDocHeader());

            final MOBIHeader mobiHeader = pdb.getMobiHeader();
            assertNotNull(mobiHeader);
            assertEquals("MobiType", 2, mobiHeader.mobiType);
            assertTrue("EXTH Flags", mobiHeader.hasExtHeader());

            assertEquals("Carlos Sessa", mobiHeader.getAuthor());
            assertEquals("50 Android Hacks", mobiHeader.getTitle());
        }
    }
}
