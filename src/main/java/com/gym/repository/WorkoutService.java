package com.gym.repository;

import com.gym.DBConnection;
import com.gym.entity.Workout;

import java.sql.Connection;
import java.util.List;

public interface WorkoutService {
    Connection connection = DBConnection.getConnection();

    boolean createWorkout(Workout workout);
    boolean updateWorkout(Workout workout);
    boolean deleteWorkout(int id);
    List<Workout> getClientWorkouts(int clientId);
    List<Workout> getCoachWorkouts(int coachId);
}
