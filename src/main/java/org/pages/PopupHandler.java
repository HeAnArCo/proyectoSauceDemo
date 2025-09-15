package org.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class PopupHandler {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public PopupHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void closePasswordPopup() {
        try {
            System.out.println("🔧 Intentando cerrar popup de forma segura...");

            // Primero intentar con esperas explícitas para elementos comunes
            try {
                // Esperar y hacer clic en botones comunes de aceptar
                String[] buttonSelectors = {
                        "button:contains('Aceptar'), button:contains('Accept')",
                        "button:contains('OK'), button:contains('Ok')",
                        "button:contains('Confirmar'), button:contains('Confirm')",
                        "input[value='Aceptar'], input[value='Accept']",
                        "input[value='OK'], input[value='Ok']"
                };

                for (String selector : buttonSelectors) {
                    try {
                        String script = String.format(
                                "var elements = document.querySelectorAll('%s');" +
                                        "if (elements.length > 0) {" +
                                        "   elements[0].click();" +
                                        "   return 'Haciendo clic en botón con selector: ' + '%s';" +
                                        "} else {" +
                                        "   return 'No se encontró elemento con selector: ' + '%s';" +
                                        "}", selector, selector, selector);

                        Object result = ((JavascriptExecutor) driver).executeScript(script);
                        System.out.println("Resultado: " + result);

                        // Pequeña pausa entre intentos
                        Thread.sleep(300);
                    } catch (Exception e) {
                        System.out.println("Intento con selector " + selector + " falló: " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println("Error en intento de botones: " + e.getMessage());
            }

            // Si aún no se ha cerrado, intentar enfoque más directo
            try {
                String directScript =
                        "// Ocultar elementos de popup comunes\n" +
                                "var popupSelectors = ['.modal', '.popup', '[role=dialog]', '.save-password-form'];\n" +
                                "for (var i = 0; i < popupSelectors.length; i++) {\n" +
                                "    var elements = document.querySelectorAll(popupSelectors[i]);\n" +
                                "    for (var j = 0; j < elements.length; j++) {\n" +
                                "        elements[j].style.display = 'none';\n" +
                                "    }\n" +
                                "}\n" +
                                "\n" +
                                "// Deshabilitar la API de credenciales\n" +
                                "if (navigator.credentials && navigator.credentials.preventSilentAccess) {\n" +
                                "    navigator.credentials.preventSilentAccess();\n" +
                                "}\n" +
                                "\n" +
                                "return 'Popup manejado con enfoque directo';";

                Object result = ((JavascriptExecutor) driver).executeScript(directScript);
                System.out.println("Resultado enfoque directo: " + result);
            } catch (Exception e) {
                System.out.println("Error en enfoque directo: " + e.getMessage());
            }

            System.out.println("PopupHandler finalizado (método seguro)");
        } catch (Exception e) {
            System.out.println("⚠Error crítico en PopupHandler: " + e.getMessage());
            // No relanzar la excepción para evitar que detenga el flujo
        }
    }
}