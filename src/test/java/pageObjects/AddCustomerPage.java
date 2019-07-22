package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	
	WebDriver driver;
	
	public AddCustomerPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By lnkCustomersMenu=By.xpath("//a[@href='#']/span[contains(text(),'Customers')]");
	By lnkCustomersMenuItem=By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");
	By lnkAddNewCustomer=By.xpath("//a[@class='btn bg-blue']");
	By txtEmail=By.id("Email");
	By txtPassword=By.id("Password");
	By txtFirstName=By.xpath("//input[@id='FirstName']");
	By txtLastName=By.xpath("//input[@id='LastName']");
	By rdBtnMale=By.id("Gender_Male");
	By rdBtnFemale=By.id("Gender_Female");
	By txtDob=By.xpath("//input[@id='DateOfBirth']");
	By txtCompanyName=By.id("Company");
	By ckbxTaxExempt=By.xpath("//input[@id='IsTaxExempt']");
	By dropboxCusRoles=By.xpath("//select[@id='SelectedCustomerRoleIds']");
	By dropboxVendorid=By.xpath("//select[@id='VendorId'][@class='form-control']");
	By txtAdminConmment=By.xpath("//textarea[@id='AdminComment']");
	By btnSave=By.xpath("//button[@class='btn bg-blue']");
	
	//Action Methods
	
	public void clickOnCustomersMenu()
	{
		driver.findElement(lnkCustomersMenu).click();
	}
	public void clickOnCustomersMenuItem()
	{
		driver.findElement(lnkCustomersMenuItem).click();
	}
	public void clickOnAddNew()
	{
		driver.findElement(lnkAddNewCustomer).click();
	}
	public void setEmail(String userEmail)
	{
		driver.findElement(txtEmail).sendKeys(userEmail);
	}
	public void setPwd(String userPwd)
	{
		driver.findElement(txtPassword).sendKeys(userPwd);
	}
	public void setFirstName(String firstName)
	{
		driver.findElement(txtFirstName).sendKeys(firstName);
	}
	public void setLastName(String lastName)
	{
		driver.findElement(txtLastName).sendKeys(lastName);
	}
	public void selectGender(String gender)
	{
		if(gender.equalsIgnoreCase("Male"))
			driver.findElement(rdBtnMale).click();
		else
			driver.findElement(rdBtnFemale).click();
	}
	public void setDob(String dob)
	{
		driver.findElement(txtDob).sendKeys(dob);
	}
	public void setCompanyName(String companyName)
	{
		driver.findElement(txtCompanyName).sendKeys(companyName);
	}
	public void setTaxExempted()
	{
		driver.findElement(ckbxTaxExempt).click();
	}
	public void setAdminComments(String comments)
	{
		driver.findElement(txtAdminConmment).sendKeys(comments);
	}
	public void setCustomerRole(String role)
	{
		Select osel=new Select(driver.findElement(dropboxCusRoles));
		List<WebElement> list=osel.getOptions();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getText().equalsIgnoreCase(role))
			{
				list.get(i).click();
				break;
			}
			
		}
		
	}
	public void setVender(String vender)
	{
		Select osel=new Select(driver.findElement(dropboxVendorid));
		List<WebElement> list=osel.getOptions();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getText().equalsIgnoreCase(vender))
			{
				list.get(i).click();
				break;
			}
			
		}
		
	}
	public void clickOnSaveButton()
	{
		driver.findElement(btnSave).click();
	}
	public String getPageTitle()
	{
		return driver.getTitle();
	}

}
