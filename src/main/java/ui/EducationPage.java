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

    public EducationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "//div[@class='hero-banner']//span[contains(text(), 'Education')]")
    private WebElement titlePage;

    @FindBy(xpath = "//div[@class='side-panel']//*[contains(text(), 'Subjects')]")
    private WebElement headerPage;

    @FindBy(xpath = "//div[@class='side-panel']//*[contains(text(), 'Subjects')]/../../..//li/a")
    private List<WebElement> subjectsList;

    public EducationPage checkTitlePage(String pageName){
        assertEquals(titlePage.getText(), pageName);
        return this;
    }

    public EducationPage checkHeaderPage(String headerName){
        assertEquals(headerPage.getText(), headerName);
        return this;
    }

    public EducationPage checkSubjectsName(String elementName){
        assertTrue(subjectsList.stream().map(WebElement::getText).anyMatch(elementName::equals));
        return this;
    }

    public EducationPage checkSubjectsNumber(int number){
        assertEquals(subjectsList.size(), number);
        return this;
    }


    public EducationPage() {
    }

    public void page(String pageName,  int number, String headerName,
                     String SubjectsName1,
                     String SubjectsName2,
                     String SubjectsName3,
                     String SubjectsName4,
                     String SubjectsName5,
                     String SubjectsName6,
                     String SubjectsName7,
                     String SubjectsName8,
                     String SubjectsName9,
                     String SubjectsName10,
                     String SubjectsName11,
                     String SubjectsName12,
                     String SubjectsName13
    ){
        this.checkTitlePage(pageName);
        this.checkHeaderPage(headerName);
        this.checkSubjectsNumber(number);
        this.checkSubjectsName(SubjectsName1);
        this.checkSubjectsName(SubjectsName2);
        this.checkSubjectsName(SubjectsName3);
        this.checkSubjectsName(SubjectsName4);
        this.checkSubjectsName(SubjectsName5);
        this.checkSubjectsName(SubjectsName6);
        this.checkSubjectsName(SubjectsName7);
        this.checkSubjectsName(SubjectsName8);
        this.checkSubjectsName(SubjectsName9);
        this.checkSubjectsName(SubjectsName10);
        this.checkSubjectsName(SubjectsName11);
        this.checkSubjectsName(SubjectsName12);
        this.checkSubjectsName(SubjectsName13);
    }
}
