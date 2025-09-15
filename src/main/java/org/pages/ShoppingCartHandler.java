package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ShoppingCartHandler {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ShoppingCartHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openCart() {
        try {
            System.out.println("Abriendo carrito...");
            WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(
                    By.className("shopping_cart_link")
            ));
            cart.click();
            System.out.println("Carrito abierto");
        } catch (Exception e) {
            System.out.println("Error al abrir el carrito: " + e.getMessage());
        }
    }

    public void proceedToCheckout() {
        try {
            System.out.println("Procediendo al checkout...");

            // Hacer clic en el botón de checkout
            WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("checkout")
            ));
            checkoutBtn.click();
            System.out.println("Checkout iniciado");
        } catch (Exception e) {
            System.out.println("Error al proceder al checkout: " + e.getMessage());
        }
    }

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        try {
            System.out.println("Llenando información de checkout...");


            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("checkout_info")
            ));


            WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("first-name")
            ));
            firstNameField.clear();
            firstNameField.sendKeys(firstName);
            System.out.println("Nombre completado: " + firstName);

            WebElement lastNameField = driver.findElement(By.id("last-name"));
            lastNameField.clear();
            lastNameField.sendKeys(lastName);
            System.out.println("Apellido completado: " + lastName);


            WebElement postalCodeField = driver.findElement(By.id("postal-code"));
            postalCodeField.clear();
            postalCodeField.sendKeys(postalCode);
            System.out.println("Código postal completado: " + postalCode);


            Thread.sleep(500);

        } catch (Exception e) {
            System.out.println("Error al llenar información de checkout: " + e.getMessage());
        }
    }

    public void continueToOverview() {
        try {
            System.out.println("Continuando a resumen de compra...");


            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("continue")
            ));
            continueBtn.click();
            System.out.println("Continuado a resumen de compra");

        } catch (Exception e) {
            System.out.println("Error al continuar al resumen: " + e.getMessage());
        }
    }

    public void finishCheckout() {
        try {
            System.out.println("🏁 Finalizando compra...");

            // Hacer clic en el botón Finish
            WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("finish")
            ));
            finishBtn.click();
            System.out.println("Compra finalizada");

        } catch (Exception e) {
            System.out.println("Error al finalizar la compra: " + e.getMessage());
        }
    }

    public boolean verifyOrderCompletion() {
        try {
            System.out.println("Verificando finalización de la orden...");

            // Verificar mensaje de éxito
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("complete-header")
            ));

            String message = successMessage.getText();
            System.out.println("Mensaje de confirmación: " + message);

            return message.contains("Thank you for your order");

        } catch (Exception e) {
            System.out.println("No se pudo verificar la finalización de la orden: " + e.getMessage());
            return false;
        }
    }

    public void completeFullCheckoutProcess(String firstName, String lastName, String postalCode) {
        try {
            openCart();
            Thread.sleep(1000);

            proceedToCheckout();
            Thread.sleep(1000);

            fillCheckoutInformation(firstName, lastName, postalCode);
            Thread.sleep(500);

            continueToOverview();
            Thread.sleep(2000);

            finishCheckout();
            Thread.sleep(2000);

            if (verifyOrderCompletion()) {
                System.out.println("🎉 ¡Proceso de compra completado exitosamente!");
            } else {
                System.out.println("⚠️ El proceso se completó pero no se pudo verificar el éxito");
            }

        } catch (Exception e) {
            System.out.println("❌ Error en el proceso completo de checkout: " + e.getMessage());
        }
    }

    public void backToProducts() {
        try {
            System.out.println("↩️ Volviendo a la página de productos...");

            WebElement backBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("back-to-products")
            ));
            backBtn.click();
            System.out.println("✅ Regresado a la página de productos");

        } catch (Exception e) {
            System.out.println("❌ Error al volver a productos: " + e.getMessage());
        }
    }
}