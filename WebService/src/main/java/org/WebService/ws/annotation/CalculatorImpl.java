package org.WebService.ws.annotation;

import javax.jws.WebService;

@WebService(endpointInterface="org.WebService.ws.annotation.ICalculator")
public class CalculatorImpl implements ICalculator {

	@Override
	public float add(float a, float b) {
		// TODO Auto-generated method stub
		System.out.println("a+b="+(a+b));  
		return a + b;
	}

	@Override
	public float minus(float a, float b) {
		// TODO Auto-generated method stub
		System.out.println("a-b="+(a-b)); 
		return a - b;
	}

	@Override
	public float multiply(float a, float b) {
		// TODO Auto-generated method stub
		return a * b;
	}

	@Override
	public float divide(float a, float b) {
		// TODO Auto-generated method stub
		return a / b;
	}

}
