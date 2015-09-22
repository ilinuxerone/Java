package org.Dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomDAOImpl implements DomDAO {
	public Document getDocument(String filePath) throws Exception {		
		//1.得到DocumentBuilderFactory
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		
		//2.得到DocumentBuilder
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		//3.指定文件
		return documentBuilder.parse(filePath);
	}

	public String getFirstElementTextVal(Element element, String name) {
		return  ((Element)element.getElementsByTagName(name).item(0)).getTextContent()+"\t\t";
	}

	public void insertNode(Document document) throws Exception{
		//创建一个新节点
		Element RootElement = document.createElement("student");
		//添加一个属性
		RootElement.setAttribute("sex", "male");
		Element element_name = document.createElement("name");
		element_name.setTextContent("zzh1");
		Element element_age = document.createElement("age");
		element_age.setTextContent("30");
		Element element_desc = document.createElement("description");
		element_desc.setTextContent("soft engineer");
		RootElement.appendChild(element_name);
		RootElement.appendChild(element_age);
		RootElement.appendChild(element_desc);
		
		//把新的节点添加到跟元素
		document.getDocumentElement().appendChild(RootElement);
		
		//得到TransformerFactory
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		//通过TransformerFactory 得到一个转换器
		Transformer  transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult("src/main/java/resource/class.xml"));
	}

	public void deleteNode(Document document) throws Exception {
		Element element = (Element)document.getElementsByTagName("student").item(0);
		element.setAttribute("age", "20");
		element.getParentNode().removeChild(element);
		//得到TransformerFactory
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		//通过TransformerFactory 得到一个转换器
		Transformer  transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult("src/main/java/resource/class.xml"));
	}

	public void findNode(Document document) throws Exception {
		NodeList nl = document.getElementsByTagName("student");
		Element student = (Element)nl.item(0);
		Element name = (Element)student.getElementsByTagName("name").item(0);
		System.out.println(name.getTextContent());
	}

	public void updateNode(Document document) throws Exception{
		Element node=(Element) document.getElementsByTagName("student").item(0);
		Element node_name=(Element) node.getElementsByTagName("name").item(0);
		node_name.setTextContent("songjiang");
		//得到TransformerFactory
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		//通过TransformerFactory 得到一个转换器
		Transformer  transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult("src/main/java/resource/class.xml"));
	}

	public void traverseNodes(Node node) throws Exception{
		if (node.ELEMENT_NODE == node.getNodeType())
		{
			System.out.println("名字"+node.getNodeName());
		}
		
		NodeList nodeList = node.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node n = nodeList.item(i);
			traverseNodes(n);
		}
	}

}
