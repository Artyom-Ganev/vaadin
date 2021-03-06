package com.vaadin.tests.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.AbstractComponentElement;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elementsbase.ServerClass;
import com.vaadin.tests.tb3.SingleBrowserTest;

public class DummyDataTest extends SingleBrowserTest {

    @ServerClass("com.vaadin.tests.data.DummyData.DummyComponent")
    public static class DummyElement extends AbstractComponentElement {
    }

    @Before
    public void setUp() {
        setDebug(true);
        openTestURL();
    }

    @Test
    public void testCorrectRowSelectedOnInit() {
        List<WebElement> selected = findElements(By.className("selected"));
        assertTrue("Only one should be selected", 1 == selected.size());
        assertEquals("Wrong item selected", "Foo 200",
                selected.get(0).getText());
    }

    @Test
    public void testServerSelectionUpdatesSelected() {
        $(ButtonElement.class).first().click();
        List<WebElement> selected = findElements(By.className("selected"));
        assertTrue("Only one should be selected", 1 == selected.size());
        assertEquals("Wrong item selected", "Foo 20",
                selected.get(0).getText());
    }

    @Test
    public void testDataUpdateDoesNotCauseBackEndRequest() {
        assertEquals("Unexpected backend requests", "2. Backend request #1",
                getLogRow(0));
        assertEquals("Unexpected backend requests", "1. Backend request #0",
                getLogRow(1));
        // Select a row on the server-side, triggers an update
        $(ButtonElement.class).first().click();
        assertEquals("No requests should have happened",
                "2. Backend request #1", getLogRow(0));
    }

    @Test
    public void testDataSourceChangeOnlyOneRequest() {
        // Change to a new logging data source
        $(ButtonElement.class).get(1).click();
        assertEquals("DataSource change should only cause 1 request",
                "3. Backend request #0", getLogRow(0));
    }

    @Test
    public void testEmptyAndRestoreContent() {
        assertEquals("Unexpected amount of content on init.", 300,
                $(DummyElement.class).first()
                        .findElements(By.className("v-label")).size());
        // Change to an empty data source
        $(ButtonElement.class).get(2).click();
        assertEquals("Empty data source did not work as expected.", 0,
                $(DummyElement.class).first()
                        .findElements(By.className("v-label")).size());
        // Change back to logging data source
        $(ButtonElement.class).get(1).click();
        assertEquals("Data was not correctly restored.", 300,
                $(DummyElement.class).first()
                        .findElements(By.className("v-label")).size());
    }

}
