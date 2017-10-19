package unitn.introsde.dallatorre.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import unitn.introsde.dallatorre.people.creator.PeopleXMLGenerator;
import unitn.introsde.dallatorre.people.generated.ActivityPreference;
import unitn.introsde.dallatorre.people.generated.Person;

class PeopleXMLGeneratorTest {

	@Test
	void createListOf20People() {
		PeopleXMLGenerator javaToXML = new PeopleXMLGenerator();
		List<Person> list = javaToXML.generatePersonList();
		assertEquals(list.size(),20);
	}
	
	@Test
	void createASingleActivityPreference() {
		PeopleXMLGenerator javaToXML = new PeopleXMLGenerator();
		List<ActivityPreference> list = javaToXML.generateActivityPreferenceList();
		assertEquals(list.size(),1);
	}
	
	@Test
	void listOfPeopleContainPreferences() {
		PeopleXMLGenerator javaToXML = new PeopleXMLGenerator();
		List<Person> list = javaToXML.generatePersonList();
		assertNotEquals(list.get(0).getActivitypreference().size(),0);
	}

}
