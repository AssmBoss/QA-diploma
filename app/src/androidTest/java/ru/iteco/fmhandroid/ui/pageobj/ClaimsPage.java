package ru.iteco.fmhandroid.ui.pageobj;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class ClaimsPage {
    public static final int addNewClaimButton = R.id.add_new_claim_material_button;
    public static final int claimsList = R.id.claim_list_recycler_view;

    public static void findAddedClaim(String title) throws InterruptedException {
        Allure.step("Найти добавленную претензию через 'Все претензии'");
        onView(ViewMatchers.withId(claimsList))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(title)))).check(matches(isDisplayed()));
                //.perform(RecyclerViewActions.scrollTo(hasDescendant(withText(title))));
        Thread.sleep(2000);
    }

}
