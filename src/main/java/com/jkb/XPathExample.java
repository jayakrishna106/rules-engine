package com.jkb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XPathExample {

    public static void main(String[] args) throws Exception {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        // add XSLT in Transformer
        Transformer transformer = transformerFactory.newTransformer(
                new StreamSource(new File("src/main/resources/format.xslt")));

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(new FileInputStream("src/main/resources/my-xml-source.xml"));

        transformer.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("src/main/resources/out.txt")));

          // Load XML file
        File xmlFile = new File("src/main/resources/my-xml-source.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc1 = builder.parse(xmlFile);

        // Create XPath object
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        // Generate XPath expression for each element in the document
        NodeList nodes = doc1.getElementsByTagName("*");
        for (int i = 0; i < nodes.getLength(); i++) {
            String xpathExpression = getXPath(nodes.item(i));
            System.out.println(xpathExpression);
        }
    }

    private static String getXPath(org.w3c.dom.Node node) {
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expr;
        try {
            expr = xpath.compile("ancestor-or-self::*");
            NodeList nodes = (NodeList) expr.evaluate(node, XPathConstants.NODESET);
            StringBuilder xpathBuilder = new StringBuilder();
            for (int i = nodes.getLength() - 1; i >= 0; i--) {
                org.w3c.dom.Node n = nodes.item(i);
                boolean hasId = n.getAttributes().getNamedItem("id") != null;
                xpathBuilder.append("/");
                xpathBuilder.append(n.getNodeName());
                if (hasId) {
                    xpathBuilder.append("[@id='");
                    xpathBuilder.append(n.getAttributes().getNamedItem("id").getNodeValue());
                    xpathBuilder.append("']");
                } else {
                    int index = 1;
                    for (int j = 0; j < i; j++) {
                        if (nodes.item(j).getNodeName().equals(n.getNodeName())) {
                            index++;
                        }
                    }
                    xpathBuilder.append("[");
                    xpathBuilder.append(index);
                    xpathBuilder.append("]");
                }
            }
            return xpathBuilder.toString();
        } catch (XPathExpressionException e) {
            return null;
        }
    }

}
