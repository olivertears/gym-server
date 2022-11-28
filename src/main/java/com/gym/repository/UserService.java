package com.gym.repository;

import com.gym.DBConnection;
import com.gym.dto.LoginDto;
import com.gym.dto.SignupDto;
import com.gym.dto.UserDataDto;
import com.gym.dto.UserRoleDto;
import com.gym.entity.User;

import java.sql.Connection;
import java.util.List;

public interface UserService {
    Connection connection = DBConnection.getConnection();

    User signup(SignupDto signupDto);
    User login(LoginDto loginDto);

    boolean doesUserExist(String email);

    User getUserById(int id);
    List<User> getUsers();
    List<User> getCoaches();
    boolean updateUser(UserDataDto user);
    boolean updateUserRole(UserRoleDto userRoleDto);
    boolean deleteUser(int id);

}
