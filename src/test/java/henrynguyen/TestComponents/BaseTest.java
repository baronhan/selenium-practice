package henrynguyen.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import henrynguyen.pageobjects.LandingPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import java.util.List;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;


    public WebDriver initializeDriver() throws IOException {
        //properties class
        //Tạo đối tượng Properties
        Properties prop = new Properties();
        //Dùng FileInputStream để mở file config
        FileInputStream fis = new FileInputStream("D:/Selenium/selenium/selenium-practice/src/main/java/henrynguyen/resources/GolbalData.properties");
        //Dùng prop.load() để đọc dữ liệu từ file .properties
        prop.load(fis);

        //Khi user truyền vào giá trị của browser thì cmd thì lúc này sys sẽ đọc xem có giá trị từ cmd không
        //nếu có thì lấy giá trị đó, còn không thì lấy giá trị trong Global.properties
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

        if(browserName.contains("chrome"))
        {
            //headless trong trường hợp mình không muốn mở các tab lên -> mình chỉ muốn thực thi dưới BE
            ChromeOptions option = new ChromeOptions();
            if(browserName.contains("headless"))
            {
                option.addArguments("headless");
            }

            driver = new ChromeDriver(option);
            //bởi vì thực thi dưới BE nên nó không thể mở fullsize nên cần set size cho web
            driver.manage().window().setSize(new Dimension(1920, 1080)); //fullscreen
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }

    public List<HashMap<String, String>> getJsonToMap(String filePath) throws IOException {
        // Read JSON content as a String
        String jsonContent = FileUtils.readFileToString(new File(filePath), "UTF-8");

        // Convert JSON string to List of HashMaps
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        File desFile = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(srcFile, desFile);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }
}
