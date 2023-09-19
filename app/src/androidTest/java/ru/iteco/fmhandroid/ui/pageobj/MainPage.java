package ru.iteco.fmhandroid.ui.pageobj;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.pageobj.CommonPageFunctions.*;

import androidx.test.espresso.matcher.RootMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;


public class MainPage {
    public static final int mainMenuButton = R.id.main_menu_image_button;
    public static final int mainPageTag = mainMenuButton;
    public static final int userProfileButton = R.id.authorization_image_button;
    public static final int logoutButton = android.R.id.title;
    public static final int allNewsHeadline = R.id.all_news_text_view;
    public static final int allClaimsHeadline = R.id.all_claims_text_view;
    public static final int quotesButton = R.id.our_mission_image_button;

    public static void logOut() {
        Allure.step("Логаут (выход из профиля)");
        waitPage(mainPageTag);
        onView(withId(userProfileButton)).perform(click());
        onView(withId(logoutButton)).perform(click());
    }

    public static void clickMainMenuItem(String item) {
        Allure.step("Выбор(клик) элемента в главном меню");
        clickItem(mainMenuButton);
        onView(withText(item))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());
    }
}
