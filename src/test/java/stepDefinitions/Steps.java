package stepDefinitions;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	
//	public WebDriver driver;
//	public LoginPage loginPage;
	@Before
	public void setUp() throws IOException {
		logger=Logger.getLogger("nopCommerce");//Adding logger
		PropertyConfigurator.configure("Log4j.properties");//Adding looger
		
		FileInputStream fis=new FileInputStream(new File("config.properties"));
		configProp=new Properties();
		configProp.load(fis);
		
		String brow=configProp.getProperty("browser");
		if(brow.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("ChromeDriverPath"));
			driver=new ChromeDriver();
		}
		else if(brow.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("FirefoxDriverPath"));
			driver=new FirefoxDriver();
		}
		else if(brow.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", configProp.getProperty("IEDriverPath"));
			driver=new InternetExplorerDriver();
		}
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        logger.info("****** Launching Browser ******");
	}
	
	@Given("^User Launch Chrome browser$")
    public void user_launch_chrome_browser() throws Throwable {
		
        loginPage=new LoginPage(driver);
    }

    @When("^User opens URL \"([^\"]*)\"$")
    public void user_opens_url_something(String url) throws Throwable {
        driver.get(url);
        logger.info("****** Opening URL ******");
    }

    @When("^user clicks on log out link$")
    public void user_clicks_on_log_out_link() throws Throwable {
    	loginPage.clickLogout();
    	logger.info("****** Successful Logout ******");
        
    }

    @Then("^Page Title should be \"([^\"]*)\"$")
    public void page_title_should_be_something(String title) throws Throwable {
        if(driver.getPageSource().contains("Login was unsuccessful."))
        {
        	driver.close();
        	logger.info("****** Unsuccessful Login ******");
        	Assert.assertTrue(false);
        }
        else
        {
        	Assert.assertEquals(title, driver.getTitle());
        	logger.info("****** Successful Login ******");
        }
    }

    @And("^user enters Email as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
    public void user_enters_email_as_something_and_password_as_something(String username, String password) throws Throwable {
    	loginPage.setUsername(username);
    	loginPage.setpassword(password);
    	logger.info("****** User entered credentials Login ******");
    }

    @And("^click on login$")
    public void click_on_login() throws Throwable {
    	loginPage.clickLogin();
    	logger.info("****** User clicked on Login ******");
    }

    @And("^close browser$")
    public void close_browser() throws Throwable {
        driver.close();
        logger.info("****** Browser Closed ******");
    }
    
    //add customer scenario
    

    @When("^User clicks on customers menu$")
    public void user_clicks_on_customers_menu() throws Throwable {
        addCustomer.clickOnCustomersMenu();
    }

    @When("^User enters customer info$")
    public void user_enters_customer_info() throws Throwable {
    	
    	logger.info("****** Adding new customer ******");
    	logger.info("****** Providing Customer Details ******");
    	addCustomer.setEmail(randomString()+"@gmail.com");
    	addCustomer.setPwd("test123");
    	addCustomer.setFirstName("Jyothi");
    	addCustomer.setLastName("B");
    	addCustomer.selectGender("female");
    	addCustomer.setDob("3/02/1994");
    	addCustomer.setCompanyName("HCLT");
    	addCustomer.setCustomerRole("Guests");
    	addCustomer.setVender("Vendor 2");
    	addCustomer.setAdminComments("This is for testing....");
        
    }

    @Then("^User can view Dashboard$")
    public void user_can_view_dashboard() throws Throwable {
        addCustomer=new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration",addCustomer.getPageTitle());
    }

    @Then("^User can view Add new customer page$")
    public void user_can_view_add_new_customer_page() throws Throwable {
        Assert.assertEquals("Add a new customer / nopCommerce administration",addCustomer.getPageTitle());
    }

    @Then("^User can view confirmation message \"([^\"]*)\"$")
    public void user_can_view_confirmation_message_something(String msg) throws Throwable {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains(msg));
    }

    @And("^click on customers menu item$")
    public void click_on_customers_menu_item() throws Throwable {
        addCustomer.clickOnCustomersMenuItem();
    }

    @And("^click on Add new customer button$")
    public void click_on_add_new_customer_button() throws Throwable {
        addCustomer.clickOnAddNew();
    }

    @And("^click on save button$")
    public void click_on_save_button() throws Throwable {
    	addCustomer.clickOnSaveButton();
    	logger.info("****** User saved customer details by clicking on save button ******");
    }

    //Search customer scenario by using email id
    
    @When("Enter customer emailId")
    public void enter_customer_emailId() {
    	logger.info("****** searching existing customer by email id ******");
    	searchCustomer=new SearchCustomerPage(driver);
    	searchCustomer.setEmail("victoria_victoria@nopCommerce.com");
    }

    @When("Click on search button")
    public void click_on_search_button() throws InterruptedException {
    	searchCustomer.clickOnSearchBtn();
    	Thread.sleep(3000);
    }

    @Then("User should found Email in the Search Table")
    public void user_should_found_Email_in_the_Search_Table() {
    	boolean flag=searchCustomer.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
    	Assert.assertEquals(true,flag);
    	logger.info("****** Validating after searching customer by email id is successful ******");
    	
    }
    
    //search customer scenario by using first name and last name
    
    @When("Enter customer first name as {string} last name as {string}")
    public void enter_customer_first_name_as_last_name_as(String firstName, String lastName) {
    	logger.info("****** searching existing customer by name ******");
    	searchCustomer=new SearchCustomerPage(driver);
    	searchCustomer.setFirstName(firstName);
    	searchCustomer.setLastName(lastName);
    	
    }

    @Then("User should found name as {string} {string} in the Search Table")
    public void user_should_found_name_as_in_the_Search_Table(String firstName, String lastName) {
    	boolean state=searchCustomer.searchCustomerByName(firstName+" "+lastName);
    	System.out.println(firstName+" "+lastName);
    	Assert.assertEquals(true,state);
    	logger.info("****** Validating after searching customer by name is successful ******");
    }

}
