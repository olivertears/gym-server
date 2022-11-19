package com.gym.repository.impl;

import com.gym.entity.Operation;
import com.gym.repository.OperationService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationServiceImpl implements OperationService {
    @Override
    public boolean createOperation(Operation operation) {
        String sql = "INSERT INTO operation (categoryId, price) VALUES (?, ?)";

        int categoryId = operation.getCategoryId();
        double price = operation.getPrice();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            statement.setDouble(2, price);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateOperation(Operation operation) {
        String sql = "UPDATE operation SET categoryId = ?, price = ? WHERE id = ?";

        int id = operation.getId();
        int categoryId = operation.getCategoryId();
        double price = operation.getPrice();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            statement.setDouble(2, price);
            statement.setInt(3, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteOperation(int id) {
        String sql = "DELETE FROM operation WHERE id = ?";

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
    public List<Operation> getOperations() {
        List<Operation> operations = new ArrayList<>();
        String sql = "SELECT * FROM operation ORDER BY id";
//        SELECT * FROM orders WHERE order_delivery BETWEEN '2007-12-01' AND '2008-01-01'

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Operation operation = new Operation();
                operation.setId(resultSet.getInt("id"));
                operation.setCategoryId(resultSet.getInt("categoryId"));
                operation.setPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return operations;
    }
}
