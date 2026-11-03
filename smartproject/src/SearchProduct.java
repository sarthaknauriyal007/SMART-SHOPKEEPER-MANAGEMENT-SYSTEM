import javax.swing.*;

import src.Product;

import java.awt.*;
import java.util.List;

public class SearchProduct extends JFrame {

    private JTextField nameField;
    private JTextArea resultArea;

    public SearchProduct() {
        setTitle("Search Product");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));

        nameField = new JTextField();
        JButton searchButton = new JButton("Search");

        topPanel.add(new JLabel("Product Name:"), BorderLayout.WEST);
        topPanel.add(nameField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        searchButton.addActionListener(e -> searchProduct());

        nameField.addActionListener(e -> searchProduct());
    }

    private void searchProduct() {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a product name.");
            return;
        }

        ProductDAO dao = new ProductDAO();
        List<Product> products = dao.searchProduct(name);

        resultArea.setText("");

        if (products.isEmpty()) {
            resultArea.setText("No products found.");
            return;
        }

        for (Product p : products) {
            resultArea.append(
                "Product ID : " + p.getProductId() + "\n" +
                "Name       : " + p.getProductName() + "\n" +
                "Quantity   : " + p.getQuantity() + "\n" +
                "Price      : " + p.getPrice() + "\n" +
                "Expiry Date: " + p.getExpiryDate() + "\n" +
                "----------------------------------\n"
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new SearchProduct().setVisible(true));
    }
}