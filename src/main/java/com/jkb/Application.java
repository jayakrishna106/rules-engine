package com.jkb;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

@SpringBootApplication
@Configuration
@EnableDiscoveryClient
@EnableEurekaClient
@ComponentScan(value = "com.jkb", lazyInit = true)
public class Application {
    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) throws Exception {

//        XMLService xmlService = new XmlServiceImpl();
//        xmlService.buildXml();
//        xmlGenerator();
//        test();
        new SpringApplicationBuilder(Application.class)
                .run(args);
    }

//
//
//    public static void xmlGenerator(){
//        try {
//
//            Document document = DocumentHelper.createDocument();
//            //MXML/test/test/@attribute
//            document.createXPath("/MXML/test/test/@attribute");
//
//            Element root = document.addElement( "cars" );
//            Element supercarElement = root.addElement("supercars")
//                    .addAttribute("company", "Ferrai");
//
//            supercarElement.addElement("carname")
//                    .addAttribute("type", "Ferrari 101")
//                    .addText("Ferrari 101");
//
//            supercarElement.addElement("carname")
//                    .addAttribute("type", "sports")
//                    .addText("Ferrari 202");
//
//            // Pretty print the document to System.out
//            OutputFormat format = OutputFormat.createPrettyPrint();
//            XMLWriter writer;
//            writer = new XMLWriter( System.out, format );
//            writer.write( document );
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public static void test()  {
        try {
        // The set of XPaths to include in the XML document
        String[] xpaths = {"/root/child1", "/root/child2"};

        // Create a new DocumentBuilder using the DocumentBuilderFactory
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;

            docBuilder = docBuilderFactory.newDocumentBuilder();


        // Create a new Document
        org.w3c.dom.Document doc = docBuilder.newDocument();

        // Create the root element
        org.w3c.dom.Element rootElement = doc.createElement("root");

        // Create child elements for each XPath and add them to the root element
        for (String xpath : xpaths) {
            String[] pathElements = xpath.split("/");
            org.w3c.dom.Element parentElement = rootElement;
            for (int i = 1; i < pathElements.length; i++) {
                String elementName = pathElements[i];
                org.w3c.dom.Element childElement = doc.createElement(elementName);
                parentElement.appendChild(childElement);
                parentElement = childElement;
            }
        }

            // Print the generated XML document
            System.out.println(toString(doc));
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

    }

    // Utility method to convert a Document to a String
    public static String toString(org.w3c.dom.Document doc) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            StringWriter sw = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error converting Document to String", e);
        }
    }

}

