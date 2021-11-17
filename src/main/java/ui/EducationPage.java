package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.thoughtworks.selenium.SeleneseTestBase.assertEquals;
import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;

public class EducationPage {
  public WebDriver driver;

  @FindBy(xpath = "//div[@class='hero-banner']//span[contains(text(), 'Education')]")
  private WebElement titlePage;

  @FindBy(xpath = "//div[@class='side-panel']//*[contains(text(), 'Subjects')]")
  private WebElement headerPage;

  @FindBy(xpath = "//div[@class='side-panel']//*[contains(text(), 'Subjects')]/../../..//li/a")
  private List<WebElement> subjectsList;

  public EducationPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }

  public EducationPage() {}

  public EducationPage checkTitlePage(String pageName) {
    assertEquals(titlePage.getText(), pageName);
    return this;
  }

  public EducationPage checkHeaderPage(String headerName) {
    assertEquals(headerPage.getText(), headerName);
    return this;
  }

  public EducationPage checkSubjectsName(String elementName) {
    assertTrue(subjectsList.stream().map(WebElement::getText).anyMatch(elementName::equals));
    return this;
  }

  public EducationPage checkSubjectsNumber(int number) {
    assertEquals(subjectsList.size(), number);
    return this;
  }
}
