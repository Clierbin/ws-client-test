package com.xavier.ws.client.api;

import com.xavier.ws.client.db.QueryOracle;
import com.xavier.ws.client.out.ToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/")
public class TestApi {

	@Autowired
	private QueryOracle queryOracle;
	@Autowired
	private ToFile toFile;

	@GetMapping(path = "test")
	public String test() {
		try {
			toFile.test(queryOracle.test());
			return "OK";
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}

	}
}
