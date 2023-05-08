package com.jkb.xml;

import org.apache.commons.lang3.StringUtils;

public class XPathUtils {

    private static final String SLASH = "/";
    private static final String R_BRACKET = "]";
    private static final String L_BRACKET = "[";

    /**
     * Looks for the last '/' and returns the name of the last element
     * @param xpath
     * @return the child element name or null
     */
    public static final String getChildElementName(String xpath) {
        if(StringUtils.isEmpty(xpath)) {
            return null;
        }
        String childName = xpath.substring(xpath.lastIndexOf(SLASH) + 1);
        return stripIndex(childName);
    }

    /**
     * returns the xpath if traversing up the tree one node
     * i.e. /root/suspension_rec returns /root
     * @param xpath
     * @return
     */
    public static final String getParentXPath(String xpath) {
        if(StringUtils.isEmpty(xpath) || xpath.lastIndexOf(SLASH) <= 0) {
            return null;
        }
        return xpath.substring(0, xpath.lastIndexOf(SLASH));
    }

    /**
     * returns the index of the child element xpath
     * i.e. /suspension_rec[3] returns 3.  /suspension_rec defaults to 1
     * @param xpath
     * @return 1, the index, or null if the provided xpath is empty
     */
    public static Integer getChildElementIndex(String xpath) {
        if(StringUtils.isEmpty(xpath)) {
            return null;
        }

        if(xpath.endsWith(R_BRACKET)) {
            String value = xpath.substring(xpath.lastIndexOf(L_BRACKET) + 1, xpath.lastIndexOf(R_BRACKET));
            if(StringUtils.isNumeric(value)) {
                return Integer.valueOf(value);
            }
        }
        return 1;
    }

    /**
     * @param xpath
     * @param childIndex
     * @return
     */
    public static String createPositionXpath(String xpath, Integer childIndex) {
        if(StringUtils.isEmpty(xpath)) {
            return null;
        }
        return stripIndex(xpath) + "[position()<" + childIndex + "]";
    }

    /**
     * @param childName
     * @return
     */
    private static String stripIndex(String childName) {
        if(childName.endsWith(R_BRACKET)) {
            return childName.substring(0, childName.lastIndexOf(L_BRACKET));
        } else {
            return childName;
        }
    }




}
