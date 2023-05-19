package com.hostaway.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ex.ElementNotFound;
import java.time.Duration;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    private SelenideElement searchButton = $("button.sc-giAqHp.brmoYr[type='button'][color='primary']");
    private SelenideElement allListingsButton = $x("//li[contains(@class, 'sc-bFSbwS')]/a[@href='/all-listings' and text()='All listings']");
    private static SelenideElement elementToAppear = $x("(//h1[normalize-space()='Test'])[1]");

    public static HomePage openHomePage() {
        open("https://kamil-demo.alpinizm.uz/");

        try {
            elementToAppear.shouldBe(Condition.appear, Duration.ofSeconds(10));
        } catch (ElementNotFound e) {
            refresh();
        }

        return new HomePage();
    }

    public SearchPage clickSearchButton() {
        searchButton.click();
        return new SearchPage();
    }

    public AllListingsPage clickAllListingsButton() {
        allListingsButton.should(exist);
        allListingsButton.click();
        return new AllListingsPage();
    }
}
