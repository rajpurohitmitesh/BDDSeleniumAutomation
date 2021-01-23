package Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class BaseClass {

	static String projectPath = System.getProperty("user.dir");
	static SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	static String strDate = formatter.format(new Date());
	static ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
			projectPath + "/ExecutionReport/extentReport_" + strDate + ".html");
	static ExtentReports extent = new ExtentReports();

	private final String propertyFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
	public static WebDriver driver = null;
	public static ExtentTest myReport;
	public static Properties properties;

	public BaseClass() {

		ConfigFileReader(propertyFilePath);
		extent.attachReporter(htmlReporter);
		myReport = extent.createTest("hotel Discover E2E Flow",
				"This test is to validate E2E flow for hotel disover on hotel website");
	}

	/*
	 * This method is used for launching the browser
	 */
	public static void launchBrowser() {
		try {
			myReport.log(Status.INFO, "Launching " + properties.getProperty("browser") + "Browser");
			
			String browserName = properties.getProperty("browser");
			
			switch (browserName) {
			  case "Chrome":
				  System.setProperty("webdriver.chrome.driver",
							projectPath + "/src/test/resources/drivers/chromedriver.exe");
					driver = new ChromeDriver();
			    break;
			  case "IE":
				// here code for IE Browser
			    break;
			  case "Firefox":
				// here code for Firefox Browser
				    break;
			  default:
			    System.out.println("browser name did not found/match in config file");
			    myReport.fail("browser name did not found in config file");
			}
			

			driver.manage().window().maximize();
			myReport.pass(browserName+" browser launched");
			myReport.log(Status.INFO, "navigating to hotel discover web page");
			driver.get(properties.getProperty("URL"));
			myReport.pass("navigated to hotel discover web page");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this function is use to read/load the config.properties file
	 */
	public static void ConfigFileReader(String propertyFilePath) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	/*
	 * this function is used to close the browser and flushing activity for html report
	 */
	public static void tearDown() {
		try {
		myReport.log(Status.INFO, "closing the browser");
		System.out.println("closing the browser");
		driver.quit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			extent.flush();
			myReport.log(Status.INFO, "HTML report generated.....");
			System.out.println("HTML report generated.....");
		}
	}
	
	

}
