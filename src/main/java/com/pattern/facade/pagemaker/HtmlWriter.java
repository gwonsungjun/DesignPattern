package com.pattern.facade.pagemaker;

import java.io.IOException;
import java.io.Writer;

class HtmlWriter {
    private Writer writer;

    HtmlWriter(Writer writer) {
        this.writer = writer;
    }

    void title(String title) throws IOException {
        writer.write("<html>");
        writer.write("<head>");
        writer.write("<title>" + title + "</title>");
        writer.write("</head>");
        writer.write("<body>\n");
        writer.write("<h1>" + title + "</h1>\n");
    }

    void paragraph(String msg) throws IOException {
        writer.write("<p> "+ msg + "</p>\n");
    }

    private void link(String href, String caption) throws IOException {
        paragraph("<a href=\""+href+"\">" + caption + "</a>");
    }

    void mailto(String mailAddr, String username) throws IOException {
        link("mailto:" + mailAddr, username);
    }

    void close() throws  IOException {
        writer.write("</body>");
        writer.write("</html>\n");
        writer.close();
    }
}
