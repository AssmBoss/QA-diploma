package ru.iteco.fmhandroid.ui.pageobj;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.utilities.Utils.childAtPosition;
import static ru.iteco.fmhandroid.ui.utilities.Utils.waitDisplayed;
import static ru.iteco.fmhandroid.ui.utilities.Utils.withIndex;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class NewsPage {
    public static final int filterNewsButton = R.id.filter_news_material_button;
    public static final int applyFilterButton = R.id.filter_button;// применить фильтр
    public static final int editNewsButton = R.id.edit_news_material_button;
    public static final int addNewsButton = R.id.add_news_image_view;
    public static final int newsList = R.id.news_list_recycler_view;
    public static final int newsTitleField = R.id.news_item_title_text_view;  //поле заголовка новости
    public static final int newsDescriptionField = R.id.news_item_description_text_view;
    public static final int newsPreview = R.id.news_item_material_card_view;
    public static final int categoryButtonOfFilter = com.google.android.material.R.id.text_input_end_icon;

    public static void findAddedNews(String description) {
        Allure.step("Найти добавленную новость через Cписок всех новостей");
        onView(withText(description)).check(matches(isDisplayed()));
    }

    public static void checkNewsCategory(String title, int position) {
        Allure.step("Проверить категорию новости в Списке");
        ViewInteraction textView = onView(
                allOf(withIndex(withId(newsTitleField), position),
                        isDisplayed()));
        textView.check(matches(withText(title)));
    }

    public static void chooseCategoryOfNews(String title) {
        Allure.step("Выбрать категорию новости " + title + " в Фильтре");
        onView(allOf(withId(categoryButtonOfFilter), withContentDescription("Show dropdown menu"))).perform(click());
        onView(withText(title))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());
    }

    public static void selectStartFilterTimeInterval() {
        Allure.step("Выбрать НАЧАЛО интервала для Фильтра - текущую дату");
        CommonPageFunctions.clickItem(R.id.news_item_publish_date_start_text_input_edit_text);
        CommonPageFunctions.clickItem(android.R.id.button1);
    }

    public static void selectEndFilterTimeInterval() {
        Allure.step("Выбрать КОНЕЦ интервала для Фильтра - текущую дату");
        CommonPageFunctions.clickItem(R.id.news_item_publish_date_end_text_input_edit_text);
        CommonPageFunctions.clickItem(android.R.id.button1);
    }

    public static void clickFreshNews() {
        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.news_list_recycler_view),
                        childAtPosition(
                                withId(R.id.all_news_cards_block_constraint_layout),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));
    }
}