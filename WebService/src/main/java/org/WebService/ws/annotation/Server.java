package org.WebService.ws.annotation;

import javax.xml.ws.Endpoint;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String address="http://localhost:9998/hw";
		Endpoint.publish(address, new CalculatorImpl());
	}

}
