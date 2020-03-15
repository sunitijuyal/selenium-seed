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

public class LoginAsOwner extends base {
    public static Logger log = LogManager.getLogger(base.class.getName());


    @BeforeTest
    public void driverInitialization() throws IOException {
        driver = initializeDriver();
        log.info("Driver initialized successfully");
    }
    @Test
        public void openPortalHomePage() throws IOException, FileNotFoundException, InterruptedException {
        driver.get(prop.getProperty("homepageURL"));
        Assert.assertTrue(driver.getTitle().contains("Hotspot Portal"));
        POLoginAsOwner po=new POLoginAsOwner(driver);
        po.getUsername().clear();
        po.getUsername().sendKeys(prop.getProperty("ownerUser"));
        po.getPassword().sendKeys(prop.getProperty("ownerPassword"));
            while (po.getPreloader().isDisplayed()) {
                    Thread.sleep(0);
            }

            po.getButtonSignIn().click();
        while (po.getPreloader().isDisplayed()) {
            Thread.sleep(0);
        }
        Thread.sleep(2000);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("portal.bhaifi.com/home.php"));
        log.info("Window url asserted successfully");
    }

    @AfterTest
    public void quitBrowser() {
        driver.quit();
    }
}
