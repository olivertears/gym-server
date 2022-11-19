package com.gym.repository.impl;

import com.gym.entity.Subscription;
import com.gym.repository.SubscriptionService;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public boolean createSubscription(Subscription subscription) {
        String sql = "INSERT INTO subscription (userId, type, price, start, end) VALUES (?, ?, ?, ?, ?)";

        int userId = subscription.getUserId();
        String type = subscription.getType();
        double price = subscription.getPrice();
        LocalDate start = subscription.getStart();
        LocalDate end = subscription.getEnd();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setString(2, type);
            statement.setDouble(3, price);
            statement.setDate(4, Date.valueOf(start));
            statement.setDate(5, Date.valueOf(end));
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Subscription getUserSubscription(int userId) {
        Subscription subscription = new Subscription();
        String sql = "SELECT * FROM subscription WHERE userId = ? AND end <= CURDATE()";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    subscription.setUserId(resultSet.getInt("userId"));
                    subscription.setType(resultSet.getString("type"));
                    subscription.setPrice(resultSet.getDouble("price"));
                    subscription.setStart(resultSet.getDate("start").toLocalDate());
                    subscription.setEnd(resultSet.getDate("end").toLocalDate());
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subscription;
    }
}