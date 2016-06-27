import javax.swing.plaf.synth.SynthSeparatorUI;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
public class JavaScript {
	public static void main(String[] args) throws Exception{
		
		WebDriver driver = new FirefoxDriver();
		System.out.println("Opening the firefox ......");
	    driver.get("http://10.0.1.86/tatoc/basic/grid/gate");
	    driver.manage().window().maximize();
	    JavascriptExecutor script = (JavascriptExecutor) driver;
	     
	    // Task one 
	     
	    System.out.println("Task one : clicking the green button..");
	    script.executeScript("document.getElementsByClassName('greenbox')[0].click();");
	    System.out.println("Task one completed....");
	     
	    // Task two
	     
	    System.out.println("Task second : repainting the box till both the boxes contain same color...."); 
	    String colorone , colorsecond;
	    colorone  =  (String) script.executeScript("return document.querySelector('#main').contentWindow.document.querySelector('#answer').className;");
	    colorsecond = (String) script.executeScript("return document.querySelector('#main').contentWindow.document.querySelector('#child').contentWindow.document.getElementById('answer').getAttribute('class');");
	    while(!colorone.equals(colorsecond)) {
			script.executeScript("document.getElementById('main').contentWindow.document.getElementsByTagName('a')[0].click();");
			Thread.sleep(500);
			colorsecond = (String)script.executeScript("return document.querySelector('#main').contentWindow.document.querySelector('#child').contentWindow.document.getElementById('answer').getAttribute('class');");
		}		
	    script.executeScript("document.getElementById('main').contentWindow.document.getElementsByTagName('a')[1].click();");
	     System.out.println("Task second completed....");	
	  
	    // Task three
         
	    System.out.println("Task third : put the box into the empty space...");
 	    WebElement element = (WebElement)script.executeScript("return document.getElementById('dragbox')");
		WebElement target = (WebElement)script.executeScript("return document.getElementById('dropbox')");
		    new Actions(driver).dragAndDrop(element, target).perform();
	        Thread.sleep(500);
	        script.executeScript("$('a:contains(\"Proceed\")').trigger('click')");  
		System.out.println("Task third completed.....");    
		
	    // Task four
		System.out.println("Task fourth : Launching the pop_up window and submitting the text....");
	    String mainwindow = driver.getWindowHandle();
		script.executeScript("$('a:contains(\"Launch Popup Window\")').trigger('click')");
		for(String winHandle :driver.getWindowHandles()) {
		      driver.switchTo().window(winHandle);
		      if(driver.getTitle().equals("Popup - Basic Course - T.A.T.O.C")) 
		           break;

		}
		script.executeScript("document.getElementById(\"name\").value=\"Vipul\";");
		Thread.sleep(500);
		script.executeScript("document.getElementById('submit').click();");
	    driver.switchTo().window(mainwindow);
		script.executeScript("$('a:contains(\"Proceed\")').trigger('click')");    
		System.out.println("Task five completed....");
		
		// Task five
		
		System.out.println("Task last : generating the token_value and add cookies into it......");
	    script.executeScript("$('a:contains(\"Generate Token\")').trigger('click')");
		String token = (String) script.executeScript("return document.getElementById('token').textContent;");
 	    token  = "Token=" + token.substring(7);
	    Cookie name = new Cookie("vipul" , token );
		driver.manage().addCookie(name);
		script.executeScript("$('a:contains(\"Proceed\")').trigger('click')");    
		
		System.out.println("All task completed....");
		System.out.println("Closing the browser....");
		
		driver.close();
	    
	}
}

