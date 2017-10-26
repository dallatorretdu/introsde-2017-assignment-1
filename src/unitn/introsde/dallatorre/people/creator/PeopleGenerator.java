package unitn.introsde.dallatorre.people.creator;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import unitn.introsde.dallatorre.people.generated.ActivityPreference;
import unitn.introsde.dallatorre.people.generated.Person;

public class PeopleGenerator {

	//Generate a list of 20 Person objects
	public List<Person> generatePersonList(){
		return generatePersonList(20);
	}
	//Generate a list of N Person objects
	public List<Person> generatePersonList(int number){
		List<Person> personList = new ArrayList<Person>();
		try {
			for (int i=0; i<number; i++) {
				Person newPerson = new Person();
				generateName(i, newPerson); //Write a name in newPerson from a list
				newPerson.setId(i+1);		//Start from 1
				// Same birthday for everyone
				newPerson.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(1993, 04, 01, 0, 0, 0, 0, 0));
				// add and generate the activitypreference list
				newPerson.getActivitypreference().addAll(generateActivityPreferenceList(i));
				personList.add(newPerson);	//Add to List
			}
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return personList;
	}
	// generate the person names, based on the ID
	private void generateName(int i, Person newPerson) {
		if(i%5 == 0) {
			newPerson.setFirstname("Stefano");
			newPerson.setLastname("Tavonatti");
		}
		if(i%5 == 1) {
			newPerson.setFirstname("Mattias");
			newPerson.setLastname("Tavonatti");
		}
		if(i%5 == 2) {
			newPerson.setFirstname("Manuel");
			newPerson.setLastname("Dezulian");
		}
		if(i%5 == 3) {
			newPerson.setFirstname("Michele");
			newPerson.setLastname("Tigurro");
		}

		if(i%5 == 4) {
			newPerson.setFirstname("Claudio");
			newPerson.setLastname("Congann");
		}
	}
	
	//Generates an activity preference list with always the same item
	public List<ActivityPreference> generateActivityPreferenceList() {
		return generateActivityPreferenceList(1);
	}
	//Generate an activity preference with 1 activity given the ID
	public List<ActivityPreference> generateActivityPreferenceList(int i) {
		if (i%3 == 0) {
			return generateSingleActivityPreferenceList("Hockey","Stadium");	//Method refactored
		}
		if (i%3 == 1) {
			return generateSingleActivityPreferenceList("CrossFit","Park");
		}
		
		return generateSingleActivityPreferenceList("Biking","Cycle lane 1");
	}
	//From the name and place of the Activity generates a single activity preference
	//Filling the other values
	public List<ActivityPreference> generateSingleActivityPreferenceList(String name, String place) {
		List<ActivityPreference> activityPreferenceList = new ArrayList<ActivityPreference>();
		ActivityPreference preference = new ActivityPreference();
		preference.setId(1);
		preference.setDescription("Sample Description");
		preference.setName(name);
		preference.setPlace(place);
		try {
			//Set startdate as NOW
			GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
			preference.setStartdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		//Add the only activityPrefenrece
		activityPreferenceList.add(preference);
		return activityPreferenceList;
	}

}