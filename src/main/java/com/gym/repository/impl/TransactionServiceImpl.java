package com.gym.repository.impl;

import com.gym.dto.TransactionFilterDto;
import com.gym.entity.Transaction;
import com.gym.repository.TransactionService;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public boolean createTransaction(Transaction transaction) {
        String sql = "INSERT INTO transaction (categoryName, price, description, date) VALUES (?, ?, ?, ?)";

        String categoryName = transaction.getCategoryName();
        double price = transaction.getPrice();
        String description = transaction.getDescription();
        LocalDate date = transaction.getDate();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, categoryName);
            statement.setDouble(2, price);
            statement.setString(3, description);
            statement.setDate(4, Date.valueOf(date));
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateTransaction(Transaction transaction) {
        String sql = "UPDATE transaction SET categoryName = ?, price = ?, description = ?, date = ? WHERE id = ?";

        int id = transaction.getId();
        String categoryName = transaction.getCategoryName();
        double price = transaction.getPrice();
        String description = transaction.getDescription();
        LocalDate date = transaction.getDate();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, categoryName);
            statement.setDouble(2, price);
            statement.setString(3, description);
            statement.setDate(4, Date.valueOf(date));
            statement.setInt(5, id);
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
    public List<Transaction> getTransactions(TransactionFilterDto transactionFilterDto) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE date BETWEEN ? AND ? ORDER BY date DESC, id DESC";

        LocalDate start = transactionFilterDto.getStart();
        LocalDate end = transactionFilterDto.getEnd();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(start));
            statement.setDate(2, Date.valueOf(end));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setCategoryName(resultSet.getString("categoryName"));
                transaction.setPrice(resultSet.getDouble("price"));
                transaction.setDescription(resultSet.getString("description"));
                transaction.setDate(resultSet.getDate("date").toLocalDate());
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return transactions;
    }
}
