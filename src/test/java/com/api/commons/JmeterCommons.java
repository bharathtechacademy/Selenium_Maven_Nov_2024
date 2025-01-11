package com.api.commons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.report.config.ConfigurationException;
import org.apache.jmeter.report.dashboard.GenerationException;
import org.apache.jmeter.report.dashboard.ReportGenerator;
import org.apache.jmeter.reporters.ResultCollector;

public class JmeterCommons {
	
	public static void runJmeterTest(String jmxFile) throws IOException, ConfigurationException, GenerationException {
		
		String jmeterHome = "src/test/resources/jmeter";
		String resultsFilePath = Paths.get(jmeterHome,"results","TestResults.csv").toString();
		String testPlanFilepath = Paths.get(jmeterHome, jmxFile).toString(); 
		
		// Set JMeter home directory
		JMeterUtils.setJMeterHome(jmeterHome);
		
		// Load JMeter properties
//		String propertiesFilePath = Paths.get(jmeterHome,"bin","jmeter.properties").toString();
		String propertiesFilePath =System.getProperty("user.dir")+"\\src\\test\\resources\\jmeter\\bin\\jmeter.properties";
		JMeterUtils.loadJMeterProperties(propertiesFilePath);
		
		// Add JMeter test plan to run
		File testPlanFile = new File(testPlanFilepath);
		HashTree testPlanTree = SaveService.loadTree(testPlanFile);
		
		//Configure Results collector to save the results and prepare the report
		ResultCollector resultCollector = new ResultCollector();
		resultCollector.setFilename(resultsFilePath);
		
		// Add test results and generate report
		StandardJMeterEngine jmeter = new StandardJMeterEngine();
		testPlanTree.add(testPlanTree.getArray()[0],resultCollector);
		jmeter.configure(testPlanTree);
		jmeter.run();
		
		// Generate Html Report
		ReportGenerator reportGenerator = new ReportGenerator(resultsFilePath,null);
		reportGenerator.generate();
		
	}

}
