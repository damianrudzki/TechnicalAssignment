package com.hostaway.tests;

import com.hostaway.base.BaseTest;
import com.hostaway.pages.FiltersPage;
import com.hostaway.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

@Epic("Filters Functionality")
@Feature("Filters Form")
public class FiltersFormTests extends BaseTest {

    @Test
    @Story("Validate filters form elements")
    @Description("This test verifies the functionality of the filters form")
    public void testFiltersForm() {
        FiltersPage filtersPage = HomePage
                .openHomePage()
                .clickSearchButton()
                .clickFilterButton();

        assertTrue(filtersPage.arePriceFieldsDisabled(), "Price fields are not disabled");

        filtersPage.clickClearAll();

        filtersPage.clickApplyButton();

        assertEquals(filtersPage.getSelectedAmenitiesCount(), 0, "Amenities checkboxes are not functional");
    }

    @Test
    @Story("Validate beds buttons")
    @Description("This test verifies the functionality of the beds buttons")
    public void testBedsButtons() {
        FiltersPage filtersPage = HomePage
                .openHomePage()
                .clickSearchButton()
                .clickFilterButton();

        int initialBeds = filtersPage.getBedsValue();

        filtersPage.clickAddBeds();
        assertEquals(filtersPage.getBedsValue(), initialBeds + 1, "Add beds button is not working");

        filtersPage.clickRemoveBeds();
        assertEquals(filtersPage.getBedsValue(), initialBeds, "Remove beds button is not working");
    }

    @Test
    @Story("Validate bedrooms buttons")
    @Description("This test verifies the functionality of the bedrooms buttons")
    public void testBedroomsButtons() {
        FiltersPage filtersPage = HomePage
                .openHomePage()
                .clickSearchButton()
                .clickFilterButton();

        int initialBedrooms = filtersPage.getBedroomsValue();

        filtersPage.clickAddBedrooms();
        assertEquals(filtersPage.getBedroomsValue(), initialBedrooms + 1, "Add bedrooms button is not working");

        filtersPage.clickRemoveBedrooms();
        assertEquals(filtersPage.getBedroomsValue(), initialBedrooms, "Remove bedrooms button is not working");
    }

    @Test
    @Story("Validate bathrooms buttons")
    @Description("This test verifies the functionality of the bathrooms buttons")
    public void testBathroomsButtons() {
        FiltersPage filtersPage = HomePage
                .openHomePage()
                .clickSearchButton()
                .clickFilterButton();

        int initialBathrooms = filtersPage.getBathroomsValue();

        filtersPage.clickAddBathrooms();
        assertEquals(filtersPage.getBathroomsValue(), initialBathrooms + 1, "Add bathrooms button is not working");

        filtersPage.clickRemoveBathrooms();
        assertEquals(filtersPage.getBathroomsValue(), initialBathrooms, "Remove bathrooms button is not working");
    }

    @Test
    @Story("Validate amenities checkboxes")
    @Description("This test verifies the functionality of the amenities checkboxes")
    public void testAmenitiesCheckboxes() {
        FiltersPage filtersPage = HomePage
                .openHomePage()
                .clickSearchButton()
                .clickFilterButton();

        List<String> amenityLabels = Arrays.asList("Beach front", "Swimming pool", "Free WiFi", "Kitchen", "Air conditioning", "Washing Machine", "Pets allowed", "Hot tub", "Street parking", "Suitable for children");

        for (String amenity : amenityLabels) {
            filtersPage.selectAmenity(amenity);
        }

        assertEquals(filtersPage.getSelectedAmenitiesCount(), amenityLabels.size(), "Amenities checkboxes are not working correctly");


        for (String amenity : amenityLabels) {
            filtersPage.deselectAmenity(amenity);
        }


        assertEquals(filtersPage.getSelectedAmenitiesCount(), 0, "Amenities checkboxes are not working correctly");
    }


    @Test
    @Story("Validate Clear all functionality")
    @Description("This test verifies the functionality of the Clear all button")
    public void testClearAll() {
        FiltersPage filtersPage = HomePage
                .openHomePage()
                .clickSearchButton()
                .clickFilterButton();

        filtersPage.selectAmenity("Free WiFi");
        filtersPage.selectAmenity("Air conditioning");
        filtersPage.clickAddBeds();
        filtersPage.clickAddBedrooms();
        filtersPage.clickAddBathrooms();

        filtersPage.clickClearAll();

        assertEquals(filtersPage.getBedsValue(), 0, "Clear all button is not working");
        assertEquals(filtersPage.getBedroomsValue(), 0, "Clear all button is not working");
        assertEquals(filtersPage.getBathroomsValue(), 0, "Clear all button is not working");
        assertEquals(filtersPage.getSelectedAmenitiesCount(), 0, "Clear all button is not working");
    }

    @Test
    @Story("Validate price range")
    @Description("This test verifies the functionality of the price range filter")
    public void testPriceRange() {
        FiltersPage filtersPage = HomePage
                .openHomePage()
                .clickSearchButton()
                .pickCheckInDate("1 June 2023")
                .pickCheckOutDate("10 June 2023")
                .clickFilterButton();

        // Test that 0 cannot be entered
        filtersPage.enterPriceFrom("0");
        assertNotEquals(filtersPage.getPriceFromValue(), "0", "Zero value is allowed in 'From' price field");

        filtersPage.enterPriceTo("0");
        assertNotEquals(filtersPage.getPriceToValue(), "0", "Zero value is allowed in 'To' price field");

        // Test that 1 can be entered
        filtersPage.enterPriceFrom("1");
        assertEquals(filtersPage.getPriceFromValue(), "1", "'From' price field does not accept 1");

        filtersPage.enterPriceTo("1");
        assertEquals(filtersPage.getPriceToValue(), "1", "'To' price field does not accept 1");

        // Test that large number can be entered and it is not transformed into scientific notation
        filtersPage.enterPriceFrom("100000000000000000000");
        assertEquals(filtersPage.getPriceFromValue(), "100000000000000000000", "'From' price field does not transform large number into scientific notation");

        filtersPage.enterPriceTo("100000000000000000000");
        assertEquals(filtersPage.getPriceToValue(), "100000000000000000000", "'To' price field does not transform large number into scientific notation");


        // Test that too large number can be entered and is transformed into scientific notation
        filtersPage.enterPriceFrom("1000000000000000000000");
        assertEquals(filtersPage.getPriceFromValue(), "1e+21", "'From' price field does not transform large number into scientific notation");

        filtersPage.enterPriceTo("1000000000000000000000");
        assertEquals(filtersPage.getPriceToValue(), "1e+21", "'To' price field does not transform large number into scientific notation");

    }
}
