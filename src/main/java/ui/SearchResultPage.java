package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.thoughtworks.selenium.SeleneseTestBase.assertEquals;
import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;

public class SearchResultPage {
  private WebDriver driver;

  @FindBy(xpath = "//div[@class='product-list-wrapper']//div[@class='product-content']")
  protected List<WebElement> productList;

  @FindBy(xpath = "//div[@class='product-list-wrapper']//section[@class='product-item']//*[@class='product-title']//span")
  protected List<WebElement> productTitleList;

  public SearchResultPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }

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

}
