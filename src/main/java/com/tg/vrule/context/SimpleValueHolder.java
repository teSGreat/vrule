package com.tg.vrule.context;

public class SimpleValueHolder<T> implements ValueCntx<T> {

    private final T value;

    public SimpleValueHolder(T value) {
        this.value = value;
    }

    @Override
    public T value() {
        return value;
    }
}
