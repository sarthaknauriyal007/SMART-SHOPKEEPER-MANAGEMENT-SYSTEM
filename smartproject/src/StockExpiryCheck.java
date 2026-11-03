import javax.swing.*;

import src.DatabaseConnection;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockExpiryCheck extends JFrame {

    private JTextArea resultArea;

    public StockExpiryCheck() {
        setTitle("Low Stock Check");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JButton checkButton = new JButton("Check Low Stock");

        add(checkButton, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        checkButton.addActionListener(e -> checkLowStock());

        checkLowStock();
    }

    private void checkLowStock() {
        resultArea.setText("");

        String sql = "SELECT product_id, product_name, quantity, price " +
                     "FROM products WHERE quantity <= 5 " +
                     "ORDER BY quantity ASC";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            resultArea.append("LOW STOCK PRODUCTS (Quantity <= 5)\n");
            resultArea.append("----------------------------------------\n");

            while (rs.next()) {
                found = true;

                resultArea.append(
                    "ID: " + rs.getInt("product_id") +
                    " | Name: " + rs.getString("product_name") +
                    " | Quantity: " + rs.getInt("quantity") +
                    " | Price: " + rs.getDouble("price") + "\n"
                );
            }

            if (!found) {
                resultArea.append("No low-stock products found.\n");
            }

        } catch (SQLException e) {
            resultArea.setText("Database error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new StockExpiryCheck().setVisible(true));
    }
}