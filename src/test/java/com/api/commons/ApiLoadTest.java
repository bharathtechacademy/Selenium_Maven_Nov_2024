package com.api.commons;

import java.io.IOException;

import org.apache.jmeter.report.config.ConfigurationException;
import org.apache.jmeter.report.dashboard.GenerationException;

public class ApiLoadTest {

	public static void main(String[] args) throws IOException, ConfigurationException, GenerationException {
		JmeterCommons.runJmeterTest("performance.jmx");
	}

}
