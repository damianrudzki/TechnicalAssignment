package com.hostaway.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage {

    private SelenideElement allLabel = $("CSS_SELECTOR_FOR_ALL_LABEL");
    private List<SelenideElement> listings = $$("CSS_SELECTOR_FOR_LISTINGS");
    private SelenideElement filterButton = $("button.sc-giAqHp.iyYKWx");
    private SelenideElement checkInField = $x("//div[@class='sc-dFRpbK bFgzLZ']//div[@class='sc-fuISkM cpwVzr']");
    private ElementsCollection calendarDays = $$("div.sc-bQCEYZ.eUlwHi div.sc-fHCHyC.crlswz div.CalendarDay");
    private SelenideElement nextButton = $x("(//div[@class='sc-ljsmAU jFDlvU'])[1]");
    private SelenideElement currentMonthYearElement = $x("//div[@class='sc-euEtCV hTuscY']//div[@class='sc-dtLLSn dpehyt']");

    public SearchPage pickCheckInDate(String date) {
        checkInField.click();

        String[] dateParts = date.split(" ");
        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];

        String expectedMonthYear = month + " " + year;

        while (true) {
            String currentMonthYear = currentMonthYearElement.text();
            if (currentMonthYear.equals(expectedMonthYear)) {
                break;
            }
            nextButton.click();
        }

        SelenideElement calendarDay = calendarDays.stream()
                .filter(dayElement -> dayElement.text().equals(day))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Day not found in calendar: " + day));

        calendarDay.click();

        return this;
    }




    public SearchPage pickCheckOutDate(String date) {

        String[] dateParts = date.split(" ");
        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];

        String expectedMonthYear = month + " " + year;

        while (true) {
            String currentMonthYear = currentMonthYearElement.text();
            if (currentMonthYear.equals(expectedMonthYear)) {
                break;
            }
            nextButton.click();
        }

        SelenideElement calendarDay = calendarDays.stream()
                .filter(dayElement -> dayElement.text().equals(day))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Day not found in calendar: " + day));

        calendarDay.click();

        return this;
    }

    public FiltersPage clickFilterButton() {
        filterButton.click();
        return new FiltersPage();
    }

}
