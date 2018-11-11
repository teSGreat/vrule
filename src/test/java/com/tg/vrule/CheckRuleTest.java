package com.tg.vrule;

import com.tg.vrule.rules.CheckRule;
import com.tg.vrule.rules.RuleSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CheckRuleTest {

    private Map<String, Param> params;

    @BeforeEach
    void setUp() {
        params = new HashMap<>();
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

