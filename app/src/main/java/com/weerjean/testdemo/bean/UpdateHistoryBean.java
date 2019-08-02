package com.weerjean.testdemo.bean;

/**
 * Created by weerjean at 2019/7/30
 */
public class UpdateHistoryBean {

    /**
     * 旧手机号
     */
    private String oldMobile;

    /**
     * 新手机号
     */
    private String newMobile;
    /**
     * 修改成功时间
     */
    private String date;
    /**
     * 类型：默认为mobile
     */
    private String type;

    public String getOldMobile() {
        return oldMobile;
    }

    public void setOldMobile(String oldMobile) {
        this.oldMobile = oldMobile;
    }

    public String getNewMobile() {
        return newMobile;
    }

    public void setNewMobile(String newMobile) {
        this.newMobile = newMobile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
