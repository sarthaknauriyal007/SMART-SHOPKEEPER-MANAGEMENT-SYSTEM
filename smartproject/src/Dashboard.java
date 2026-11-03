import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {
        getContentPane().setBackground(new Color(245, 248, 252));
        setTitle("Smart Shopkeeper Management System");
        setSize(650, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel heading = new JLabel(
            "SMART SHOPKEEPER MANAGEMENT SYSTEM",
            SwingConstants.CENTER
        );
        heading.setFont(new Font("Arial", Font.BOLD, 20));

        JButton addButton = new JButton("Add Product");
        JButton updateButton = new JButton("Update Product");
        JButton deleteButton = new JButton("Delete Product");
        JButton searchButton = new JButton("Search Product");
        JButton stockButton = new JButton("Check Low Stock");
        JButton expiryButton = new JButton("Check Expiry Date");
        JButton viewButton = new JButton("View All Products");
        JButton exitButton = new JButton("Exit");

        addButton.addActionListener(e -> {
            AddProduct form = new AddProduct();
            form.setVisible(true);});
        updateButton.addActionListener(e ->
            new UpdateProduct().setVisible(true));

        deleteButton.addActionListener(e ->
            new DeleteProduct().setVisible(true));

        searchButton.addActionListener(e ->
            new SearchProduct().setVisible(true));

        stockButton.addActionListener(e ->
            new StockExpiryCheck().setVisible(true));

        expiryButton.addActionListener(e ->
            new StockExpiryCheck().setVisible(true));

        viewButton.addActionListener(e ->
            new ViewProducts().setVisible(true));
            setLayout(new BorderLayout(10, 10));
            add(heading, BorderLayout.NORTH);
            add(panel, BorderLayout.CENTER);
        exitButton.addActionListener(e -> {
        int choice = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to exit?",
        "Confirm Exit",
        JOptionPane.YES_NO_OPTION );

         if (choice == JOptionPane.YES_OPTION) {
        System.exit(0);
    }});

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(searchButton);
        panel.add(stockButton);
        panel.add(expiryButton);
        panel.add(viewButton);
        panel.add(exitButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
        });
    }
}