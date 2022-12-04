package com.gym.repository.impl;

import com.gym.dto.ChartDto;
import com.gym.entity.ChartValue;
import com.gym.repository.ReportService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReportServiceImpl implements ReportService {
    Random r = new Random();

    @Override
    public ChartDto getWeekChartReport() {
        List<ChartValue> expenseChartValueList = new ArrayList<>();
        List<ChartValue> incomeChartValueList = new ArrayList<>();

        LocalDate end = LocalDate.now();
        LocalDate start = end.minusWeeks(1);

        while (start.isBefore(end)) {
            expenseChartValueList.add(new ChartValue(String.valueOf(start.getDayOfWeek()), 100 * r.nextDouble()));
            incomeChartValueList.add(new ChartValue(String.valueOf(start.getDayOfWeek()), 100 * r.nextDouble()));
            start = start.plusDays(1);
        }

        return new ChartDto(expenseChartValueList, incomeChartValueList);
    }

    @Override
    public ChartDto getMonthChartReport() {
        List<ChartValue> expenseChartValueList = new ArrayList<>();
        List<ChartValue> incomeChartValueList = new ArrayList<>();

        LocalDate end = LocalDate.now();
        LocalDate start = end.minusMonths(1);

        while (start.isBefore(end)) {
            expenseChartValueList.add(new ChartValue(start.getDayOfMonth() + "." + start.getMonthValue(), 100 * r.nextDouble()));
            incomeChartValueList.add(new ChartValue(start.getDayOfMonth() + "." + start.getMonthValue(), 100 * r.nextDouble()));
            start = start.plusDays(1);
        }

        return new ChartDto(expenseChartValueList, incomeChartValueList);
    }

    @Override
    public ChartDto getYearChartReport() {
        List<ChartValue> expenseChartValueList = new ArrayList<>();
        List<ChartValue> incomeChartValueList = new ArrayList<>();

        LocalDate end = LocalDate.now();
        LocalDate start = end.minusYears(1);

        while (start.isBefore(end)) {
            expenseChartValueList.add(new ChartValue(String.valueOf(start.getMonth()), 3000 * r.nextDouble()));
            incomeChartValueList.add(new ChartValue(String.valueOf(start.getMonth()), 3000 * r.nextDouble()));
            start = start.plusMonths(1);
        }

        return new ChartDto(expenseChartValueList, incomeChartValueList);
    }
}
