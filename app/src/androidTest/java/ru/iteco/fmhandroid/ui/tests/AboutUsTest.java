package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.PageIsReached;
import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.clickItem;
import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.selectField;
import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.waitPage;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageobj.AboutUsPage;
import ru.iteco.fmhandroid.ui.pageobj.LoginPage;
import ru.iteco.fmhandroid.ui.pageobj.MainPage;
import ru.iteco.fmhandroid.ui.utilities.LoginData;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Тест-кейсы для проведения функционального тестирования вкладки 'О приложении' мобильного приложения 'Мобильный хоспис'")
public class AboutUsTest {
    private final String aboutMenuItem = "About";

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ActivityTestRule<AppActivity> mActivityTestRule =
            new ActivityTestRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));

    @Before
    public void setUp() {
        try {
            waitPage(MainPage.mainPageTag);
        } catch (Exception e) {
            waitPage(LoginPage.loginPageTag);
            selectField(LoginPage.loginField);
            LoginPage.feelField(LoginPage.loginField, LoginData.trueLogin);
            selectField(LoginPage.passwordField);
            LoginPage.feelField(LoginPage.passwordField,LoginData.truePassword);
            clickItem(LoginPage.signInButton);
            waitPage(MainPage.mainPageTag);
            PageIsReached(MainPage.mainPageTag);
        }
    }

    @Story("Case 27. Переход по ссылке 'Политика конфиденциальности'")
    @Description("Перейти по ссылке 'Политика конфиденциальности' во вкладке 'О приложении' мобильного приложения 'Мобильный хоспис' и дождаться загрузки информации (Позитивный)")
    @Test
    public void followTheLinkPrivacyPolicyTest() throws InterruptedException {
        MainPage.clickMainMenuItem(aboutMenuItem);
        Thread.sleep(5000);
        Intents.init();
        clickItem(AboutUsPage.privacyPolicyLink);
        intended(hasData("https://vhospice.org/#/privacy-policy"));
        intended(hasAction(Intent.ACTION_VIEW));
        Intents.release();
        AboutUsPage.policyText.check(matches(isDisplayed()));
        pressBack();
    }

    @Story("Case 28. Переход по ссылке 'Правила использования'")
    @Description("Перейти по ссылке 'Правила использования' во вкладке 'О приложении' мобильного приложения 'Мобильный хоспис' и дождаться загрузки информации (Позитивный)")
    @Test
    public void followTheLinkTermsOfUseTest() throws InterruptedException {
        MainPage.clickMainMenuItem(aboutMenuItem);
        Thread.sleep(5000);
        Intents.init();
        clickItem(AboutUsPage.termsOfUseLink);
        intended(hasData("https://vhospice.org/#/terms-of-use"));
        intended(hasAction(Intent.ACTION_VIEW));
        Intents.release();
        AboutUsPage.termsOfUseText.check(matches(isDisplayed()));
        pressBack();
    }

}