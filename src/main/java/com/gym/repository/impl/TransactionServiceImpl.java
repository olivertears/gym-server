package com.gym.repository.impl;

import com.gym.entity.Transaction;
import com.gym.repository.TransactionService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public boolean createTransaction(Transaction transaction) {
        String sql = "INSERT INTO transaction (categoryName, price, description) VALUES (?, ?, ?)";

        String categoryName = transaction.getCategoryName();
        double price = transaction.getPrice();
        String description = transaction.getDescription();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, categoryName);
            statement.setDouble(2, price);
            statement.setString(3, description);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateTransaction(Transaction transaction) {
        String sql = "UPDATE transaction SET categoryName = ?, price = ?, description = ? WHERE id = ?";

        int id = transaction.getId();
        String categoryName = transaction.getCategoryName();
        double price = transaction.getPrice();
        String description = transaction.getDescription();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, categoryName);
            statement.setDouble(2, price);
            statement.setString(3, description);
            statement.setInt(4, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteTransaction(int id) {
        String sql = "DELETE FROM transaction WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction ORDER BY id";
//        SELECT * FROM orders WHERE order_delivery BETWEEN '2007-12-01' AND '2008-01-01'

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setCategoryName(resultSet.getString("categoryName"));
                transaction.setPrice(resultSet.getDouble("price"));
                transaction.setDescription(resultSet.getString("description"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return transactions;
    }
}
