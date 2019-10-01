package com.dingtao.common.bean;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public class DoctorIdCardInfo {
    private String name;
    private String sex;
    private String nation;
    private String idNumber;

    public DoctorIdCardInfo(String name, String sex, String nation, String idNumber) {
        this.name = name;
        this.sex = sex;
        this.nation = nation;
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
