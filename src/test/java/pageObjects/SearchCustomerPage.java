package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {
	
	public WebDriver ldriver;
	WaitHelper waitHelper;
	
	public SearchCustomerPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		waitHelper=new WaitHelper(ldriver);
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(how=How.ID,using="SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(how=How.ID,using="SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(how=How.ID, using="SearchLastName")
	@CacheLookup
	WebElement txtLastName;
	
	@FindBy(how=How.ID,using="SearchMonthOfBirth")
	@CacheLookup
	WebElement dropdownMonth;
	
	@FindBy(how=How.ID,using="SearchDayOfBirth")
	@CacheLookup
	WebElement dropdownDay;
	
	@FindBy(how=How.ID,using="SearchCompany")
	@CacheLookup
	WebElement txtCompany;
	
	@FindBy(how=How.ID,using="SelectedCustomerRoleIds")
	@CacheLookup
	List<WebElement> dropdownCustomerRoles;
	
	@FindBy(how=How.ID,using="search-customers")
	@CacheLookup
	WebElement btnSearch;
	
	@FindBy(how=How.XPATH,using="//table[@role='grid]")
	@CacheLookup
	WebElement tblSearchResults;
	
	@FindBy(how=How.XPATH,using="//table[@id='customers-grid']")
	WebElement table;
	
	@FindBy(how=How.XPATH,using="//table[@id='customers-grid']/tbody/tr")
	List<WebElement> tableRows;
	
	@FindBy(how=How.XPATH,using="//table[@id='customers-grid']/tbody/tr/td")
	List<WebElement>  tableColumns;
	
	
	public void setEmail(String email)
	{
		waitHelper.waitForElement(txtEmail, 20);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setFirstName(String firstName)
	{
		waitHelper.waitForElement(txtFirstName, 20);
		txtFirstName.clear();
		txtFirstName.sendKeys(firstName);
	}
	
	public void setLastName(String lastName)
	{
		waitHelper.waitForElement(txtLastName, 20);
		txtLastName.clear();
		txtLastName.sendKeys(lastName);
	}
	
	public void clickOnSearchBtn()
	{
		btnSearch.click();;
	}
	
	public int getNumOfRows() {
		return tableRows.size();
	}
	
	public int getNumOfColumns() {
		return tableColumns.size();
	}
	public boolean searchCustomerByEmail(String email)
	{
		boolean flag=false;
		
		for(int i=1;i<=getNumOfRows();i++)
		{
			String emailId=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();
			System.out.println(emailId);
			if(emailId.equals(email))
			{
				flag=true;
			}
		}
		System.out.println(flag);
		return flag;
	}
	public boolean searchCustomerByName(String name)
	{
		boolean flag=false;
		
		for(int i=1;i<=getNumOfRows();i++)
		{
			String cusName=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText();
			System.out.println(cusName);
			if(name.equals(cusName))
			{
				flag=true;
				//break;
			}
		}
		System.out.println(flag);
		return flag;
	}

}
