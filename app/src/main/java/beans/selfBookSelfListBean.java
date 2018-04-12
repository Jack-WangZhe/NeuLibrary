package beans;

/**
 * 个人书架Bean
 */

public class selfBookSelfListBean {
    private String bookId;
    private String bookName;
    private String bookAuthor;
    private String bookURL;
    private String getTime;

    public selfBookSelfListBean(String bookId, String bookName, String bookAuthor, String bookURL, String getTime) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookURL = bookURL;
        this.getTime = getTime;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookURL() {
        return bookURL;
    }

    public void setBookURL(String bookURL) {
        this.bookURL = bookURL;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }
}
