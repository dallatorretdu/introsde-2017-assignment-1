package unitn.introsde.dallatorre.people.evaluation;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import unitn.introsde.dallatorre.people.PeopleMarshaller;
import unitn.introsde.dallatorre.people.PeopleXPathNavigatorFunctions;
import unitn.introsde.dallatorre.people.creator.PeopleGenerator;
import unitn.introsde.dallatorre.people.generated.People;

public class PeopleEvaluation4 {

	public static void main(String[] args) throws JAXBException, IOException, ParserConfigurationException, SAXException {
		People people = new People();
		PeopleGenerator peopleGenerator = new PeopleGenerator();
		//Use the people generator to create a list of 3 person. Add it to People
		people.getPerson().addAll(peopleGenerator.generatePersonList(3));
		//Marshal the People Object to Evaluation4.xml file
		PeopleMarshaller.marshalToFile(people, "Evaluation4.xml");
		
		//Use the previous navigator to print the detailed people list
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document document = builder.parse("Evaluation4.xml");
		
		System.out.println(navigator.getPeopleDetailedList(document));
	}
}

