package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class POLoginAsAgent {
    public WebDriver driver;

    By inputUsername=By.id("username");
    By inputPassword=By.id("user_password");
    By buttonSignIn=By.xpath("//button[contains(@class,'success')]");
    By preloader=By.xpath("//div[@id='preloader']");

    public POLoginAsAgent(WebDriver driver) {
        this.driver=driver;
    }


    public WebElement getUsername(){ return driver.findElement(inputUsername); }
    public WebElement getPassword(){return driver.findElement(inputPassword);}
    public WebElement getButtonSignIn(){return driver.findElement(buttonSignIn);}
    public WebElement getPreloader(){return driver.findElement(preloader);}

}
