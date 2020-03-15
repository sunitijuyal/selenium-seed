package Portal;

import PageObjects.POLoginAsOwner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoginAsAgent extends base {
    public static Logger log = LogManager.getLogger(base.class.getName());

    @BeforeTest
    public void driverInitialization() throws IOException {
        driver = initializeDriver();
        log.info("Driver initialized successfully");
    }
    @Test
    public void openPortalHomePage() throws IOException, FileNotFoundException, InterruptedException {
       /* Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("/Users/sunitijuyal/Work/PortalAutomation/src/main/java/Resources/data.properties");
        prop.load(fis);*/
        //driver = initializeDriver();
        driver.get(prop.getProperty("homepageURL"));
        Assert.assertTrue(driver.getTitle().contains("Hotspot Portal"));

        POLoginAsOwner po = new POLoginAsOwner(driver);
        boolean Selected = po.getUserType().isSelected();
        AssertJUnit.assertEquals(true, Selected);
        while (po.getPreloader().isDisplayed()) {
            Thread.sleep(0);
        }
        po.getClient().click();

        po.getUsername().clear();
        po.getUsername().sendKeys(prop.getProperty("agentUser"));
        po.getPassword().sendKeys(prop.getProperty("agentPassword"));
        while (po.getPreloader().isDisplayed()) {
            Thread.sleep(0);
        }

        po.getButtonSignIn().click();
        while (po.getPreloader().isDisplayed()) {
            Thread.sleep(0);
        }
        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

       Assert.assertTrue(driver.getCurrentUrl().contains("portal.bhaifi.com/home.php"));
        log.info("Window url asserted successfully");

    }
        @AfterTest
        public void quitBrowser() {
            driver.quit();
        }
    }


