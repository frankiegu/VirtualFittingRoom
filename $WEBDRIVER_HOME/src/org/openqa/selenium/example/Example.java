package org.openqa.selenium.example;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import java.util.*;
import java.util.HashMap;
//import org.openqa.selenium.RemoteWebDriver;
public class Example {
	private static boolean success = true;
	private static int glassNum = 2;
	private static int hatNum = 2;
	private static int headPhonesNum = 2;
	private static final boolean localServer = true;
	private static final String[] category = {"glasses/","hats/", "headphones/"}; 
	private static final String[][] productsLists = {{"rayban%20glasses_1/","nike%20glasses_2/"},{"adidas%20cap_3/","levis%20hat_4/"},{"beats%20headphones_5/","sony%20headphones_6/"}};
	private static final String testComment = "this is test comment";
	private static String location = "";

	
	public static void main(String[] args) {
			
	    	System.setProperty("webdriver.chrome.driver", "chromedriver");
	    	//test all the categories
	    	System.out.println("\n/////////////////////\nstart testing chrome browser\n////////////////////");
	    	WebDriver driver = new ChromeDriver();
	    	testBrowser(driver);
	    	
	    	System.out.println("\n/////////////////////\nstart testing firefox browser\n///////////////////");
	    	WebDriver driver2 = new FirefoxDriver();
	    	testBrowser(driver2);
	    	System.exit(0);
	    }
	    
	    public static void sleep() {
	    	try{
	        	Thread.sleep(1000);
	        }catch (InterruptedException e){
	        	System.out.println("going into exception");
	        }
	    }
	    
	    
	    public static void assertTrue(String a, String b){
	    	if ( a.toLowerCase().hashCode() != b.toLowerCase().hashCode()){
	    		System.out.println("\nError Found, actual link is " + a + " what we expected is " +  b );
	    		success = false;
	    		System.out.println("success changed to false");
	    	}
	    }
	    
	    
	    public static void openWebsite(WebDriver driver){
	    	if (localServer){
	    	location = "http://localhost:8000/";
	    	driver.get(location);
	    	}else{
	    	location = "http://virtualfittingroom.heroku.com";
	    	driver.get(location);
	    	}	    	
	    }
	    public static void testBrowser(WebDriver driver1){
	    	try{
	    	basicBrowserTester(driver1); //// just test some basic adding and browsing
	    	
	    	
	    	addWishListTest(driver1); /// try add to wishlist without user login
	    	
	    	logInUser(driver1);
	    	
	    	addWishListTest(driver1);//// try again with user lgoin
	    	
	    	removeProductsFromWishList(driver1, 3);  /// remove products from wish list
	    	
	    	//addComment(driver1, 0,0);
	    	
	    	logOutUser(driver1);
   	
	    	driver1.close();   
	    	if (success){
	    		System.out.println("\ntest successfully");
	    	}
	    	}catch (Exception e){
	    		System.out.println("\nException appearred, element not found.\n ");
	    		driver1.close();
	    		System.exit(0);
	    	} 	
	    }
	    
	    public static void logInUser(WebDriver driver){
	    	
	    	System.out.println("\nStart log in user");
	    	openWebsite(driver);
	    	sleep();
	    	
	    	WebElement element0 = driver.findElement(By.className("global-nav")).findElement(By.id("login"));
	    	element0.click();
	    	sleep();
	    	sleep();
	    	//// log into google account;
	    	element0 = driver.findElement(By.className("wrapper")).findElement(By.id("gaia_loginform")).findElement(By.id("Email"));
	    	element0.sendKeys("virtualfittingroom169@gmail.com");
	    	sleep();
	    	
	    	element0 = driver.findElement(By.className("wrapper")).findElement(By.id("gaia_loginform")).findElement(By.id("Passwd"));
	    	element0.sendKeys("169virtual");
	    	sleep();
	    	
	    	element0 = driver.findElement(By.className("wrapper")).findElement(By.id("gaia_loginform")).findElement(By.id("signIn"));
	    	element0.click();
	    	sleep();
	    	sleep();
	    	
	    	element0 = driver.findElement(By.id("scope_and_buttons")).findElement(By.id("buttons_container")).findElement(By.id("connect-approve")).findElement(By.id("submit_approve_access"));
	    	element0.click();
	    	sleep();
	    	
	    	openWebsite(driver);
	    }
	    
