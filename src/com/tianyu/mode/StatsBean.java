package com.tianyu.mode;

/**
 * Created by dongqingqing on 17/9/5.
 */
public class StatsBean {
    private int id;
    private String time;
    private String groups;
    private String content;
    private String result;
    private String developer;
    private String pd;
    private String apkversion;
    private String tester;
    private String reason;
    private String apkplugs;
    private String isrequirement;
    private String isproducts;

    public String getIsboning() {
        return isboning;
    }

    public void setIsboning(String isboning) {
        this.isboning = isboning;
    }

    public String getApkplugs() {
        return apkplugs;
    }

    public void setApkplugs(String apkplugs) {
        this.apkplugs = apkplugs;
    }

    public String getIsrequirement() {
        return isrequirement;
    }

    public void setIsrequirement(String isrequirement) {
        this.isrequirement = isrequirement;
    }

    public String getIsproducts() {
        return isproducts;
    }

    public void setIsproducts(String isproducts) {
        this.isproducts = isproducts;
    }

    private String isboning;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPd() {
        return pd;
    }

    public void setPd(String pd) {
        this.pd = pd;
    }

    public String getApkversion() {
        return apkversion;
    }

    public void setApkversion(String model) {
        this.apkversion = model;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }
}
