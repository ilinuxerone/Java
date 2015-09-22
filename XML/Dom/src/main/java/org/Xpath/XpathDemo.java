package org.Xpath;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

public class XpathDemo {

	public static void main(String[] args) throws Exception {
		XpathDAOIntf dao = new XpathDAOImpl();
		Document document = dao.getDocument("src/main/java/resource/XpathDemo.xml");
		
		//3.可以使用xpath随心读取
				List e=document.selectNodes("/AAA/BBB[1]/CCC[1]/KKK");//返回多个元素 document.selectSingleNode
				System.out.println(((Element)e.get(0)).getText());
				System.out.println(((Element)e.get(0)).attributeValue("age"));
				
				
				//如果我们确定只有一个Node,元素则可以使用selectSingleNode
				Element e2=(Element) document.selectSingleNode("/AAA/BBB[last()]");
				System.out.println(e2.getText());
	}

}
