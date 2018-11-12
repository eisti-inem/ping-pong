package fr.eisti.inem.pingpong.engine.statistics;

import android.content.ContentValues;

import java.io.Serializable;

public abstract class Statistic implements Serializable {

    public enum StatisticScope {
        USER, // Statistic for a user
        GAME, // Statistic for a game
        USER_GAME // Statistic defined for a user during a given game
    }

    protected static final String STATISTIC_NOT_FOUND_ERROR = "The statistic could not be found" +
            "in the database.";

    protected Integer id;
    protected StatisticScope statisticScope;
    protected StatisticType statisticType;
    protected Integer value;

    public Integer getId() {
        return id;
    }

    public StatisticScope getStatisticScope() {
        return statisticScope;
    }

    public StatisticType getStatisticType() {
        return statisticType;
    }

    public Integer getValue() {
        return value;
    }

    public void incrementValue() {
        value++;
    }

    public void incrementValue(Integer incrementValue) {
        value += incrementValue;
    }

    protected abstract void loadStatContent() throws StatisticNotFoundException;

    abstract ContentValues getValues();
}
