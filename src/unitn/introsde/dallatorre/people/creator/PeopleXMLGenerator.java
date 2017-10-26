package unitn.introsde.dallatorre.people.creator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import unitn.introsde.dallatorre.people.generated.People;

public class PeopleXMLGenerator extends PeopleGenerator {
	
	//Class to help me generate the starting XML file, starting from the XSD
	//Saves in sample_people.xml
	public void marshalXMLDocument(File xmlDocument) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(People.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", new Boolean(true));

			People people = new People();
			people.getPerson().addAll(generatePersonList());	//Generate a list of 20 persons, add them to People

			marshaller.marshal(people, new FileOutputStream(xmlDocument));

		} catch (IOException e) {
			System.out.println(e.toString());

		} catch (PropertyException e) {
			System.out.println(e.toString());

		} catch (JAXBException e) {
			System.out.println(e.toString());

		}

	}

	public static void main(String[] argv) {
		String xmlDocument = "sample_people.xml";
		PeopleXMLGenerator javaToXML = new PeopleXMLGenerator();
		javaToXML.marshalXMLDocument(new File(xmlDocument));
	}
}
