package StepsDef;

import Utility.WebDriverFactory;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class MySteps {
    WebDriver driver=WebDriverFactory.getDriver();;
    @Given("user Launch the Browser")
    public void userLaunchTheBrowser() {

        driver.get("http://www.google.com");
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.click();
        searchBar.sendKeys("Well done");


    }
}
