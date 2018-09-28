package com.tg.vrule.rules;

import com.tg.vrule.context.ValueCntx;

import java.util.function.Consumer;

public class ValueRule<T> implements Rule {

    private final Consumer<T> param;
    private final ValueCntx<T> valueCntx;

    public ValueRule(Consumer<T> param, ValueCntx<T> valueCntx) {
        this.param = param;
        this.valueCntx = valueCntx;
    }

    @Override
    public void apply() {
        param.accept(valueCntx.value());
    }
}
