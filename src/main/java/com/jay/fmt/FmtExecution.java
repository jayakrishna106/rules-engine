package com.jay.fmt;

import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.InputSource;

import freemarker.ext.dom.NodeModel;

public class FmtExecution {
	
	public static void main(String[] args) throws Exception {
		FmtManager fmtManager = new FmtManager();
		
		// Transformation from xml to json
		xmlToJson(fmtManager);
		
		// Transformation from json to xml
		jsonToXml(fmtManager);
	}

	/**
	 * Converts a JSON file to XML format.
	 *
	 * @param templateManager The template manager to process the XML template.
	 * @throws Exception If there is an error reading the JSON file or processing the template.
	 */
	private static void jsonToXml(FmtManager templateManager) throws Exception {
		// Read the JSON file
		String input = Files.readString(Path.of("src/main/resources/test.json"));

		// Prepare the data for the template
		Map<String, Object> data = new HashMap<>();
		data.put("input", input);
		data.put("JsonUtil", FmtJsonUtil.class);

		// Process the template and get the XML output
		String output = templateManager.processTemplate("json2xml", data);

		// Print the XML output
		System.out.println(output);
	}
	/**
	 * Converts an XML file to JSON using a FreeMarker template.
	 *
	 * @param templateManager The FreeMarker template manager.
	 * @throws Exception If an error occurs during the conversion.
	 */
	public static void xmlToJson(FmtManager templateManager) throws Exception {

		// Read the XML file as a string
		String xmlString = new String(Files.readAllBytes(Paths.get("src/main/resources/test.xml")));

		// Parse the XML string into a FreeMarker NodeModel
		NodeModel xmlNodeModel = NodeModel.parse(new InputSource(new StringReader(xmlString)));

		// Create a data map for the FreeMarker template
		Map<String, Object> data = new HashMap<>();
		data.put("xml", xmlNodeModel);

		// Print the size of the XML model (for debugging purposes)
		System.out.println(xmlNodeModel.size());

		// Process the FreeMarker template to convert the XML to JSON
		String json = templateManager.processTemplate("xml2json", data);

		// Print the resulting JSON
		System.out.println(json);
	}



	
}
