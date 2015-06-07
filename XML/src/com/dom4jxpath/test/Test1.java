package com.dom4jxpath.test;

import java.util.List;

import org.dom4j.*;
import org.dom4j.io.*;

public class Test1 {

	//dom4j 配合 xpath案例
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//1.得到SAXReader 解析器
		SAXReader saxReader =new SAXReader();
		//2.指定去解析哪个文件
		Document document = saxReader.read("src/com/dom4jxpath/test/test.xml");
		
		//3.可以使用xpath随心读取
		List e=document.selectNodes("/AAA/BBB[1]/CCC[1]/KKK");//返回多个元素 document.selectSingleNode
		System.out.println(((Element)e.get(0)).getText());
		//System.out.println(((Attribute)e.get(1)).getText());
		
		
		//如果我们确定只有一个Node,元素则可以使用selectSingleNode
		//Element e2=(Element) document.selectSingleNode("/AAA/BBB[last()]");
		//System.out.println(e2.getText());
	}

}
