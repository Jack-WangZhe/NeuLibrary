package beans;

public class renewBookListBean {
    private String bookId;
    private String bookName;
    private String bookAuthor;
    private String bookInfo;
    private int book_image;
    private String book_url;
    private Boolean borrowStatus;
    private String timeOver;

    public Boolean getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(Boolean borrowStatus) {
        this.borrowStatus = borrowStatus;
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


    public renewBookListBean(String bookId, String bookName, String bookAuthor, String bookInfo, int book_image, Boolean borrowStatus, String timeOver, Boolean isPDF) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookInfo = bookInfo;
        this.book_image = book_image;
        this.borrowStatus = borrowStatus;
        this.timeOver = timeOver;
        this.isPDF = isPDF;
    }

    public renewBookListBean(String bookId, String bookName, String bookAuthor, String bookInfo, String book_url, Boolean borrowStatus, String timeOver, Boolean isPDF) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookInfo = bookInfo;
        this.book_url = book_url;
        this.borrowStatus = borrowStatus;
        this.timeOver = timeOver;
        this.isPDF = isPDF;
    }
}
