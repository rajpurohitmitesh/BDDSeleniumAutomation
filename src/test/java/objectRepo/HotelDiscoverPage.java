package objectRepo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class HotelDiscoverPage {

	protected WebDriver driver;
	protected ExtentTest myReport;
	SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	String strDate;

	@FindBy(xpath = "(//input[@type='search'])[1]")
	WebElement txt_search;

	@FindBy(xpath = "(//li[@class='SearchForm_control__2IwnO SearchForm_distance__1BR6G'])[1]")
	WebElement btn_radius;

	@FindAll(@FindBy(how = How.XPATH, using = "//span[@class='ControlSelectItem_label__IbRcQ']"))
	List<WebElement> allRadiusElements;

	@FindBy(xpath = "(//div[@class='DistanceControl_label__UtfjY']//span[@class='IconLabel_label__rUNtX IconLabel_truncateLabel__PDMe-'])[1]")
	WebElement txt_radiusValue;

	@FindBy(xpath = "(//li[@class='SearchForm_control__2IwnO SearchForm_stay__21GWP'])[1]")
	WebElement btn_stayDates;

	@FindAll(@FindBy(how = How.XPATH, using = "//div[@class='Inspiration_searchForm__3NLKF']//span[@class='ControlSelectItem_label__IbRcQ']"))
	List<WebElement> allStayDayElements;

	@FindBy(xpath = "//input[@class='NumberInput_value__1i8N5']")
	WebElement txt_nightField;

	@FindBy(xpath = "(//div[@class='StaySelect_duration__32rUY']//span[@class='Icon_container__2BGOM Icon_contain__2U53c'])[2]")
	WebElement btn_nightIncrement;

	@FindBy(xpath = "//div[@class='StaySelect_apply__1bMzT']//button")
	WebElement btn_StayDateApply;

	@FindBy(xpath = "(//span[@class='StayControl_dateRanges__1-OgH'])[1]")
	WebElement txt_monthStayDateSelected;

	@FindBy(xpath = "(//span[@class='StayControl_lengthOfStay__Yo0OW'])[1]")
	WebElement txt_nightStayDateSelected;

	@FindBy(xpath = "(//li[@class='SearchForm_control__2IwnO SearchForm_guest__2Lc5l'])[1]")
	WebElement btn_guest;

	@FindBy(xpath = "//input[@data-qa='guests-adults-input-value']")
	WebElement txt_adultsField;

	@FindBy(xpath = "//input[@data-qa='guests-children-input-value']")
	WebElement txt_childField;

	@FindBy(xpath = "//input[@data-qa='guests-rooms-input-value']")
	WebElement txt_roomField;

	@FindBy(xpath = "//div[@data-qa='guests-adults-input-plus']//span[@class='Icon_container__2BGOM Icon_contain__2U53c']")
	WebElement btn_adultsIncrement;

	@FindBy(xpath = "//div[@data-qa='guests-children-input-plus']//span[@class='Icon_container__2BGOM Icon_contain__2U53c']")
	WebElement btn_childIncrement;

	@FindBy(xpath = "//div[@data-qa='guests-rooms-input-plus']//span[@class='Icon_container__2BGOM Icon_contain__2U53c']")
	WebElement btn_roomIncrement;

	@FindBy(xpath = "(//div[@class='GuestSelect_button__yIFVb']//button)[2]")
	WebElement btn_guestApply;

	@FindBy(xpath = "(//li[@class='SearchForm_control__2IwnO SearchForm_guest__2Lc5l']//span[@class='IconLabel_label__rUNtX IconLabel_truncateLabel__PDMe-'])[1]")
	WebElement txt_guestSelected;

	@FindBy(xpath = "(//li[@class='SearchForm_control__2IwnO SearchForm_guest__2Lc5l']//span[@class='IconLabel_label__rUNtX IconLabel_truncateLabel__PDMe-'])[2]")
	WebElement txt_roomsSelected;

	@FindAll(@FindBy(how = How.XPATH, using = "//a[@class='Tile_link__3kTDO']"))
	List<WebElement> allViewDealElements;

	@FindBy(xpath = "(//div[@class='Footer_button__6d5Ut']//button)[1]")
	WebElement btn_viewDeal1stButton;

	public HotelDiscoverPage(WebDriver driver, ExtentTest myReport) {
		this.driver = driver;
		this.myReport = myReport;
		PageFactory.initElements(driver, this);

		if (!driver.getTitle().equals("hotel Discover")) {
			myReport.log(Status.FAIL, "seems we are not navigated to hotel web application");
			throw new IllegalStateException(
					"This is not hotel discover page. The current URL is" + driver.getCurrentUrl());
		}
	}

	/*
	 * this method is used to enter location
	 */
	public void enterLocation(String location) {
		try {
			txt_search.click();
			txt_search.clear();
			txt_search.sendKeys(location);
			myReport.pass("entered location as: " + location);
			captureScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method is used to capture the screenshot
	 */
	public void captureScreen() {
		try {
			strDate = formatter.format(new Date());
			File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			BufferedImage img = ImageIO.read(screen);
			File filetest = Paths.get(".").toAbsolutePath().normalize().toFile();
			ImageIO.write(img, "png", new File(filetest + "\\Screenshots\\" + "Screenshot_" + strDate + ".png"));

			myReport.info("",
					MediaEntityBuilder.createScreenCaptureFromPath(
							System.getProperty("user.dir") + "\\Screenshots\\" + "Screenshot_" + strDate + ".png")
							.build());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method is used to click on Radius Field
	 */

	public void clickRadius() {
		try {
			btn_radius.click();
			myReport.pass("clicked on radius field");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method is used to select the radius value from radius dropdown
	 */

	public void selectRadius(String radiusValue) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,50)");
			List<WebElement> li = allRadiusElements;
			for (int i = 0; i < li.size(); i++) {
				if (li.get(i).getText().contains(radiusValue)) {
					li.get(i).click();
					myReport.pass("clicked on : " + radiusValue + " miles from radius list");
					captureScreen();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method is used to verify the radius value is selected or not
	 */

	public void verifyRadiusFieldValue(String expectedRadiusValue) {
		try {
			String ActualRadiusValue = txt_radiusValue.getText();
			if (ActualRadiusValue.trim().contains(expectedRadiusValue.trim())) {
				myReport.pass("radius is selected as : " + expectedRadiusValue + " miles");
				captureScreen();
			} else {
				myReport.log(Status.FAIL, "radius is displaying as : " + ActualRadiusValue + ", but expected is:"
						+ expectedRadiusValue + " miles");
				captureScreen();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method is used to click on Stay Dates Field
	 */

	public void clickStayDates() {
		try {
			btn_stayDates.click();
			myReport.pass("clicked on stay dates field");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method is used to select the month for stay on Stay Dates Field
	 */

	public void selectStayMonths(String stayMonth) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)");
			List<WebElement> li = allStayDayElements;
			for (int i = 0; i < li.size(); i++) {
				if (!li.get(i).getText().equals(stayMonth)) {
					li.get(i).click();
				}
			}
			myReport.pass("selected the month as: " + stayMonth);
			captureScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method is used to click on + icon for night optino in stay dates Field
	 */

	public void incrementNight(String StayNight) {
		try {
			btn_nightIncrement.click();
			myReport.log(Status.INFO, "clicked on + to add one night");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method is used to click on Apply button on StayDate Field
	 */

	public void clickApplyForStayDate() {
		try {
			btn_StayDateApply.click();
			myReport.log(Status.INFO, "clicked on Apply button for stay dates field");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method is used to verify that selected option on stay dates Field
	 */

	public void verifyStayDateFieldValue(String expectedStayDateFieldValue) {
		try {
			String ActualMonthNight = txt_monthStayDateSelected.getText() + txt_nightStayDateSelected.getText();
			if (ActualMonthNight.trim().equals(expectedStayDateFieldValue.trim())) {
				myReport.pass("stay dates field values are displaying as expected:" + expectedStayDateFieldValue);
				captureScreen();
			} else {
				myReport.log(Status.FAIL, "stay dates field values are not displaying as expected, actual is: "
						+ ActualMonthNight + ", but exepected is:" + expectedStayDateFieldValue);
				captureScreen();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method is used to click on Guest Field
	 */

	public void clickGuest() {
		try {
			btn_guest.click();
			myReport.log(Status.INFO, "clicked on Guests field label");
			captureScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method is used to click on + icon for adult on Guest Field
	 */

	public void incrementAdult(String NoOfAdults) {
		try {
			btn_adultsIncrement.click();
			myReport.log(Status.INFO, "clicked on + to add one adult");
			verifyAdultCountIsIncremented(NoOfAdults);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void verifyAdultCountIsIncremented(String NoOfAdults) {
		try {

		} catch (Exception e) {

		}
	}

	/*
	 * this method is used to click on + icon for child on Guest Field
	 */

	public void incrementChild(String NoOfChilds) {
		try {
			btn_childIncrement.click();
			myReport.log(Status.INFO, "clicked on + to add one child");
			verifyChildCountIsIncremented(NoOfChilds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void verifyChildCountIsIncremented(String NoOfChilds) {
		try {

		} catch (Exception e) {

		}
	}

	/*
	 * this method is used to click on + icon for rooms on Guest Field
	 */

	public void incrementRoom(String NoOfRooms) {
		try {
			btn_roomIncrement.click();
			myReport.log(Status.INFO, "clicked on + to add one room");
			verifyRoomCountIsIncremented(NoOfRooms);
			captureScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void verifyRoomCountIsIncremented(String NoOfRooms) {
		try {

		} catch (Exception e) {

		}
	}

	/*
	 * this method is used to click on Apply button on Guest Field
	 */

	public void clickApplyForGuest() {
		try {
			btn_guestApply.click();
			myReport.log(Status.INFO, "clicked on Apply button Guests field");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method is used to verify the selected option on Guest Field
	 */

	public void verifyGuestFieldValue(String expectedGuestFieldValue, String expectedRoomFieldValue) {
		try {
			String ActualGuest = txt_guestSelected.getText();
			String ActualRooms = txt_roomsSelected.getText();
			if (ActualGuest.trim().equals(expectedGuestFieldValue.trim())) {
				myReport.pass("guest values are displaying as expected:"+expectedGuestFieldValue+" guests");
			} else {
				myReport.log(Status.FAIL, "guest field values are not displaying as expected, actual is: " + ActualGuest
						+ ", but exepected is:" + expectedGuestFieldValue);
			}

			if (ActualRooms.trim().equals(expectedRoomFieldValue.trim())) {
				myReport.pass("room values are displaying as expected:"+expectedRoomFieldValue+" rooms");
			} else {
				myReport.log(Status.FAIL, "room field values are not displaying as expected, actual is: " + ActualRooms
						+ ", but exepected is:" + expectedRoomFieldValue);
			}
			captureScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method is used to click on view deal button on hotel web page
	 */

	public void clickFirstViewDealButton() {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,300)");
			captureScreen();
			btn_viewDeal1stButton.click();
			myReport.pass("clicked on view deal button");
			String mainWindowHandle = driver.getWindowHandle();
			for (String childWindowHandle : driver.getWindowHandles()) {

				if (!childWindowHandle.equals(mainWindowHandle)) {
					driver.switchTo().window(childWindowHandle);
					myReport.info("after clicking on view deal button, new tab opened");
					captureScreen();
					driver.close();
				}
			}

			driver.switchTo().window(mainWindowHandle);
			myReport.info("switched back to parent webpage");
			captureScreen();
		} catch (Exception e) {
			e.printStackTrace();
			myReport.fail("view deal button not found");
		}
	}

}
