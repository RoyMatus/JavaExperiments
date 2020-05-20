package utils;

import org.w3c.dom.*;
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
        Document doc1 = xmlMap.get(to);
        Element rootElement1 = doc1.getDocumentElement();
        Document doc2 = xmlMap.get(from);
        Element rootElement2 = doc2.getDocumentElement();

        NamedNodeMap namedNodeMap2 = doc2.getAttributes();
        for (int i = 0; i < namedNodeMap2.getLength(); i++) {
            Attr importedNode = (Attr) doc1.importNode(namedNodeMap2.item(i), true);
            rootElement1.setAttributeNodeNS(importedNode);
        }
        NodeList childNodes2 = rootElement2.getChildNodes();
        for (int x = 0; x < childNodes2.getLength(); x++) {
            Node importedNode = doc1.importNode(childNodes2.item(x), true);
            rootElement1.appendChild(importedNode);
        }

//        document.getChildNodes().item(0).getChildNodes().getLength();
//        Element element = document.createElement(xmlMap.get(from).getDocumentElement().getTagName());
//        Node node = xmlMap.get(from).getFirstChild();
//        System.out.println(document.getNodeType());
//        System.out.println(node.getNodeType());
//        document.importNode(node, true);
//        element.appendChild(nodeList);
//        document.appendChild(node);
    }

    public void save(String xmlName, Path path) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        DOMSource domSource = new DOMSource(xmlMap.get(xmlName));
        StreamResult streamResult = new StreamResult(new File(path.toString()));
        transformer.transform(domSource, streamResult);
    }
}
