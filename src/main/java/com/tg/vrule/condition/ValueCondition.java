package com.tg.vrule.condition;

import com.tg.vrule.context.ValueCntx;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ValueCondition<T> implements Condition<T> {

    private final Predicate<ValueCntx<T>> predicate;
    private final Consumer<String> errConsumer;

    public ValueCondition(Predicate<ValueCntx<T>> predicate, Consumer<String> errConsumer) {

        this.predicate = predicate;
        this.errConsumer = errConsumer;
    }

    @Override
    public boolean check(ValueCntx<T> valueCntx) {
        return predicate.test(valueCntx);
    }

    @Override
    public void onError(String err) {
        this.errConsumer.accept(err);
    }
}
