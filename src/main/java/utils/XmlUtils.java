package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class XmlUtils {

    Map<String, Document> xmlMap = new HashMap();

    public Map<String, Document> getXmlMap() {
        return xmlMap;
    }

    public void getXml(Path... paths) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Arrays.asList(paths).forEach(it -> {
            try {
                Document document = db.parse(it.toString());
                xmlMap.put(it.getFileName().toString(), document);
            } catch (SAXException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void append(String from, String to) {
        Document document = xmlMap.get(from);
        Element cd = document.createElement("CD");
    }

    public void save(String xmlName, Path path) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        DOMSource domSource = new DOMSource(xmlMap.get(xmlName));
        StreamResult streamResult = new StreamResult(new File(path.toString()));
        transformer.transform(domSource, streamResult);
    }
}
