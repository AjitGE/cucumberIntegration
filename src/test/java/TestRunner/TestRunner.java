package TestRunner;

import Utility.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


@CucumberOptions(
        features = "Features",
        glue = {"StepsDef"},
        tags = "@Run_first_test",
        plugin = {"pretty",
                "json: target",
                "html: target/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                },
        monochrome = true,
        publish = true


)


public class TestRunner extends AbstractTestNGCucumberTests{
    private static WebDriver driver= WebDriverFactory.getDriver();



    @AfterClass
    public void tearDown(){

        driver.quit();
    }

}

