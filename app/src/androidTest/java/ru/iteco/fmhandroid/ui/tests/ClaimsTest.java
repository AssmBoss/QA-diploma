package ru.iteco.fmhandroid.ui.tests;


import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.PageIsReached;
import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.clickItem;
import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.selectField;
import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.waitPage;
import static ru.iteco.fmhandroid.ui.utilities.LoginData.trueLogin;
import static ru.iteco.fmhandroid.ui.utilities.LoginData.truePassword;

import android.view.View;

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
import ru.iteco.fmhandroid.ui.pageobj.ClaimsPage;
import ru.iteco.fmhandroid.ui.pageobj.CreateClaimPage;
import ru.iteco.fmhandroid.ui.pageobj.LoginPage;
import ru.iteco.fmhandroid.ui.pageobj.MainPage;
import ru.iteco.fmhandroid.ui.utilities.Utils;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Тест-кейсы для проведения функционального тестирования вкладки 'Претензии' мобильного приложения Мобильный хоспис")
public class ClaimsTest {
    private final String claimsMenuItem = "Claims";
    private final String invalidDate = "11.11.1111";
    private final String invalidTime = "25:65";
    private final String errorMessageWrongDate = "Invalid date!";
    private final String errorMessageWrongTime = "Invalid time!";

    public View decorView;

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

    @Story("Case 15. Добавление новой претензии с текущей датой и временем")
    @Description("Создание новой претензии с текущей датой и временем во вкладке 'Претензии' мобильного приложения 'Мобильный хоспис' (Позитивный)")
    @Test
    public void addNewClaimCurrentDataTest() throws InterruptedException {
        String claimDescription = Utils.getRandomNewsDescription();
        String claimTitle = Utils.getRandomClaimTitle();
        clickItem(ClaimsPage.addNewClaimButton);
        waitPage(CreateClaimPage.executorField);
        CreateClaimPage.addClaimTitle(claimTitle);
        CreateClaimPage.chooseExecutor();
        CreateClaimPage.addClaimCurrentDate();
        CreateClaimPage.addClaimCurrentTime();
        CreateClaimPage.addClaimDescription(claimDescription);
        CreateClaimPage.saveNewClaim();
       // Thread.sleep(10000);
        waitPage(MainPage.mainPageTag);
        MainPage.clickMainMenuItem(claimsMenuItem);
        Thread.sleep(5000);
        waitPage(ClaimsPage.claimsList);
        ClaimsPage.findAddedClaim(claimTitle);
    }

    @Story("Case 17. Добавление новой претензии с текущей датой и временем через Главное меню")
    @Description("Создание новой претензии с текущей датой и временем через вкладку Главное меню мобильного приложения 'Мобильный хоспис' (Позитивный)")
    @Test
    public void addNewClaimCurrentDataMainMenuTest() throws InterruptedException {
        String claimDescription = Utils.getRandomNewsDescription();
        String claimTitle = Utils.getRandomClaimTitle();
        MainPage.clickMainMenuItem(claimsMenuItem);
        waitPage(ClaimsPage.claimsList);
        clickItem(ClaimsPage.addNewClaimButton);
        waitPage(CreateClaimPage.executorField);
        CreateClaimPage.addClaimTitle(claimTitle);
        CreateClaimPage.chooseExecutor();
        CreateClaimPage.addClaimCurrentDate();
        CreateClaimPage.addClaimCurrentTime();
        CreateClaimPage.addClaimDescription(claimDescription);
        CreateClaimPage.saveNewClaim();
        Thread.sleep(5000);
        waitPage(ClaimsPage.claimsList);
        //Thread.sleep(10000);
        //waitPage(ClaimsPage.addNewClaimButton);
        ClaimsPage.findAddedClaim(claimTitle);
    }

    @Story("Case 17. Добавление новой претензии с некорректной датой и корректным временем")
    @Description("Попытка создания новой претензии с некорректной датой и корректным временем во вкладке 'Претензии' мобильного приложения 'Мобильный хоспис' (Негативный)")
    @Test
    public void addNewClaimInvalidDateTest() throws InterruptedException {
        String claimDescription = Utils.getRandomNewsDescription();
        String claimTitle = Utils.getRandomClaimTitle();
        clickItem(ClaimsPage.addNewClaimButton);
        waitPage(CreateClaimPage.executorField);
        CreateClaimPage.addClaimTitle(claimTitle);
        CreateClaimPage.chooseExecutor();
        CreateClaimPage.addClaimInvalidDate(invalidDate);
        CreateClaimPage.addClaimCurrentTime();
        CreateClaimPage.addClaimDescription(claimDescription);
        CreateClaimPage.saveNewClaim();
        Thread.sleep(5000);
        CreateClaimPage.checkToastErrorMessage(errorMessageWrongDate, decorView);
    }

    @Story("18. Добавление новой претензии с корректной датой и некорректным временем")
    @Description("Попытка создания новой претензии с корректной датой и некорректным временем во вкладке 'Претензии' мобильного приложения 'Мобильный хоспис' (Негативный)")
    @Test
    public void addNewClaimInvalidTimeTest() throws InterruptedException {
        String claimDescription = Utils.getRandomNewsDescription();
        String claimTitle = Utils.getRandomClaimTitle();
        clickItem(ClaimsPage.addNewClaimButton);
        waitPage(CreateClaimPage.executorField);
        CreateClaimPage.addClaimTitle(claimTitle);
        CreateClaimPage.chooseExecutor();
        CreateClaimPage.addClaimCurrentDate();
        CreateClaimPage.addClaimInvalidTime(invalidTime);
        CreateClaimPage.addClaimDescription(claimDescription);
        CreateClaimPage.saveNewClaim();
        Thread.sleep(5000);
        CreateClaimPage.checkToastErrorMessage(errorMessageWrongTime, decorView);
    }
}
