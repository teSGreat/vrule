package com.tg.vrule.rules;

import com.tg.vrule.condition.Condition;
import com.tg.vrule.context.ValueCntx;

public class CheckRule<T> implements Rule {

    private final ValueCntx<T> valueCntx;
    private final Condition<T> condition;
    private final String err;

    public CheckRule(Condition<T> condition, ValueCntx<T> valueCntx, String err) {
        this.valueCntx = valueCntx;
        this.condition = condition;
        this.err = err;
    }

    @Override
    public void apply() {
        if (!condition.check(valueCntx)) {
            condition.onError(err);
        }
    }
}
