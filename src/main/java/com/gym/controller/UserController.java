package com.gym.controller;

import com.gym.Connection;
import com.gym.command.ClientAction;
import com.gym.dto.SignupDto;
import com.gym.dto.LoginDto;
import com.gym.entity.User;
import com.gym.repository.UserService;
import com.gym.repository.impl.UserServiceImpl;

import java.net.Socket;

public class UserController implements Runnable {
    private final Connection connection;

    public UserController(Socket socket) {
        connection = new Connection(socket);
    }

    @Override
    public void run() {
        UserService userService = new UserServiceImpl();
        while (true) {
            ClientAction clientAction = (ClientAction) connection.readObject();
            switch (clientAction) {
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
                case UPDATE_USER -> {
                    User user = (User) connection.readObject();
                    connection.writeObject(userService.updateUser(user));
                }
                case DELETE_USER -> {
                    int id = (int) connection.readObject();
                    connection.writeObject(userService.deleteUser(id));
                }
            }
        }
    }
}
