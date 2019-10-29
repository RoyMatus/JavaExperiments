package utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.nio.file.Path;
import java.util.Map;

public class XmlUtilsTest {

    private Path xml1 = Path.of("src/main/resources/xml/books.xml");
    private Path xml2 = Path.of("src/main/resources/xml/extra-book.xml");
    private XmlUtils xmlUtils;

    @Before
    public void setUp() throws Exception {
        xmlUtils = new XmlUtils();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getXml() throws ParserConfigurationException {
        xmlUtils.getXml(xml1, xml2);
        Map<String, Document> xmlMap = xmlUtils.getXmlMap();
    }

    @Test
    public void append() throws ParserConfigurationException {
        xmlUtils.getXml(xml1, xml2);
        xmlUtils.append(xml2.getFileName().toString(), xml2.getFileName().toString());
    }

    @Test
    public void save() throws ParserConfigurationException, TransformerException {
        xmlUtils.getXml(xml1, xml2);
        xmlUtils.append(xml2.getFileName().toString(), xml1.getFileName().toString());
        xmlUtils.save(xml1.getFileName().toString(), Path.of("C:\\Users\\SashaMatus\\AppData\\Local\\Temp\\test-result.xml"));
    }
}