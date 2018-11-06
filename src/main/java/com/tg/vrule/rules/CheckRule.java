package com.tg.vrule.rules;

import com.tg.vrule.ctx.ValueConsumerCtx;
import java.util.function.Predicate;

public class CheckRule<V, E, T> extends CondRule<T> {

    private final ValueConsumerCtx<E, T> errorConsumer;
    private Predicate<T> checkCondition;

    private E err;

    public CheckRule(ValueConsumerCtx<E, T> errorConsumer) {
        this.errorConsumer = errorConsumer;
    }

    public CheckRule<V, E, T> setCheckCondition(Predicate<T> condition) {
        this.checkCondition = condition;
        return this;
    }

    public CheckRule<V, E, T> setErr(E err) {
        this.err = err;
        return this;
    }

    @Override
    public void accept(T targetCtx) {
        if (isApplied(targetCtx) && !checkCondition.test(targetCtx)) {
            if (err != null) {
                errorConsumer.accept(err, targetCtx);
            }
        }
    }
}
