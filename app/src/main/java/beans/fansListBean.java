package beans;


public class fansListBean {
    private String userId;
    private String userName;
    private String userImageURL;
    private int userImageDrawable;

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

    public String getUserImageURL() {
        return userImageURL;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    public int getUserImageDrawable() {
        return userImageDrawable;
    }

    public void setUserImageDrawable(int userImageDrawable) {
        this.userImageDrawable = userImageDrawable;
    }

    public fansListBean(String userId, String userName, String userImageURL) {
        this.userId = userId;
        this.userName = userName;
        this.userImageURL = userImageURL;
    }

    public fansListBean(String userId, String userName, int userImageDrawable) {
        this.userId = userId;
        this.userName = userName;
        this.userImageDrawable = userImageDrawable;
    }
}
