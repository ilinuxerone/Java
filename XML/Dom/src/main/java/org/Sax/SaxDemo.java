package org.Sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxDemo {

	public static void main(String[] args) throws Exception {
		//1.创建SxaParserFactory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		//2.创建SaxParser解析器
		SAXParser parser = spf.newSAXParser();
		//3.把xml文件和事件处理器绑定
		parser.parse("src/main/java/resource/class.xml", new CustDocumentHandler());
	}
}

class CustDocumentHandler extends DefaultHandler{
	private boolean isName = false;
	private boolean isAge = false;
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if ("name".equalsIgnoreCase(qName))
		{
			this.isName = true;
		}else if ("age".equalsIgnoreCase(qName))
		{
			this.isAge = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		String con = new String(ch, start, length);
		if (!"".equalsIgnoreCase(con.trim()) && (isName || isAge))
		{
			System.out.println(con);
		}
		isName = false;
		isAge = false;
	}
	
}
