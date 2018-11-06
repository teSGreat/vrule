package com.tg.vrule;

/**
 * Created by Uleychik_SA on 06.11.2018.
 */
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
        this(id, null);
    }

    public String getId() {
        return id;
    }

    public Param setId(String id) {
        this.id = id;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Param setValue(String value) {
        this.value = value;
        return this;
    }

    public String getErr() {
        return err;
    }

    public Param setErr(String err) {
        this.err = err;
        return this;
    }
}
