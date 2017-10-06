package nl.putoet.ebooks.meta.mobi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Format information based on https://wiki.mobileread.com/wiki/MOBI
 */
public class MOBIHeader {
    public final int headerLength;
    public final int mobiType;
    public final int exthFlags;

    protected List<EXTHHeader> exthHeaders;

    private static final int AUTHOR = 100;
    private static final int TITLE = 503;

    public static final MOBIHeader getInstance(final InputStream is) {
        byte[] data = ByteArrayHelper.getBytes(is, 4);
        String identifier = ByteArrayHelper.getFixedSizeString(data);
        if (!"MOBI".equals(identifier))
            throw new IllegalArgumentException("invalid identifier " + identifier);

        data = ByteArrayHelper.getBytes(is, 4);
        final int headerLength = ByteArrayHelper.getShort(data, 2);

        data = ByteArrayHelper.getBytes(is, headerLength - 8);
        final MOBIHeader mobiHeader = extractMOBIHeader(headerLength, data);

        if (mobiHeader.hasExtHeader()) {
            mobiHeader.exthHeaders = getExthHeaders(is);
        }

        return mobiHeader;
    }

    private static List<EXTHHeader> getExthHeaders(InputStream is) {
        byte[] data = ByteArrayHelper.getBytes(is, 4);
        final String identifier = ByteArrayHelper.getFixedSizeString(data);
        if (!"EXTH".equals(identifier))
            throw new IllegalArgumentException("invalid identifier " + identifier);

        data = ByteArrayHelper.getBytes(is, 4);
        final int headerLength = ByteArrayHelper.getIntUnswapped(data);

        data = ByteArrayHelper.getBytes(is, 4);
        final int recordCount = ByteArrayHelper.getIntUnswapped(data);

        final List<EXTHHeader> exthHeaders = new ArrayList<>(recordCount);
        for (int idx = 0; idx < recordCount; idx++) {
            final EXTHHeader header = EXTHHeader.getInstance(is);
            exthHeaders.add(header);
        }

        return exthHeaders;
    }

    public boolean hasExtHeader() {
        return (exthFlags & 0x40) != 0;
    }

    private static MOBIHeader extractMOBIHeader(int headerLength, byte[] data) {
        final int mobiType = ByteArrayHelper.getIntUnswapped(data);
        final int exthFlags = ByteArrayHelper.getIntUnswapped(data, 0x80 - 0x10 - 0x08);

        return new MOBIHeader(headerLength, mobiType, exthFlags);
    }

    private MOBIHeader(final int headerLength, final int mobiType,
                       final int exthFlags) {
        this.headerLength = headerLength;
        this.mobiType = mobiType;
        this.exthFlags = exthFlags;
    }

    public List<EXTHHeader> getExthHeaders() {
        return Collections.unmodifiableList(exthHeaders);
    }

    public String getTitle() {
        final EXTHHeader header = findHeader(TITLE);
        return header.getDataAsString();
    }

    public String getAuthor() {
        final EXTHHeader header = findHeader(AUTHOR);
        return header.getDataAsString();
    }

    private EXTHHeader findHeader(final int type) {
        if (!hasExtHeader())
            throw new IllegalArgumentException("No EXTH headers available");

        for (EXTHHeader header : exthHeaders) {
            if (header.type == type)
                return header;
        }

        throw new IllegalArgumentException("EXTH header with id " + type + " not found");
    }
}
