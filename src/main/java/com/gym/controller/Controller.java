package com.gym.controller;

import com.gym.Connection;
import com.gym.command.Action;
import com.gym.dto.*;
import com.gym.entity.*;
import com.gym.repository.*;
import com.gym.repository.impl.*;

import java.net.Socket;

public class Controller implements Runnable {
    private final Connection connection;

    public Controller(Socket socket) {
        connection = new Connection(socket);
    }

    @Override
    public void run() {
        UserService userService = new UserServiceImpl();
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        WorkoutService workoutService = new WorkoutServiceImpl();
        CategoryService categoryService = new CategoryServiceImpl();
        TransactionService transactionService = new TransactionServiceImpl();
        ReportService reportService = new ReportServiceImpl();

        while (true) {
            Action action = (Action) connection.readObject();
            switch (action) {
                case SIGNUP -> {
                    SignupDto signupDto = (SignupDto) connection.readObject();
                    if (userService.doesUserExist(signupDto.getEmail())) {
                        connection.writeObject(null);
                    } else {
                        connection.writeObject(userService.signup(signupDto));
                    }
                }
                case LOGIN -> {
                    LoginDto loginDto = (LoginDto) connection.readObject();
                    connection.writeObject(userService.login(loginDto));
                }
                case GET_USER_BY_ID -> {
                    int id = (int) connection.readObject();
                    connection.writeObject(userService.getUserById(id));
                }
                case GET_USERS -> {
                    connection.writeObject(userService.getUsers());
                }
                case GET_COACHES -> {
                    connection.writeObject(userService.getCoaches());
                }
                case UPDATE_USER -> {
                    UserDataDto userDataDto = (UserDataDto) connection.readObject();
                    connection.writeObject(userService.updateUser(userDataDto));
                }
                case UPDATE_USER_ROLE -> {
                    UserRoleDto userRoleDto = (UserRoleDto) connection.readObject();
                    connection.writeObject(userService.updateUserRole(userRoleDto));
                }
                case DELETE_USER -> {
                    int id = (int) connection.readObject();
                    connection.writeObject(userService.deleteUser(id));
                }

                case CREATE_SUBSCRIPTION -> {
                    Subscription subscription = (Subscription) connection.readObject();
                    connection.writeObject(subscriptionService.createSubscription(subscription));
                }
                case UPDATE_SUBSCRIPTION_TO_PREMIUM -> {
                    int id = (int) connection.readObject();
                    connection.writeObject(subscriptionService.updateSubscriptionToPremium(id));
                }
                case DELETE_SUBSCRIPTION -> {
                    int id = (int) connection.readObject();
                    connection.writeObject(subscriptionService.deleteSubscription(id));
                }
                case GET_USER_SUBSCRIPTION -> {
                    int userId = (int) connection.readObject();
                    connection.writeObject(subscriptionService.getUserSubscription(userId));
                }

                case CREATE_WORKOUT -> {
                    Workout workout = (Workout) connection.readObject();
                    connection.writeObject(workoutService.createWorkout(workout));
                }
                case UPDATE_WORKOUT -> {
                    Workout workout = (Workout) connection.readObject();
                    connection.writeObject(workoutService.updateWorkout(workout));
                }
                case SET_WORKOUT_DONE -> {
                    Workout workout = (Workout) connection.readObject();
                    connection.writeObject(workoutService.setWorkoutDone(workout));
                }
                case DELETE_WORKOUT -> {
                    int id = (int) connection.readObject();
                    connection.writeObject(workoutService.deleteWorkout(id));
                }
                case GET_CLIENT_WORKOUTS -> {
                    int clientId = (int) connection.readObject();
                    connection.writeObject(workoutService.getClientWorkouts(clientId));
                }
                case GET_COACH_WORKOUTS -> {
                    int coachId = (int) connection.readObject();
                    connection.writeObject(workoutService.getCoachWorkouts(coachId));
                }
                case GET_COACH_DATE_WORKOUT_TIMES -> {
                    WorkoutTimeDto workoutTimeDto = (WorkoutTimeDto) connection.readObject();
                    connection.writeObject(workoutService.getCoachDateWorkoutTimes(workoutTimeDto));
                }

                case CREATE_CATEGORY -> {
                    Category category = (Category) connection.readObject();
                    if (categoryService.doesCategoryExist(category.getName(), category.getId())) {
                        connection.writeObject(false);
                    } else {
                        connection.writeObject(categoryService.createCategory(category));
                    }
                }
                case UPDATE_CATEGORY -> {
                    Category category = (Category) connection.readObject();
                    if (categoryService.doesCategoryExist(category.getName(), category.getId())) {
                        connection.writeObject(false);
                    } else {
                        connection.writeObject(categoryService.updateCategory(category));
                    }
                }
                case SET_DEFAULT_CATEGORY -> {
                    DefaultCategoryDto defaultCategoryDto = (DefaultCategoryDto) connection.readObject();
                    connection.writeObject(categoryService.setDefaultCategory(defaultCategoryDto));
                }
                case DELETE_CATEGORY -> {
                    int id = (int) connection.readObject();
                    connection.writeObject(categoryService.deleteCategory(id));
                }
                case GET_CATEGORIES_BY_TYPE -> {
                    String type = (String) connection.readObject();
                    connection.writeObject(categoryService.getCategoriesByType(type));
                }

                case CREATE_TRANSACTION -> {
                    Transaction transaction = (Transaction) connection.readObject();
                    connection.writeObject(transactionService.createTransaction(transaction));
                }
                case UPDATE_TRANSACTION -> {
                    Transaction transaction = (Transaction) connection.readObject();
                    connection.writeObject(transactionService.updateTransaction(transaction));
                }
                case DELETE_TRANSACTION -> {
                    int id = (int) connection.readObject();
                    connection.writeObject(transactionService.deleteTransaction(id));
                }
                case GET_TRANSACTION -> {
                    TransactionFilterDto transactionFilterDto = (TransactionFilterDto) connection.readObject();
                    connection.writeObject(transactionService.getTransactions(transactionFilterDto));
                }
                case GET_WEEK_CHART_REPORT -> {
                    connection.writeObject(reportService.getWeekChartReport());
                }
                case GET_MONTH_CHART_REPORT -> {
                    connection.writeObject(reportService.getMonthChartReport());
                }
                case GET_YEAR_CHART_REPORT -> {
                    connection.writeObject(reportService.getYearChartReport());
                }
            }
        }
    }
}
