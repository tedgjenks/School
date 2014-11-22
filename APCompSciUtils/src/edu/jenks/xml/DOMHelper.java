package edu.jenks.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DOMHelper {

	public static Document buildDocument(String fileName) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		builderFactory.setValidating(true);
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		File file = new File(fileName);
		return builder.parse(file);
	}

}
