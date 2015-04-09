package com.unico.helper;

import java.io.Serializable;

/**
 *
 * @author H.Husain
 */
public class GCDPoJo implements Serializable {

    private Integer i1;
    private Integer i2;

    public GCDPoJo(Integer i1, Integer i2) {
        this.i1 = i1;
        this.i2 = i2;
    }

    public GCDPoJo() {
    }

    public Integer getI1() {
        return i1;
    }

    public void setI1(Integer i1) {
        this.i1 = i1;
    }

    public Integer getI2() {
        return i2;
    }

    public void setI2(Integer i2) {
        this.i2 = i2;
    }

    public String toString() {
        return i1 + " " + i2;
    }
}
