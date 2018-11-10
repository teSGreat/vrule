package com.tg.vrule.rules;

import com.sun.istack.internal.NotNull;

import java.util.function.Predicate;

public class IfElseRule<T> extends CondRule<T> {

    private final Predicate<T> checkCondition;
    private final Rule<T> onTrueCondRule;
    private Rule<T> onFalseCondRule;

    public IfElseRule(@NotNull Predicate<T> checkCondition, @NotNull Rule<T> onTrueCondRule) {
        this.checkCondition = checkCondition;
        this.onTrueCondRule = onTrueCondRule;
    }

    public IfElseRule<T> onElse(Rule<T> onFalseRule) {
        this.onFalseCondRule = onFalseRule;
        return this;
    }

    @Override
    public void accept(T targetCtx) {
        if (isApplied(targetCtx) && checkCondition.test(targetCtx)) {
            onTrueCondRule.accept(targetCtx);
        } else if (onFalseCondRule != null) {
            onFalseCondRule.accept(targetCtx);
        }
    }

}
