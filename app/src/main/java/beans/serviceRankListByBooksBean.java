package beans;

/**
 * 排行榜列表:根据阅读书籍
 */

public class serviceRankListByBooksBean {
    public int rankNumber;
    public String userId;
    public String userPhotoURL;
    public String userName;
    public String userReadBooksNumber;
    public int userPhotoImage;

    public serviceRankListByBooksBean(int rankNumber, String userId, String userPhotoURL, String userName, String userReadBooksNumber) {
        this.rankNumber = rankNumber;
        this.userId = userId;
        this.userPhotoURL = userPhotoURL;
        this.userName = userName;
        this.userReadBooksNumber = userReadBooksNumber;
    }

    public serviceRankListByBooksBean(int rankNumber, String userId, String userName, String userReadBooksNumber, int userPhotoImage) {
        this.rankNumber = rankNumber;
        this.userId = userId;
        this.userName = userName;
        this.userReadBooksNumber = userReadBooksNumber;
        this.userPhotoImage = userPhotoImage;
    }

    public int getUserPhotoImage() {
        return userPhotoImage;
    }

    public void setUserPhotoImage(int userPhotoImage) {
        this.userPhotoImage = userPhotoImage;
    }

    public String getUserReadBooksNumber() {
        return userReadBooksNumber;
    }

    public void setUserReadBooksNumber(String userReadBooksNumber) {
        this.userReadBooksNumber = userReadBooksNumber;
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

}
