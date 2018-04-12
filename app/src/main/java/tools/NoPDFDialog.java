package tools;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import nl.neulibrary.R;

/**
 * 无pdf书弹出框
 */

public class NoPDFDialog extends Dialog implements View.OnClickListener {
    private ImageView bookPicture;  //书图
    private TextView bookName;  //书名
    private TextView remainNum; //剩余书数
    private TextView publishOrg; //出版社
    private TextView authorName; //书作者
    private TextView bookLocation; //书地址
    private Button joinBookself;  //加入书架
    private LinearLayout recentBorrowUser;  //借阅用户
    private ImageView dialogCancel;  //关闭对话框

    private Context mContext;
    private OnCloseListener listener;
    private int book_pic_url;
    private String book_name;
    private String remain_num;
    private String publish_org;
    private String author_name;
    private String book_location;
    private String recent_borrow_user;

    public NoPDFDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public NoPDFDialog(Context context, int themeResId, int book_pic_url,String book_name,String remain_num,String publish_org,String author_name,String book_location,String recent_borrow_user, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.listener = listener;
        this.book_pic_url = book_pic_url;
        this.book_name = book_name;
        this.remain_num = remain_num;
        this.publish_org = publish_org;
        this.author_name = author_name;
        this.book_location = book_location;
        this.recent_borrow_user = recent_borrow_user;
    }

    protected NoPDFDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_no_pdf_dialog);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        bookPicture = (ImageView)findViewById(R.id.bookPicture);
        bookPicture.setImageResource(book_pic_url);
        //发送volley请求更新书图片

        bookName = (TextView)findViewById(R.id.bookName);
        bookName.setText(book_name);

        remainNum = (TextView)findViewById(R.id.remainNum);
        remainNum.setText(remain_num);

        publishOrg = (TextView)findViewById(R.id.publishOrg);
        publishOrg.setText(publish_org);

        authorName = (TextView)findViewById(R.id.authorName);
        authorName.setText(author_name);

        bookLocation = (TextView)findViewById(R.id.bookLocation);
        bookLocation.setText(book_location);

        joinBookself = (Button)findViewById(R.id.joinBookself);
        joinBookself.setOnClickListener(this);

        recentBorrowUser = (LinearLayout) findViewById(R.id.recentBorrowUser);
        addBorrowUser();

        dialogCancel = (ImageView) findViewById(R.id.dialogCancel);
        dialogCancel.setOnClickListener(this);
    }

    public void addBorrowUser(){
        //需要对borrowUser字符串进行解析

        //以下模拟解析结果，加载借阅用户
        DialogBorrowUserInfoLayout borrowUser1 = new DialogBorrowUserInfoLayout(mContext,"","Daming","2018.1.2");
        recentBorrowUser.addView(borrowUser1);
        DialogBorrowUserInfoLayout borrowUser2 = new DialogBorrowUserInfoLayout(mContext,"","Sam","2018.1.3");
        recentBorrowUser.addView(borrowUser2);
        DialogBorrowUserInfoLayout borrowUser3 = new DialogBorrowUserInfoLayout(mContext,"","Amy","2018.1.4");
        recentBorrowUser.addView(borrowUser3);
        DialogBorrowUserInfoLayout borrowUser4 = new DialogBorrowUserInfoLayout(mContext,"","Jack","2018.1.5");
        recentBorrowUser.addView(borrowUser4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialogCancel:
                if(listener != null){
                    listener.onClick(this, false);
                }
                this.dismiss();
                break;
            case R.id.joinBookself:
                if(listener != null){
                    listener.onClick(this, true);
                }
                break;
        }
    }

    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm);
    }
}
