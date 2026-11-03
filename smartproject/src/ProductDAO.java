import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.DatabaseConnection;
import src.Product;

public class ProductDAO {

    // Add a new product
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO products " +
                "(product_name, quantity, price, expiry_date) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getQuantity());
            ps.setDouble(3, product.getPrice());

            if (product.getExpiryDate() == null) {
                ps.setNull(4, java.sql.Types.DATE);
            } else {
                ps.setDate(4,
                    java.sql.Date.valueOf(product.getExpiryDate()));
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
            return false;
        }
    }

    // Display all products
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                java.sql.Date date = rs.getDate("expiry_date");

                products.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    date == null ? null : date.toLocalDate()
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching products: " + e.getMessage());
        }

        return products;
    }

    // Search products by name
    public List<Product> searchProduct(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE product_name LIKE ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    java.sql.Date date = rs.getDate("expiry_date");

                    products.add(new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        date == null ? null : date.toLocalDate()
                    ));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching product: " + e.getMessage());
        }

        return products;
    }

    // Update quantity and price
    public boolean updateProduct(int id, int quantity, double price) {
        String sql = "UPDATE products SET quantity = ?, price = ? " +
                     "WHERE product_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setDouble(2, price);
            ps.setInt(3, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
            return false;
        }
    }

    // Delete a product
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE product_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
            return false;
        }
    }
}