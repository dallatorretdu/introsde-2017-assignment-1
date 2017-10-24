package unitn.introsde.dallatorre.test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import unitn.introsde.dallatorre.people.creator.PeopleGenerator;
import unitn.introsde.dallatorre.people.generated.People;

public class PeopleTestSuperclass {

	protected static int countLines(String str) {
	   String[] lines = str.split("\r\n|\r|\n");
	   return  lines.length;
	}
	
	public static OutputStream returnNewStringableOutputStream() {
		OutputStream output = new OutputStream()
	    {
	        private StringBuilder string = new StringBuilder();
	        @Override
	        public void write(int b) throws IOException {
	            this.string.append((char) b );
	        }
	        //Netbeans IDE automatically overrides this toString()
	        public String toString(){
	            return this.string.toString();
	        }
	    };
		return output;
	}
	
	protected People generateExamplePeople() {
		People people = new People();
		PeopleGenerator peopleGenerator = new PeopleGenerator();
		people.getPerson().addAll(peopleGenerator.generatePersonList(5));
		return people;
	}
	
	protected static Document loadXMLFromString(String xml) throws Exception{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return builder.parse(is);
	}
	
	protected Document getValidXmlSampleDocument() throws Exception {
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
	protected Document getValidXmlSampleDoubleDocument() throws Exception {
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

	public PeopleTestSuperclass() {
		super();
	}

}