package com.xavier.ws.api;

import com.xavier.ws.send.ToWebSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class TestApi {

	/*@Autowired
	private QueryOracle queryOracle;
	@Autowired
	private ToFile toFile;*/

	@Autowired
	private ToWebSV toWebSV;

	@GetMapping(path = "test")
	public String test() {
		//toFile.date2File(queryOracle.test());
		return "OK";
	}

	@GetMapping(path = "websv")
	public String websv() {
		//http://<前置机IP>/ws/query/data
		System.out.println("GetIn");
		String webUrl = "http://192.168.20.10:8080/dataexchange/ws/query/attachment?wsdl";
		String methodName = "run";
		System.out.println("Calling" + webUrl);
		toWebSV.callWebSV(webUrl, methodName, "A_BW0061_1542604227318");
		System.out.println("Called");
		return "Finished!Looking for console!";
	}
}
