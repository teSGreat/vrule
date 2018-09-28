package com.tg.vrule.rules;

import com.tg.vrule.context.ValueCntx;

import java.util.HashMap;
import java.util.Map;

public class CaseRule<T> implements Rule {

    private final ValueCntx<T> valueRule;
    private final Map<T, Rule> cases;

    public CaseRule(ValueCntx<T> caseValue) {
        this.valueRule = caseValue;
        this.cases = new HashMap<>();
    }

    public CaseRule<T> onCase(ValueCntx<T> caseValue, Rule rule) {
        cases.put(caseValue.value(), rule);
        return this;
    }

    @Override
    public void apply() {
        Rule rule = cases.get(valueRule.value());
        if (rule != null) {
            rule.apply();
        }
    }

}
