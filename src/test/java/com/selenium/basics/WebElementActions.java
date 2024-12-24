package com.selenium.basics;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Demonstrates various WebElement interactions using Selenium WebDriver.
 * Covers elements such as buttons, textboxes, dropdowns, checkboxes, images, etc.
 */
public class WebElementActions {

    public static void main(String[] args) throws IOException {

        // Step 1: Launch the browser
        WebDriver driver = new ChromeDriver();

        // Step 2: Maximize the browser window
        driver.manage().window().maximize();

        // Step 3: Open the application URL
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        // Step 4: Locate the 'Services' element (placeholder XPath for demo purposes)
        WebElement element = driver.findElement(By.xpath("//a[@id='services']"));

        /********** Common WebElement Actions **********/

        // Check if the element is displayed on the page
        boolean isElementDisplayed = element.isDisplayed();

        // Check if the element is enabled
        boolean isElementEnabled = element.isEnabled();

        /********** Hidden Element Actions **********/

        // Scroll to a hidden element using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        // Click on a hidden element using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

        // Enter text into a hidden element using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='Text';", element);

        /************* Regular Element Actions *************/

        /********** Button Element **********/

        // Get the label of the button
        String labelTextAsInnerText = element.getText(); // If label is stored as text value
        String labelTextAsAttribute = element.getAttribute("value"); // If label is stored as an attribute

        // Click the button
        element.click();

        /********** Textbox Element **********/

        // Clear the textbox
        element.clear();

        // Enter text into the textbox
        element.sendKeys("Value");

        /********** Dropdown Element **********/

        // Select options from a dropdown
        Select dropdown = new Select(element);

        dropdown.selectByIndex(0); // Select by index
        dropdown.selectByValue("ws"); // Select by value
        dropdown.selectByVisibleText("Web Service"); // Select by visible text

        // Verify the selected option
        String selectedOption = dropdown.getFirstSelectedOption().getText();

        // Get all options available in the dropdown
        List<WebElement> options = dropdown.getOptions();
        int totalOptions = options.size();

        // Print all options
        for (WebElement option : options) {
            System.out.println(option.getText());
        }

        // Handle multi-select dropdowns
        boolean isMultiSelect = dropdown.isMultiple();

        // Select and deselect options
        if (isMultiSelect) {
            dropdown.selectByIndex(0);
            dropdown.selectByValue("ws");
            dropdown.selectByVisibleText("Web Service");

            dropdown.deselectByIndex(0);
            dropdown.deselectByValue("ws");
            dropdown.deselectByVisibleText("Web Service");
        }

        /********** Checkbox Element **********/

        // Click the checkbox only if it is not already selected
        boolean isCheckboxSelected = element.isSelected();
        if (!isCheckboxSelected) {
            element.click();
        }

        /********** Radio Button Element **********/

        // Click the radio button
        element.click();

        /********** Image Element **********/

        // Verify if the image element is displayed
        boolean isImageDisplayed = element.isDisplayed();

        // Get the source of the image
        String imageSrc = element.getAttribute("src");

        // Verify the image dimensions
        int imageWidth = element.getSize().getWidth();
        int imageHeight = element.getSize().getHeight();

        // Get the position of the image
        Point imagePosition = element.getLocation();
        int imageX = imagePosition.getX();
        int imageY = imagePosition.getY();

        // Load the image and verify a specific pixel color
        BufferedImage image = ImageIO.read(new URL(imageSrc));
        int pixelColor = image.getRGB(10, 23);

        /********** Text Element **********/

        // Get text from the element
        String elementText = element.getText(); // If text is stored as inner text
        String elementAttributeText = element.getAttribute("value"); // If text is stored as an attribute

        /********** Hyperlink Element **********/

        // Verify the URL in the hyperlink
        String hyperlink = element.getAttribute("href");

        // Navigate using the hyperlink and verify the URL
        element.click();
        String currentUrl = driver.getCurrentUrl();

        /********** Web Table **********/
        // (Add logic to handle rows, columns, headers, and table data as needed)

        /********** Calendar **********/
        // (Add logic to select a date from the calendar)

        /********** Mouse and Keyboard Actions **********/
        Actions actions = new Actions(driver);

        // Double-click on the element
        actions.doubleClick(element).perform();

        // Right-click (context click) on the element
        actions.contextClick(element).perform();

        // Hover over the element
        actions.moveToElement(element).perform();

        // Drag and drop an element
        actions.dragAndDrop(element, element).perform();

        // Perform key combinations (e.g., Ctrl + Delete)
        actions.keyDown(Keys.CONTROL).keyDown(Keys.DELETE)
                .keyUp(Keys.CONTROL).keyUp(Keys.DELETE).perform();

        // Close the browser
        driver.quit();
    }
}
