package unitn.introsde.dallatorre.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.w3c.dom.Document;

import unitn.introsde.dallatorre.people.PeopleXPathNavigatorFunctions;

public class PeopleXPathNavigatorTest extends PeopleTestSuperclass{
	
	private void assertEmptyString(String string) {
		assertTrue(string.isEmpty());
	}
	
	@Test
	public void getActivityForEmptyXMLReturnsEmptyString() throws Exception {
		Document document = loadXMLFromString("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> "
				+ "<people>"
				+ "</people>");
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		assertEmptyString(navigator.getActivity(document, 1));
	}
	
	@Test
	public void getActivityForNonExistentIDReturnsEmptyString() throws Exception {
		Document document = getValidXmlSampleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		assertEmptyString(navigator.getActivity(document, 2));
	}
	
	@Test
	public void getActivityForValidIDReturnsString() throws Exception {
		Document document = getValidXmlSampleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String activityPreference = navigator.getActivity(document, 1);
		assertNotNull(activityPreference);
		assertEquals(activityPreference,"\n            Running\n"
				+ "            Running to the Park\n"
				+ "            Gocciadoro\n"
				+ "            2017-10-13T11:50:00.0\n        ");
	}
	
	
	@Test
	public void getActivityDescriptionForValidIDReturnsCorrectData() throws Exception {
		Document document = getValidXmlSampleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String activityPreference = navigator.getActivityDescription(document, 1);
		assertNotNull(activityPreference);
		assertEquals(activityPreference,"Running to the Park");
	}
	
	@Test
	public void getActivityPlaceForValidIDReturnsCorrectData() throws Exception {
		Document document = getValidXmlSampleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String activityPreference = navigator.getActivityPlace(document, 1);
		assertNotNull(activityPreference);
		assertEquals(activityPreference,"Gocciadoro");
	}
	
	@Test
	public void getAllPeopleReturnCorrectString() throws Exception {
		Document document = getValidXmlSampleDoubleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String people = navigator.getPeopleDetailedList(document);
		assertNotNull(people);
		assertEquals(people.trim().substring(0, 7),"Bond R.");
		assertEquals(countLines(people),23);
	}
	
	@Test
	public void getAllPeopleWithActivityStartedAfter() throws Exception {
		Document document = getValidXmlSampleDoubleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String people = navigator.getPeopleWithActivityStartDate(document,"2017-10-14",">");
		assertNotNull(people);
		assertEquals(people.trim().substring(0, 5),"Lollo");
		assertEquals(countLines(people),11);
	}
	@Test
	public void getAllPeopleWithActivityStartedEquals() throws Exception {
		Document document = getValidXmlSampleDoubleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String people = navigator.getPeopleWithActivityStartDate(document,"2017-10-13","=");
		assertNotNull(people);
		assertEquals(people.trim().substring(0, 4),"Bond");
		assertEquals(countLines(people),11);
	}
	@Test
	public void getAllPeopleWithActivityInexistentReturnEmptyString() throws Exception {
		Document document = getValidXmlSampleDoubleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String people = navigator.getPeopleWithActivityStartDate(document,"2017-10-13","<");
		assertEmptyString(people);
	}
	@Test
	public void getAllPeopleWithActivityUsingMalformedDataReturnsEmptyString() throws Exception {
		Document document = getValidXmlSampleDoubleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String people = navigator.getPeopleWithActivityStartDate(document,"2017100-S3","<");
		assertEmptyString(people);
	}
	@Test
	public void getAllPeopleWithActivityUsingMalformedOperatorReturnsEmptyString() throws Exception {
		Document document = getValidXmlSampleDoubleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String people = navigator.getPeopleWithActivityStartDate(document,"2017-10-13","ASD");
		assertEmptyString(people);
	}

}
