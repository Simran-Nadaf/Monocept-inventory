package com.techlabs.inventorymanagement;



import java.util.Date;
import java.util.Scanner;

import com.techlabs.inventorymanagement.data.FileManager;
import com.techlabs.inventorymanagement.data.Inventory;
import com.techlabs.inventorymanagement.model.Product;
import com.techlabs.inventorymanagement.model.Supplier;
import com.techlabs.inventorymanagement.model.Transaction;
import com.techlabs.inventorymanagement.service.IDGenerator;

public class InventoryManagementSystem {
    private static Inventory inventory = new Inventory();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    manageProducts();
                    break;
                case 2:
                    manageSuppliers();
                    break;
                case 3:
                    manageTransactions();
                    break;
                case 4:
                    saveData();
                    break;
                case 5:
                    loadData();
                    break;
                case 6:
                    generateReports();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("Welcome to the Inventory Management System");
        System.out.println("1. Product Management");
        System.out.println("2. Supplier Management");
        System.out.println("3. Transaction Management");
        System.out.println("4. Save Data");
        System.out.println("5. Load Data");
        System.out.println("6. Generate Reports");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void manageProducts() {
        System.out.println("Product Management");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. View Product Details");
        System.out.println("5. View All Products");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    viewProductDetails();
                    break;
                case 5:
                    viewAllProducts();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void manageSuppliers() {
        System.out.println("Supplier Management");
        System.out.println("1. Add Supplier");
        System.out.println("2. Update Supplier");
        System.out.println("3. Delete Supplier");
        System.out.println("4. View Supplier Details");
        System.out.println("5. View All Suppliers");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    addSupplier();
                    break;
                case 2:
                    updateSupplier();
                    break;
                case 3:
                    deleteSupplier();
                    break;
                case 4:
                    viewSupplierDetails();
                    break;
                case 5:
                    viewAllSuppliers();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void manageTransactions() {
        System.out.println("Transaction Management");
        System.out.println("1. Add Stock");
        System.out.println("2. Remove Stock");
        System.out.println("3. View Transaction History");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            switch (choice) {
                case 1:
                    addStock();
                    break;
                case 2:
                    removeStock();
                    break;
                case 3:
                    viewTransactionHistory();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void saveData() {
        FileManager.saveProducts(inventory.getAllProducts());
        FileManager.saveSuppliers(inventory.getAllSuppliers());
        FileManager.saveTransactions(inventory.getAllTransactions());
        System.out.println("Data saved successfully.");
    }

    private static void loadData() {
        inventory.setProducts(FileManager.loadProducts());
        inventory.setSuppliers(FileManager.loadSuppliers());
        inventory.setTransactions(FileManager.loadTransactions());
        System.out.println("Data loaded successfully.");
    }

    private static void generateReports() {
        // Example: Generate a report of total number of products
        int totalProducts = inventory.getAllProducts().size();
        System.out.println("Total number of products: " + totalProducts);

        // Example: Generate a report of total stock value
        double totalStockValue = 0.0;
        for (Product product : inventory.getAllProducts()) {
            totalStockValue += product.getPrice() * product.getQuantity();
        }
        System.out.println("Total stock value: $" + totalStockValue);
    }

    private static void addProduct() {
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Product product = new Product(productId, name, description, quantity, price);
        inventory.addProduct(product);
        System.out.println("Product added successfully.");
    }

    private static void updateProduct() {
        System.out.print("Enter product ID to update: ");
        String productId = scanner.nextLine();
        Product existingProduct = inventory.findProductById(productId);
        if (existingProduct == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter new product quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        existingProduct.setName(name);
        existingProduct.setDescription(description);
        existingProduct.setQuantity(quantity);
        existingProduct.setPrice(price);
        System.out.println("Product updated successfully.");
    }

    private static void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        String productId = scanner.nextLine();
        Product deletedProduct = inventory.deleteProduct(productId);
        if (deletedProduct != null) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void viewProductDetails() {
        System.out.print("Enter product ID to view details: ");
        String productId = scanner.nextLine();
        Product product = inventory.findProductById(productId);
        if (product != null) {
            System.out.println("Product Details:");
            System.out.println("ID: " + product.getProductId());
            System.out.println("Name: " + product.getName());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Quantity: " + product.getQuantity());
            System.out.println("Price: $" + product.getPrice());
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void viewAllProducts() {
        System.out.println("All Products:");
        for (Product product : inventory.getAllProducts()) {
            System.out.println("ID: " + product.getProductId() +
                               ", Name: " + product.getName() +
                               ", Quantity: " + product.getQuantity() +
                               ", Price: $" + product.getPrice());
        }
    }

    private static void addSupplier() {
        System.out.print("Enter supplier ID: ");
        String supplierId = scanner.nextLine();
        System.out.print("Enter supplier name: ");
        String name = scanner.nextLine();
        System.out.print("Enter supplier contact info: ");
        String contactInfo = scanner.nextLine();

        Supplier supplier = new Supplier(supplierId, name, contactInfo);
        inventory.addSupplier(supplier);
        System.out.println("Supplier added successfully.");
    }

    private static void updateSupplier() {
        System.out.print("Enter supplier ID to update: ");
        String supplierId = scanner.nextLine();
        Supplier existingSupplier = inventory.findSupplierById(supplierId);
        if (existingSupplier == null) {
            System.out.println("Supplier not found.");
            return;
        }

        System.out.print("Enter new supplier name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new supplier contact info: ");
        String contactInfo = scanner.nextLine();

        existingSupplier.setName(name);
        existingSupplier.setContactInfo(contactInfo);
        System.out.println("Supplier updated successfully.");
    }

    private static void deleteSupplier() {
        System.out.print("Enter supplier ID to delete: ");
        String supplierId = scanner.nextLine();
        Supplier deletedSupplier = inventory.deleteSupplier(supplierId);
        if (deletedSupplier != null) {
            System.out.println("Supplier deleted successfully.");
        } else {
            System.out.println("Supplier not found.");
        }
    }

    private static void viewSupplierDetails() {
        System.out.print("Enter supplier ID to view details: ");
        String supplierId = scanner.nextLine();
        Supplier supplier = inventory.findSupplierById(supplierId);
        if (supplier != null) {
            System.out.println("Supplier Details:");
            System.out.println("ID: " + supplier.getSupplierId());
            System.out.println("Name: " + supplier.getName());
            System.out.println("Contact Info: " + supplier.getContactInfo());
        } else {
            System.out.println("Supplier not found.");
        }
    }

    private static void viewAllSuppliers() {
        System.out.println("All Suppliers:");
        for (Supplier supplier : inventory.getAllSuppliers()) {
            System.out.println("ID: " + supplier.getSupplierId() +
                               ", Name: " + supplier.getName() +
                               ", Contact Info: " + supplier.getContactInfo());
        }
    }

    private static void addStock() {
        System.out.print("Enter product ID to add stock: ");
        String productId = scanner.nextLine();
        System.out.print("Enter quantity to add: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String transactionId = IDGenerator.generateID();
        Transaction transaction = new Transaction(transactionId, productId, "add", quantity, new Date());
        inventory.addTransaction(transaction);
        System.out.println("Stock added successfully.");
    }

    private static void removeStock() {
        System.out.print("Enter product ID to remove stock: ");
        String productId = scanner.nextLine();
        System.out.print("Enter quantity to remove: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String transactionId = IDGenerator.generateID();
        Transaction transaction = new Transaction(transactionId, productId, "remove", quantity, new Date());
        inventory.addTransaction(transaction);
        System.out.println("Stock removed successfully.");
    }

    private static void viewTransactionHistory() {
        System.out.print("Enter product ID to view transaction history: ");
        String productId = scanner.nextLine();
        for (Transaction transaction : inventory.viewTransactionHistory(productId)) {
            System.out.println("Transaction ID: " + transaction.getTransactionId() +
                               ", Product ID: " + transaction.getProductId() +
                               ", Type: " + transaction.getType() +
                               ", Quantity: " + transaction.getQuantity() +
                               ", Date: " + transaction.getDate());
        }
    }
}
