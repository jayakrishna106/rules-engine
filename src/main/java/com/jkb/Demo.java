package com.jkb;
import java.io.FileInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@ComponentScan(value = "com.jkb", lazyInit = true)
public class Demo {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Demo.class, args);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        XMLReader xr = sp.getXMLReader();

        xr.setContentHandler(new FragmentContentHandler(xr));
        xr.parse(new InputSource(new FileInputStream("src/main/resources/my-xml-source.xml")));
        System.out.println("start App");
    }
}
