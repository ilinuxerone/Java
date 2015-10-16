package org.WebService.ws.annotation;

import javax.jws.WebService;

@WebService
public interface ICalculator {
	float add(float a, float b);
	float minus(float a, float b);
	float multiply(float a, float b);
	float divide(float a, float b);
}
