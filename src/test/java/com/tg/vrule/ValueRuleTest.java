package com.tg.vrule;

import com.tg.vrule.condition.ValueCondition;
import com.tg.vrule.context.SimpleValueHolder;
import com.tg.vrule.context.ValueCntx;
import com.tg.vrule.rules.CaseRule;
import com.tg.vrule.rules.CheckRule;
import com.tg.vrule.rules.RuleSet;
import com.tg.vrule.rules.ValueRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

public class ValueRuleTest {

    @Test
    void test_1() {

        final Param param = new Param("Num");
        new RuleSet()
                .addRule(new ValueRule<>(param::setValue, new SimpleValueHolder<>("123")))
                .apply();

        Assertions.assertEquals("123", param.getValue());
    }

    @Test
    void test_2() {

        final Param param = new Param("Num", "");
        new RuleSet()
                .addRule(new CheckRule<>(
                        new ValueCondition<>(new ParamValueEqualsPred(param), param::setErr),
                        new SimpleValueHolder<>(123), "Не равен 123"
                ))
                .apply();

        Assertions.assertEquals("Не равен 123", param.getErr());
    }

    @Test
    void test_3() {
        final Param paramE = new Param("E", "0");
        final Param paramX = new Param("X");
        new RuleSet()
                .addRule(new CaseRule<>(paramE::getValue)
                        .onCase(() -> "0", new ValueRule<>(paramX::setValue, () -> "X")))
                .apply();
        Assertions.assertEquals("X", paramX.getValue());
    }
}

class Param {
    private String id;
    private String value;
    private String err;

    public Param(String id, String value) {
        this.id = id;
        this.value = value;
        this.err = "";
    }

    public Param(String id) {
        this(id, "");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
}

class ParamValueEqualsPred implements Predicate<ValueCntx<Integer>> {
    private final Param param;

    public ParamValueEqualsPred(Param param) {
        this.param = param;
    }

    @Override
    public boolean test(ValueCntx<Integer> integerValueCntx) {
        try {
            int value = Integer.parseInt(param.getValue());
            return value == integerValueCntx.value();

        } catch (NumberFormatException ex) {
            return false;
        }
    }
}