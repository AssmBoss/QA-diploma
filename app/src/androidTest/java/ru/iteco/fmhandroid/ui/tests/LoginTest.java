package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pageobj.LoginPage;
import ru.iteco.fmhandroid.ui.pageobj.MainPage;
import ru.iteco.fmhandroid.ui.utilities.LoginData;

import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.*;

import android.view.View;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Тесты для функционального тестирования: логин(вход) и логаут(выход) из личного кабинета(ЛК) мобильного приложения 'Мобильный хоспис' ")
public class LoginTest {
    public View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void goToLoginPage() {
        try {
            waitPage(LoginPage.loginPageTag);
        } catch (Exception e) {
            MainPage.logOut();
        }
    }

    @Story("Case 1. Успешный логин с валидными данными")
    @Description("Успешный логин с валидными данными login2 и password2 через страницу авторизации приложения 'Мобильный хоспис' (Позитивный)")
    @Test
    public void successfulLogin() throws InterruptedException {
        waitPage(LoginPage.loginPageTag);
        selectField(LoginPage.loginField);
        LoginPage.feelField(LoginPage.loginField, LoginData.trueLogin);
        selectField(LoginPage.passwordField);
        LoginPage.feelField(LoginPage.passwordField,LoginData.truePassword);
        clickItem(LoginPage.signInButton);
        waitPage(MainPage.mainPageTag);
        PageIsReached(MainPage.mainPageTag);
    }

    @Story("Case 2. Успешный логаут из ЛК")
    @Description("Успешный логаут из ЛК через меню ЛК приложения 'Мобильный хоспис' (Позитивный)")
    @Test
    public void successfulLogOut() throws InterruptedException {
        waitPage(LoginPage.loginPageTag);
        selectField(LoginPage.loginField);
        LoginPage.feelField(LoginPage.loginField, LoginData.trueLogin);
        selectField(LoginPage.passwordField);
        LoginPage.feelField(LoginPage.passwordField,LoginData.truePassword);
        clickItem(LoginPage.signInButton);
        waitPage(MainPage.mainPageTag);
        MainPage.logOut();
        waitPage(LoginPage.loginPageTag);
        PageIsReached(LoginPage.loginPageTag);
    }

    @Story("Case 3. Безуспешеный логин с пустыми логином и паролем")
    @Description("Безуспешеный логин с с пустыми логином и паролем через страницу авторизации приложения 'Мобильный хоспис' (Негативный)")
    @Test
    public void failedLoginWithEmptyLoginAndPass() {
        waitPage(LoginPage.loginPageTag);
        selectField(LoginPage.loginField);
        LoginPage.feelField(LoginPage.loginField, LoginData.emptyLogin);
        selectField(LoginPage.passwordField);
        LoginPage.feelField(LoginPage.passwordField, LoginData.emptyPassword);
        clickItem(LoginPage.signInButton);
        LoginPage.isNotLogin();
    }

    @Story("Case 4. Безуспешеный логин с невалидным логином и валидным паролем")
    @Description("Безуспешеный логин с невалидным логином login и валидным паролем password2 через страницу авторизации приложения 'Мобильный хоспис' (Негативный)")
    @Test
    public void failedLoginWithWrongLogin() throws InterruptedException {
        waitPage(LoginPage.loginPageTag);
        selectField(LoginPage.loginField);
        LoginPage.feelField(LoginPage.loginField, LoginData.wrongLogin);
        selectField(LoginPage.passwordField);
        LoginPage.feelField(LoginPage.passwordField,LoginData.truePassword);
        clickItem(LoginPage.signInButton);
        Thread.sleep(2000);
        LoginPage.isNotLogin();
//        LoginPage.checkToastErrorMessage(errorLoginOrPassword, decorView);
    }
    @Story("Case 5. Безуспешеный логин с валидным логином и невалидным паролем")
    @Description("Безуспешеный логин с валидным логином login2 и невалидным паролем password через страницу авторизации приложения 'Мобильный хоспис' (Негативный)")
    @Test
    public void failedLoginWithWrongPassword() {
        waitPage(LoginPage.loginPageTag);
        selectField(LoginPage.loginField);
        LoginPage.feelField(LoginPage.loginField, LoginData.trueLogin);
        selectField(LoginPage.passwordField);
        LoginPage.feelField(LoginPage.passwordField, LoginData.wrongPassword);
        clickItem(LoginPage.signInButton);
        LoginPage.isNotLogin();
    }
}
