package unitn.introsde.dallatorre.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.w3c.dom.Document;

import unitn.introsde.dallatorre.people.PeopleUnMarshaller;
import unitn.introsde.dallatorre.people.generated.People;

public class PeopleUnMarshallerTest extends PeopleTestSuperclass{

	@Test
	public void TestUnmarshallingWorksCorrectly() throws Exception {
		Document document = getValidXmlSampleDocument();
		PeopleUnMarshaller unMarshaller = new PeopleUnMarshaller();
		People people = unMarshaller.unMarshal(document);
		assertNotNull(people);
		assertEquals(people.getPerson().size(), 1);
		assertEquals(people.getPerson().get(0).getFirstname(), "Bond R.");
		assertEquals(people.getPerson().get(0).getActivitypreference().get(0).getName(), "Running");
	}
	
	@Test
	public void TestUnmarshallingWorksCorrectlyWithMoreData() throws Exception {
		Document document = getValidXmlSampleDoubleDocument();
		PeopleUnMarshaller unMarshaller = new PeopleUnMarshaller();
		People people = unMarshaller.unMarshal(document);
		assertNotNull(people);
		assertEquals(people.getPerson().size(), 2);
		assertEquals(people.getPerson().get(0).getFirstname(), "Bond R.");
		assertEquals(people.getPerson().get(1).getFirstname(), "Lollo");
		assertEquals(people.getPerson().get(0).getActivitypreference().get(0).getName(), "Running");
		assertEquals(people.getPerson().get(1).getActivitypreference().get(0).getName(), "Biking");
	}

}
