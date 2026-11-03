
import javax.swing.*;
import java.awt.*;

public class DeleteProduct extends JFrame {

    private JTextField idField;

    public DeleteProduct() {
        setTitle("Delete Product");
        setSize(400, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Product ID:"));
        idField = new JTextField();
        panel.add(idField);

        JButton deleteButton = new JButton("Delete Product");
        JButton clearButton = new JButton("Clear");

        panel.add(deleteButton);
        panel.add(clearButton);

        add(panel);

        deleteButton.addActionListener(e -> deleteProduct());
        clearButton.addActionListener(e -> idField.setText(""));
    }

    private void deleteProduct() {
        try {
            int id = Integer.parseInt(idField.getText().trim());

            if (id <= 0) {
                JOptionPane.showMessageDialog(this,
                    "Product ID must be greater than zero.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete product ID " + id + "?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            ProductDAO dao = new ProductDAO();
            boolean success = dao.deleteProduct(id);

            if (success) {
                JOptionPane.showMessageDialog(this,
                    "Product deleted successfully!");
                idField.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Product not found or deletion failed.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid numeric Product ID.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new DeleteProduct().setVisible(true));
    }
}