package com.tg.vrule.rules;

import java.util.LinkedList;

public class RuleSet<T> extends CondRule<T> {

    private LinkedList<Rule<T>> rules;

    public RuleSet() {
        rules = new LinkedList<>();
    }

    public RuleSet<T> addRule(Rule<T> rule) {
        rules.addLast(rule);
        return this;
    }

    @Override
    public void accept(T targetCtx) {
        if (isApplied(targetCtx)) {
            for (Rule<T> rule : rules) {
                rule.accept(targetCtx);
            }
        }
    }

}
