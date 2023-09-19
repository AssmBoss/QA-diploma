package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.PageIsReached;
import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.clickItem;
import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.selectField;
import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.waitPage;
import static ru.iteco.fmhandroid.ui.utilities.LoginData.trueLogin;
import static ru.iteco.fmhandroid.ui.utilities.LoginData.truePassword;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

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
import ru.iteco.fmhandroid.ui.pageobj.LoginPage;
import ru.iteco.fmhandroid.ui.pageobj.MainPage;
import ru.iteco.fmhandroid.ui.pageobj.QuotesPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

@Epic("Тест-кейс для проведения функционального тестирования вкладки 'Цитаты' мобильного приложения 'Мобильный хоспис'")
public class QuotesTest {

    private final String title = "\"Хоспис для меня - это то, каким должен быть мир.\"";
    private final String description = "\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.\" Юля Капис, волонтер";
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
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
            LoginPage.feelField(LoginPage.loginField,trueLogin);
            selectField(LoginPage.passwordField);
            LoginPage.feelField(LoginPage.passwordField,truePassword);
            clickItem(LoginPage.signInButton);
            waitPage(MainPage.mainPageTag);
            PageIsReached(MainPage.mainPageTag);
        }
    }

    @Story("Case 25. Открытие цитаты")
    @Description("Открытие цитаты в развернутом виде во вкладке 'Цитаты' мобильного приложения 'Мобильный хоспис'(Позитивный)")
    @Test
    public void openingQuoteTest() throws InterruptedException {
        clickItem(MainPage.quotesButton);
        Thread.sleep(5000);
        waitPage(QuotesPage.missionLogo);
        QuotesPage.openQuote(0);
        QuotesPage.checkTextQuote(description);
    }
}