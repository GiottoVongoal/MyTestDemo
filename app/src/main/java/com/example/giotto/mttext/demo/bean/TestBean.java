package com.example.giotto.mttext.demo.bean;

/**
 * @author SHAOS.
 * @PackageName com.example.giotto.myapplication
 * @date 2016-09-09 11:48
 * @description
 */
public class TestBean {
    private String checkResult;
    private String checkItem;

    public TestBean() {
        super();
    }

    public TestBean(String checkResult, String checkItem) {
        super();
        this.checkResult = checkResult;
        this.checkItem = checkItem;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(String checkItem) {
        this.checkItem = checkItem;
    }
}
