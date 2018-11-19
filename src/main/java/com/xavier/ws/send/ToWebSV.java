package com.xavier.ws.send;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.stereotype.Service;

@Service
public class ToWebSV {

	public Object callWebSV(String wsdUrl, String operationName, String... params) {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(wsdUrl);
		//client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
		Object[] objects;
		try {
			System.out.println("调用");
			// invoke("方法名",参数1,参数2,参数3....);
			objects = client.invoke(operationName, params);
			return objects[0];
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
