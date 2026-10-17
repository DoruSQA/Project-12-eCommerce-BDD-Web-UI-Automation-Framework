
package project.base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initDriver() {

    	String browser = System.getProperty("browser", "chrome");

        switch (browser.toLowerCase()) {

            case "chrome":
            	ChromeOptions options = new ChromeOptions();

            	Map<String, Object> prefs = new HashMap<>();

            	// Disable password manager
            	prefs.put("credentials_enable_service", false);
            	prefs.put("profile.password_manager_enabled", false);

            	// Disable password breach and leak detection
            	prefs.put("profile.password_manager_leak_detection", false);

            	// Disable notifications and permission prompts
            	prefs.put("profile.default_content_setting_values.notifications", 2);
            	prefs.put("profile.default_content_setting_values.geolocation", 2);

            	options.setExperimentalOption("prefs", prefs);

            	driver = new ChromeDriver(options);
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException(
                    "Browser not supported: " + browser
                );
        }

        driver.manage().window().maximize();

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
