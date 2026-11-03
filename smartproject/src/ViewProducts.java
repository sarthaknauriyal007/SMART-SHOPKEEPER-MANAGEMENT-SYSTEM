import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.Product;

import java.awt.*;
import java.util.List;

public class ViewProducts extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewProducts() {
        setTitle("All Products");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
            "Product ID",
            "Product Name",
            "Quantity",
            "Price",
            "Expiry Date"
        };

        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(25);

        JButton refreshButton = new JButton("Refresh");

        refreshButton.addActionListener(e -> loadProducts());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);

        loadProducts();
    }

    private void loadProducts() {
        model.setRowCount(0);

        List<Product> products = new ProductDAO().getAllProducts();

        for (Product p : products) {
            model.addRow(new Object[] {
                p.getProductId(),
                p.getProductName(),
                p.getQuantity(),
                p.getPrice(),
                p.getExpiryDate() == null
                    ? "N/A"
                    : p.getExpiryDate()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new ViewProducts().setVisible(true)
        );
    }
}