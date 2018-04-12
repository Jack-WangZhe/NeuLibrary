package beans;

public class rebackBookListBean {
    private String bookId;
    private String bookName;
    private String bookAuthor;
    private String bookInfo;
    private int book_image;
    private String book_url;
    private Boolean rebackStatus;
    private String timeOver;
    private String rebackTime;

    public String getRebackTime() {
        return rebackTime;
    }

    public void setRebackTime(String rebackTime) {
        this.rebackTime = rebackTime;
    }

    public Boolean getRebackStatus() {
        return rebackStatus;
    }

    public void setRebackStatus(Boolean rebackStatus) {
        this.rebackStatus = rebackStatus;
    }

    public String getTimeOver() {
        return timeOver;
    }

    public void setTimeOver(String timeOver) {
        this.timeOver = timeOver;
    }

    public Boolean getPDF() {
        return isPDF;
    }

    public void setPDF(Boolean PDF) {
        isPDF = PDF;
    }

    private Boolean isPDF;

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

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public int getBook_image() {
        return book_image;
    }

    public void setBook_image(int book_image) {
        this.book_image = book_image;
    }

    public String getBook_url() {
        return book_url;
    }

    public void setBook_url(String book_url) {
        this.book_url = book_url;
    }


    public rebackBookListBean(String bookId, String bookName, String bookAuthor, String bookInfo, int book_image, Boolean rebackStatus, String timeOver, Boolean isPDF,String rebackTime) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookInfo = bookInfo;
        this.book_image = book_image;
        this.rebackStatus = rebackStatus;
        this.timeOver = timeOver;
        this.isPDF = isPDF;
        this.rebackTime = rebackTime;
    }

    public rebackBookListBean(String bookId, String bookName, String bookAuthor, String bookInfo, String book_url, Boolean rebackStatus, String timeOver, Boolean isPDF,String rebackTime) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookInfo = bookInfo;
        this.book_url = book_url;
        this.rebackStatus = rebackStatus;
        this.timeOver = timeOver;
        this.isPDF = isPDF;
        this.rebackTime = rebackTime;
    }
}
