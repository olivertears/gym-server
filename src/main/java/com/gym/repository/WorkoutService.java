package com.gym.repository;

import com.gym.DBConnection;

import java.sql.Connection;

public interface WorkoutService {
    Connection connection = DBConnection.getConnection();



}
