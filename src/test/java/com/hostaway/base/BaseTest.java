package com.hostaway.base;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        Configuration.baseUrl = "https://kamil-demo.alpinizm.uz/";
        Configuration.browser = Browsers.CHROME;
        Configuration.browserSize = "1920x1080";
        open();
    }

    @AfterMethod
    public void tearDown() {
    }
}

