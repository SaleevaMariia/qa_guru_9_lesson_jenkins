package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.page.RegistrationPage;
import guru.qa.utils.Attach;
import guru.qa.utils.TestData;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

public class RegistrationFormTest {

    static RegistrationPage registrationPage;

    @BeforeAll
    public static void setUp(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        Configuration.baseUrl="https://demoqa.com";
        Configuration.startMaximized = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
        TestData.initPositiveTestData();
        registrationPage = new RegistrationPage();
    }

    @Test
    public void fullPositiveTest() {
        registrationPage.openPage();
        registrationPage.fillAllPositiveData();
        registrationPage.clickSubmit();
        registrationPage.checkResult();
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
