package unitn.introsde.dallatorre.people;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import unitn.introsde.dallatorre.people.generated.ActivityPreference;

public class PeopleXPathNavigatorFunctions {
	
	private NodeList getPeopleXpath(Document document, String path) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr;
		expr = xpath.compile("/people/person"+path);
		Object result;
		result = expr.evaluate(document, XPathConstants.NODESET);
		return (NodeList) result;
	}

	private NodeList getActivityPreferenceXpath(Document document, String path, int id) throws XPathExpressionException {
		return getPeopleXpath(document, "[@id="+id+"]/activitypreference"+path);
	}
	
	public String getActivity(Document document, int id) {
		String path = "";
		NodeList nodes = null;
		try {
			nodes = getActivityPreferenceXpath(document, path, id);
		} catch (XPathExpressionException e) {
			return null;
		}
		
		if (nodes.getLength()==0) {
			return null;
		}
		return nodes.item(0).getTextContent();
			
		
	}
	
	public String getActivityDescription(Document document, int id) {
		String path = "/description/text()";
		NodeList nodes = null;
		try {
			nodes = getActivityPreferenceXpath(document, path, id);
		} catch (XPathExpressionException e) {
			return null;
		}
		
		if (nodes.getLength()==0) {
			return null;
		}
		return nodes.item(0).getNodeValue();
	}

	public String getActivityPlace(Document document, int id) {
		String path = "/place/text()";
		NodeList nodes = null;
		try {
			nodes = getActivityPreferenceXpath(document, path, id);
		} catch (XPathExpressionException e) {
			return null;
		}
		
		if (nodes.getLength()==0) {
			return null;
		}
		return nodes.item(0).getNodeValue();
	}

	public String getPeopleDetailedList(Document document) {
		String path = "";
		NodeList nodes = null;
		try {
			nodes = getPeopleXpath(document, path);
		} catch (XPathExpressionException e) {
			return null;
		}
		
		if (nodes.getLength()==0) {
			return null;
		}
		return nodes.item(0).getTextContent();
	}

}
