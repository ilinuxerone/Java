package org.Xpath;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class XpathDAOImpl implements XpathDAOIntf {

	public Document getDocument(String filePath) throws Exception {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(filePath);
		
		return document;
	}

}
