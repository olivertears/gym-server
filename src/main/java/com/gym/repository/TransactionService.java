package com.gym.repository;

import com.gym.DBConnection;
import com.gym.entity.Transaction;

import java.sql.Connection;
import java.util.List;

public interface TransactionService {
    Connection connection = DBConnection.getConnection();

    boolean createTransaction(Transaction transaction);
    boolean updateTransaction(Transaction transaction);
    boolean deleteTransaction(int id);
    List<Transaction> getTransactions();
}
