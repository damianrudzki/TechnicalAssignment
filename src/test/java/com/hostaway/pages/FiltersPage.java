package com.hostaway.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class FiltersPage {
    private SelenideElement priceFrom = $x("(//input[@placeholder='From'])[1]");
    private SelenideElement priceTo = $x("(//input[@placeholder='To'])[1]");
    private SelenideElement bedsAdd = $x("(//button[@class='sc-fWWYYk sc-gzcbmu bZTTYU fKwyEY'])[1]");
    private SelenideElement bedsRemove = $x("(//button[@class='sc-fWWYYk sc-fIxmyt bZTTYU cnkbFD'])[1]");
    private SelenideElement bedroomsAdd = $x("(//button[@class='sc-fWWYYk sc-gzcbmu bZTTYU fKwyEY'])[2]");
    private SelenideElement bedroomsRemove = $x("(//button[@class='sc-fWWYYk sc-fIxmyt bZTTYU cnkbFD'])[2]");
    private SelenideElement bathroomsAdd = $x("(//button[@class='sc-fWWYYk sc-gzcbmu bZTTYU fKwyEY'])[3]");
    private SelenideElement bathroomsRemove = $x("(//button[@class='sc-fWWYYk sc-fIxmyt bZTTYU cnkbFD'])[3]");
    private SelenideElement bedsValue = $x("(//div[contains(text(), 'Beds')]/following-sibling::div//span)[1]");
    private SelenideElement bedroomsValue = $x("(//div[contains(text(), 'Bedrooms')]/following-sibling::div//span)[1]");
    private SelenideElement bathroomsValue = $x("(//div[contains(text(), 'Bathrooms')]/following-sibling::div//span)[1]");
    private SelenideElement applyButton = $x("//button[span[text()='Apply']]");
    private SelenideElement clearAllButton = $x("//b[contains(text(), 'Clear all')]");
    private SelenideElement priceFromField = $x("//input[@placeholder='From']");
    private SelenideElement priceToField = $x("//input[@placeholder='To']");

    public FiltersPage enterPriceFrom(String price) {
        priceFromField.clear();
        priceFromField.setValue(price);
        return this;
    }

    public FiltersPage enterPriceTo(String price) {
        priceToField.doubleClick();
        actions().sendKeys(BACK_SPACE).perform();
        priceToField.setValue(price);
        return this;
    }

    public String getPriceFromValue() {
        return priceFromField.getValue();
    }

    public String getPriceToValue() {
        return priceToField.getValue();
    }

    public boolean arePriceFieldsDisabled() {
        return priceFrom.is(disabled) && priceTo.is(disabled);
    }

    public FiltersPage clickAddBeds() {
        bedsAdd.click();
        return this;
    }

    public FiltersPage clickRemoveBeds() {
        bedsRemove.click();
        return this;
    }

    public FiltersPage clickAddBedrooms() {
        bedroomsAdd.click();
        return this;
    }

    public FiltersPage clickRemoveBedrooms() {
        bedroomsRemove.click();
        return this;
    }

    public FiltersPage clickAddBathrooms() {
        bathroomsAdd.click();
        return this;
    }

    public FiltersPage clickRemoveBathrooms() {
        bathroomsRemove.click();
        return this;
    }

    public int getBedsValue() {
        return Integer.parseInt(bedsValue.text());
    }

    public int getBedroomsValue() {
        return Integer.parseInt(bedroomsValue.text());
    }

    public int getBathroomsValue() {
        return Integer.parseInt(bathroomsValue.text());
    }

    public FiltersPage clickClearAll() {
        clearAllButton.click();
        return this;
    }

    public FiltersPage clickApplyButton() {
        applyButton.click();
        return this;
    }

    public FiltersPage selectAmenity(String amenityLabel) {
        $x("//span[@class='sc-htmcrh emRwHY' and normalize-space()='" + amenityLabel + "']").click();
        return this;
    }

    public FiltersPage deselectAmenity(String amenityLabel) {
        SelenideElement checkbox = $x("//input[@type='checkbox'][following-sibling::span[@class='sc-htmcrh emRwHY' and normalize-space()='" + amenityLabel + "']]");
        if (checkbox.isSelected()) {
            $x("//span[@class='sc-htmcrh emRwHY' and normalize-space()='" + amenityLabel + "']").click();
        }
        return this;
    }


    public int getSelectedAmenitiesCount() {
        int count = 0;
        List<SelenideElement> checkboxes = $$x("//input[@type='checkbox']");
        for (SelenideElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                count++;
            }
        }
        return count;
    }


}
