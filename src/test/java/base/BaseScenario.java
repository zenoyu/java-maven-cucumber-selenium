/**
 * @preserve Copyright 2014 Zeno Yu <zeno.yu@gmail.com>.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *     * Redistributions of source code must retain the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer.
 *
 *     * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDER "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
 * THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */

package base;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseScenario {

  /** handle to the webdriver */
  protected static WebDriver driver = null;

  // Wait for AJAX if needed
  protected int secondsToWait = 1000;

  /**
   *  Get the driver with devices
   */
  public WebDriver getDriver() {
    if (null != driver) {
      return driver;
    }

      // Environment variables
    String envName = (String)System.getProperty("envName","local");
    String browserName = (String)System.getProperty("browserName","googlechrome");
    String appName = (String)System.getProperty("appName","Zeno Yu");

    if (envName.equalsIgnoreCase("local")) {
      String driverDirectory = System.getProperty("user.dir")+"\\drivers\\";
      String driverLocation = System.getProperty("driverLocation",driverDirectory);
      String machineType = System.getProperty("machineType","");
      if (browserName.equalsIgnoreCase("googlechrome")) { 

        /* Chrome Local */
        if (machineType.equalsIgnoreCase("mac")) {
          System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
        }
        else {
          System.setProperty("webdriver.chrome.driver", driverLocation + "chromedriver.exe");
        }
        driver = new ChromeDriver();

      } else if (browserName.equalsIgnoreCase("ie")) {

        /* IE Local */
        System.setProperty("webdriver.ie.driver", driverLocation + "IEDriverServer.exe");
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        ieCapabilities.setCapability("ignoreZoomSetting", true);
        driver = new InternetExplorerDriver(ieCapabilities);

      } else if (browserName.equalsIgnoreCase("safari")) {

        /* Safari Driver */
        driver = new SafariDriver();
      } else if (browserName.equalsIgnoreCase("firefox")) {

        /* Firefox */
        driver = new FirefoxDriver();
      }
    } else {
      // default chrome
      DesiredCapabilities capabillities = DesiredCapabilities.chrome();
      /* Remote Testing Bot */
      /* All supporting browsers: https://api.testingbot.com/v1/browsers*/

      if (browserName.equalsIgnoreCase("ie8")) { 
        // iexplorer 8
        capabillities = DesiredCapabilities.internetExplorer();
        capabillities.setCapability("version", "8");
        capabillities.setCapability("platform", Platform.WINDOWS);  
        capabillities.setCapability("name", appName + " - Automated Testing (IE8)");
      } else if (browserName.equalsIgnoreCase("ie9")) { 
        // iexplorer 9
        capabillities = DesiredCapabilities.internetExplorer();
        capabillities.setCapability("version", "9");
        capabillities.setCapability("platform", Platform.WINDOWS);  
        capabillities.setCapability("name", appName + " - Automated Testing (IE9)");
      } else if (browserName.equalsIgnoreCase("ie10")) { 
        // iexplorer 10
        capabillities = DesiredCapabilities.internetExplorer();
        capabillities.setCapability("version", "10");
        capabillities.setCapability("platform", Platform.WINDOWS);  
        capabillities.setCapability("name", appName + " - Automated Testing (IE10)");
      } else if (browserName.equalsIgnoreCase("firefox")) { 
        // Windows FireFox
        capabillities = DesiredCapabilities.firefox();
        capabillities.setCapability("version", "31");  
        capabillities.setCapability("platform", Platform.WINDOWS);  
        capabillities.setCapability("name", appName + " - Automated Testing (Firefox)");
      } else if (browserName.equalsIgnoreCase("safari")) { 
        // safari
        capabillities = DesiredCapabilities.safari();
        capabillities.setCapability("version", "6");  
        capabillities.setCapability("platform", Platform.MAC);  
        capabillities.setCapability("name", appName + " - Automated Testing (Safari)");
      } else if (browserName.equalsIgnoreCase("iphone")) { 
        // iphone
        capabillities = DesiredCapabilities.iphone();
        capabillities.setCapability("platform", Platform.MAC);  
        capabillities.setCapability("name", appName + " - Automated Testing (iPhone)");
      } else if (browserName.equalsIgnoreCase("ipad")) { 
        // ipad
        capabillities = DesiredCapabilities.ipad();
        capabillities.setCapability("platform", Platform.MAC);  
        capabillities.setCapability("name", appName + " - Automated Testing (iPad)");
      } else {
        // Windows Google Chrome
        capabillities = DesiredCapabilities.chrome();
        capabillities.setCapability("platform", Platform.WINDOWS);  
        capabillities.setCapability("name", appName + " - Automated Testing (Chrome)");  
      }
      try {
        String remoteWebDriver = System.getProperty("remoteWebDriver");
        driver = new RemoteWebDriver(new URL(remoteWebDriver), capabillities);
        // http://www.seleniumhq.org/docs/04_webdriver_advanced.jsp#implicit-waits
        // Wait for DOM to avoid `stale element reference: element is not attached to the page document`
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      } catch (Exception e) {

      }
    }
    return driver;
  }

  /**
   * Get Site Url from pom.xml properties or command line -DsiteUrl
   */
  public String getSiteBaseUrl() {
    return (String)System.getProperty("siteUrl");
  }

  /**
   * Close Driver: delete all cookies for new session, tearDownClass() will handle closing the driver
   */
  public static void closeDriver() {
    if (null != driver) {
      driver.manage().deleteAllCookies();
    }
  }
  /**
   * Quit Driver: tearDownClass() will handle closing the driver
   */
  public static void quitDriver() {
    if (null != driver) {
      driver.quit();
    }
  }

}
