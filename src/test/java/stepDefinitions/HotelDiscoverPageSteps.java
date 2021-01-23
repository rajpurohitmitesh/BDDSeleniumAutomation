package stepDefinitions;

import Utility.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectRepo.HotelDiscoverPage;

public class HotelDiscoverPageSteps extends BaseClass {

	HotelDiscoverPage hotelDiscoverPage;

	@Given("user open the browser and launch the hotel discover application")
	public void user_open_the_browser_and_launch_the_hotel_discover_application() {
		BaseClass.launchBrowser();
		hotelDiscoverPage = new HotelDiscoverPage(driver, myReport);
	}

	@When("^user select the location as (.*)$")
	public void user_select_the_location_as_hyderabad(String location) {
		hotelDiscoverPage.enterLocation(location);
	}

	@When("^user select the radius as (.*) miles$")
	public void user_select_the_radius_as_miles(String Radius) {
		hotelDiscoverPage.clickRadius();
		hotelDiscoverPage.selectRadius(Radius);
	}

	@Then("^radius field should display as (.*) miles$")
	public void radius_field_should_display_as_miles(String Radius) {
		hotelDiscoverPage.verifyRadiusFieldValue(Radius);
	}

	@When("^user select the months for stay dates as (.*) and nights (.*)$")
	public void user_select_the_months_for_stay_dates_as_february_and_nights(String StayMonth, String StayNight)
			throws InterruptedException {
		hotelDiscoverPage.clickStayDates();
		hotelDiscoverPage.selectStayMonths(StayMonth);
		hotelDiscoverPage.incrementNight(StayNight);
	}

	@When("user click on Apply button for stayDate field")
	public void user_click_on_apply_button_for_stay_date_field() {
		hotelDiscoverPage.clickApplyForStayDate();
	}

	@Then("^stayDates field should display as (.*)$")
	public void stay_dates_field_should_display_as_feb_nights(String StayMonthAndNight) {
		hotelDiscoverPage.verifyStayDateFieldValue(StayMonthAndNight);
	}

	@When("^user select the adults (.*) children (.*) and rooms (.*)$")
	public void user_select_the_adults_children_and_rooms(String Adults, String Childs, String Rooms)
			throws InterruptedException {
		hotelDiscoverPage.clickGuest();
		hotelDiscoverPage.incrementAdult(Adults);
		hotelDiscoverPage.incrementChild(Childs);
		hotelDiscoverPage.incrementRoom(Rooms);
	}

	@When("user click on Apply button for guest field")
	public void user_click_on_apply_button_for_guest_field() {
		hotelDiscoverPage.clickApplyForGuest();
	}

	@Then("^guest field should display guest as (.*) and rooms as (.*)$")
	public void guest_field_should_display_guest_as_and_rooms_as(String int1, String int2) {
		hotelDiscoverPage.verifyGuestFieldValue(int1, int2);
	}

	@Then("user click on View Deal button")
	public void user_click_on_view_deal_button() throws InterruptedException {
		hotelDiscoverPage.clickFirstViewDealButton();
	}

	@After
	public void afterScenaro() {
		BaseClass.tearDown();
	}

}
