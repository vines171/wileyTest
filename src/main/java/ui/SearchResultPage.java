package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.thoughtworks.selenium.SeleneseTestBase.assertEquals;
import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;

public class SearchResultPage extends EducationPage {
  private WebDriver driver;

  @FindBy(xpath = "//div[@class='product-list-wrapper']//div[@class='product-content']")
  protected List<WebElement> productList;

  @FindBy(xpath = "//div[@class='product-list-wrapper']//section[@class='product-item']//*[@class='product-title']//span")
  protected List<WebElement> productTitleList;

  @FindBy(xpath = "//nav[@id='main-header-navbar']//a[contains(text(), 'SUBJECTS')]")
  protected WebElement subjectsButton;

  @FindBy(xpath = "//nav[@id='main-header-navbar']//a[contains(text(), 'SUBJECTS')]/../div//a[contains(text(),'Education')]")
  protected WebElement educationButton;


  public SearchResultPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }

  public SearchResultPage() {}

  public SearchResultPage checkProductListNumber(int number) {
    assertEquals(productList.size(), number);
    return this;
  }

  public SearchResultPage checkSearchResults(String nameElement) {
    assertTrue(productTitleList.stream().anyMatch((s) -> s.getText().contains(nameElement)));
    return this;
  }

  public SearchResultPage productList(String eBook, String print, String DVD) {
    assertEquals(
        (productList.stream()
            .allMatch(
                ((s) ->
                    (s.getText().contains(eBook))
                        || (s.getText().contains(print))
                        || (s.getText().contains(DVD))))),
        true);
    return this;
  }

  public EducationPage educationButtonClick() {
    educationButton.click();
    for (String windowHandle : driver.getWindowHandles()) driver.switchTo().window(windowHandle);
    return new EducationPage(driver);
  }

  public SearchResultPage hoverElement() {
    Actions action = new Actions(driver);
    action.moveToElement(subjectsButton).build().perform();
    return this;
  }
}
