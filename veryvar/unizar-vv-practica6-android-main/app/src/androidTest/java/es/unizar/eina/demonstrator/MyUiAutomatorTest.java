package es.unizar.eina.demonstrator;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @see <a href="https://developer.android.com/training/testing/other-components/ui-automator">UiAutomator documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MyUiAutomatorTest {
    private UiDevice mDevice;

    private static final int TIMEOUT = 5000;

    final private static String APP_NAME = "Demonstrator";

    final private static String APP_PACKAGE = "es.unizar.eina.demonstrator";

    /**
     * Alternativa como método Before para abrir la aplicación haciendo clic sobre su nombre
     * en la pantalla donde se muestran todas las aplicaciones disponibles
     */
    public void startMainActivityFromHomeScreenClickingOnAppName() throws UiObjectNotFoundException {
        // Se inicializa la instancia de UiDevice
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Se pulsa el botón para ir a la pantalla inicial
        mDevice.pressHome();


        // Nos deslizamos por la pestaña en sentido vertical (por defecto) u horizontal
        UiScrollable appViews = new UiScrollable(new UiSelector()
                .scrollable(true));
        appViews.scrollForward();

        // Abrir la aplicación
        mDevice.wait(Until.hasObject(By.desc(APP_NAME)),TIMEOUT);
        UiObject2 app = mDevice.findObject(By.desc(APP_NAME));
        assertThat(app,notNullValue());
        app.clickAndWait(Until.newWindow(),TIMEOUT);
    }

    @Before
    public void startMainActivityFromHomeScreen() {
        // Se inicializa la instancia de UiDevice
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Se pulsa el botón para ir a la pantalla inicial
        mDevice.pressHome();

        // Esperar a que el lanzador de aplicaciones esté disponible
        final String launcherPackage = mDevice.getLauncherPackageName();
        // launcherPackageName toma el valor "com.android.settings"
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), TIMEOUT);

        // Abrir la aplicación
        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(APP_PACKAGE);
        // Configurar que se elimine cualquier instancia previa
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Esperar hasta que el dispositivo esté disponible
        mDevice.wait(Until.hasObject(By.pkg(APP_PACKAGE).depth(0)),TIMEOUT);
    }

    @Test
    public void ensureTextChangesWork() {

        // Localizar la caja de texto y teclear un texto
        UiObject2 editText = mDevice.findObject(By.clazz("android.widget.EditText"));
        editText.setText("Hello");

        //Pulsar el botón
        UiObject2 changeTextButton = mDevice.findObject(By.text("Change Text"));
        changeTextButton.click();

        // Verificar que ha cambiado el valor localizando caja de texto por su contenido
        String expectedText = "Lalala";
        mDevice.wait(Until.hasObject(By.text(expectedText)),TIMEOUT);
        UiObject2 lalala = mDevice.findObject(By.text(expectedText));
        assertThat(lalala,notNullValue());
    }

}