package unitn.introsde.dallatorre.people.evaluation;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import unitn.introsde.dallatorre.people.PeopleXPathNavigatorFunctions;

public class PeopleEvaluation3 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//Initialize navigator object (its not static)
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		//Use document builder to parse the XML file into a document
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document document = builder.parse("people.xml");
		//Return the people that have their activity start after 2017-10-13 AAAA-MM-GG
		System.out.println(navigator.getPeopleWithActivityStartDate(document, "2017-10-13", ">"));
	}

}
