package com.gym.repository;

import com.gym.DBConnection;
import com.gym.dto.WorkoutTimeDto;
import com.gym.entity.Subscription;
import com.gym.entity.Workout;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface WorkoutService {
    Connection connection = DBConnection.getConnection();

    boolean createWorkout(Workout workout);
    boolean updateWorkout(Workout workout);
    boolean updateClientWorkoutsToPremiumPrice(int clientId);
    boolean setWorkoutDone(int id);
    boolean deleteWorkout(int id);
    boolean deleteClientWorkouts(int clientId);
    boolean deleteCoachWorkouts(int coachId);
    List<Workout> getClientWorkouts(int clientId);
    List<Workout> getCoachWorkouts(int coachId);
    List<String> getCoachDateWorkoutTimes(WorkoutTimeDto workoutTimeDto);
}
