package unitn.introsde.dallatorre.people.creator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import unitn.introsde.dallatorre.people.generated.ActivityPreference;
import unitn.introsde.dallatorre.people.generated.People;
import unitn.introsde.dallatorre.people.generated.Person;

public class PeopleXMLGenerator {
	
	public void marshalXMLDocument(File xmlDocument) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(People.class);

			Marshaller marshaller = jaxbContext.createMarshaller();

			marshaller.setProperty("jaxb.formatted.output", new Boolean(true));

			People people = new People();
			people.getPerson().addAll(generatePersonList());

			marshaller.marshal(people, new FileOutputStream(xmlDocument));

		} catch (IOException e) {
			System.out.println(e.toString());

		} catch (PropertyException e) {
			System.out.println(e.toString());

		} catch (JAXBException e) {
			System.out.println(e.toString());

		}

	}
	
	public List<Person> generatePersonList(){
		List<Person> personList = new ArrayList<Person>();
		try {
			for (int i=0; i<20; i++) {
				Person newPerson = new Person();
				newPerson.setFirstname("Stefano");
				newPerson.setLastname("Tavonatti");
				newPerson.setId(i+1);
				newPerson.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(1993, 04, 01, 0, 0, 0, 0, 0));
				newPerson.getActivitypreference().addAll(generateActivityPreferenceList(i));
				personList.add(newPerson);
			}
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return personList;
	}

	public List<ActivityPreference> generateActivityPreferenceList(int i) {
		if (i%3 == 0) {
			return generateSingleActivityPreferenceList("Hockey","Stadium");
		}
		if (i%3 == 1) {
			return generateSingleActivityPreferenceList("CrossFit","Park");
		}
		
		return generateSingleActivityPreferenceList("Biking","Cycle lane 1");
	}
	
	public List<ActivityPreference> generateSingleActivityPreferenceList(String name, String place) {
		List<ActivityPreference> activityPreferenceList = new ArrayList<ActivityPreference>();
		ActivityPreference preference = new ActivityPreference();
		preference.setId(1);
		preference.setDescription("Sample Description");
		preference.setName(name);
		preference.setPlace(place);
		try {
			GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
			preference.setStartdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		activityPreferenceList.add(preference);
		return activityPreferenceList;
	}
		
	public List<ActivityPreference> generateActivityPreferenceList() {
		return generateActivityPreferenceList(1);
	}

	public static void main(String[] argv) {
		String xmlDocument = "sample_people.xml";
		PeopleXMLGenerator javaToXML = new PeopleXMLGenerator();
		javaToXML.marshalXMLDocument(new File(xmlDocument));
	}
}
