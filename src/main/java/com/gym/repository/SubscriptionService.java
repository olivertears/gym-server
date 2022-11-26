package com.gym.repository;

import com.gym.DBConnection;
import com.gym.entity.Subscription;

import java.sql.Connection;

public interface SubscriptionService {
    Connection connection = DBConnection.getConnection();

    boolean createSubscription(Subscription subscription);
    boolean updateSubscriptionToPremium(int id);
    boolean deleteSubscription(int id);
    Subscription getUserSubscription(int userId);
}
