import javax.swing.*;

import src.Product;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddProduct extends JFrame {

    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;
    private JTextField expiryField;

    public AddProduct() {
        setTitle("Add Product");
        setSize(420, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Product Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        panel.add(quantityField);

        panel.add(new JLabel("Price:"));
        priceField = new JTextField();
        panel.add(priceField);

        panel.add(new JLabel("Expiry Date (YYYY-MM-DD):"));
        expiryField = new JTextField();
        panel.add(expiryField);

        JButton saveButton = new JButton("Save Product");
        JButton clearButton = new JButton("Clear");

        panel.add(saveButton);
        panel.add(clearButton);

        add(panel);

        saveButton.addActionListener(e -> saveProduct());
        clearButton.addActionListener(e -> clearFields());
    }

    private void saveProduct() {
        try {
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Please enter a product name.");
                return;
            }

            int quantity = Integer.parseInt(quantityField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            if (quantity < 0 || price < 0) {
                JOptionPane.showMessageDialog(this,
                    "Quantity and price cannot be negative.");
                return;
            }

            String expiryText = expiryField.getText().trim();
            LocalDate expiryDate = expiryText.isEmpty()
                ? null : LocalDate.parse(expiryText);

            Product product = new Product(
                0, name, quantity, price, expiryDate
            );

            ProductDAO dao = new ProductDAO();

            if (dao.addProduct(product)) {
                JOptionPane.showMessageDialog(this,
                    "Product added successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Product could not be added. Check the database.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Enter a valid whole number for quantity and a valid price.");
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this,
                "Use the date format YYYY-MM-DD, for example 2026-11-05.");
        }
    }

    private void clearFields() {
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
        expiryField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new AddProduct().setVisible(true));
    }
}