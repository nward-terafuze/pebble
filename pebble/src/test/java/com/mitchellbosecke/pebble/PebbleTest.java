package com.mitchellbosecke.pebble;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

public class PebbleTest {

	private String templateOutputBasePath = "src/test/resources/expected-output";
	
	protected String getExpectedOutput(String filename) throws IOException {
		
		Path path = Paths.get(this.templateOutputBasePath, filename);
	    System.out.println(path.toAbsolutePath());
	    return FileUtils.readFileToString(path.toFile(), "UTF-8");
	}
	
}
