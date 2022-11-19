package com.gym.repository.impl;

import com.gym.entity.Workout;
import com.gym.repository.WorkoutService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutServiceImpl implements WorkoutService {

    @Override
    public boolean createWorkout(Workout workout) {
        String sql = "INSERT INTO workout (clientId, coachId, price, start) VALUES (?, ?, ?, ?)";

        int clientId = workout.getClientId();
        int coachId = workout.getCoachId();
        double price = workout.getPrice();
        Timestamp start = workout.getStart();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, clientId);
            statement.setInt(2, coachId);
            statement.setDouble(3, price);
            statement.setTimestamp(4, start);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateWorkout(Workout workout) {
        String sql = "UPDATE workout SET coachId = ?, price = ?, start = ? WHERE id = ?";

        int id = workout.getId();
        int coachId = workout.getCoachId();
        double price = workout.getPrice();
        Timestamp start = workout.getStart();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, coachId);
            statement.setDouble(2, price);
            statement.setTimestamp(3, start);
            statement.setInt(4, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteWorkout(int id) {
        String sql = "DELETE FROM workout WHERE id = ?";

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
    public List<Workout> getClientWorkouts(int clientId) {
        List<Workout> workouts = new ArrayList<>();
        String sql = "SELECT * FROM workout WHERE clientId = ? AND start > CURTIME() ORDER BY start";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Workout workout = new Workout();
                workout.setId(resultSet.getInt("id"));
                workout.setClientId(resultSet.getInt("clientId"));
                workout.setCoachId(resultSet.getInt("coachId"));
                workout.setPrice(resultSet.getDouble("price"));
                workout.setStart((resultSet.getTimestamp("start")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return workouts;
    }

    @Override
    public List<Workout> getCoachWorkouts(int coachId) {
        List<Workout> workouts = new ArrayList<>();
        String sql = "SELECT * FROM workout WHERE coachId = ? AND done = 0 ORDER BY start";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, coachId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Workout workout = new Workout();
                workout.setId(resultSet.getInt("id"));
                workout.setClientId(resultSet.getInt("clientId"));
                workout.setCoachId(resultSet.getInt("coachId"));
                workout.setDone(resultSet.getBoolean("done"));
                workout.setPrice(resultSet.getDouble("price"));
                workout.setStart((resultSet.getTimestamp("start")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return workouts;
    }
}
