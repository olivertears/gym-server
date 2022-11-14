package com.gym.controller;

import com.gym.Connection;
import com.gym.command.ClientAction;
import com.gym.dto.SignupDto;
import com.gym.dto.LoginDto;
import com.gym.repository.IUser;
import com.gym.repository.impl.UserImpl;

import java.net.Socket;

public class UserController implements Runnable {
    private final Connection connectionTCP;

    public UserController(Socket socket) {
        connectionTCP = new Connection(socket);
    }

    @Override
    public void run() {
        IUser iUser = new UserImpl();
        while (true) {
            ClientAction clientAction = (ClientAction) connectionTCP.readObject();
            switch (clientAction) {
                case SIGNUP -> {
                    SignupDto signupDto = (SignupDto) connectionTCP.readObject();
                    if (iUser.doesUserExist(signupDto.getEmail())) {
                        connectionTCP.writeObject(false);
                    } else {
                        connectionTCP.writeObject(iUser.signup(signupDto));
                    }
                }
                case LOGIN -> {
                    LoginDto loginDto = (LoginDto) connectionTCP.readObject();
                    connectionTCP.writeObject(iUser.login(loginDto));
                }
            }
        }
    }
}
