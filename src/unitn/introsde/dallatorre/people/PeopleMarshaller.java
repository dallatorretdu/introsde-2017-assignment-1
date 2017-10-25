package unitn.introsde.dallatorre.people;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import unitn.introsde.dallatorre.people.generated.People;

public class PeopleMarshaller {

	public static void marshalToFile(People people, String fileName) throws IOException, JAXBException {
		marshalToStream(people, new FileOutputStream(fileName));
	}
	
	public static OutputStream marshalToStream(People people, OutputStream outputStream) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance("unitn.introsde.dallatorre.people.generated");
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", new Boolean(true));
		
		JAXBElement<People> peopleElement = createPeople(people);
		marshaller.marshal(peopleElement, outputStream);
		
		return outputStream;	
	}
	
    public static JAXBElement<People> createPeople(People value) {
        return new JAXBElement<People>(new QName("", "people"), People.class, null, value);
    } 

}
