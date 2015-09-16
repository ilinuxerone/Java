package org.Dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *Demo of Dom parse xml 
 *
 */
public interface DomDAO 
{
	public Document getDocument(String filePath) throws Exception;
	
	public String getFirstElementTextVal(Element element, String name);
	
	public void insertNode(Document document) throws Exception;
	
	public void deleteNode(Document document) throws Exception;
	
	public void findNode(Document document) throws Exception;
	
	public void updateNode(Document document) throws Exception;
	
	public void traverseNodes(Node node) throws Exception;
}
