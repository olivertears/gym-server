package com.gym.command;

public enum Action {
        SIGNUP,
        LOGIN,
        GET_USER_BY_ID,
        GET_USERS,
        GET_COACHES,
        UPDATE_USER,
        UPDATE_USER_ROLE,
        DELETE_USER,

        CREATE_SUBSCRIPTION,
        UPDATE_SUBSCRIPTION_TO_PREMIUM,
        DELETE_SUBSCRIPTION,
        GET_USER_SUBSCRIPTION,

        CREATE_WORKOUT,
        UPDATE_WORKOUT,
        SET_WORKOUT_DONE,
        DELETE_WORKOUT,
        GET_CLIENT_WORKOUTS,
        GET_COACH_WORKOUTS,
        GET_COACH_DATE_WORKOUT_TIMES,

        CREATE_CATEGORY,
        UPDATE_CATEGORY,
        SET_DEFAULT_CATEGORY,
        DELETE_CATEGORY,
        GET_CATEGORIES_BY_TYPE,

        CREATE_TRANSACTION,
        UPDATE_TRANSACTION,
        DELETE_TRANSACTION,
        GET_TRANSACTION,

        GET_WEEK_CHART_REPORT,
        GET_MONTH_CHART_REPORT,
        GET_YEAR_CHART_REPORT,
}
