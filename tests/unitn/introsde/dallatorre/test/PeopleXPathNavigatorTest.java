package unitn.introsde.dallatorre.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import unitn.introsde.dallatorre.people.PeopleXPathNavigatorFunctions;

class PeopleXPathNavigatorTest {

	private static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return builder.parse(is);
	}
	private Document getValidXmlSampleDocument() throws Exception {
		Document document = loadXMLFromString("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> "
				+ "<people>\n" + 
				"    <person id=\"0001\">\n" + 
				"        <firstname>Bond R.</firstname>\n" + 
				"        <lastname>Martin</lastname>\n" + 
				"        <birthdate>1984-09-20</birthdate>\n" + 
				"        <activitypreference id=\"100\">\n" + 
				"            <name>Running</name>\n" + 
				"            <description>Running to the Park</description>\n" + 
				"            <place>Gocciadoro</place>\n" + 
				"            <startdate>2017-10-13T11:50:00.0</startdate>\n" + 
				"        </activitypreference>\n" + 
				"    </person>\n" + 
				"</people>");
		return document;
	}
	private Document getValidXmlSampleDoubleDocument() throws Exception {
		Document document = loadXMLFromString("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> "
				+ "<people>\n" + 
				"    <person id=\"0001\">\n" + 
				"        <firstname>Bond R.</firstname>\n" + 
				"        <lastname>Martin</lastname>\n" + 
				"        <birthdate>1984-09-20</birthdate>\n" + 
				"        <activitypreference id=\"100\">\n" + 
				"            <name>Running</name>\n" + 
				"            <description>Running to the Park</description>\n" + 
				"            <place>Gocciadoro</place>\n" + 
				"            <startdate>2017-10-13T11:50:00.0</startdate>\n" + 
				"        </activitypreference>\n" + 
				"    </person>\n" + 
				"    <person id=\"0002\">\n" + 
				"        <firstname>Lollo</firstname>\n" + 
				"        <lastname>Gagging</lastname>\n" + 
				"        <birthdate>1999-01-01</birthdate>\n" + 
				"        <activitypreference id=\"100\">\n" + 
				"            <name>Biking</name>\n" + 
				"            <description>Long distance training</description>\n" + 
				"            <place>Bikelane</place>\n" + 
				"            <startdate>2017-10-19T16:20:00.0</startdate>\n" + 
				"        </activitypreference>\n" + 
				"    </person>\n" + 
				"</people>");
		return document;
	}
	
	private static int countLines(String str){
		   String[] lines = str.split("\r\n|\r|\n");
		   return  lines.length;
		}
	
	@Test
	void getActivityForEmptyXMLReturnsNull() throws Exception {
		Document document = loadXMLFromString("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> "
				+ "<people>"
				+ "</people>");
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		assertNull(navigator.getActivity(document, 1));
	}
	
	@Test
	void getActivityForNonExistentIDReturnsNull() throws Exception {
		Document document = getValidXmlSampleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		assertNull(navigator.getActivity(document, 2));
	}
	
	@Test
	void getActivityForValidIDReturnsString() throws Exception {
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
	void getActivityDescriptionForValidIDReturnsCorrectData() throws Exception {
		Document document = getValidXmlSampleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String activityPreference = navigator.getActivityDescription(document, 1);
		assertNotNull(activityPreference);
		assertEquals(activityPreference,"Running to the Park");
	}
	
	@Test
	void getActivityPlaceForValidIDReturnsCorrectData() throws Exception {
		Document document = getValidXmlSampleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String activityPreference = navigator.getActivityPlace(document, 1);
		assertNotNull(activityPreference);
		assertEquals(activityPreference,"Gocciadoro");
	}
	
	@Test
	void getAllPeopleReturnCorrectString() throws Exception {
		Document document = getValidXmlSampleDoubleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String people = navigator.getPeopleDetailedList(document);
		assertNotNull(people);
		assertEquals(people.trim().substring(0, 7),"Bond R.");
		assertEquals(countLines(people),23);
	}
	
	@Test
	void getAllPeopleWithActivityStartedAfter() throws Exception {
		Document document = getValidXmlSampleDoubleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String people = navigator.getPeopleWithActivityStartDate(document,"2017-10-14",">");
		assertNotNull(people);
		assertEquals(people.trim().substring(0, 5),"Lollo");
		assertEquals(countLines(people),11);
	}
	@Test
	void getAllPeopleWithActivityStartedEquals() throws Exception {
		Document document = getValidXmlSampleDoubleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String people = navigator.getPeopleWithActivityStartDate(document,"2017-10-13","=");
		assertNotNull(people);
		assertEquals(people.trim().substring(0, 4),"Bond");
		assertEquals(countLines(people),11);
	}
	@Test
	void getAllPeopleWithActivityInexistentReturnNull() throws Exception {
		Document document = getValidXmlSampleDoubleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String people = navigator.getPeopleWithActivityStartDate(document,"2017-10-13","<");
		assertNull(people);
	}
	@Test
	void getAllPeopleWithActivityUsingMalformedDataReturnsNull() throws Exception {
		Document document = getValidXmlSampleDoubleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String people = navigator.getPeopleWithActivityStartDate(document,"2017100-S3","<");
		assertNull(people);
	}
	@Test
	void getAllPeopleWithActivityUsingMalformedOperatorReturnsNull() throws Exception {
		Document document = getValidXmlSampleDoubleDocument();
		PeopleXPathNavigatorFunctions navigator = new PeopleXPathNavigatorFunctions();
		String people = navigator.getPeopleWithActivityStartDate(document,"2017-10-13","ASD");
		assertNull(people);
	}

}
