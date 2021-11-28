package nl.putoet.ebooks.meta.mobi;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPalmDatabase {
    @Test
    public void testMobi() throws IOException {
        try (final InputStream is = new FileInputStream("/Users/renevanputten/Dropbox/Books/Manning Books/50 Android Hacks/50_Android_Hacks.mobi")) {
            final PalmDatabase pdb = PalmDatabase.getInstance(is);
            final List<PalmDatabaseHeaderRecord> headerRecords = pdb.getHeaderRecords();

            assertEquals(pdb.getHeader().numberOfRecords, headerRecords.size());

            assertNotNull(pdb.getDocHeader());

            final MOBIHeader mobiHeader = pdb.getMobiHeader();
            assertNotNull(mobiHeader);
            assertEquals(2, mobiHeader.mobiType);
            assertTrue(mobiHeader.hasExtHeader());

            assertEquals("Carlos Sessa", mobiHeader.getAuthor());
            assertEquals("50 Android Hacks", mobiHeader.getTitle());
        }
    }
}
