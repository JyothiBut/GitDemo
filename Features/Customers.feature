Feature: Customers

Background: Below are common steps for all scenarios
	Given User Launch Chrome browser
	When User opens URL "http://admin-demo.nopcommerce.com/login"
	And user enters Email as "admin@yourstore.com" and Password as "admin"
	And click on login
	Then User can view Dashboard

@Sanity
Scenario: Successful Login With Valid Credentials
	When User clicks on customers menu
	And click on customers menu item
	And click on Add new customer button
	Then User can view Add new customer page
	When User enters customer info
	And click on save button
	Then User can view confirmation message "The new customer has been added successfully."
	And close browser

@Regression	
Scenario: Search customer by emailId
	When User clicks on customers menu
	And click on customers menu item
	And Enter customer emailId
	When Click on search button
	Then User should found Email in the Search Table
	And close browser

@Regression		
Scenario Outline: Search customer by first name and last name
	When User clicks on customers menu
	And click on customers menu item
	And Enter customer first name as "<FirstName>" last name as "<LastName>"
	When Click on search button
	Then User should found name as "<FirstName>" "<LastName>" in the Search Table
	And close browser
	
	Examples:
	|FirstName|LastName|
	|Victoria|Terces|
	
	
	
	
	
	