package unitn.introsde.dallatorre.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import unitn.introsde.dallatorre.people.creator.PeopleXMLGenerator;
import unitn.introsde.dallatorre.people.generated.ActivityPreference;
import unitn.introsde.dallatorre.people.generated.Person;

public class PeopleXMLGeneratorTest {

	@Test
	public void createListOf20People() {
		PeopleXMLGenerator javaToXML = new PeopleXMLGenerator();
		List<Person> list = javaToXML.generatePersonList();
		assertEquals(list.size(),20);
	}
	
	@Test
	public void createASingleActivityPreference() {
		PeopleXMLGenerator javaToXML = new PeopleXMLGenerator();
		List<ActivityPreference> list = javaToXML.generateActivityPreferenceList();
		assertEquals(list.size(),1);
	}
	
	@Test
	public void listOfPeopleContainPreferences() {
		PeopleXMLGenerator javaToXML = new PeopleXMLGenerator();
		List<Person> list = javaToXML.generatePersonList();
		assertNotEquals(list.get(0).getActivitypreference().size(),0);
	}

}
