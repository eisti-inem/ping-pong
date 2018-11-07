package fr.eisti.inem.pingpong.engine;

import android.content.Context;

public abstract class AbstractManager {

    protected Context context;

    public AbstractManager(Context context) {
        this.context = context;
    }
}
