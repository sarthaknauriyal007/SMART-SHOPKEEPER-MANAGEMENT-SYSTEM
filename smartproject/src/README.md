# Smart Shopkeeper Management System

## Project Overview

The Smart Shopkeeper Management System is a Java-based desktop application developed to help shopkeepers manage product information efficiently. It uses MySQL to store product details and JDBC to connect Java with the database.

## Team Members

- Saumya Chandra — RA2511056030003
- Niraj Patel — RA2511056030004
- Ujjwal — RA2511056030015

**Branch:** CSE (Data Science)  
**College:** SRM IST, Ghaziabad

## Features

- **Add Product:** Store new product details.
- **Update Product:** Update product quantity and price.
- **Delete Product:** Remove a product using its ID.
- **Search Product:** Find products by name.
- **Check Low Stock:** Identify products with a quantity of 5 or less.
- **Check Expiry Date:** Find expired products and products expiring within the next 30 days.
- **View All Products:** Display all stored products in a table.

## Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- SQL
- Visual Studio Code

## Database Structure

**Database name:** `smart_shopkeeper_db`

**Table name:** `products`

| Column | Description |
|---|---|
| product_id | Unique product ID generated automatically |
| product_name | Name of the product |
| quantity | Available quantity |
| price | Product price |
| expiry_date | Product expiry date |

## Project Setup

1. Install JDK 21.
2. Install MySQL Server and MySQL Workbench.
3. Open `database/schema.sql` in MySQL Workbench and execute the SQL statements.
4. Download MySQL Connector/J and place its JAR file in the `lib` folder.
5. Add the JAR file to the Java project classpath.
6. Open `DatabaseConnection.java` and configure the local MySQL username and password.
7. Run `Main.java` to open the application dashboard.

## Application Workflow

Enter Product Details → Store in Database → Search and Manage Products → Check Stock and Expiry → Display Information

## Conclusion

The Smart Shopkeeper Management System helps shopkeepers organize product information, update stock details, search for products, and monitor low stock and expiry dates. It reduces the effort required for manual product management.