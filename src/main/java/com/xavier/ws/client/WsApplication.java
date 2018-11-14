package com.xavier.ws.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class WsApplication {

	public static void main(String[] args) {
		//SpringApplication.run(WsApplication.class, args);
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://localhost:8081/test?wsdl");
		//client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
		Object[] objects = new Object[200];
		try {
			// invoke("方法名",参数1,参数2,参数3....);
			objects = client.invoke("sayHello","hehe");
			System.out.println("返回数据:" + objects[0]);
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}
}
