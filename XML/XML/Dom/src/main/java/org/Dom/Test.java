package org.Dom;

import org.w3c.dom.Document;

public class Test {

	public static void main(String[] args) {
		DomDAO domDao = new DomDAOImpl();
		try {
			Document document = domDao.getDocument("src/main/java/resource/class.xml");

			domDao.insertNode(document);
			domDao.findNode(document);
			domDao.updateNode(document);
			domDao.traverseNodes(document.getDocumentElement());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
