package nl.putoet.ebooks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFormat {
    @Test
    public void testIsPdf() {
        assertFalse(Format.isPDF("pdf"));
        assertTrue(Format.isPDF("test.pdf"));
        assertTrue(Format.isPDF("test.abc.pdf"));
        assertTrue(Format.isPDF("test.abc.efg.pdf"));
        assertFalse(Format.isPDF("test.abc.efg.epub"));
        assertFalse(Format.isPDF("test.abc.efg.mobi"));
    }

    @Test
    public void testIsMobi() {
        assertFalse(Format.isMOBI("mobi"));
        assertTrue(Format.isMOBI("test.mobi"));
        assertTrue(Format.isMOBI("test.abc.mobi"));
        assertTrue(Format.isMOBI("test.abc.efg.mobi"));
        assertFalse(Format.isMOBI("test.abc.efg.epub"));
        assertFalse(Format.isMOBI("test.abc.efg.pdf"));
    }

    @Test
    public void testIsEpub() {
        assertFalse(Format.isEPUB("epub"));
        assertTrue(Format.isEPUB("test.epub"));
        assertTrue(Format.isEPUB("test.abc.epub"));
        assertTrue(Format.isEPUB("test.abc.efg.epub"));
        assertFalse(Format.isEPUB("test.abc.efg.pdf"));
        assertFalse(Format.isEPUB("test.abc.efg.mobi"));
    }

    @Test
    public void testIs() {
        assertEquals(Format.PDF, Format.from("test.pdf"));
        assertEquals(Format.MOBI, Format.from("test.mobi"));
        assertEquals(Format.EPUB, Format.from("test.epub"));
    }

    @Test
    public void testInvalidIs() {
        assertThrows(IllegalArgumentException.class, () -> Format.from("test.abc"));
    }

    @Test
    public void testIsValidEbook() {
        assertTrue(Format.isValidEbook("test.pdf"));
        assertTrue(Format.isValidEbook("test.mobi"));
        assertTrue(Format.isValidEbook("test.epub"));
        assertFalse(Format.isValidEbook("test"));
        assertFalse(Format.isValidEbook("test."));
        assertFalse(Format.isValidEbook("test.abc"));
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
