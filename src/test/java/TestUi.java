import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.EducationPage;
import ui.MainPage;
import ui.SearchResultPage;

import java.util.concurrent.TimeUnit;

public class TestUi {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {

        System.setProperty(ConfigReader.getParam("driver"), ConfigReader.getParam("chromeDriver"));
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ira\\drivers\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
//        driver.get(getProperty("mainPageUrl"));
        driver.get("https://www.wiley.com/en-us");
        mainPage = new MainPage(driver);
    }

    @After
    public void tearDown(){
        driver.quit();

    }

    @Test
    @DisplayName("search Java ")
    public void loginTest() {

//        System.setProperty(ConfigReader.getParam("driver"), ConfigReader.getParam("chromeDriver"));
//
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        driver.get(ConfigReader.getParam("mainPageUrl"));
//        mainPage = new MainPage(driver);


        SearchResultPage searchResultPage = mainPage
                .search("WHO WE SERVE",
                        12,
                        "Students",
                        "Instructors",
                        "Book Authors",
                        "Professionals",
                        "Researchers",
                        "Institutions",
                        "Librarians",
                        "Corporations",
                        "Societies",
                        "Journal Editors",
                        "Government",
                        "Bookstores",
                        "java",
                        "java");

        EducationPage educationPage = searchResultPage
                .educationPage(10, "Java", "E-Book", "Print", "DVD");

        educationPage
                .page("Education",
                        13,
                        "Subjects",
                        "Information & Library Science",
                        "Education & Public Policy",
                        "K-12 General",
                        "Higher Education General",
                        "Vocational Technology",
                        "Conflict Resolution & Mediation (School settings)",
                        "Curriculum Tools- General",
                        "Special Educational Needs",
                        "Theory of Education",
                        "Education Special Topics",
                        "Educational Research & Statistics",
                        "Literacy & Reading",
                        "Classroom Management");
    }

}
