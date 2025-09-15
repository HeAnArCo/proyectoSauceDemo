package org.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddToCartHandler {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public AddToCartHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addTwoProducts() {
        try {
            System.out.println("🛒 Intentando añadir productos al carrito...");

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("inventory_list")
            ));

            System.out.println("Página de productos cargada correctamente");

            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//button[contains(text(),'Add to cart')])[1]")
            ));
            firstProduct.click();
            System.out.println("Primer producto añadido");

            Thread.sleep(500);

            WebElement secondProduct = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//button[contains(text(),'Add to cart')])[2]")
            ));
            secondProduct.click();
            System.out.println("Segundo producto añadido");

            System.out.println("2 productos añadidos al carrito correctamente");

        } catch (Exception e) {
            System.out.println("Error al añadir productos: " + e.getMessage());
            e.printStackTrace();

            tryAlternativeMethod();
        }
    }

    private void tryAlternativeMethod() {
        try {
            System.out.println("🔄 Intentando método alternativo...");

            String[] productIds = {
                    "add-to-cart-sauce-labs-backpack",
                    "add-to-cart-sauce-labs-bike-light"
            };

            for (String id : productIds) {
                try {
                    WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
                            By.id(id)
                    ));
                    product.click();
                    System.out.println("Producto añadido (método alternativo): " + id);
                    Thread.sleep(300);
                } catch (Exception e) {
                    System.out.println("⚠️ No se pudo añadir producto con ID: " + id);
                }
            }

        } catch (Exception e) {
            System.out.println("Método alternativo también falló: " + e.getMessage());
        }
    }

    public boolean verifyProductsAdded() {
        try {
            WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("shopping_cart_badge")
            ));
            String count = cartBadge.getText();
            System.out.println("🛒 Número de productos en el carrito: " + count);
            return Integer.parseInt(count) >= 2;
        } catch (Exception e) {
            System.out.println("⚠️ No se pudo verificar el carrito: " + e.getMessage());
            return false;
        }
    }
}