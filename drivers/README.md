About Drivers folder
============================
This folder contain all the webdrivers for Chrome & IE (FF driver is maintained by Selenium in the dependency version, see below)

= WebDriver =
As browser update their version, we might need to update the webdriver for each:

Local machine testing drivers are under /drivers

- Chrome Driver (Mac / Linux / Windows):
http://chromedriver.storage.googleapis.com/index.html



- IE Internet Explorer (Windows):
http://selenium-release.storage.googleapis.com/index.html


- Safari need to install extensions into your Safari browser to allow connection
Download: SafariDriver.safariextz from latest version from:
http://selenium-release.storage.googleapis.com/index.html

Double click the safari extension to install into your Safari browser


- FireFox Driver (Windows / Mac):
As FireFox driver is default setting in selenium (other than htmlunit), it is required to update the selenium version to latest version from: 
http://selenium.googlecode.com/git/rb/CHANGES

pom.xml dependency changes in
<!-- Selenium -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>2.45.0</version>
</dependency>



= Other useful Selenium Tools download =
http://docs.seleniumhq.org/download/