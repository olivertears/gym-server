package com.gym.repository;

import com.gym.DBConnection;
import com.gym.entity.Operation;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public interface OperationService {
    Connection connection = DBConnection.getConnection();

    boolean createOperation(Operation operation);
    boolean updateOperation(Operation operation);
    boolean deleteOperation(int id);
    List<Operation> getOperations();
}
