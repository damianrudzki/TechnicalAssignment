package com.hostaway.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class AllListingsPage {

    private SelenideElement allLabel = $x("//span[@class='sc-eGJWMs lkeyLH']/span");
    private ElementsCollection allListings = $$x("//div[@class='sc-gSYDnn wmfak']");

    public int getAllLabelCount() {
        allLabel.should(exist);
        String allLabelText = allLabel.text();
        return Integer.parseInt(allLabelText.substring(allLabelText.indexOf('(') + 1, allLabelText.indexOf(')')));
    }

    public int getListingsCount() {
        return allListings.size();
    }

    public void scrollToEndOfPage() {
        int oldCount = 0;
        int newCount = allListings.size();

        while(oldCount != newCount) {
            oldCount = newCount;
            executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");
            sleep(6000);
            newCount = allListings.size();
        }
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
