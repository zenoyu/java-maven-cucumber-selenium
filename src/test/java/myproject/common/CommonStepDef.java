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

package myproject.common;

import org.apache.commons.lang3.StringUtils;
import base.BaseScenario;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertTrue;

public class CommonStepDef extends BaseScenario {

  /**
   * Go to a page
   */
  @When("^I go to \"(.*?)\" page$")
  public void i_go_to_page(String pageType) throws Throwable {

    String uri = pageType;
    if (StringUtils.isNotBlank(pageType))
    {
      driver.get(getSiteBaseUrl() + "/" + uri);
    }
  }

  /**
   * Validate page words
   */
  @Then("^The page class \"(.*?)\" should contains \"(.*?)\"$")
  public void the_page_should_contains(String className, String keyword) throws Throwable {
    //System.out.println(driver.findElement(By.className(className)).getText());
    assertTrue(driver.findElement(By.className(className)).getText().contains(keyword));
  }

  /**
   * Validate page title words
   */
  @Then("^The page title should contains \"(.*?)\"$")
  public void the_page_title_should_contains(String keyword) throws Throwable {
    //System.out.println("Page title is: " + driver.getTitle());
    assertTrue(driver.getTitle().contains(keyword));
  }

  /**
   * Click on a link
   */
  @When("^I click link by class name \"(.*?)\"$")
  public void i_click_link_by_class_name(String theClassName) throws Throwable {

      JavascriptExecutor jse = (JavascriptExecutor)driver;
      jse.executeScript("arguments[0].click()", driver.findElement(By.className(theClassName)));
  }

  /**
   * Wait till a div appear
   */
  @Then("^I wait for class name \"(.*?)\" visible$")
  public void i_wait_for_class_name_visible(String theClassName) throws Throwable {

      WebDriverWait wait = new WebDriverWait(driver, secondsToWait);

      WebElement element = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.className(theClassName)));
  }

  /**
   * Click on a link By ID
   */
  @Then("^I click link by id \"(.*?)\"$")
  public void i_click_link_by_id(String theIdName) throws Throwable {

      JavascriptExecutor jse = (JavascriptExecutor)driver;
      jse.executeScript("arguments[0].click()", driver.findElement(By.id(theIdName)));

  }

  /**
   * Click by Xpath
   */
  @Then("^I click link by xpath \"(.*?)\"$")
  public void i_click_link_by_xpath(String theXPath) throws Throwable {

      JavascriptExecutor jse = (JavascriptExecutor)driver;
      jse.executeScript("arguments[0].click()", driver.findElement(By.xpath(theXPath)));
  }

  /**
   * Fill in By ID
   */
  @Then("^I fill in \"(.*?)\" by id \"(.*?)\"$")
  public void i_fill_in_by_id(String id, String value) throws Throwable {

    // Finn in the email /username
    WebElement element = driver.findElement(By.id(id));
    element.sendKeys(value);
  }

  /**
   * Fill in By input Name
   */
  @Then("^I fill in \"(.*?)\" by name \"(.*?)\"$")
  public void i_fill_in_by_name(String name, String value) throws Throwable {
    WebElement element = driver.findElement(By.name(name));
    element.sendKeys(value);
  }

  /**
   * Form submit By Form ID
   */
  @Then("^I submit form by form id \"(.*?)\"$")
  public void i_submit_form_by_form_id(String formId) throws Throwable {

    // Finn in the email /username
    WebElement element = driver.findElement(By.id(formId));

    element.submit();
  }
    /**
   * Form submit By Form Name
   */
  @Then("^I submit form by form name \"(.*?)\"$")
  public void i_submit_form_by_form_name(String formName) throws Throwable {

    // Finn in the email /username
    WebElement element = driver.findElement(By.name(formName));

    element.submit();
  }

  @Then("^I wait for \"(.*?)\" seconds$")
  public void i_wait_for_seconds(Long sec) throws Throwable {
      Thread.sleep(sec * 1000);
  }

  /**
   * Common Step to Open the site with device
   * @param pageType For your project to define page type url / init page object
   * @param screenSize hd-desktop | tablet | mobile
   * @throws Throwable
   */
  @Given("^The web site is opened as \"(.*?)\"$")
  public void the_web_site_is_opened_as(String screenSize) throws Throwable {

    WebDriver driver = getDriver();

    if (screenSize.equalsIgnoreCase("desktop"))
    {
      // Resize the browser to desktop
      driver.manage().window().setPosition(new Point(0, 0));
      driver.manage().window().setSize(new Dimension(1268, 1024));
    } else if (screenSize.equalsIgnoreCase("hd-desktop"))
    {
      // Resize the browser to desktop
      driver.manage().window().setPosition(new Point(0, 0));
      driver.manage().window().setSize(new Dimension(1968, 1024));
    } else if (screenSize.equalsIgnoreCase("mobile")) {
      // Resize the browser to mobile
      driver.manage().window().setPosition(new Point(0, 0));
      driver.manage().window().setSize(new Dimension(320, 568));
    } else if (screenSize.equalsIgnoreCase("tablet")) {
      // Resize the browser to mobile
      driver.manage().window().setPosition(new Point(0, 0));
      driver.manage().window().setSize(new Dimension(768, 1024));
    }
    driver.get(getSiteBaseUrl() + "/" );
  }



}