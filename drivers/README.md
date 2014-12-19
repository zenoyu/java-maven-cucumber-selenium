About Drivers folder
============================
This folder contain all the webdrivers for Chrome & IE (FF driver is maintained by Selenium in the dependency version, see below)

= WebDriver =
As browser update their version, we might need to update the webdriver for each:

Local machine testing drivers are under /drivers

- Chrome Driver (Mac / Linux / Windows):
http://chromedriver.storage.googleapis.com/index.html



- IE Internet Explorer (Windows):
https://code.google.com/p/selenium/downloads/list



- FireFox Driver (Windows / Mac):
As FireFox driver is default setting in selenium (other than htmlunit), it is required to update the selenium version to latest version from: 
http://selenium.googlecode.com/git/rb/CHANGES

pom.xml dependency changes in
<!-- Selenium -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>2.44.0</version>
</dependency>



= Other useful Selenium Tools download =
http://docs.seleniumhq.org/download/