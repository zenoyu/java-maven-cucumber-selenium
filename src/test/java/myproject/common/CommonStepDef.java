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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import cucumber.api.java.en.Given;

public class CommonStepDef extends BaseScenario {

  /**
   * Common Step to Open the site with device
   * @param pageType For your project to define page type url / init page object
   * @param screenSize hd-desktop | tablet | mobile
   * @throws Throwable
   */
  @Given("^The web site page type \"(.*?)\" is opened as \"(.*?)\"$")
  public void the_web_site_page_type_is_opened_as(String pageType, String screenSize) throws Throwable {

    WebDriver driver = getDriver();
    String uri = "";

    if (screenSize.equalsIgnoreCase("hd-desktop"))
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

    if (StringUtils.isNotBlank(pageType))
    {
      if (pageType.equalsIgnoreCase("search"))
      {
        uri = "";
      }
      driver.get(getSiteBaseUrl() + "/" + uri);
    }

    // Wait for Google search field to appear
    WebDriverWait wait = new WebDriverWait(driver, 200);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
  }

}