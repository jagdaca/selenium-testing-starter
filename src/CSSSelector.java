import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.chrome.ChromeDriver;	

public class CSSSelector {

	public static void main(String[] args) {
        String baseUrl = "https://www.facebook.com/login/identify?ctx=recover";					
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");					
        WebDriver driver = new ChromeDriver();					
        		
        driver.get(baseUrl);					
        //click on the "Facebook" logo on the upper left portion		
			driver.findElement(By.cssSelector("a[title=\"Go to Facebook Home\"]")).click();					

			//verify that we are now back on Facebook's homepage		
			if (driver.getTitle().equals("Facebook - Log In or Sign Up")) {							
            System.out.println("We are back at Facebook's homepage");					
        } else {			
            System.out.println("We are NOT in Facebook's homepage");					
        }	

	}

}
