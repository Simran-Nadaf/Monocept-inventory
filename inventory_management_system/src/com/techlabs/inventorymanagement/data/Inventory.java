package com.techlabs.inventorymanagement.data;



import java.util.ArrayList;
import java.util.List;

import com.techlabs.inventorymanagement.model.Product;
import com.techlabs.inventorymanagement.model.Supplier;
import com.techlabs.inventorymanagement.model.Transaction;

public class Inventory {
    private List<Product> products;
    private List<Supplier> suppliers;
    private List<Transaction> transactions;

    public Inventory() {
        this.products = new ArrayList<>();
        this.suppliers = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    // Product operations
    public void addProduct(Product product) {
        products.add(product);
    }

    public Product deleteProduct(String productId) {
        Product product = findProductById(productId);
        if (product != null) {
            products.remove(product);
        }
        return product;
    }

    public Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    // Supplier operations
    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    public Supplier deleteSupplier(String supplierId) {
        Supplier supplier = findSupplierById(supplierId);
        if (supplier != null) {
            suppliers.remove(supplier);
        }
        return supplier;
    }

    public Supplier findSupplierById(String supplierId) {
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierId().equals(supplierId)) {
                return supplier;
            }
        }
        return null;
    }

    public List<Supplier> getAllSuppliers() {
        return suppliers;
    }

    // Transaction operations
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> viewTransactionHistory(String productId) {
        List<Transaction> history = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getProductId().equals(productId)) {
                history.add(transaction);
            }
        }
        return history;
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
