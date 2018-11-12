package fr.eisti.inem.pingpong.engine.statistics;

import android.content.ContentValues;

import java.io.Serializable;

public abstract class Statistic implements Serializable {

    public enum StatisticScope {
        USER, // Statistic for a user
        GAME, // Statistic for a game
        USER_GAME // Statistic defined for a user during a given game
    }

    protected Integer id;
    protected StatisticScope statisticScope;
    protected StatisticType statisticType;
    protected Integer value;

    Integer getId() {
        return id;
    }

    StatisticScope getStatisticScope() {
        return statisticScope;
    }

    StatisticType getStatisticType() {
        return statisticType;
    }

    Integer getValue() {
        return value;
    }

    void incrementValue() {
        value++;
    }

    void incrementValue(Integer incrementValue) {
        value += incrementValue;
    }

    protected abstract void loadStatContent() throws StatisticNotFoundException;

    abstract ContentValues getValues();
}
