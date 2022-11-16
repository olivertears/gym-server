package com.gym.controller;

import com.gym.Connection;
import com.gym.command.ClientAction;
import com.gym.dto.SignupDto;
import com.gym.dto.LoginDto;
import com.gym.repository.UserService;
import com.gym.repository.impl.UserServiceImpl;

import java.net.Socket;

public class UserController implements Runnable {
    private final Connection connectionTCP;

    public UserController(Socket socket) {
        connectionTCP = new Connection(socket);
    }

    @Override
    public void run() {
        UserService userService = new UserServiceImpl();
        while (true) {
            ClientAction clientAction = (ClientAction) connectionTCP.readObject();
            switch (clientAction) {
                case SIGNUP -> {
                    SignupDto signupDto = (SignupDto) connectionTCP.readObject();
                    if (userService.doesUserExist(signupDto.getEmail())) {
                        connectionTCP.writeObject(null);
                    } else {
                        connectionTCP.writeObject(userService.signup(signupDto));
                    }
                }
                case LOGIN -> {
                    LoginDto loginDto = (LoginDto) connectionTCP.readObject();
                    connectionTCP.writeObject(userService.login(loginDto));
                }
            }
        }
    }
}
