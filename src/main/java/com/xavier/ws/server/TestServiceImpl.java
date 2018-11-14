package com.xavier.ws.server;

import javax.jws.WebService;

@WebService
public class TestServiceImpl implements TestService {

	@Override
	public String sayHello(String word) {
		return word + ":shou dao le !";
	}
}
