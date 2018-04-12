package beans;

/**
 * 排行榜列表:根据阅读时间
 */

public class serviceRankListByTimeBean {
    public int rankNumber;
    public String userId;
    public String userPhotoURL;
    public String userName;
    public String userLearnTime;
    public int userPhotoImage;

    public serviceRankListByTimeBean(int rankNumber, String userId, String userPhotoURL, String userName, String userLearnTime) {
        this.rankNumber = rankNumber;
        this.userId = userId;
        this.userPhotoURL = userPhotoURL;
        this.userName = userName;
        this.userLearnTime = userLearnTime;
    }

    public serviceRankListByTimeBean(int rankNumber, String userId, String userName, String userLearnTime, int userPhotoImage) {
        this.rankNumber = rankNumber;
        this.userId = userId;
        this.userName = userName;
        this.userLearnTime = userLearnTime;
        this.userPhotoImage = userPhotoImage;
    }

    public int getUserPhotoImage() {
        return userPhotoImage;
    }

    public void setUserPhotoImage(int userPhotoImage) {
        this.userPhotoImage = userPhotoImage;
    }

    public int getRankNumber() {
        return rankNumber;
    }

    public void setRankNumber(int rankNumber) {
        this.rankNumber = rankNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserLearnTime() {
        return userLearnTime;
    }

    public void setUserLearnTime(String userLearnTime) {
        this.userLearnTime = userLearnTime;
    }
}
