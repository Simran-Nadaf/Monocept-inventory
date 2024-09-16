package com.techlabs.inventorymanagement.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.techlabs.inventorymanagement.model.Product;
import com.techlabs.inventorymanagement.model.Supplier;
import com.techlabs.inventorymanagement.model.Transaction;

public class FileManager {
    private static final String PRODUCTS_FILE = "products.txt";
    private static final String SUPPLIERS_FILE = "suppliers.txt";
    private static final String TRANSACTIONS_FILE = "transactions.txt";

    public static void saveProducts(List<Product> products) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PRODUCTS_FILE))) {
            oos.writeObject(products);
        } catch (IOException e) {
            System.err.println("Error saving products: " + e.getMessage());
        }
    }

    public static List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRODUCTS_FILE))) {
            products = (List<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading products: " + e.getMessage());
        }
        return products;
    }

    public static void saveSuppliers(List<Supplier> suppliers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SUPPLIERS_FILE))) {
            oos.writeObject(suppliers);
        } catch (IOException e) {
            System.err.println("Error saving suppliers: " + e.getMessage());
        }
    }

    public static List<Supplier> loadSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SUPPLIERS_FILE))) {
            suppliers = (List<Supplier>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading suppliers: " + e.getMessage());
        }
        return suppliers;
    }

    public static void saveTransactions(List<Transaction> transactions) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRANSACTIONS_FILE))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            System.err.println("Error saving transactions: " + e.getMessage());
        }
    }

    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TRANSACTIONS_FILE))) {
            transactions = (List<Transaction>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading transactions: " + e.getMessage());
        }
        return transactions;
    }
}

