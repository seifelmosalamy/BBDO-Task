package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
    public static WebDriver driver;
    private Properties prop;
    public static WebDriverWait w;//test
    private JavascriptExecutor js;

    public JavascriptExecutor getJS() {
        js = (JavascriptExecutor) driver;
        return js;
    }

    public WebDriver initializeDriver() throws IOException {
        prop = new Properties();
        // Try multiple possible paths for the properties file
        String[] possiblePaths = {
            "src/main/java/resources/data.properties",
            System.getProperty("user.dir") + "/src/main/java/resources/data.properties",
            "../src/main/java/resources/data.properties"
        };
        
        FileInputStream fis = null;
        for (String path : possiblePaths) {
            try {
                fis = new FileInputStream(path);
                break;
            } catch (FileNotFoundException e) {
                // Try next path
            }
        }
        
        if (fis == null) {
            throw new FileNotFoundException("Could not find data.properties in any of the expected locations");
        }
        
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        return driver;
    }

    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;

    }
    public void saveScreenshot(String methodName, byte[] screenshotBytes) {
        try {
            // Create a folder named "screenshots" if it doesn't exist
            File screenshotDir = new File("screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs(); // Create directory and parent directories if necessary
            }

            // Save screenshot to a file in the "screenshots" folder with the given method name
            File screenshotFile = new File(screenshotDir, methodName + ".png");
            FileUtils.writeByteArrayToFile(screenshotFile, screenshotBytes);

            // For demonstration, print method name and screenshot path
            System.out.println("Screenshot taken for method: " + methodName);
            System.out.println("Screenshot saved to: " + screenshotFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to take screenshot
    public byte[] takeScreenshot() {
        try {
            // Take screenshot as byte array
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
