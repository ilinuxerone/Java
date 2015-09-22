package org.Dom4j;

import org.dom4j.Document;

public class TestDemo {

	public static void main(String[] args) throws Exception {
		Dom4jDAOintf dao = new Dom4jDAOImpl();
		Document document = dao.getDocument("src/main/java/resource/class.xml");
		//list(document.getRootElement());//得到根元素.
		//read(document);
		dao.add(document);
		//del(document);
		//update(document);
		//addByIndex(document);
	}

}
