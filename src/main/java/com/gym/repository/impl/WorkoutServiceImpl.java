package com.gym.repository.impl;

import com.gym.dto.WorkoutTimeDto;
import com.gym.entity.Workout;
import com.gym.repository.WorkoutService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkoutServiceImpl implements WorkoutService {
    @Override
    public boolean createWorkout(Workout workout) {
        String sql = "INSERT INTO workout (clientId, coachId, price, date, time) VALUES (?, ?, ?, ?, ?)";

        int clientId = workout.getClientId();
        int coachId = workout.getCoachId();
        double price = workout.getPrice();
        LocalDate date = workout.getDate();
        String time = workout.getTime();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, clientId);
            statement.setInt(2, coachId);
            statement.setDouble(3, price);
            statement.setDate(4, Date.valueOf(date));
            statement.setString(5, time);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateWorkout(Workout workout) {
        String sql = "UPDATE workout SET coachId = ?, price = ?, date = ?, time = ? WHERE id = ?";

        int id = workout.getId();
        int coachId = workout.getCoachId();
        double price = workout.getPrice();
        LocalDate date = workout.getDate();
        String time = workout.getTime();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, coachId);
            statement.setDouble(2, price);
            statement.setDate(3, Date.valueOf(date));
            statement.setString(4, time);
            statement.setInt(5, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateClientWorkoutsToPremiumPrice(int clientId) {
        String sql = "UPDATE workout SET price = price * 0.8 WHERE clientId = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, clientId);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean setWorkoutDone(int id) {
        String sql = "UPDATE workout SET done = 1 WHERE id = ?";

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
    public boolean deleteClientWorkouts(int clientId) {
        String sql = "DELETE FROM workout WHERE clientId = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, clientId);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCoachWorkouts(int coachId) {
        String sql = "DELETE FROM workout WHERE coachId = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, coachId);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Workout> getClientWorkouts(int clientId) {
        List<Workout> workouts = new ArrayList<>();
        String sql = "SELECT * FROM workout WHERE clientId = ? AND date > CURDATE() OR (date = CURDATE() AND time > CURTIME()) ORDER BY date, time";

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
                workout.setDate((resultSet.getDate("date")).toLocalDate());
                workout.setTime((resultSet.getString("time")));

                workout.setCoach(UserServiceImpl.getUserFullName(workout.getCoachId()));

                workouts.add(workout);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return workouts;
    }

    @Override
    public List<Workout> getCoachWorkouts(int coachId) {
        List<Workout> workouts = new ArrayList<>();
        String sql = "SELECT * FROM workout WHERE coachId = ? AND done = 0 ORDER BY date, time";

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
                workout.setDate((resultSet.getDate("date")).toLocalDate());
                workout.setTime((resultSet.getString("time")));

                workout.setClient(UserServiceImpl.getUserFullName(workout.getClientId()));

                workouts.add(workout);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return workouts;
    }

    @Override
    public List<String> getCoachDateWorkoutTimes(WorkoutTimeDto workoutTimeDto) {
        List<String> times = new ArrayList<>();
        String sql = "SELECT * FROM workout WHERE coachId = ? AND date = ? ORDER BY time";

        int coachId = workoutTimeDto.getCoachId();
        LocalDate date = workoutTimeDto.getDate();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, coachId);
            statement.setDate(2, Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                times.add((resultSet.getString("time")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return times;
    }
}
