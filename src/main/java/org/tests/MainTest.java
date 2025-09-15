package org.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.pages.LoginHandler;
import org.pages.PopupHandler;
import org.pages.AddToCartHandler;
import org.pages.ShoppingCartHandler;

public class MainTest {
    public static void main(String[] args) {
        // Se configura chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-autofill-keyboard-accessory-view");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-web-security");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = null;
        try {
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            System.out.println("Navegador iniciado correctamente");

            driver.get("https://www.saucedemo.com/");
            System.out.println("Página cargada: " + driver.getTitle());

            System.out.println("Iniciando proceso de login...");
            LoginHandler loginHandler = new LoginHandler(driver);
            loginHandler.login("standard_user", "secret_sauce");
            System.out.println("Login completado");
            Thread.sleep(2000);


            System.out.println("🪟 Intentando manejar popup...");
            try {
                PopupHandler popupHandler = new PopupHandler(driver);
                popupHandler.closePasswordPopup();
                System.out.println("Popup manejado");
            } catch (Exception e) {
                System.out.println("No se pudo manejar el popup, pero continuando: " + e.getMessage());
            }

            System.out.println("🛒 Añadiendo productos al carrito...");
            AddToCartHandler addToCartHandler = new AddToCartHandler(driver);
            addToCartHandler.addTwoProducts();

            if (addToCartHandler.verifyProductsAdded()) {
                System.out.println("Verificación exitosa: Los productos fueron añadidos al carrito");
            } else {
                System.out.println("Advertencia: No se pudieron añadir los productos al carrito");
            }

            Thread.sleep(1000);

            System.out.println("📦 Abriendo carrito...");
            ShoppingCartHandler cartHandler = new ShoppingCartHandler(driver);
            cartHandler.completeFullCheckoutProcess("Juan", "Pérez", "28001");
            System.out.println("Carrito abierto");

            System.out.println("⏳ Esperando 5 segundos antes de cerrar...");
            Thread.sleep(5000);

        } catch (Exception e) {
            System.out.println("❌ Error durante la ejecución: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                System.out.println("🛑 Cerrando navegador...");
                driver.quit();
                System.out.println("👋 Ejecución finalizada");
            }
        }
    }
}