package com.jkb.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class XmlServiceImpl implements XMLService {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlServiceImpl.class);

    private static final String ROOT = "root";
    private static final String BASE_XPATH = "/" + ROOT + "/data";

    @Override
    public void buildXml() {
//        Document document = DocumentHelper.createDocument(DocumentHelper.createElement(ROOT));
//
//        addElementToParent(document, prependBase("/discharge_status"), "ACTIVE");
//        addElementToParent(document, prependBase("/suspension_rec[2]/start"), "0915");
//        addElementToParent(document, prependBase("/suspension_rec[1]/start"), "0815");
//        addElementToParent(document, prependBase("/suspension_rec[3]/start"), "0115");
//        addElementToParent(document, prependBase("/suspension_rec[1]/end"), "0115");

//        printDoc(document);

    }

    /**
     * @param string
     * @return
     */
//    private String prependBase(String string) {
//        return BASE_XPATH + string;
//    }
//
//    private void printDoc(Document document) {
//        OutputFormat format = OutputFormat.createPrettyPrint();
//        format.setEncoding("ISO-8859-1");
//        StringWriter writer = new StringWriter();
//        XMLWriter xmlwriter = new XMLWriter(writer, format);
//        try {
//            xmlwriter.write( document );
//            LOGGER.debug(writer.getBuffer().toString());
//        } catch (IOException e) {
//            LOGGER.error(e.getMessage(), e);
//        }
//    }
//
//    /**
//     * Recursive method to create an element and, if necessary, its parents and siblings
//     * @param document
//     * @param xpath to single element
//     * @param value if null an empty element will be created
//     * @return the created Node
//     */
//    private Node addElementToParent(Document document, String xpath, String value) {
//        if(LOGGER.isDebugEnabled()) {
//            LOGGER.debug("adding Element: " + xpath + " -> " + value);
//        }
//
//        String elementName = XPathUtils.getChildElementName(xpath);
//        String parentXPath = XPathUtils.getParentXPath(xpath);
//        Node parentNode = document.selectSingleNode(parentXPath);
//        if(parentNode == null) {
//            parentNode = addElementToParent(document, parentXPath, null);
//        }
//
//        // create younger siblings if needed
//        Integer childIndex = XPathUtils.getChildElementIndex(xpath);
//        if(childIndex > 1) {
//            List<?> nodelist = document.selectNodes(XPathUtils.createPositionXpath(xpath, childIndex));
//            // how many to create = (index wanted - existing - 1 to account for the new element we will create)
//            int nodesToCreate = childIndex - nodelist.size() - 1;
//            for(int i = 0; i < nodesToCreate; i++) {
//                ((Element)parentNode).addElement(elementName);
//            }
//        }
//
//        // create requested element
//        Element created = ((Element)parentNode).addElement(elementName);
//        if(null != value) {
//            created.addText(value);
//        }
//        return created;
//    }

}