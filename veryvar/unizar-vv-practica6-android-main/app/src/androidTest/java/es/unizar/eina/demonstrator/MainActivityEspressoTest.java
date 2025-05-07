package es.unizar.eina.demonstrator;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * @see <a href="https://developer.android.com/training/testing/espresso">Espresso documentation</a>
 */
public class MainActivityEspressoTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void ensureTextChangesWork() {
        // Type text and then press the button.
        onView(withId(R.id.inputField))
                .perform(typeText("Hello"), closeSoftKeyboard());
        onView(withText("Change Text")).perform(click());
        // Check that the text was changed.
        onView(withId(R.id.inputField)).check(matches(withText("Lalala")));
    }

    @Test
    public void ensureLaunchSecondActivityWithInputTextWork() {
        // Type text and then press the button.
        onView(withId(R.id.inputField)).perform(typeText("NewText"),closeSoftKeyboard());
        onView(withText("Switch Activity")).perform(click());
        // This view is in a different Activity, no need to tell Espresso.
        onView(withId(R.id.resultView)).check(matches(withText("NewText")));
    }
}