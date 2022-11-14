package com.gym.repository;

import com.gym.DBConnection;
import com.gym.dto.LoginDto;
import com.gym.dto.SignupDto;

import java.sql.Connection;

public interface IUser {
    Connection connection = DBConnection.getConnection();

    boolean signup(SignupDto signupDto);

    boolean doesUserExist(String password);

    boolean login(LoginDto loginDto);
}
