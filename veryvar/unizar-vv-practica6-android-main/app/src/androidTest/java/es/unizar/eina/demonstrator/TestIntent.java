package es.unizar.eina.demonstrator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;

import android.content.Intent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import androidx.test.espresso.intent.Intents;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class TestIntent {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    @Before
    public void setUp() {
        Intents.init();
    }
    @After
    public void tearDown() {
        Intents.release();
    }
    @Test
    public void triggerIntentTest() {
        onView(withId(R.id.sendEmail)).perform(click());
        intended(hasAction(equalTo(Intent.ACTION_CHOOSER)));
// se podría utilizar toPackage si conociésemos a priori el paquete que
// contiene a la actividad que se lance:
// intended(toPackage("paqueteActividadLanzada"));
    }
}