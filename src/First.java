import java.util.List;
//basic
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//explicit wait
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//mouse and click
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
//download method
import java.io.IOException;
//alert handling
//import org.openqa.selenium.NoAlertPresentException;	
import org.openqa.selenium.Alert;
//popup handling
import java.util.Iterator;		
import java.util.Set;	
//handling of dynamic tables
import java.text.ParseException;
//get max number from table
import java.text.NumberFormat;
//check of broken links
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class First {
		
	public static WebDriver driver = new FirefoxDriver();

	public static void main(String[] args) {
		//System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		autoType();
		try {
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wait")));
		} catch(Exception e) {
			System.out.println("Wait finished.");
		}

		driver.close();


	}
	
	public static void useWebElements() {
		driver.manage().window().maximize();

		driver.get("http://demo.guru99.com/test/ajax.html");

		// Find the radio button for “No” using its ID and click on it
		driver.findElement(By.id("no")).click();
			
		// Click on Check Button
		driver.findElement(By.id("buttoncheck")).click();
		
		
	    List<WebElement> elements = driver.findElements(By.name("name"));
	    System.out.println("Number of elements:" +elements.size());

	    for (int i=0; i<elements.size();i++){
	      System.out.println("Radio button text:" + elements.get(i).getAttribute("value"));
	    }
	}
	
	public static void clickAndKeyboard() {
        driver.get("http://demo.guru99.com/test/newtours/");           
        WebElement link_Home = driver.findElement(By.linkText("Home"));
        WebElement td_Home = driver
                .findElement(By
                .xpath("//html/body/div"
                + "/table/tbody/tr/td"
                + "/table/tbody/tr/td"
                + "/table/tbody/tr/td"
                + "/table/tbody/tr"));    
         
        Actions builder = new Actions(driver);
        Action mouseOverHome = builder
                .moveToElement(link_Home)
                .build();
         
        String bgColor = td_Home.getCssValue("background-color");
        System.out.println("Before hover: " + bgColor);        
        mouseOverHome.perform();        
        bgColor = td_Home.getCssValue("background-color");
        System.out.println("After hover: " + bgColor);
        
        // Series of actions
        driver.get("http://www.facebook.com/");
        WebElement txtUsername = driver.findElement(By.id("email"));
        
        builder = new Actions(driver);
        Action seriesOfActions = builder
        	.moveToElement(txtUsername)
        	.click()
        	.keyDown(txtUsername, Keys.SHIFT)
        	.sendKeys(txtUsername, "hello")
        	.keyUp(txtUsername, Keys.SHIFT)
        	.doubleClick(txtUsername)
        	.contextClick()
        	.build();
        	
        seriesOfActions.perform() ;
	}
	
	public static void uploadDownload() {
		// upload
        driver.get("http://demo.guru99.com/test/upload/");
        WebElement uploadElement = driver.findElement(By.id("uploadfile_0"));

        // enter the file path onto the file-selection input field
        uploadElement.sendKeys("C:\\Reflect_Install.log");

        // check the "I accept the terms of service" check box
        driver.findElement(By.id("terms")).click();

        // click the "UploadFile" button
        driver.findElement(By.name("send")).click();
        
        // download
        driver.get("http://demo.guru99.com/test/yahoo.html");
        WebElement downloadButton = driver.findElement(By
        .id("messenger-download"));
        String sourceLocation = downloadButton.getAttribute("href");
        String wget_command = "cmd /c C:\\Wget\\wget.exe -P D: --no-check-certificate " + sourceLocation;

        try {
        	Process exec = Runtime.getRuntime().exec(wget_command);
        	int exitVal = exec.waitFor();
        	System.out.println("Exit value: " + exitVal);
        } catch (InterruptedException | IOException ex) {
        	System.out.println(ex.toString());
        }
	}
	
	public static void alertHandling() {
        driver.get("http://demo.guru99.com/test/delete_customer.php");	
        driver.findElement(By.name("cusid")).sendKeys("53920");					
        driver.findElement(By.name("submit")).submit();			
        		
        // Switching to Alert        
        Alert alert = driver.switchTo().alert();		
        		
        // Capturing alert message.    
        String alertMessage= driver.switchTo().alert().getText();		
        		
        // Displaying alert message		
        System.out.println(alertMessage);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
        // Accepting alert		
        alert.accept();
        
        //popup
        driver.get("http://demo.guru99.com/popup.php");			
        driver.manage().window().maximize();		
            		
        driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();			
    		
        String MainWindow=driver.getWindowHandle();		
    		
        // To handle all new opened window.				
        Set<String> s1=driver.getWindowHandles();		
        Iterator<String> i1=s1.iterator();		
    		
        while(i1.hasNext())			
        {		
        	String ChildWindow=i1.next();		
        		
        	if(!MainWindow.equalsIgnoreCase(ChildWindow))			
        	{    		
             
                // Switching to Child window
                driver.switchTo().window(ChildWindow);	                                                                                                           
                driver.findElement(By.name("emailid"))
                .sendKeys("gaurav.3n@gmail.com");                			
                
                driver.findElement(By.name("btnLogin")).click();			
                             
                // Closing the Child Window.
                driver.close();		
        	}		
        }		
        // Switching to Parent window i.e Main Window.
        driver.switchTo().window(MainWindow);	
	}
	
	public static void webTable() {
		driver.get("http://demo.guru99.com/test/write-xpath-table.html");
		String innerText = driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]")).getText(); 	
	    System.out.println(innerText);
	    
	    //nested table
		driver.get("http://demo.guru99.com/test/accessing-nested-table.html");
		String innerText2 = driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]/table/tbody/tr/td[2]")).getText(); 		
	    System.out.println(innerText2);
	    
	    //attribute as predicate
		driver.get("http://demo.guru99.com/test/newtours/");
		String innerText3 = driver.findElement(By
			.xpath("//table[@width=\"270\"]/tbody/tr[4]/td"))
			.getText(); 		
		System.out.println(innerText3); 
		
		//skip shortcut of getting xpath using element inspect of browsers
	    
		//dynamic table
        driver.get("http://demo.guru99.com/test/web-table-element.php");         
        //No.of Columns
        List  col = driver.findElements(By.xpath(".//*[@id=\"leftcontainer\"]/table/thead/tr/th"));
        System.out.println("No of cols are : " +col.size()); 
        //No.of rows 
        List  rows = driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr/td[1]")); 
        System.out.println("No of rows are : " + rows.size());
        
        //dynamic table 2
		 //driver.get("http://demo.guru99.com/test/web-table-element.php");
		 try {
			WebDriverWait wait = new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
		 } catch(Exception e) {
			System.out.println("Table not found.");
		 }
		 WebElement baseTable = driver.findElement(By.tagName("table"));
		  
		 //To find third row of table
		 WebElement tableRow = baseTable.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[3]"));
		 String rowtext = tableRow.getText();
		 System.out.println("Third row of table : "+rowtext);
		    
		 //to get 3rd row's 2nd column data
		 WebElement cellIneed = tableRow.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[3]/td[2]"));
		 String valueIneed = cellIneed.getText();
		 System.out.println("Cell value is : " + valueIneed); 
		 
		 //max number
		 //driver.get("http://demo.guru99.com/test/web-table-element.php");
		 String max;
	     double m=0,r=0;
		 
	     //No. of Columns
	     List  col2 = driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/thead/tr/th"));
	     System.out.println("\nTotal No of columns are : " +col2.size());
	     //No.of rows
	     List  rows2 = driver.findElements(By.xpath (".//*[@id='leftcontainer']/table/tbody/tr/td[1]"));
	     System.out.println("Total No of rows are : " + rows2.size());
	     for (int i =1;i<rows2.size();i++)
	     {    
	    	 max= driver.findElement(By.xpath("//*[@id='leftcontainer']/table/tbody/tr[" + i + "]/td[4]")).getText();
	         NumberFormat f =NumberFormat.getNumberInstance(); 
	         Number num = 0;
			try {
				num = f.parse(max);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	         max = num.toString();
	         m = Double.parseDouble(max);
	         if(m>r)
	         {    
	        	 r=m;
	         }
	     }
	     System.out.println("Maximum current price is : "+ r);
	     
	     
	     //get all values of a dynamic table
	     driver.get("http://demo.guru99.com/test/table.html");
	     //To locate table.
	     WebElement mytable = driver.findElement(By.xpath("/html/body/table/tbody"));
	     //To locate rows of table. 
	     List < WebElement > rows_table = mytable.findElements(By.tagName("tr"));
	     //To calculate no of rows In table.
	     int rows_count = rows_table.size();
	     //Loop will execute till the last row of table.
	     for (int row = 0; row < rows_count; row++) {
	    	 //To locate columns(cells) of that specific row.
	    	 List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
	    	 //To calculate no of columns (cells). In that specific row.
	    	 int columns_count = Columns_row.size();
	    	 System.out.println("\nNumber of cells In Row " + row + " are " + columns_count);
	    	 //Loop will execute till the last cell of that specific row.
	    	 for (int column = 0; column < columns_count; column++) {
	    		 // To retrieve text from that specific cell.
	    		 String celtext = Columns_row.get(column).getText();
	    	 	 System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
	    	  }
	    	  System.out.println("-------------------------------------------------- ");
	     }
	}
	
	public static void verifyTooltip() {
        driver.get("http://demo.guru99.com/test/social-icon.html");					
        String expectedTooltip = "Github";	
        
        // Find the Github icon at the top right of the header		
        WebElement github = driver.findElement(By.xpath(".//*[@class='soc-ico show-round']/a[4]"));	
        
        //get the value of the "title" attribute of the github icon		
        String actualTooltip = github.getAttribute("title");	
        
        //Assert the tooltip's value is as expected 		
        System.out.println("Actual Title of Tool Tip "+actualTooltip);							
        if(actualTooltip.equals(expectedTooltip)) {							
            System.out.println("Test Case Passed");					
        }		
        
        //handling script powered tooltip
        String expectedTooltip2 = "What's new in 3.2";					
        driver.get("http://demo.guru99.com/test/tooltip.html");					
        		
        WebElement download = driver.findElement(By.xpath(".//*[@id='download_now']"));							
        Actions builder = new Actions (driver);							

        builder.clickAndHold().moveToElement(download);					
        builder.moveToElement(download).build().perform(); 	
        
        WebElement toolTipElement = driver.findElement(By.xpath(".//*[@class='box']/div/a"));							
        String actualTooltip2 = toolTipElement.getText();			
        
        System.out.println("Actual Title of Tool Tip  "+actualTooltip2);							
        if(actualTooltip2.equals(expectedTooltip2)) {							
            System.out.println("Test Case 2 Passed");					
        }	

	}
	
	public static void checkBrokenLinks() {
        String urlString = "";
        HttpURLConnection httpsURLConnection = null;

        
        driver.get("http://demo.guru99.com/test/tooltip.html");
        
        List<WebElement> links = driver.findElements(By.tagName("a"));
        
        Iterator<WebElement> it = links.iterator();
        
        while(it.hasNext()){
            
        	urlString = it.next().getAttribute("href");
        
            if(urlString == null || urlString.isEmpty()){
            	System.out.println("URL is either not configured for anchor tag or it is empty");
            }
            else {
            	//System.out.println(urlString);
                if(urlString.startsWith("http://demo.guru99.com")){
                    try {
                    	URL url = new URL(urlString);
                    	httpsURLConnection = (HttpURLConnection)url.openConnection();
                    	httpsURLConnection.setRequestMethod("HEAD");
                        if(httpsURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                        	System.out.println(url+" is a valid link");
                        }
                        else{
                        	System.out.println(url+" is a broken link");
                        }
                            
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    
                }
            }
            


        }
	}
	
	public static void quitModal() {
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.cssSelector("._29YdH8")).click();
		
	}
	
	public static void autoType() {
		driver.get("https://10fastfingers.com/typing-test/english");
		String currentString = "";
		try {
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"words\"]")));
			// word box is only visible during the 60 second period, so loop this code block until the word box is invisible
			while (driver.findElement(By.xpath("//*[@id=\"words\"]")).isDisplayed()) {
				// capture text value of the highlighted word
				currentString = driver.findElement(By.cssSelector(".highlight")).getText();
				// put currentString to input field, append a space to confirm input and proceed on highlighting the next word
				driver.findElement(By.xpath("//*[@id=\"inputfield\"]")).sendKeys(currentString + " ");
				// delay the next loop, without this, the loop will easily exhaust the word list before 60 seconds passes
				Thread.sleep(150);
			}

		} catch(Exception e) {
			System.out.println("Element not found.");
		}

		
	}
	
	

}