	    public static void logOutUser(WebDriver driver){
	    	System.out.println("\nStart log out user");
	    	openWebsite(driver);
	    	sleep();
	    	WebElement element0 = driver.findElement(By.className("global-nav")).findElement(By.id("logout"));
	    	if (element0 != null){
	    		element0.click();
	    		sleep();
	    	}
	    	sleep();
	    }
	    public static void basicBrowserTester(WebDriver driver){
	    	System.out.println("\nStart basic browser test");
	    	openWebsite(driver);
	    	sleep();	    	
	    	//// test glass
	    	////
	    	for (int i = 0; i < category.length; i++){
	    	WebElement element = driver.findElement(By.className("contain-page")).findElement(By.className((String)("service-count"+(i+1)))).findElement(By.id("img"));
	
	    	element.click();
	        sleep();		        
	        assertTrue(driver.getCurrentUrl(), location + category[i] );

	        for (int j = 0; j < productsLists[i].length;j++){
				WebElement element2 = driver.findElement(By.id((String)("item-img" + j)));		
				element2.click();
				sleep();
				assertTrue(driver.getCurrentUrl(), location + category[i] + productsLists[i][j]);					
				driver.navigate().back();
				sleep();
	        }
	        driver.navigate().back();
	        openWebsite(driver);
	        sleep();
	    	}
			
	    	String[] topMenuBar = new String[]{"home"};
	    	for ( int i=0; i<topMenuBar.length; i++) {
		        WebElement element = driver.findElement(By.id(topMenuBar[i]));
		        element.click();
		        sleep();
	    	}
	    	
	    }
	    
	    
	    public static void addWishListTest(WebDriver driver){  
	    	System.out.println("\nStart adding wish list tests");
	    	openWebsite(driver);
	    	sleep();	    	

	    	
	    	for (int i = 0; i < category.length; i++){
	    	WebElement element = driver.findElement(By.className((String)("service-count"+(i+1)))).findElement(By.id("img"));
	        element.click();
	        sleep();		        
	        assertTrue(driver.getCurrentUrl(), location + category[i]);


			WebElement element2 = driver.findElement(By.id((String)("item-img0")));		
			element2.click();
			sleep();
			assertTrue(driver.getCurrentUrl(), location + category[i] + productsLists[i][0]);
			
			WebElement element3 = driver.findElement(By.id("mainview")).findElement(By.id("description")).findElement(By.id("add_to_wishlist"));
			element3.click();
			sleep();
			sleep();

	        Alert alert = driver.switchTo().alert();
	        alert.accept();
	        sleep();
	        
	        driver.navigate().back();
	        openWebsite(driver);
	        sleep();
	    	}		    	
	    	
	    	WebElement element4 = driver.findElement(By.className("global-nav")).findElement(By.id("wishlist"));
	    	element4.click();
	    	sleep();	
	    }
	    
	    public static void removeProductFromWishList(WebDriver driver){
	    	
	    	WebElement element = driver.findElement(By.id("basket")).findElement(By.className("remove"));
	    	element.click();
	    	sleep();
	    }
	    
	    public static void removeProductsFromWishList(WebDriver driver, int i){
	    	System.out.println("\nStart removing product from wishlist tests");
	    	openWebsite(driver);
	    	sleep();
	    	
	    	WebElement element4 = driver.findElement(By.className("global-nav")).findElement(By.id("wishlist"));
	    	element4.click();
	    	sleep();	    	
	    	
	    	for (int j = 0; j < i; j++){
	    	goToDetailPage(driver, j);
	    	removeProductFromWishList(driver);
	    	}
	    }
	    
	    public static void goToDetailPage(WebDriver driver, int j){
	    	WebElement element = driver.findElement(By.id("basket")).findElement(By.className("left"));
	    	element.click();
	    	sleep();
	    	assertTrue(driver.getCurrentUrl(), location + category[j] + productsLists[j][0]);
	    	driver.navigate().back();
	    	sleep();
	    	
	    }
	    
	    public static void addComment(WebDriver driver, int categoryID,  int productID){
	    	System.out.println("\nStart add comment test");
	    	openWebsite(driver);
	    	sleep();
	    	
	    	WebElement element = driver.findElement(By.className((String)("service-count"+(categoryID+1))));
	        element.click();
	        sleep();	
	        
	        
			element = driver.findElement(By.id((String)("item-img" + productID)));		
			element.click();
			sleep();
		
	    	element = driver.findElement(By.id("w")).findElement(By.className("cmmnt")).findElement(By.className("cmmnt-content")).findElement(By.xpath("//form[1]")).findElement(By.id("textarea"));
	    	element.sendKeys(testComment);
	    	sleep();
	    	
	    	element = driver.findElement(By.id("w")).findElement(By.className("cmmnt")).findElement(By.className("cmmnt-content")).findElement(By.cssSelector("button.content"));
	    	element.click();
	    	sleep();
	    	
	    	sleep();
	    	sleep();
	    }
	}










