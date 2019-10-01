package com.dingtao.common.bean;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public class Jianyi {
    private String releaseUserId;
    private String releaseUserNickName;
    private String releaseUserHeadPic;
    private String title;

    public Jianyi(String releaseUserId, String releaseUserNickName, String releaseUserHeadPic, String title) {
        this.releaseUserId = releaseUserId;
        this.releaseUserNickName = releaseUserNickName;
        this.releaseUserHeadPic = releaseUserHeadPic;
        this.title = title;
    }

    public String getReleaseUserId() {
        return releaseUserId;
    }

    public void setReleaseUserId(String releaseUserId) {
        this.releaseUserId = releaseUserId;
    }

    public String getReleaseUserNickName() {
        return releaseUserNickName;
    }

    public void setReleaseUserNickName(String releaseUserNickName) {
        this.releaseUserNickName = releaseUserNickName;
    }

    public String getReleaseUserHeadPic() {
        return releaseUserHeadPic;
    }

    public void setReleaseUserHeadPic(String releaseUserHeadPic) {
        this.releaseUserHeadPic = releaseUserHeadPic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
