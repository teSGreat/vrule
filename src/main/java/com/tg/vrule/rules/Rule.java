package com.tg.vrule.rules;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Rule<T> extends Consumer<T> {

    Rule<T> onCondition(Predicate<T> condition);

    boolean isApplied(T targetContext);
}
