Feature:  Hotel Discover Application

@e2eTest
Scenario Outline: E2E Flow for Hotel Discover application

Given user open the browser and launch the hotel discover application	
When user select the location as <location>	
And user select the radius as <radius>
Then radius field should display as <radius>
When user select the months for stay dates as <stayDateMonth> and nights <nights>
And user click on Apply button for stayDate field
Then stayDates field should display as <StayDatesField> 
When user select the adults <adults> children <children> and rooms <rooms>
And user click on Apply button for guest field
Then guest field should display guest as <totalguest> and rooms as <rooms>
And user click on View Deal button

Examples:
|location |radius   |stayDateMonth|nights|StayDatesField|adults|children|rooms|totalguest|
|Hyderabad|150 miles|February     |3     |Feb,3 nights  |3     |1       |2    |4         |