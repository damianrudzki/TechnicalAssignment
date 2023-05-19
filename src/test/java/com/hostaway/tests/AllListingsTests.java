package com.hostaway.tests;

import com.hostaway.base.BaseTest;
import com.hostaway.pages.AllListingsPage;
import com.hostaway.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AllListingsTests extends BaseTest {
    @Test
    @Story("Validate number of listings")
    @Description("This test verifies that the 'All listings' page has the same amount of listings as the 'All' label")
    public void testAllListingsCount() {
        AllListingsPage allListingsPage = HomePage
                .openHomePage()
                .clickAllListingsButton();

        int expectedListingsCount = allListingsPage.getAllLabelCount();

        allListingsPage.scrollToEndOfPage();

        int actualListingsCount = allListingsPage.getListingsCount();

        assertEquals(actualListingsCount, expectedListingsCount, "The 'All listings' page count doesn't match the 'All' label count");
    }


}
