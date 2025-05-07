package es.unizar.eina.demonstrator.test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotNull;

import androidx.test.rule.ActivityTestRule;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import es.unizar.eina.demonstrator.MainActivity;
import es.unizar.eina.demonstrator.R;

public class RunStepsDefinition {
    ActivityTestRule rule = new ActivityTestRule<>(MainActivity.class);
    @Before
    public void launchActivity() throws Exception {
        rule.launchActivity(null);
    }
    @After
    public void finishActivity() throws Exception {
        rule.getActivity().finish();
    }
    @Given("Abro la aplicación Demonstrator")
    public void abro_la_aplicacion_Demonstrator() {
        assertNotNull(rule.getActivity());
    }

    @When("Tecleo {string} en la caja de texto")
    public void tecleo_textoTecleado_en_la_caja_de_texto(final String textoTecleado) {
        onView(withId(R.id.inputField)).perform(replaceText(textoTecleado), closeSoftKeyboard());
    }

    @When("Pulso el botón {string}")
    public void pulso_el_boton_etiqueta(final String etiqueta) {
        onView(withText(etiqueta)).perform(click());
    }

    @Then("Debería ver {string}")
    public void deberia_ver_textoCambiado(final String textoCambiado) {
        // Aserción: comprobación de que el texto esperado aparece
        onView(withText(textoCambiado)).check(matches(isDisplayed()));
    }
}