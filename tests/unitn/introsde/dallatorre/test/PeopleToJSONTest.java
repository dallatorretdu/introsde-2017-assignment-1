package unitn.introsde.dallatorre.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import unitn.introsde.dallatorre.people.PersonJSONMarshaller;

class PeopleToJSONTest extends PeopleTestSuperclass{

	@Test
	void testJsonCreatesSinglePerson() throws JsonProcessingException {
		String json = PersonJSONMarshaller.marshal(generateExamplePeople(1));
		assertThat(countLines(json), greaterThan(10));
		assertThat(countLines(json), lessThan(20));
		assertThat(json, containsString("\"person\" : [ {"));
		assertThat(json, containsString("\"lastname\" : \"Tavonatti\","));
	}

	@Test
	void testJsonCreatesMultiplePerson() throws JsonProcessingException {
		String json = PersonJSONMarshaller.marshal(generateExamplePeople(5));
		assertThat(countLines(json), greaterThan(40));
		assertThat(countLines(json), lessThan(80));
		assertThat(json, containsString("\"person\" : [ {"));
		assertThat(json, containsString("\"name\" : \"Biking\""));
		assertThat(json, containsString("\"name\" : \"CrossFit\""));
		assertThat(json, containsString("\"name\" : \"Hockey\""));
	}
}
