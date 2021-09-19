package qaclickacademy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.xml.sax.helpers.AttributesImpl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AppiumTest {
	private AppiumDriver<IOSElement> iosDriver;
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

		serviceBuilder.withStartUpTimeOut(30,TimeUnit.SECONDS);
		server = AppiumDriverLocalService.buildService(serviceBuilder);
		server.start();





	}
	@BeforeClass
	public void setup() throws MalformedURLException {
		while (!server.isRunning()){
			System.out.println("Starting Server....");
		}

		DesiredCapabilities desiredCapabilities= new DesiredCapabilities();
		desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone 8");
		desiredCapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID,"io.appium.TestApp");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"14.5");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.IOS);
		iosDriver = new IOSDriver (server.getUrl(),desiredCapabilities);
		iosDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		TouchActions t = new TouchActions(iosDriver);


	}


	@Test
	public void NativeIOSApp()
	{
		WebElement element =iosDriver.findElementByAccessibilityId("IntegerA");
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
