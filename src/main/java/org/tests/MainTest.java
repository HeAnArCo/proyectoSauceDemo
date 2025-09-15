package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 1) WebDriverManager (evita manejar chromedriver manualmente)
        WebDriverManager.chromedriver().setup();

        // 2) ChromeOptions: intentar evitar popup nativo de guardar contraseñas
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--incognito"); // opcional: reduce uso de perfil con contraseñas guardadas

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();

            // 3) PopupHandler: JS attempt + fallback Robot(ESC)
            PopupHandler popupHandler = new PopupHandler(driver);
            popupHandler.closePasswordPopup();

            // 4) Login (Java)
            LoginHandler loginHandler = new LoginHandler(driver);
            loginHandler.login("standard_user", "secret_sauce");

            // 5) Add to cart (Java)
            AddToCartHandler addHandler = new AddToCartHandler(driver);
            addHandler.addTwoProductsAndAcceptIfAlert();

            // 6) Quedamos con navegador abierto para que observes (ajusta tiempo a tu preferencia)
            System.out.println("🚀 Flujo finalizado. El navegador queda abierto 10s para inspección...");
            Thread.sleep(10_000);

            System.out.println("✅ Proceso completado (no cerrado automáticamente).");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Si quieres que NO se cierre, comenta la siguiente línea.
            driver.quit();
            System.out.println("🔒 Navegador cerrado por Main (comenta driver.quit() si no quieres cerrarlo).");
        }
    }
}
