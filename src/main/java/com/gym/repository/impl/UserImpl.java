package com.gym.repository.impl;

import com.gym.dto.SignupDto;
import com.gym.dto.LoginDto;
import com.gym.repository.IUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImpl implements IUser {
    @Override
    public boolean signup(SignupDto signupDto) {
        String name = signupDto.getPassword();
        String surname = signupDto.getSurname();
        String email = signupDto.getEmail();
        String password = signupDto.getPassword();

        String sql = "INSERT INTO user (name, surname, email, password) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
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

//        return false;
    }

    @Override
    public boolean login(LoginDto loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        String sql = "SELECT name, surname, role FROM user WHERE email = ? AND password = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.isBeforeFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        return false;
    }
}
