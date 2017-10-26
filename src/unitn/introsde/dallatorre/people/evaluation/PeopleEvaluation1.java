package unitn.introsde.dallatorre.people.evaluation;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import unitn.introsde.dallatorre.people.PeopleXPathNavigatorFunctions;

public class PeopleEvaluation1 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//Initialize navigator class (it's not static)
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		//Document builder for parsing the XML
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document document = builder.parse("people.xml");
		
		//Print people list
		System.out.println(navigator.getPeopleDetailedList(document));
	}

}
