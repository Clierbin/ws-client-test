package com.xavier.ws.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface TestService {

	@WebMethod
	String sayHello(String word);
}
