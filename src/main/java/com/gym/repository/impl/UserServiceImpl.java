package com.gym.repository.impl;

import com.gym.dto.SignupDto;
import com.gym.dto.LoginDto;
import com.gym.dto.UserRoleDto;
import com.gym.entity.Subscription;
import com.gym.entity.User;
import com.gym.repository.SubscriptionService;
import com.gym.repository.UserService;
import com.gym.repository.WorkoutService;
import com.gym.utils.PasswordEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    SubscriptionService subscriptionService = new SubscriptionServiceImpl();

    @Override
    public User signup(SignupDto signupDto) {
        User user;
        String sql = "INSERT INTO user (name, surname, email, password) VALUES (?, ?, ?, ?)";

        String name = signupDto.getName();
        String surname = signupDto.getSurname();
        String email = signupDto.getEmail();
        String password = signupDto.getPassword();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setString(4, PasswordEncoder.encode(password));
            statement.executeUpdate();

            user = login(signupDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public User login(LoginDto loginDto) {
        User user = new User();
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, PasswordEncoder.encode(password));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setRole(resultSet.getString("role"));
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public boolean doesUserExist(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.isBeforeFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(int id) {
        User user = new User();
        String sql = "SELECT * FROM user WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setRole(resultSet.getString("role"));
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE role != 'ADMIN' ORDER BY name, surname";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    @Override
    public List<User> getCoaches() {
        List<User> coaches = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE role = 'COACH' ORDER BY id";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User coach = new User();
                coach.setId(resultSet.getInt("id"));
                coach.setName(resultSet.getString("name"));
                coach.setSurname(resultSet.getString("surname"));
                coach.setEmail(resultSet.getString("email"));
                coach.setPrice(resultSet.getDouble("price"));
                coaches.add(coach);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return coaches;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE user SET name = ?, surname = ?, role = ? WHERE id = ?";

        int id = user.getId();
        String name = user.getName();
        String surname = user.getSurname();
        String role = user.getRole();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, role);
            statement.setInt(4, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateUserRole(UserRoleDto userRoleDto) {
        String sql = "UPDATE user SET role = ?, price = ? WHERE id = ?";

        int id = userRoleDto.getId();
        String role = userRoleDto.getRole();
        double price = role.equals("COACH") ? 20.00 : 0.00;

        Subscription currentSubscription = subscriptionService.getUserSubscription(id);
        if (currentSubscription != null) {
            subscriptionService.deleteSubscription(currentSubscription.getId());
        }

        if (role.equals("COACH")) {
            Subscription subscription = new Subscription();
            subscription.setUserId(id);
            subscription.setType("CONTRACT");
            subscription.setPrice(660.00);
            subscription.setStart(LocalDate.now());
            subscription.setEnd(LocalDate.now().plusYears(1));
            subscriptionService.createSubscription(subscription);
        } else {
            subscriptionService.deleteContract(id);
        }

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, role);
            statement.setDouble(2, price);
            statement.setInt(3, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM user WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static public String getUserFullName(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    return resultSet.getString("name") + " " + resultSet.getString("surname");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
