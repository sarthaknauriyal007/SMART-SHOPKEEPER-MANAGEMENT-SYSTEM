import javax.swing.*;
import java.awt.*;

public class UpdateProduct extends JFrame {

    private JTextField idField;
    private JTextField quantityField;
    private JTextField priceField;

    public UpdateProduct() {
        setTitle("Update Product");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Product ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("New Quantity:"));
        quantityField = new JTextField();
        panel.add(quantityField);

        panel.add(new JLabel("New Price:"));
        priceField = new JTextField();
        panel.add(priceField);

        JButton updateButton = new JButton("Update Product");
        panel.add(updateButton);

        JButton clearButton = new JButton("Clear");
        panel.add(clearButton);

        add(panel);

        updateButton.addActionListener(e -> updateProduct());
        clearButton.addActionListener(e -> clearFields());
    }

    private void updateProduct() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            if (id <= 0 || quantity < 0 || price < 0) {
                JOptionPane.showMessageDialog(this,
                    "ID must be positive; quantity and price cannot be negative.");
                return;
            }

            ProductDAO dao = new ProductDAO();
            boolean success = dao.updateProduct(id, quantity, price);

            if (success) {
                JOptionPane.showMessageDialog(this,
                    "Product updated successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Product not found or update failed.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Enter valid numbers in all fields.");
        }
    }

    private void clearFields() {
        idField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new UpdateProduct().setVisible(true));
    }
}