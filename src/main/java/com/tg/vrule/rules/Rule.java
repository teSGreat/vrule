package com.tg.vrule.rules;

public interface Rule {
    void apply();

    default boolean isRight() {
        return true;
    }
}
