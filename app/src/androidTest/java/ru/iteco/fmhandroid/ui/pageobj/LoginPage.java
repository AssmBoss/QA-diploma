package ru.iteco.fmhandroid.ui.pageobj;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.waitPage;
import static ru.iteco.fmhandroid.ui.utilities.Utils.*;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

import android.view.View;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import org.hamcrest.Matchers;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

public class LoginPage {
    public static final int loginField = R.id.login_text_input_layout;
    public static final int loginPageTag = loginField;
    public static final int passwordField = R.id.password_text_input_layout;
    public static final int signInButton = R.id.enter_button;
    public static final String errorLoginOrPassword = "Wrong login or password";
    public static final String errorEmptyField = "Login and password cannot be empty";

    public static void waitLoginPage() {
        onView(isRoot()).perform(waitDisplayed(loginField,10000 ));
    }

    public static void feelField(int field, String inputText) {
        Allure.step("Заполнение выбранного поля текстом: {inputText}");
        onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(field),
                                0),
                        0)))
                .perform(replaceText(inputText), closeSoftKeyboard());
    }

    public static void isNotLogin() {
        Allure.step("Проверка того, что войти в приложение (логин) НЕ удалось");
        try {
            waitPage(MainPage.mainPageTag);
        } catch (Exception e) {
            //CommonPageFunctions.PageIsReached(loginPageTag);
        }
        finally {
            CommonPageFunctions.PageIsReached(loginPageTag);
        }
    }

    @Step("Появление всплывающего сообщения об ошибке 'Неверные данные'")
    public static void checkToastErrorMessage(String messageError, View decorView) {
        onView(withText(messageError))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));

        // onView(allOf(withContentDescription(messageError), isDisplayed()));
    }
}
