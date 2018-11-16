package com.xavier.ws.client.out;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Service
public class ToFile {

	private String fileFolder = "D:/xfjwj";

	private String newLine = System.getProperty("line.separator");

	public void test(List<Map<String, Object>> mapList) throws IOException {
		Path rootLocation = Paths.get(fileFolder);
		if (Files.notExists(rootLocation)) {
			Files.createDirectories(rootLocation);
		}
		String restut = dataConvert(mapList);
		Files.write(rootLocation.resolve(getFileName()), restut.getBytes());
	}

	private String getFileName() {
		return "xfj_test_0001.dat";
	}

	private String dataConvert(List<Map<String, Object>> mapList) {
		CharSequence lineDelimiter = newLine;
		CharSequence prefix = "";
		CharSequence suffix = "";
		StringJoiner result = new StringJoiner(lineDelimiter, prefix, suffix);
		for (Map<String, Object> it : mapList) {
			CharSequence delimiter = "|";
			StringJoiner line = new StringJoiner(delimiter, prefix, suffix);
			for (Object value : it.values()) {
				if (null != value) {
					line.add(value.toString());
				} else {
					line.add("");
				}
			}
			result.add(line.toString());
		}
		return result.toString();
	}
}
