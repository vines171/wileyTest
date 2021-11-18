import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import ui.EducationPage;
import ui.MainPage;
import ui.SearchResultPage;
import ui.TopPanel;

import java.util.concurrent.TimeUnit;

public class UiTest {
  private WebDriver driver;
  private MainPage mainPage;

  @Before
  public void setUp() {

    System.setProperty(ConfigReader.getParam("driver"), ConfigReader.getParam("chromeDriver"));

    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    driver.get(EndpointEnum.mainWileyPageUrl);
    mainPage = new MainPage(driver);
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  @DisplayName("Check main page")
  public void mainPageTest() {

    MainPage mainPage = PageFactory.initElements(driver, MainPage.class);

        mainPage
            .checkPageTitleText("WHO WE SERVE")
            .checkBlockNumber(12)
            .checkBlockName("Students")
            .checkBlockName("Instructors")
            .checkBlockName("Book Authors")
            .checkBlockName("Professionals")
            .checkBlockName("Researchers")
            .checkBlockName("Institutions")
            .checkBlockName("Librarians")
            .checkBlockName("Corporations")
            .checkBlockName("Societies")
            .checkBlockName("Journal Editors")
            .checkBlockName("Government")
            .checkBlockName("Bookstores");
        }


  @Test
  @DisplayName("Check search java")
  public void searchJvaTest() {

    SearchResultPage searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
    MainPage mainPage = PageFactory.initElements(driver, MainPage.class);

              mainPage
                  .inputSearch("java")
                  .checkSearchResults("java")
                  .clickSearch();

      searchResultPage
              .checkProductListNumber(10)
              .checkSearchResults("Java")
              .productList("E-Book", "Print", "DVD");
  }


    @Test
    @DisplayName("check education page")
    public void educationPageTest() {

    EducationPage educationPage = PageFactory.initElements(driver, EducationPage.class);
    TopPanel topPanel = PageFactory.initElements(driver, TopPanel.class);

    topPanel.hoverElementAndClickElement("SUBJECTS", "Education" );

        educationPage
        .checkTitlePage("Education")
        .checkHeaderPage("Subjects")
        .checkSubjectsNumber(13)
        .checkSubjectsName("Information & Library Science")
        .checkSubjectsName("Education & Public Policy")
        .checkSubjectsName("K-12 General")
        .checkSubjectsName("Higher Education General")
        .checkSubjectsName("Vocational Technology")
        .checkSubjectsName("Conflict Resolution & Mediation (School settings)")
        .checkSubjectsName("Curriculum Tools- General")
        .checkSubjectsName("Special Educational Needs")
        .checkSubjectsName("Theory of Education")
        .checkSubjectsName("Education Special Topics")
        .checkSubjectsName("Educational Research & Statistics")
        .checkSubjectsName("Literacy & Reading")
        .checkSubjectsName("Classroom Management");
  }
}
