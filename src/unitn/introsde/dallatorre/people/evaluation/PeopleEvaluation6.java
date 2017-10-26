package unitn.introsde.dallatorre.people.evaluation;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.fasterxml.jackson.core.JsonProcessingException;

import unitn.introsde.dallatorre.people.PersonJSONMarshaller;
import unitn.introsde.dallatorre.people.creator.PeopleGenerator;
import unitn.introsde.dallatorre.people.generated.People;

public class PeopleEvaluation6 {

	public static void main(String[] args) throws JsonProcessingException, FileNotFoundException {
		People people = new People();
		//generate a list of 3 persons and add them to people
		PeopleGenerator peopleGenerator = new PeopleGenerator();
		people.getPerson().addAll(peopleGenerator.generatePersonList(3));
		//Marshal the object to JSON STRING
		String json = PersonJSONMarshaller.marshal(people);
		//print the string
		System.out.println(json);
		//Print the string to a file
		try(  PrintWriter out = new PrintWriter( "Evaluation6.json" )  ){
		    out.println(json);
		}
	}

}
