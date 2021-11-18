package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TopPanel  {
    private WebDriver driver;

    public TopPanel(WebDriver driver) {
        this.driver = driver;
    }

    public EducationPage hoverElementAndClickElement(String elementNameHover, String elementNameClick) {
        Actions action = new Actions(driver);
        WebElement elementHover = driver.findElement(By.xpath("//nav[@id='main-header-navbar']//a[contains(text(), '" + elementNameHover + "')]"));
        WebElement elementClick = driver.findElement(By.xpath("//nav[@id='main-header-navbar']//a[contains(text(), '" + elementNameHover + "')]/../div//a[contains(text(),'" + elementNameClick + "')]"));
        action.moveToElement(elementHover).build().perform();
        elementClick.click();
        for (String windowHandle : driver.getWindowHandles()) driver.switchTo().window(windowHandle);
        return new EducationPage(driver);
    }
}
