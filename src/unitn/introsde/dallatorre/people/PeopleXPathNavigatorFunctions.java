package unitn.introsde.dallatorre.people;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class PeopleXPathNavigatorFunctions {
	
	private NodeList getPeopleXpathRes(Document document, String path) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr;
		expr = xpath.compile("/people"+path);
		Object result;
		result = expr.evaluate(document, XPathConstants.NODESET);
		return (NodeList) result;
	}


	private NodeList executePeopleXPath(Document document, String path) {
		NodeList nodes = null;
		try {
			nodes = getPeopleXpathRes(document, path);
		} catch (XPathExpressionException e) {
			return null;
		}
		if (nodes.getLength()==0) {
			return null;
		}
		return nodes;
	}
	
	public String getActivity(Document document, int id) {
		String path = "/person[@id="+id+"]/activitypreference";
		NodeList nodes = executePeopleXPath(document, path);
		if(nodes == null)
			return "";
		return nodes.item(0).getTextContent();
			
		
	}
	
	public String getActivityDescription(Document document, int id) {
		String path = "/person[@id="+id+"]/activitypreference/description/text()";
		NodeList nodes = executePeopleXPath(document, path);
		if(nodes == null)
			return "";
		return nodes.item(0).getNodeValue();
	}

	public String getActivityPlace(Document document, int id) {
		String path = "/person[@id="+id+"]/activitypreference/place/text()";
		NodeList nodes = executePeopleXPath(document, path);
		if(nodes == null)
			return "";
		return nodes.item(0).getNodeValue();
	}

	public String getPeopleDetailedList(Document document) {
		String path = "";
		NodeList nodes = executePeopleXPath(document, path);
		if(nodes == null)
			return "";
		return nodes.item(0).getTextContent();
	}

	public String getPeopleWithActivityStartDate(Document document, String date, String operator) {
		String path = "/person[number(translate(substring(activitypreference/startdate/text(),1,10),'-','')) "+operator+" number(translate(\""+date+"\",'-',''))]";
		NodeList nodes = executePeopleXPath(document, path);
		if(nodes == null)
			return "";
		String outputList = "";
		for (int i = 0; i < nodes.getLength(); i++) {
			outputList += nodes.item(i).getTextContent();
		}
		return outputList;
	}

}
