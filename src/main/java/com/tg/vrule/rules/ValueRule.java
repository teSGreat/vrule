package com.tg.vrule.rules;

import com.tg.vrule.ctx.ValueConsumerCtx;
import com.tg.vrule.ctx.ValueCtx;

public class ValueRule<V, T> extends CondRule<T> {

    private final ValueConsumerCtx<V, T> valueConsumer;
    private final ValueCtx<V, T> valueCtx;

    public ValueRule(ValueConsumerCtx<V, T> valueConsumer, ValueCtx<V, T> valueCtx) {
        this.valueConsumer = valueConsumer;
        this.valueCtx = valueCtx;
    }

    @Override
    public void accept(T targetCtx) {
        if (isApplied(targetCtx)) {
            valueConsumer.accept(valueCtx.value(targetCtx), targetCtx);
        }
    }
}
