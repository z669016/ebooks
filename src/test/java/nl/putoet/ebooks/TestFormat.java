package nl.putoet.ebooks;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestFormat {
    @Test
    public void testIsPdf() {
        assertFalse("pdf test no extension", Format.isPDF("pdf"));
        assertTrue("pdf test single dotted name", Format.isPDF("test.pdf"));
        assertTrue("pdf test double dotted name", Format.isPDF("test.abc.pdf"));
        assertTrue("pdf test triple dotted name", Format.isPDF("test.abc.efg.pdf"));
        assertFalse("pdf invalid extension epub", Format.isPDF("test.abc.efg.epub"));
        assertFalse("pdf invalid extension mobi", Format.isPDF("test.abc.efg.mobi"));
    }

    @Test
    public void testIsMobi() {
        assertFalse("mobi test no extension", Format.isMOBI("mobi"));
        assertTrue("mobi test single dotted name", Format.isMOBI("test.mobi"));
        assertTrue("mobi test double dotted name", Format.isMOBI("test.abc.mobi"));
        assertTrue("mobi test triple dotted name", Format.isMOBI("test.abc.efg.mobi"));
        assertFalse("mobi invalid extension epub", Format.isMOBI("test.abc.efg.epub"));
        assertFalse("mobi invalid extension mobi", Format.isMOBI("test.abc.efg.pdf"));
    }

    @Test
    public void testIsEpub() {
        assertFalse("epub test no extension", Format.isEPUB("epub"));
        assertTrue("epub test single dotted name", Format.isEPUB("test.epub"));
        assertTrue("epub test double dotted name", Format.isEPUB("test.abc.epub"));
        assertTrue("epub test triple dotted name", Format.isEPUB("test.abc.efg.epub"));
        assertFalse("epub invalid extension epub", Format.isEPUB("test.abc.efg.pdf"));
        assertFalse("epub invalid extension mobi", Format.isEPUB("test.abc.efg.mobi"));
    }

    @Test
    public void testIs() {
        assertEquals("test PDF file", Format.PDF, Format.from("test.pdf"));
        assertEquals("test MOBI file", Format.MOBI, Format.from("test.mobi"));
        assertEquals("test EPUB file", Format.EPUB, Format.from("test.epub"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidIs() {
        assertEquals("test invalid file", Format.PDF, Format.from("test.abc"));
    }

    @Test
    public void testIsValidEbook() {
        assertTrue("PDF is valid", Format.isValidEbook("test.pdf"));
        assertTrue("MOBI is valid", Format.isValidEbook("test.mobi"));
        assertTrue("EPUB is valid", Format.isValidEbook("test.epub"));
        assertFalse(". is invalid", Format.isValidEbook("test"));
        assertFalse(". is invalid", Format.isValidEbook("test."));
        assertFalse(".abc is invalid", Format.isValidEbook("test.abc"));
    }

    @Test
    public void testMissing() {
        assertArrayEquals(new Format[] {}, Format.missing(new Format[] {Format.EPUB, Format.MOBI, Format.PDF}));
        assertArrayEquals(new Format[] {Format.PDF}, Format.missing(new Format[] {Format.EPUB, Format.MOBI}));
        assertArrayEquals(new Format[] {Format.PDF, Format.MOBI}, Format.missing(new Format[] {Format.EPUB}));
        assertArrayEquals(new Format[] {Format.PDF, Format.EPUB}, Format.missing(new Format[] {Format.MOBI}));
        assertArrayEquals(new Format[] {Format.PDF, Format.EPUB, Format.MOBI}, Format.missing(new Format[] {}));
    }
}
