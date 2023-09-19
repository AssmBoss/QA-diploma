package ru.iteco.fmhandroid.ui.pageobj;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutUsPage {
    public static final int privacyPolicyLink = R.id.about_privacy_policy_value_text_view;
    public static final int termsOfUseLink = R.id.about_privacy_policy_value_text_view;
    public static final ViewInteraction policyText = onView(withText("Политика конфиденциальности"));
    public static final ViewInteraction termsOfUseText = onView(withText("Пользовательское соглашение"));

}