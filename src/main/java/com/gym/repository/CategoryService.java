package com.gym.repository;

import com.gym.DBConnection;
import com.gym.dto.DefaultCategoryDto;
import com.gym.entity.Category;

import java.sql.Connection;
import java.util.List;

public interface CategoryService {
    Connection connection = DBConnection.getConnection();

    boolean doesCategoryExist(String name);

    boolean createCategory(Category category);
    boolean updateCategory(Category category);
    boolean setDefaultCategory(DefaultCategoryDto defaultCategoryDto);
    boolean deleteCategory(int id);
    List<Category> getCategoriesByType(String type);
}
