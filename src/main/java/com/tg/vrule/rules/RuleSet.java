package com.tg.vrule.rules;

import java.util.LinkedList;

public class RuleSet implements Rule {

    private LinkedList<Rule> rules;

    public RuleSet() {
        rules = new LinkedList<>();
    }

    public RuleSet addRule(Rule rule) {
        rules.addLast(rule);
        return this;
    }

    @Override
    public void apply() {
        for (Rule rule : rules) {
            rule.apply();
        }
    }

}
