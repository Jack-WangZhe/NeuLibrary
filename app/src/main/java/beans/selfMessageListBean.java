package beans;

/**
 * 我的消息
 */

public class selfMessageListBean {
    private String userId;
    private String userName;
    private String userPhotoURL;
    private String userMessageType;
    private String userMessageInfo;
    private String userCommentInfo;

    public selfMessageListBean(String userId, String userName, String userPhotoURL, String userMessageType, String userMessageInfo, String userCommentInfo) {
        this.userId = userId;
        this.userName = userName;
        this.userPhotoURL = userPhotoURL;
        this.userMessageType = userMessageType;
        this.userMessageInfo = userMessageInfo;
        this.userCommentInfo = userCommentInfo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhotoURL() {
        return userPhotoURL;
    }

    public void setUserPhotoURL(String userPhotoURL) {
        this.userPhotoURL = userPhotoURL;
    }

    public String getUserMessageType() {
        return userMessageType;
    }

    public void setUserMessageType(String userMessageType) {
        this.userMessageType = userMessageType;
    }

    public String getUserMessageInfo() {
        return userMessageInfo;
    }

    public void setUserMessageInfo(String userMessageInfo) {
        this.userMessageInfo = userMessageInfo;
    }

    public String getUserCommentInfo() {
        return userCommentInfo;
    }

    public void setUserCommentInfo(String userCommentInfo) {
        this.userCommentInfo = userCommentInfo;
    }
}
