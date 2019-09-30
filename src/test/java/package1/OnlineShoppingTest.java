package package1;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class OnlineShoppingTest {
	  WebDriver driver=Drivers.getDriver("chrome");
	  
	  
  @Test(priority=3)
  public void testCart() throws InterruptedException {
	  Actions actions=new Actions(driver);
	  actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/a/span"))).perform();
	  actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/ul/li[1]/a/span"))).click().perform();
	  Thread.sleep(5000);
	  actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"submenuul11290\"]/li[1]/a/span"))).perform();
	  actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"submenuul11290\"]/li[1]/a/span"))).click().build().perform();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();
	  driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();
	  driver.findElement(By.xpath("//*[@id=\"cart\"]/tfoot/tr[2]/td[5]/a")).click();
	  driver.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input")).click();
	  Thread.sleep(5000);
  }
  
  @Test(priority=2)
  public void testLogin() {
	  driver.findElement(By.xpath("//a[@href='login.htm']")).click();
	  driver.findElement(By.id("userName")).sendKeys("Lalitha");
	  driver.findElement(By.id("password")).sendKeys("Password123");
	  driver.findElement(By.name("Login")).click();
  }
  
  @Test(priority=4)
  public void testPayment() throws InterruptedException, IOException {
	  driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div/div/div[2]/div[2]/div")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//*[@id=\"btn\"]")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input[1]")).sendKeys("123457");
	  driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input[2]")).sendKeys("Pass@457");
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/div/div[3]/input")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.name("transpwd")).sendKeys("Trans@457");
	  driver.findElement(By.xpath("//input[@value='PayNow']")).click();
	  Thread.sleep(5000);
//	  TakesScreenshot ts=(TakesScreenshot) driver;
//	  File src=ts.getScreenshotAs(OutputType.FILE);
//	  FileUtils.copyFile(src, new File("C:\\Users\\training_b6b.01.16\\Desktop\\Cap.PNG"));
	  
  }
  
  @Test(priority=1,enabled=false)
  public void testRegistration() throws InterruptedException {
	  driver.findElement(By.xpath("//a[@href='RegisterUser.htm']")).click();
	  driver.findElement(By.id("userName")).sendKeys("aaaaaaaa");
	  driver.findElement(By.id("firstName")).sendKeys("namefirst");
	  driver.findElement(By.id("lastName")).sendKeys("namelast");
	  driver.findElement(By.id("password")).sendKeys("Password1234");
	  driver.findElement(By.id("pass_confirmation")).sendKeys("Password1234");
	  driver.findElement(By.xpath("//input[@value='Male']")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.id("emailAddress")).sendKeys("aaaaaaaa@gmail.com");
	  driver.findElement(By.id("mobileNumber")).sendKeys("9988776666");
	  driver.findElement(By.name("dob")).sendKeys("11/11/1999");
	  driver.findElement(By.id("address")).sendKeys("Bangalore, Bangalore");
	  Select pc=new Select(driver.findElement(By.id("securityQuestion")));
	  pc.selectByIndex(0);
	  driver.findElement(By.id("answer")).sendKeys("Bangalore");
	  Thread.sleep(5000);
	  driver.findElement(By.name("Submit")).click();
  }
  
  @AfterMethod
  public void getResultAfterMethod(ITestResult result) throws IOException
  {
	  ExtentHtmlReporter reporter1=new ExtentHtmlReporter("C:\\Users\\Training_b6b.01.16\\Desktop\\casestudy.html");
	  ExtentReports extent=new ExtentReports();
	  extent.attachReporter(reporter1);
	  ExtentTest logger=extent.createTest("OnlineShopTesting");
	  logger.log(Status.INFO, "Report");
	 
	  if(result.getStatus()==ITestResult.SUCCESS)
	  { 
		 logger.log(Status.PASS,MarkupHelper.createLabel(result.getName()+"Test passed",ExtentColor.GREEN));
		 TakesScreenshot capture=(TakesScreenshot)driver;
		 File source=capture.getScreenshotAs(OutputType.FILE);
		 String imgpath=System.getProperty("user.dir")+"/extent-reports/snapshots/"+result.getName()+".png"; 
		System.out.println(result.getName());
		 FileUtils.copyFile(source,new File(imgpath));
		 logger.addScreenCaptureFromPath(imgpath,result.getName());
		 }
		 else if(result.getStatus()==ITestResult.FAILURE)
		 {
		 logger.log(Status.FAIL,MarkupHelper.createLabel(result.getName()+"Test fail",ExtentColor.RED));
		 TakesScreenshot capture=(TakesScreenshot)driver;
		 File source=capture.getScreenshotAs(OutputType.FILE);
		 String imgpath=System.getProperty("user.dir")+"/extent-reports/snapshots/"+result.getName()+".png"; 
		System.out.println(result.getName());
		 FileUtils.copyFile(source,new File(imgpath));
		 logger.addScreenCaptureFromPath(imgpath,result.getName());
		 }
	  extent.flush();
  }

  @BeforeTest
  public void startReportBeforeTest() {
	  String url="http://10.232.237.143:443/TestMeApp/fetchcat.htm";
	  driver.get(url);
  }

  @AfterTest
  public void endReportAfterTest() {
	  driver.close();
	  
  }

}
