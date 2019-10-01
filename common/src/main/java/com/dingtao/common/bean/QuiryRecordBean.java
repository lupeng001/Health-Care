package com.dingtao.common.bean;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public class QuiryRecordBean {

    /**
     * inquiryTime : 1564110167000
     * nickName : 哈哈哈哈
     * recordId : 33
     * status : 1
     * userHeadPic : http://172.17.8.100/images/health/user/head_pic/default/default_head_1.jpg
     * userId : 90
     */

    private long inquiryTime;
    private String nickName;
    private int recordId;
    private int status;
    private String userHeadPic;
    private int userId;

    public long getInquiryTime() {
        return inquiryTime;
    }

    public void setInquiryTime(long inquiryTime) {
        this.inquiryTime = inquiryTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserHeadPic() {
        return userHeadPic;
    }

    public void setUserHeadPic(String userHeadPic) {
        this.userHeadPic = userHeadPic;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
