package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.thoughtworks.selenium.SeleneseTestBase.assertEquals;
import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;

public class MainPage {

  private WebDriver driver;

  @FindBy(xpath = "//div[contains(@class, 'main-navigation-search')]//input/..//button")
  protected WebElement namePage;

  @FindBy(xpath = "//div[contains(@class, 'main-navigation-search')]//input")
  private WebElement searchField;

  @FindBy(xpath = "//div[contains(@class, 'who-we-serve-blocks')]//a")
  private List<WebElement> blockList;

  @FindBy(xpath = "//div[contains(@class, 'component-wrapper')]//h2[contains(text(), 'Who we serve')]")
  private WebElement namePageTitle;

  @FindBy(xpath = "//div[contains(@class, 'main-navigation-search')]//input/..//button")
  private WebElement searchButton;

  @FindBy(xpath = "(//section[@class='searchresults-section search-related-content suggestions'])[1]//span")
  private List<WebElement> searchResults;

  @FindBy(xpath = "//form[@id='country-location-form']//button[@class='close']")
  private WebElement countryForm;

  public MainPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }

  public MainPage inputSearch(String typeSearch) {
    searchField.sendKeys(typeSearch);
    return this;
  }

  public MainPage checkPageTitleText(String pageName) {
    assertEquals(namePageTitle.getText(), pageName);
    return this;
  }

  public MainPage checkBlockName(String nameBlock) {
    assertTrue(blockList.stream().map(WebElement::getText).anyMatch(nameBlock::equals));
    return this;
  }

  public MainPage checkBlockNumber(int number) {
    assertEquals(blockList.size(), number);
    return this;
  }

  public MainPage checkSearchResults(String nameElement) {
    assertEquals(searchResults.stream().allMatch((s) -> s.getText().contains(nameElement)), true);
    return this;
  }

  public SearchResultPage clickSearch() {
    searchButton.click();

    if (countryForm.isDisplayed()) countryForm.click();

    for (String windowHandle : driver.getWindowHandles()) driver.switchTo().window(windowHandle);
    return new SearchResultPage(driver);
  }

}
