package com.tg.vrule.condition;

import com.tg.vrule.context.ValueCntx;

public interface Condition<T> {

    boolean check(ValueCntx<T> valueCntx);

    void onError(String err);
}
