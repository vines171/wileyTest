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

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }


    @FindBy(xpath = "//div[contains(@class, 'main-navigation-search')]//input")
    private WebElement searchField;

    @FindBy(xpath = "//div[contains(@class, 'who-we-serve-blocks')]//a")
    private List<WebElement> blockList;

    @FindBy(xpath = "//div[contains(@class, 'component-wrapper')]//h2[contains(text(), 'Who we serve')]")
    private WebElement namePageTitle;

    @FindBy(xpath = "//div[contains(@class, 'main-navigation-search')]//input/..//button")
    WebElement searchButton;

    @FindBy(xpath = "(//section[@class='searchresults-section search-related-content suggestions'])[1]//span")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//div[contains(@class, 'main-navigation-search')]//input/..//button")
    protected WebElement namePage;

    @FindBy(xpath = "//form[@id='country-location-form']//button[@class='close']")
    private WebElement countryForm;


    public MainPage inputSearch(String typeSearch) {
        searchField.sendKeys(typeSearch);
        return this;
    }

    public MainPage getPageTitleText(String pageName) {
        assertEquals(namePageTitle.getText(), pageName);
        return this;
    }

    public MainPage checkBlockName(String nameBlock){
        assertTrue(blockList.stream().map(WebElement::getText).anyMatch(nameBlock::equals));
        return this;
    }

    public MainPage checkBlockNumber(int number){
        assertEquals(blockList.size(), number);
        return this;
    }

    public MainPage checkSearchResults(String nameElement){
        assertEquals(searchResults.stream().allMatch((s) -> s.getText().contains(nameElement)), true);
        return this;
    }

    public void countryFormClick(){
        countryForm.click();
    }
    public SearchResultPage clickSearch(){
        searchButton.click();
        for (String windowHandle : driver.getWindowHandles())
            driver.switchTo().window(windowHandle);
        return new SearchResultPage(driver);
    }

    public SearchResultPage search(String namePage, int number, String nameBlock, String nameBlock2, String nameBlock3, String nameBlock4, String nameBlock5, String nameBlock6, String nameBlock7, String nameBlock8, String nameBlock9, String nameBlock10, String nameBlock11, String nameBlock12, String typeSearch, String nameElement){
        this.getPageTitleText(namePage);
        this.checkBlockNumber(number);
        this.checkBlockName(nameBlock);
        this.checkBlockName(nameBlock2);
        this.checkBlockName(nameBlock3);
        this.checkBlockName(nameBlock4);
        this.checkBlockName(nameBlock5);
        this.checkBlockName(nameBlock6);
        this.checkBlockName(nameBlock7);
        this.checkBlockName(nameBlock8);
        this.checkBlockName(nameBlock9);
        this.checkBlockName(nameBlock10);
        this.checkBlockName(nameBlock11);
        this.checkBlockName(nameBlock12);
        this.inputSearch(typeSearch);
        this.checkSearchResults(nameElement);
        this.clickSearch();
        this.countryFormClick();
        return new SearchResultPage(driver);
    }
}
