package com.tg.vrule.rules;


import java.util.function.Predicate;


public abstract class CondRule<T> implements Rule<T> {

    private Predicate<T> condition;

    @Override
    public final Rule<T> onCondition(Predicate<T> condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public final boolean isApplied(T targetCtx) {
        return condition == null || condition.test(targetCtx);
    }
}
