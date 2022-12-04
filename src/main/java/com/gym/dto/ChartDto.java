package com.gym.dto;

import com.gym.entity.ChartValue;

import java.io.Serializable;
import java.util.List;

public class ChartDto implements Serializable {
    private List<ChartValue> expenseChartValueList;
    private List<ChartValue> incomeChartValueList;

    public ChartDto(List<ChartValue> expenseChartValueList, List<ChartValue> incomeChartValueList) {
        this.expenseChartValueList = expenseChartValueList;
        this.incomeChartValueList = incomeChartValueList;
    }

    public List<ChartValue> getExpenseChartValueList() {
        return expenseChartValueList;
    }

    public void setExpenseChartValueList(List<ChartValue> expenseChartValueList) {
        this.expenseChartValueList = expenseChartValueList;
    }

    public List<ChartValue> getIncomeChartValueList() {
        return incomeChartValueList;
    }

    public void setIncomeChartValueList(List<ChartValue> incomeChartValueList) {
        this.incomeChartValueList = incomeChartValueList;
    }
}