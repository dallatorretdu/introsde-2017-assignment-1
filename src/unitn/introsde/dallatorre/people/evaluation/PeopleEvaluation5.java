package unitn.introsde.dallatorre.people.evaluation;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import unitn.introsde.dallatorre.people.PeopleUnMarshaller;
import unitn.introsde.dallatorre.people.generated.People;

public class PeopleEvaluation5 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, JAXBException {
		//Read the Evaluation4.xml file and parses it into a Document
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document document = builder.parse("Evaluation4.xml");
		//Set-up the unmarshaller and unmarshal the Document to the people object
		PeopleUnMarshaller unMarshaller = new PeopleUnMarshaller();
		People people = unMarshaller.unMarshal(document);
		//print an easy debug message
		System.out.println("People class contains: " + people.getPerson().size() + " persons");
	}

}
