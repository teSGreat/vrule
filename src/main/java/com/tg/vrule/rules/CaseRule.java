package com.tg.vrule.rules;

import com.tg.vrule.ctx.ValueConsumerCtx;
import com.tg.vrule.ctx.ValueCtx;
import java.util.HashMap;
import java.util.Map;

public class CaseRule<V, E, T> extends CondRule<T> {

    private final ValueCtx<V, T> value;
    private final ValueConsumerCtx<E, T> errorConsumer;
    private final Map<V, Rule<T>> cases;
    private E err;

    public CaseRule(ValueCtx<V, T> value, ValueConsumerCtx<E, T> errorConsumer) {
        this.value = value;
        this.errorConsumer = errorConsumer;
        this.cases = new HashMap<>();
    }

    public CaseRule<V, E, T> setErr(E err) {
        this.err = err;
        return this;
    }

    public CaseRule<V, E, T> onCase(V caseValue, Rule<T> rule) {
        cases.put(caseValue, rule);
        return this;
    }

    @Override
    public void accept(T targetCtx) {
        if (isApplied(targetCtx)) {
            Rule<T> rule = cases.get(value.value(targetCtx));
            if (rule != null) {
                rule.accept(targetCtx);
            } else if (err != null) {
                errorConsumer.accept(err, targetCtx);
            }
        }
    }
}
