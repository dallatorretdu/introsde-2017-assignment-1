package unitn.introsde.dallatorre.people;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class PeopleXPathNavigatorFunctions {
	
	//Returns a generic NodeList result for all paths starting from /people does not handle exceptions
	private NodeList getPeopleCustomXpathResource(Document document, String path) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr;
		expr = xpath.compile("/people"+path);
		Object result;
		result = expr.evaluate(document, XPathConstants.NODESET);
		return (NodeList) result;
	}

	//Executes the above method but handles the exceptions making so it returns a null NodeList in case of failure or empty file
	private NodeList executeGenericPeopleXPath(Document document, String path) {
		NodeList nodes = null;
		try {
			nodes = getPeopleCustomXpathResource(document, path);
		} catch (XPathExpressionException e) {
			return null;
		}
		if (nodes.getLength()==0) {
			return null;
		}
		return nodes;
	}
	
	//Executes the XPath function returning the FIRST activity of a given person as string.
	//No results equals empty string.
	public String getActivity(Document document, int id) {
		String path = "/person[@id="+id+"]/activitypreference";
		NodeList nodes = executeGenericPeopleXPath(document, path);
		if(nodes == null)
			return "";
		return nodes.item(0).getTextContent();
			
		
	}
	//Executes the XPath function returning the FIRST activity of a given person, selecting only the description.
	//No results equals empty string.
	public String getActivityDescription(Document document, int id) {
		String path = "/person[@id="+id+"]/activitypreference/description/text()";
		NodeList nodes = executeGenericPeopleXPath(document, path);
		if(nodes == null)
			return "";
		return nodes.item(0).getNodeValue();
	}
	//Executes the XPath function returning the FIRST activity of a given person selecting the place.
	//No results equals empty string.
	public String getActivityPlace(Document document, int id) {
		String path = "/person[@id="+id+"]/activitypreference/place/text()";
		NodeList nodes = executeGenericPeopleXPath(document, path);
		if(nodes == null)
			return "";
		return nodes.item(0).getNodeValue();
	}
	//Executes the XPath function returning all the persons as a printable string
	//No results equals empty string.
	public String getPeopleDetailedList(Document document) {
		String path = "";
		NodeList nodes = executeGenericPeopleXPath(document, path);
		if(nodes == null)
			return "";
		return nodes.item(0).getTextContent();
	}
	//Executes the XPath function returning the people with a certain <>= activity start date 
	//No results equals empty string.
	//XPATH = /person[activitypreference/startdate <|>|= GIVEN DATE]
	//Date should be YYYY-MM-DD as it is saved in XML
	public String getPeopleWithActivityStartDate(Document document, String date, String operator) {
		String path = "/person[number(translate(substring(activitypreference/startdate/text(),1,10),'-','')) "+operator+" number(translate(\""+date+"\",'-',''))]";
		NodeList nodes = executeGenericPeopleXPath(document, path);
		if(nodes == null)
			return "";
		String outputList = "";
		for (int i = 0; i < nodes.getLength(); i++) {
			outputList += nodes.item(i).getTextContent();
		}
		return outputList;
	}

}
