package com.tg.vrule;

import com.tg.vrule.rules.CheckRule;
import com.tg.vrule.rules.RuleSet;
import com.tg.vrule.rules.ValueRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class ValueRuleTest {

    private Map<String, Param> params;

    @BeforeEach
    void setUp() {
        params = new HashMap<>();
    }

    @Test
    void simple_value_rule_test() {
        params.put("Num", new Param("Num"));

        RuleSet<Map<String, Param>> rs =
                new RuleSet<Map<String, Param>>()
                        .addRule(new ValueRule<>((value, ctx) -> ctx.get("Num").setValue(value),
                                targetCtx -> "123"));
        rs.accept(params);
        Assertions.assertEquals("123", params.get("Num").getValue());
    }

    @Test
    void value_with_condition_rule_test() {

        params.put("Num", new Param("Num").setValue("1"));
        params.put("Code", new Param("2"));

        RuleSet<Map<String, Param>> rs =
                new RuleSet<Map<String, Param>>()
                        .addRule(new ValueRule<String, Map<String, Param>>((value, ctx) -> ctx.get("Num").setValue(value),
                                targetCtx -> "123")
                                .onCondition(ctx -> "3".equals(ctx.get("Code").getValue()))
                        );

        rs.accept(params);
        Assertions.assertEquals("1", params.get("Num").getValue());
    }

    @Test
    void check_rule_test() {

        String error = "error. must be 100!";
        params.put("Num", new Param("Num").setValue("123"));

        RuleSet<Map<String, Param>> rs =
                new RuleSet<Map<String, Param>>()
                        .addRule(new CheckRule<String, Map<String, Param>>(
                                (err, ctx) -> ctx.get("Num").setErr(err))
                                .setErr(error)
                                .setCheckCondition(ctx -> "100".equals(ctx.get("Num").getValue()))
                        );

        rs.accept(params);
        Assertions.assertEquals(error, params.get("Num").getErr());

        params.put("Num", new Param("Num").setValue("100"));
        rs.accept(params);
        Assertions.assertEquals("", params.get("Num").getErr());
    }
}

