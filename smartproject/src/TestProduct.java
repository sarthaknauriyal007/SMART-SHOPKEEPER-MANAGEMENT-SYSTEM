
import java.time.LocalDate;

import src.Product;

public class TestProduct {
    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();

        Product product = new Product(
            0,
            "Milk",
            20,
            30.00,
            LocalDate.of(2026, 11, 5)
        );

        boolean success = dao.addProduct(product);

        if (success) {
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Failed to add product.");
        }

        System.out.println("\nAll Products:");

        for (Product p : dao.getAllProducts()) {
            System.out.println(
                "ID: " + p.getProductId()
                + " | Name: " + p.getProductName()
                + " | Quantity: " + p.getQuantity()
                + " | Price: " + p.getPrice()
                + " | Expiry: " + p.getExpiryDate()
            );
        }
    }
}