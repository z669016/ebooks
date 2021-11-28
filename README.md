# ebooks

Small poject to manage my technical books. It should support listing the titles, including different 
formats and help me find out which formats of which titles I'm missing.

For obtaining meta data, I've reused other libraries:
* EPUB - https://github.com/psiegman/epublib
* PDF - https://pdfbox.apache.org/

For analysing MOBI files I didn't find a library, so I've created the classes I needed 
based on publicly available information on the format from:
*  Palm Database Format: https://wiki.mobileread.com/wiki/PDB#Palm_Database_Format
* MOBI format: https://wiki.mobileread.com/wiki/MOBI

For testing I've used some of my own books: "50 android Hacks" in EPUB, PDF and MOBI format 
and "Web Performance In Action". The tests contain references to a local folder you probably don't have. 
