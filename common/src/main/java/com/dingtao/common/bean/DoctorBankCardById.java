package com.dingtao.common.bean;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public class DoctorBankCardById {
    private String bankCardNumber;
    private String bankName;
    private String bankCardType;

    public DoctorBankCardById(String bankCardNumber, String bankName, String bankCardType) {
        this.bankCardNumber = bankCardNumber;
        this.bankName = bankName;
        this.bankCardType = bankCardType;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(String bankCardType) {
        this.bankCardType = bankCardType;
    }

}
