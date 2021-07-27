package com.project.cba.fdp.pub;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HomeLoanRepaymentCal {

	static String baseUrl = "https://www.commbank.com.au";
	static WebDriver driver;
	
	public static void navigateToCal(String baseUrl) {
		driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Home loans")).click();
        driver.findElement(By.linkText("Dismiss")).click();
        driver.findElement(By.linkText("Calculators & tools")).click();
        driver.findElement(By.linkText("Calculate now")).click();
        driver.findElement(By.linkText("Repayment calculator")).click();
        }
	
	public static void amountCal(String amount,String term) {
		driver.findElement(By.id("amount")).clear();
		driver.findElement(By.id("amount")).sendKeys(amount);
		driver.findElement(By.id("term")).clear();
		driver.findElement(By.id("term")).sendKeys(term);
		driver.findElement(By.id("submit")).submit();
        String totalRepayment = driver.findElement(By.xpath("//*[@id=\"repayments-app\"]/div[3]/div[1]/div/div[3]/div[3]/div[1]/div[1]/p/span[3]")).getText();
        System.out.println(totalRepayment);
        String totalIntrest = driver.findElement(By.xpath("//*[@id=\"repayments-app\"]/div[3]/div[1]/div/div[3]/div[3]/div[1]/div[2]/p/span[3]")).getText();
        System.out.println(totalIntrest);
        HomeLoanCal homeLoanCal = new HomeLoanCal();
        float repayment = homeLoanCal.repaymentCal(Float.valueOf(amount), (float)2.69, Float.valueOf(term));
        String repaymentCal =String.valueOf(repayment);
        String totalIntrestCharged = String.valueOf(repayment - Float.valueOf(amount));
		
		assertEquals(totalRepayment.replace(",", ""),"$"+repaymentCal.substring(0, repaymentCal.indexOf(".")));
		assertEquals(totalIntrest.replace(",", ""),"$"+totalIntrestCharged.substring(0, repaymentCal.indexOf(".")));
        }
	
	public static void main( String[] args ) throws IOException, InterruptedException, InstantiationException, IllegalAccessException
    {
		

		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		navigateToCal(baseUrl);
		amountCal("50000","25");		
        driver.close();
    }
}
