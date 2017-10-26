package unitn.introsde.dallatorre.people;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import unitn.introsde.dallatorre.people.generated.People;

public class PersonJSONMarshaller {

	//Marshal the people object to a JSON file
	public static void marshalToFile(People people, String filename) throws IOException {
		ObjectMapper mapper = initializeMapper();
        mapper.writeValue(new File(filename), people);
	}
	//Marshal people into a JSON string
	public static String marshal(People people) throws JsonProcessingException {
		ObjectMapper mapper = initializeMapper();
     	return mapper.writeValueAsString(people);
	}
	//Executes the above printing directly in console
	public static void marshalToConsole(People people) throws JsonProcessingException {
        System.out.println(marshal(people));
	}
	
	//Generic mapper initialization, refactored in a method.
	private static ObjectMapper initializeMapper() {
		// Jackson Object Mapper 
		ObjectMapper mapper = new ObjectMapper();
		// Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();
		// configure as necessary
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
		return mapper;
	}
}
