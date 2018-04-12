package beans;

/**
 * 操场=>用户书友圈
 */

public class playgroundListBean {
    private String userPhotoURL;  //用户头像URL
    private String userName;  //用户名
    private String userSendTime;  //用户发送时间
    private Boolean userConcernSatatus;  //用户关注状态
    private String imageInfoURL;  //图片地址URL
    private String itemDesc;  //信息描述
    private String praiseNum;  //点赞数
    private String commentNum;  //评论数

    public playgroundListBean(){

    }

    public playgroundListBean(String userPhotoURL, String userName, String userSendTime, Boolean userConcernSatatus, String imageInfoURL, String itemDesc, String praiseNum, String commentNum) {
        this.userPhotoURL = userPhotoURL;
        this.userName = userName;
        this.userSendTime = userSendTime;
        this.userConcernSatatus = userConcernSatatus;
        this.imageInfoURL = imageInfoURL;
        this.itemDesc = itemDesc;
        this.praiseNum = praiseNum;
        this.commentNum = commentNum;
    }

    public String getUserPhotoURL() {
        return userPhotoURL;
    }

    public void setUserPhotoURL(String userPhotoURL) {
        this.userPhotoURL = userPhotoURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSendTime() {
        return userSendTime;
    }

    public void setUserSendTime(String userSendTime) {
        this.userSendTime = userSendTime;
    }

    public Boolean getUserConcernSatatus() {
        return userConcernSatatus;
    }

    public void setUserConcernSatatus(Boolean userConcernSatatus) {
        this.userConcernSatatus = userConcernSatatus;
    }

    public String getImageInfoURL() {
        return imageInfoURL;
    }

    public void setImageInfoURL(String imageInfoURL) {
        this.imageInfoURL = imageInfoURL;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(String praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }
}
