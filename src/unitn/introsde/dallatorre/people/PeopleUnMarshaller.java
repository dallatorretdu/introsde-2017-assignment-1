package unitn.introsde.dallatorre.people;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import unitn.introsde.dallatorre.people.generated.ActivityPreference;
import unitn.introsde.dallatorre.people.generated.People;
import unitn.introsde.dallatorre.people.generated.Person;

public class PeopleUnMarshaller {

	public void unMarshalToConsole(Document xmlDocument) throws JAXBException, SAXException {
		People people = unMarshal(xmlDocument);
		printPeopleToConsole(people);
	}
	
	public void unMarshalToConsole(File xmlDocument) throws JAXBException, SAXException {
		People people = unMarshal(xmlDocument);
		printPeopleToConsole(people);
	}
	
	public People unMarshal(Document xmlDocument) throws JAXBException, SAXException {
		Unmarshaller unMarshaller = generateUnmarshaller();
		return (People) unMarshaller.unmarshal(xmlDocument);
	}
	
	public People unMarshal(File xmlDocument) throws JAXBException, SAXException {
		Unmarshaller unMarshaller = generateUnmarshaller();
		return (People) unMarshaller.unmarshal(xmlDocument);
	}

	private void printPeopleToConsole(People people) {
		List<Person> personList = people.getPerson();
		for (int i = 0; i < personList.size(); i++) {
			Person person = (Person) personList.get(i);
			System.out.println("Person: " + person.getFirstname() + " " + person.getLastname());
			List<ActivityPreference> activityList = person.getActivitypreference();
			for (int j = 0; j < activityList.size(); j++) {
				ActivityPreference activity = (ActivityPreference) activityList.get(j);
				System.out.println("\tActivity: " + activity.getName());
			}
		}
	}

	private Unmarshaller generateUnmarshaller() throws JAXBException, SAXException {
		JAXBContext jaxbContext = JAXBContext.newInstance("unitn.introsde.dallatorre.people.generated");

		Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		Schema schema = schemaFactory.newSchema(new File("people.xsd"));
		unMarshaller.setSchema(schema);
		CustomValidationEventHandler validationEventHandler = new CustomValidationEventHandler();
		unMarshaller.setEventHandler(validationEventHandler);
		return unMarshaller;
	}
	
	class CustomValidationEventHandler implements ValidationEventHandler {
		public boolean handleEvent(ValidationEvent event) {
			if (event.getSeverity() == ValidationEvent.WARNING) {
				return true;
			}
			if ((event.getSeverity() == ValidationEvent.ERROR)
					|| (event.getSeverity() == ValidationEvent.FATAL_ERROR)) {

				System.out.println("Validation Error:" + event.getMessage());

				ValidationEventLocator locator = event.getLocator();
				System.out.println("at line number:" + locator.getLineNumber());
				System.out.println("Unmarshalling Terminated");
				return false;
			}
			return true;
		}

	}
}
