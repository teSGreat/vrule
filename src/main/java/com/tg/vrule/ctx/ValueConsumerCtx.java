package com.tg.vrule.ctx;

public interface ValueConsumerCtx<V, T> {
    void accept(V value, T targetCtx);
}
