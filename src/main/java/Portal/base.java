package Portal;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileInputStream;
import org.apache.commons.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class base {
    public static WebDriver driver;
    public Properties prop;


        public WebDriver initializeDriver() throws IOException {
            prop=new Properties();
            FileInputStream fis=new FileInputStream("/Users/sunitijuyal/Work/PortalAutomation/src/main/java/Resources/data.properties");
            prop.load(fis);
            String browserName=prop.getProperty("browser");

            if (browserName.contains("chrome")) {
                System.setProperty("webdriver.chrome.driver","/Users/sunitijuyal/bin/chromedriver");

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-extensions");
                options.addArguments("--dns-prefetch-disable");
                options.addArguments("--disable-gpu");
                options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
                options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
                options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
                options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
                options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
                options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                driver=new ChromeDriver(options);
            }

            else
            if(browserName.contains("firefox")) {
                System.setProperty("webdriver.gecko.driver","/Users/sunitijuyal/bin/geckodriver");
                driver=new FirefoxDriver();
            }

            if(browserName.contains("internet")) {
                System.setProperty("webdriver.firefox.driver","/Users/sunitijuyal/bin/firefoxdriver");
                driver=new InternetExplorerDriver();

            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return driver;

        }

//    public void quitBroswer(WebDriver driver){
//            driver.quit();
//
//    }

    public void getScreenshot(String result) throws IOException {
        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("/Users/sunitijuyal/Work/PortalAutomation/screenshots/"+result+"screenshot.png"));

    }
    }



