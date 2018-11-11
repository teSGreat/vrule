package com.tg.vrule;

import com.tg.vrule.rules.IfElseRule;
import com.tg.vrule.rules.RuleSet;
import com.tg.vrule.rules.ValueRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class IfElseRuleTest {

    private Map<String, Param> params;

    @BeforeEach
    void setUp() {
        params = new HashMap<>();
    }


    @Test
    void if_with_elde_condition_rule_test() {

        params.put("Flag", new Param("Flag", "true"));
        params.put("FlagValue", new Param("FlagValue"));

        RuleSet<Map<String, Param>> rs =
                new RuleSet<Map<String, Param>>()
                        .addRule(
                                new IfElseRule<Map<String, Param>>(params ->
                                        Boolean.parseBoolean(params.get("Flag").getValue()),
                                        new ValueRule<>((value, ctx) ->
                                                ctx.get("FlagValue").setValue(value), targetCtx -> "flag is true"))
                                        .onElse(new ValueRule<>((value, ctx) ->
                                                ctx.get("FlagValue").setValue(value), targetCtx -> "flag is false")));

        rs.accept(params);
        Assertions.assertEquals("flag is true", params.get("FlagValue").getValue());

        params.put("Flag", new Param("Flag", "false"));
        rs.accept(params);
        Assertions.assertEquals("flag is false", params.get("FlagValue").getValue());
    }

}

