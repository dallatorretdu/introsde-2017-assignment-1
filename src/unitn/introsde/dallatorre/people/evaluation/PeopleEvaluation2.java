package unitn.introsde.dallatorre.people.evaluation;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import unitn.introsde.dallatorre.people.PeopleXPathNavigatorFunctions;

public class PeopleEvaluation2 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//Initialize navigator class (its not static)
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		//Parse XML into Document
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document document = builder.parse("people.xml");
		//get the first activity for the id=5 people
		System.out.println(navigator.getActivity(document, 5));
	}

}
