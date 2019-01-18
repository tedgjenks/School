package edu.jenks.xml;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JDOMHelper {

	public static Document buildDocument(String fileName) throws JDOMException, IOException {
		Document document = null;
		File file = new File(fileName);
		if(file.exists()) {
			SAXBuilder builder = new SAXBuilder(XMLReaders.DTDVALIDATING);
			//SAXBuilder builder = new SAXBuilder();
			document = builder.build(file);
		}
		return document;
	}
	
	public static void updateXml(Document document, String xmlFilePath) throws IOException {
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		xmlOutput.output(document, new FileWriter(xmlFilePath));
	}
	
	public static void main(String[] args) {
		try {
			buildDocument("testing/testing-config.xml");
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}
}
