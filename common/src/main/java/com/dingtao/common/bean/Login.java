package com.dingtao.common.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
@Entity
public class Login {

    /**
     * departmentId : 5
     * departmentName : 小儿科
     * id : 37
     * inauguralHospital : dasda
     * jiGuangPwd : mRUCs5zd+scQNoZQfdsXgYvbncR2oQzJdd/fQw0LfwozLShT7xKOzNhfypi5WZUAzhIl7DLQEnD78lAnsDLbtEe93d1iRVNZsU0nwiGtlBtr/UA4l5u5xPbiaywiouKBVzsJoABzrZ4xLiYF4tVue+0NVDOLMCphU7NaGmao7Cc=
     * jobTitle : 主任
     * name : sssss
     * reviewStatus : 2
     * sessionId : 37156315205335137
     * userName : MQtmPd2295587818
     * whetherHaveImagePic : 2
     */
@Id
    private Long id;
    private int departmentId;
    private String departmentName;
    private String inauguralHospital;
    private String jiGuangPwd;
    private String jobTitle;
    private String name;
    private int reviewStatus;
    private String sessionId;
    private String userName;
    private int whetherHaveImagePic;
    private String imagePic;
    int status;//记录本地用户登录状态，用于直接登录和退出,1:登录，0：未登录或退出
    @Generated(hash = 852058225)
    public Login(Long id, int departmentId, String departmentName, String inauguralHospital, String jiGuangPwd, String jobTitle, String name, int reviewStatus, String sessionId, String userName,
            int whetherHaveImagePic, String imagePic, int status) {
        this.id = id;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.inauguralHospital = inauguralHospital;
        this.jiGuangPwd = jiGuangPwd;
        this.jobTitle = jobTitle;
        this.name = name;
        this.reviewStatus = reviewStatus;
        this.sessionId = sessionId;
        this.userName = userName;
        this.whetherHaveImagePic = whetherHaveImagePic;
        this.imagePic = imagePic;
        this.status = status;
    }
    @Generated(hash = 1827378950)
    public Login() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getDepartmentId() {
        return this.departmentId;
    }
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    public String getDepartmentName() {
        return this.departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getInauguralHospital() {
        return this.inauguralHospital;
    }
    public void setInauguralHospital(String inauguralHospital) {
        this.inauguralHospital = inauguralHospital;
    }
    public String getJiGuangPwd() {
        return this.jiGuangPwd;
    }
    public void setJiGuangPwd(String jiGuangPwd) {
        this.jiGuangPwd = jiGuangPwd;
    }
    public String getJobTitle() {
        return this.jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getReviewStatus() {
        return this.reviewStatus;
    }
    public void setReviewStatus(int reviewStatus) {
        this.reviewStatus = reviewStatus;
    }
    public String getSessionId() {
        return this.sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getWhetherHaveImagePic() {
        return this.whetherHaveImagePic;
    }
    public void setWhetherHaveImagePic(int whetherHaveImagePic) {
        this.whetherHaveImagePic = whetherHaveImagePic;
    }
    public String getImagePic() {
        return this.imagePic;
    }
    public void setImagePic(String imagePic) {
        this.imagePic = imagePic;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
  
   
}
