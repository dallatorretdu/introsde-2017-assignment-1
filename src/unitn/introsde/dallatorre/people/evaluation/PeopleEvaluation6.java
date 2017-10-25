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
		PeopleGenerator peopleGenerator = new PeopleGenerator();
		people.getPerson().addAll(peopleGenerator.generatePersonList(3));
		
		String json = PersonJSONMarshaller.marshal(people);
		System.out.println(json);
		
		try(  PrintWriter out = new PrintWriter( "Evaluation6.json" )  ){
		    out.println(json);
		}
	}

}
