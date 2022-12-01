package com.gym.repository.impl;

import com.gym.dto.DefaultCategoryDto;
import com.gym.entity.Category;
import com.gym.entity.User;
import com.gym.repository.CategoryService;
import com.gym.utils.PasswordEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public boolean doesCategoryExist(String name, int id) {
        String sql = "SELECT * FROM category WHERE name = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.isBeforeFirst()){
                while (resultSet.next()) {
                    return resultSet.getInt("id") != id;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean createCategory(Category category) {
        String sql = "INSERT INTO category (type, name) VALUES (?, ?)";

        String type = category.getType();
        String name = category.getName();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, type);
            statement.setString(2, name);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateCategory(Category category) {
        String sql = "UPDATE category SET type = ?, name = ? WHERE id = ?";

        String type = category.getType();
        String name = category.getName();
        int id = category.getId();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, type);
            statement.setString(2, name);
            statement.setInt(3, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean setDefaultCategory(DefaultCategoryDto defaultCategoryDto) {
        String sql1 = "UPDATE category SET isDefaultWorkoutCategory = 0 WHERE isDefaultWorkoutCategory = 1";
        String sql2 = "UPDATE category SET isDefaultWorkoutCategory = 1 WHERE id = ?";
        String sql3 = "UPDATE category SET isDefaultSubscriptionCategory = 0 WHERE isDefaultSubscriptionCategory = 1";
        String sql4 = "UPDATE category SET isDefaultSubscriptionCategory = 1 WHERE id = ?";

        int defaultWorkoutCategoryId = defaultCategoryDto.getDefaultWorkoutCategoryId();
        int defaultSubscriptionCategoryId = defaultCategoryDto.getDefaultSubscriptionCategoryId();

        try {
            PreparedStatement statement = connection.prepareStatement(sql1);
            statement.executeUpdate();

            statement = connection.prepareStatement(sql2);
            statement.setInt(1, defaultWorkoutCategoryId);
            statement.executeUpdate();

            statement = connection.prepareStatement(sql3);
            statement.executeUpdate();

            statement = connection.prepareStatement(sql4);
            statement.setInt(1, defaultSubscriptionCategoryId);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCategory(int id) {
        String sql = "DELETE FROM category WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> getCategoriesByType(String type) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category WHERE type = ? ORDER BY name";

        if (type.equals("ALL")) {
            sql = "SELECT * FROM category ORDER BY name";
        }

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            if (!type.equals("ALL")) {
                statement.setString(1, type);
            }
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setType(resultSet.getString("type"));
                category.setDefaultWorkoutCategory(resultSet.getBoolean("isDefaultWorkoutCategory"));
                category.setDefaultSubscriptionCategory(resultSet.getBoolean("isDefaultSubscriptionCategory"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }
}
