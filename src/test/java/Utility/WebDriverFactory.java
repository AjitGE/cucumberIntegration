package Utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver(){
        WebDriverManager.edgedriver().operatingSystem(OperatingSystem.MAC).setup();
        if (driver==null)
          return  driver= WebDriverManager.edgedriver().create();
     return driver;
    }
}
