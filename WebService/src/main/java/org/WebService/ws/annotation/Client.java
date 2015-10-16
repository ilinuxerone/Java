package org.WebService.ws.annotation;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Client {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		URL url=new URL("http://localhost:9998/hw?wsdl");
		QName qname = new QName("http://annotation.ws.WebService.org/", "CalculatorImplService");
		Service service = Service.create(url, qname);
		ICalculator cal = service.getPort(ICalculator.class);
		cal.add(1, 2);
		cal.minus(2, 1);
		
	}

}
