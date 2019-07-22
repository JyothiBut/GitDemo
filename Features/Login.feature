Feature: Login

@Sanity @E2E
Scenario: Successful Login With Valid Credentials
	Given User Launch Chrome browser
	When User opens URL "http://admin-demo.nopcommerce.com/login"
	And user enters Email as "admin@yourstore.com" and Password as "admin"
	And click on login
	Then Page Title should be "Dashboard / nopCommerce administration"
	When user clicks on log out link
	Then Page Title should be "Your store. Login"
	And close browser
	
@Regression	
Scenario Outline: Login Data driven
	Given User Launch Chrome browser
	When User opens URL "http://admin-demo.nopcommerce.com/login"
	And user enters Email as "<Email>" and Password as "<Password>"
	And click on login
	Then Page Title should be "Dashboard / nopCommerce administration"
	When user clicks on log out link
	Then Page Title should be "Your store. Login"
	And close browser
	
Examples:
| Email | Password |
| admin@yourstore.com | admin |	
| admin@yourstore.com | admin123 |
