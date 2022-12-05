package com.gym.repository.impl;

import com.gym.dto.ChartDto;
import com.gym.dto.TransactionFilterDto;
import com.gym.entity.Category;
import com.gym.entity.ChartValue;
import com.gym.entity.Transaction;
import com.gym.repository.CategoryService;
import com.gym.repository.ReportService;
import com.gym.repository.TransactionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReportServiceImpl implements ReportService {
    TransactionService transactionService = new TransactionServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();
    Random r = new Random();

    @Override
    public ChartDto getWeekChartReport() {
        List<ChartValue> expenseChartValueList = new ArrayList<>();
        List<ChartValue> incomeChartValueList = new ArrayList<>();

        LocalDate end = LocalDate.now();
        LocalDate start = end.minusWeeks(1);

        List<Category> categories = categoryService.getCategoriesByType("ALL");

        while (start.isBefore(end)) {
            TransactionFilterDto transactionFilterDto = new TransactionFilterDto();
            transactionFilterDto.setStart(start);
            transactionFilterDto.setEnd(start);
            List<Transaction> transactions = transactionService.getTransactions(transactionFilterDto);

            Double expense = 0.0;
            Double income = 0.0;
            for (Transaction transaction: transactions) {
                String type = categories.stream().filter(category -> category.getName().equals(transaction.getCategoryName())).findAny().orElse(null).getType();
                if (type.equals("INCOME")) {
                    income += transaction.getPrice();
                } else {
                    expense -= transaction.getPrice();
                }
            }

            expenseChartValueList.add(new ChartValue(String.valueOf(start.getDayOfWeek()), expense));
            incomeChartValueList.add(new ChartValue(String.valueOf(start.getDayOfWeek()), income));
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

        List<Category> categories = categoryService.getCategoriesByType("ALL");

        while (start.isBefore(end)) {
            TransactionFilterDto transactionFilterDto = new TransactionFilterDto();
            transactionFilterDto.setStart(start);
            transactionFilterDto.setEnd(start);
            List<Transaction> transactions = transactionService.getTransactions(transactionFilterDto);

            Double expense = 0.0;
            Double income = 0.0;
            for (Transaction transaction: transactions) {
                String type = categories.stream().filter(category -> category.getName().equals(transaction.getCategoryName())).findAny().orElse(null).getType();
                if (type.equals("INCOME")) {
                    income += transaction.getPrice();
                } else {
                    expense -= transaction.getPrice();
                }
            }

            String date = (start.getDayOfMonth() < 10 ? "0" + start.getDayOfMonth() : start.getDayOfMonth()) + "." + (start.getMonthValue() < 10 ? "0" + start.getMonthValue() : start.getMonthValue());

            expenseChartValueList.add(new ChartValue(date, expense));
            incomeChartValueList.add(new ChartValue(date, income));
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

        List<Category> categories = categoryService.getCategoriesByType("ALL");

        while (start.isBefore(end)) {
            TransactionFilterDto transactionFilterDto = new TransactionFilterDto();
            transactionFilterDto.setStart(start);
            transactionFilterDto.setEnd(start.plusMonths(1));
            List<Transaction> transactions = transactionService.getTransactions(transactionFilterDto);

            Double expense = 0.0;
            Double income = 0.0;
            for (Transaction transaction: transactions) {
                String type = categories.stream().filter(category -> category.getName().equals(transaction.getCategoryName())).findAny().orElse(null).getType();
                if (type.equals("INCOME")) {
                    income += transaction.getPrice();
                } else {
                    expense -= transaction.getPrice();
                }
            }

            expenseChartValueList.add(new ChartValue(String.valueOf(start.getMonth()), expense));
            incomeChartValueList.add(new ChartValue(String.valueOf(start.getMonth()), income));
            start = start.plusMonths(1);
        }

        return new ChartDto(expenseChartValueList, incomeChartValueList);
    }
}
