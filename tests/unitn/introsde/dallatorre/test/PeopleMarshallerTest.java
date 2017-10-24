package unitn.introsde.dallatorre.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.number.OrderingComparison.*;
import java.io.OutputStream;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.Test;

import unitn.introsde.dallatorre.people.PeopleMarshaller;
import unitn.introsde.dallatorre.people.generated.People;

class PeopleMarshallerTest extends PeopleTestSuperclass{
    
	@Test
	void testMarshallingEmptyObjectreturnsEmptyXML() throws JAXBException {
		OutputStream output = returnNewStringableOutputStream();	    
		People emptyPeople = new People();
		PeopleMarshaller.marshalToStream(emptyPeople, output);
		
		String stringStream = output.toString();
		assertEquals(2, countLines(stringStream));
		assertThat(stringStream, containsString("<People/>"));
		assertThat(stringStream, containsString("<?xml version="));
	}

	@Test
	void testMarshallingObjectReturnsCorrectXML() throws JAXBException {
		OutputStream output = returnNewStringableOutputStream();
		People people = generateExamplePeople();
		PeopleMarshaller.marshalToStream(people, output);
		
		String stringStream = output.toString();
		assertThat(countLines(stringStream), greaterThan(10));
		assertThat("timestamp", countLines(stringStream), greaterThan(40));
		assertThat("timestamp", countLines(stringStream), lessThan(100));
		assertThat(stringStream, containsString("<People>"));
		assertThat(stringStream, containsString("</People>"));
		assertThat(stringStream, containsString("<person id=\"5\">"));
		assertThat(stringStream, containsString("<activitypreference id=\"1\">"));
		assertThat(stringStream, containsString("<name>CrossFit</name>"));
	}

}