package qaclickacademy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Android {
    private AppiumDriver androidDriver;
    private static AppiumDriverLocalService server;
    @BeforeSuite
    public void serverSetUp(){
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingAnyFreePort();
        serviceBuilder.usingDriverExecutable(new File("/opt/homebrew/bin/node"));
        serviceBuilder.withAppiumJS(new File("/opt/homebrew/bin/appium"));
        HashMap<String, String> environment = new HashMap();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
        serviceBuilder.withEnvironment(environment);

        serviceBuilder.withStartUpTimeOut(30, TimeUnit.SECONDS);
        server = AppiumDriverLocalService.buildService(serviceBuilder);
        server.start();





    }
    @BeforeClass
    public void setup() throws MalformedURLException {
        while (!server.isRunning()){
            System.out.println("Starting Server....");
        }
        File app = new File("/Users/ajityadav/Downloads/ApiDemos-debug.apk");
        DesiredCapabilities desiredCapabilities= new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"");
        desiredCapabilities.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        androidDriver = new AndroidDriver <AndroidElement> ( server.getUrl(),desiredCapabilities);
        androidDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        TouchActions t = new TouchActions(androidDriver);
        AndroidDriver driver=(AndroidDriver)androidDriver;
        driver.findElementByAndroidUIAutomator("new UIScrollable(new UISelector()).ScrollIntoView(attribute(\"webview\"))");

    }


    @Test
    public void NativeIOSApp()
    {
        WebElement element =androidDriver.findElementByAccessibilityId("IntegerA");
        element.click();
        element.sendKeys("10");


    }

    @AfterClass
    public void tearDown()
    {

    }

    @AfterSuite
    public  void shutDownServer(){
        server.stop();
    }
}

