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
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document document = builder.parse("Evaluation4.xml");
		
		PeopleUnMarshaller unMarshaller = new PeopleUnMarshaller();
		People people = unMarshaller.unMarshal(document);
		System.out.println("People class contains: " + people.getPerson().size() + " persons");
	}

}
