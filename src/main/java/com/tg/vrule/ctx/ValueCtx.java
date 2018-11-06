package com.tg.vrule.ctx;

public interface ValueCtx<V, T> {
    V value(T targetCtx);
}
