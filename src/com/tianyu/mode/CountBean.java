package com.tianyu.mode;

/**
 * Created by dongqingqing on 17/9/21.
 */
public class CountBean {
    private int conut_all;
    private int conut_pass;
    private int count_fail;
    private float percent_pass;

    public int getCount_fail() {
        return count_fail;
    }

    public void setCount_fail(int count_fail) {
        this.count_fail = count_fail;
    }

    public int getConut_all() {
        return conut_all;
    }

    public void setConut_all(int conut_all) {
        this.conut_all = conut_all;
    }

    public int getConut_pass() {
        return conut_pass;
    }

    public void setConut_pass(int conut_pass) {
        this.conut_pass = conut_pass;
    }

    public void setPercent_pass(float percent_pass) {
        this.percent_pass = percent_pass;
    }

    public float getPercent_pass() {
        int all = getConut_all();
        int pass = getConut_pass();
        return pass/all*100;
    }

}
