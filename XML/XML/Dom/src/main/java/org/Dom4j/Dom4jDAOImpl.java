package org.Dom4j;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jDAOImpl implements Dom4jDAOintf {

	public Document getDocument(String filePath) throws Exception {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(filePath);
		
		return document;
	}

	public void add(Document document) throws Exception {
		Element element = DocumentHelper.createElement("student");
		Element name = DocumentHelper.createElement("name");
		name.addAttribute("nick", "jiangjiang");
		name.setText("songjiang");
		Element age = DocumentHelper.createElement("age");
		age.setText("19");
		Element info = DocumentHelper.createElement("introduction");
		
		element.add(name);
		element.add(age);
		element.add(info);		
		document.getRootElement().add(element);
		
		OutputFormat output = OutputFormat.createPrettyPrint();
		output.setEncoding("UTF-8");
		
		XMLWriter writer = new XMLWriter(new FileOutputStream(new File("src/main/java/resource/class.xml")), output);
		writer.write(document);
		writer.close();
		
	}

	public void delete(Document document) throws Exception {
		// TODO Auto-generated method stub

	}

	public void update(Document document) throws Exception {
		// TODO Auto-generated method stub

	}

	public void find(Document document) throws Exception {
		// TODO Auto-generated method stub

	}

	public void tranverse(Document document) throws Exception {
		// TODO Auto-generated method stub

	}

}
