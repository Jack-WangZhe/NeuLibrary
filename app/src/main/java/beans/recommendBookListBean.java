package beans;

public class recommendBookListBean {
    private String bookId;
    private String bookName;
    private String bookAuthor;
    private String bookInfo;
    private Boolean bookstatus;
    private int book_image;
    private String book_url;

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

    public Boolean getBookstatus() {
        return bookstatus;
    }

    public void setBookstatus(Boolean bookstatus) {
        this.bookstatus = bookstatus;
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

    public recommendBookListBean(String bookId, String bookName, String bookAuthor, String bookInfo, Boolean bookstatus, int book_image,Boolean isPDF) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookInfo = bookInfo;
        this.bookstatus = bookstatus;
        this.book_image = book_image;
        this.isPDF = isPDF;
    }

    public recommendBookListBean(String bookId, String bookName, String bookAuthor, String bookInfo, Boolean bookstatus, String book_url,Boolean isPDF) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookInfo = bookInfo;
        this.bookstatus = bookstatus;
        this.book_url = book_url;
        this.isPDF = isPDF;
    }
}
