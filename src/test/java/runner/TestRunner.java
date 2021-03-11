package runner;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.And;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.gherkin.model.Then;
import com.aventstack.extentreports.gherkin.model.When;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.Runner.Builder;
import com.intuit.karate.core.Result;
import com.intuit.karate.core.ScenarioResult;
import com.intuit.karate.core.Step;

import CustomeReport.CustomExtentReport;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

//@RunWith(Karate.class)
@KarateOptions(tags = { "@login2,@smoke", "~@ignore" })

public class TestRunner {
	

	@Test
	public void testParallel() {
		String currentDirectory = System.getProperty("user.dir");
		String cucumberReport = currentDirectory + "/target/cucumber-html-reports";
		String SurefireReport = currentDirectory + "/target/surefire-reports";
		removeDir(cucumberReport);
		removeDir(SurefireReport);		

		
		Results results = Runner.parallel(getClass(), 5);
		
		CustomExtentReport extentReport = new CustomExtentReport()
				.withKarateResult(results)
				.withReportDir(results.getReportDir())
				.withReportTitle("Karate Test Execution Report");
		extentReport.generateExtentReport();
		
		generateReport(results.getReportDir());
		assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
		
//		Builder aRunner = new Builder();
//		aRunner.path("classpath:com/api/automation");
//		Results result = aRunner.parallel(5);
		
//		CustomExtentReport extentReport = new CustomExtentReport()
//				.withKarateResult(results)
//				.withReportDir(results.getReportDir())
//				.withReportTitle("Karate Test Execution Report");
//		extentReport.generateExtentReport();

	}

	public static void generateReport(String karateOutputPath) {
		Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] { "json" }, true);
		List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
		jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
		Configuration config = new Configuration(new File("target"), "API Automation project");
		ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
		reportBuilder.generateReports();
	}

	public static void removeDir(String dirPath) {
		File dir = new File(dirPath);
		removeDirectory(dir);
	}

	public static void removeDirectory(File dir) {

		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			if (files != null && files.length > 0) {
				for (File aFile : files) {
					removeDirectory(aFile);
				}
			}
			dir.delete();
		} else {
			dir.delete();
		}
	}

	
}
