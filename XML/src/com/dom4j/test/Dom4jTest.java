package com.dom4j.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jTest {

	//演示使用dom4j对xml文件进行crud操作
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//1.得到解析器
		SAXReader saxReader=new SAXReader();
		//2指定解析哪个xml文件
		Document document=saxReader.read(new File("src/com/dom4j/test/myclasses3.xml"));
		
		//list(document.getRootElement());//得到根元素.
		//read(document);
		//add(document);
		//del(document);
		//update(document);
		addByIndex(document);
	}
	
	
	//添加一个元素到指定位置( 林冲到 卢俊义后,在武松前)
	
	public static void addByIndex(Document document) throws Exception{
		
		//创建一个元素
		Element newHero=DocumentHelper.createElement("学生");
		newHero.setText("林冲");
		//得到所有学生的list
		List allHeros=document.getRootElement().elements("学生");
		
		for(int i=0;i<allHeros.size();i++){
			//取出各个人的名
			Element name=((Element)allHeros.get(i)).element("名字");
			if(name.getText().equals("卢俊义")){
				//System.out.println("发现 "+i);
				allHeros.add(i+1, newHero);
				break;
			}
		}
		
		//allHeros.add(1,newHero);
		//更新
		//直接输出会出现中文乱码:
		OutputFormat output=OutputFormat.createPrettyPrint();
		output.setEncoding("utf-8");//输出的编码utf-8
		
		//把我们的xml文件更新
		// lets write to a file
		//new FileOutputStream(new File("src/myClass.xml"))
        XMLWriter writer = new XMLWriter(
        		new FileOutputStream(new File("src/com/dom4j/test/myclasses3.xml")) /*new FileWriter( "src/com/dom4j/test/myclasses3.xml" )*/,output
        );
        writer.write( document );
        writer.close();
		
		
	}
	
	//更新元素(要求把所有学生的年龄+3)
	public static void update(Document document ) throws Exception{
		
		//得到所有学生的年龄
		List<Element> stus =document.getRootElement().elements("学生");
		
		for(Element el:stus){
			//从el中取出年龄
			Element age=el.element("年龄");
			age.setText((Integer.parseInt(age.getText())+3)+"");
			Element name=el.element("名字");
			//name.setAttributeValue("别名", "hello,world");
			name.addAttribute("别名2", "okok");
		}
		
		//更新
		//直接输出会出现中文乱码:
		OutputFormat output=OutputFormat.createPrettyPrint();
		output.setEncoding("utf-8");//输出的编码utf-8
		
		//把我们的xml文件更新
		// lets write to a file
		//new FileOutputStream(new File("src/myClass.xml"))
        XMLWriter writer = new XMLWriter(
        		new FileOutputStream(new File("src/com/dom4j/test/myclasses3.xml")) /*new FileWriter( "src/com/dom4j/test/myclasses3.xml" )*/,output
        );
        writer.write( document );
        writer.close();
		
	}
	
	
	//删除元素(要求：删除第一个学生)
	public static void del(Document document) throws Exception{
		
		//找到该元素
		Element stu1=document.getRootElement().element("学生").element("名字");
		//删除元素
		///stu1.getParent().remove(stu1);
		
		//删除元素的某个属性
		stu1.remove(stu1.attribute("别名"));
		
		
		//更新xml
		
		//直接输出会出现中文乱码:
		OutputFormat output=OutputFormat.createPrettyPrint();
		output.setEncoding("utf-8");//输出的编码utf-8
		
		//把我们的xml文件更新
		// lets write to a file
		//new FileOutputStream(new File("src/myClass.xml"))
        XMLWriter writer = new XMLWriter(
        		new FileOutputStream(new File("src/com/dom4j/test/myclasses3.xml")) /*new FileWriter( "src/com/dom4j/test/myclasses3.xml" )*/,output
        );
        writer.write( document );
        writer.close();
		
	}
	
	//添加元素(要求: 添加一个学生到xml中)
	
	public static void add(Document docment) throws Exception{
		
		//首先我们来创建一个学生节点对象 
		Element newStu=DocumentHelper.createElement("学生");
		Element newStu_name=DocumentHelper.createElement("名字");
		//如何给元素添加属性
		newStu_name.addAttribute("别名", "江江");
		newStu_name.setText("宋江");
		Element newStu_age=DocumentHelper.createElement("年龄");
		Element newStu_intro=DocumentHelper.createElement("介绍");
		
		//把三个子元素（节点）加到 newStu下
		newStu.add(newStu_name);
		newStu.add(newStu_age);
		newStu.add(newStu_intro);
		//再把newStu节点加到根元素
		docment.getRootElement().add(newStu);
		
		//直接输出会出现中文乱码:
		OutputFormat output=OutputFormat.createPrettyPrint();
		output.setEncoding("utf-8");//输出的编码utf-8
		
		//把我们的xml文件更新
		// lets write to a file
		//new FileOutputStream(new File("src/myClass.xml"))
        XMLWriter writer = new XMLWriter(
        		new FileOutputStream(new File("src/com/dom4j/test/myclasses3.xml")) /*new FileWriter( "src/com/dom4j/test/myclasses3.xml" )*/,output
        );
        writer.write( docment );
        writer.close();


		
	}
	
	//如何指定读取某个元素(要求 ：读取第一个学生的信息,)
	public static void read(Document docment){
		
		//得到根元素
		Element root=docment.getRootElement();
		//root.elements("学生") :表示取出 root元素 下的所有学生元素
		//root.element("学生") :表示取出 root元素 下的第一个学生元素
		//root.elements("学生").get(0) : 表示取出 root元素 下的第一个学生元素
		Element stu= (Element) root.elements("学生").get(0);
		
		Element stu_name=stu.element("名字");
		
		System.out.println(stu_name.getText()+" 别名 "+stu_name.attributeValue("别名"));
		//System.out.println(((Element)stu.elements("名字").get(0)).getText());
		
		
		
		//看看下面的代码是否能够(跨层取XXXXX)
		//Element stu2= (Element) root.elements("名字").get(0);//==>xpath
		//System.out.println(root.elements("名字").size());
		
		//如果使用xpath，取出第一个学生
		Element stu2=(Element) docment.selectSingleNode("/班级/学生[1]");
	}

	//遍历我们的xml文件
	public static void list(Element element){
		
		System.out.println(element.getName()+element.getTextTrim());
		
		Iterator iterator=element.elementIterator();
		
		while(iterator.hasNext()){
			
			Element e=(Element) iterator.next();
			//递归
			list(e);
		}
		
	}
	
}
