package com.gym.repository;

import com.gym.DBConnection;
import com.gym.dto.ChartDto;
import com.gym.dto.WorkoutTimeDto;
import com.gym.entity.ChartValue;
import com.gym.entity.Workout;

import java.sql.Connection;
import java.util.List;

public interface ReportService {
    Connection connection = DBConnection.getConnection();

    ChartDto getWeekChartReport();
    ChartDto getMonthChartReport();
    ChartDto getYearChartReport();
}
