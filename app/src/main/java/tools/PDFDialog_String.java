package tools;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import nl.neulibrary.R;

/**
 * 无pdf书弹出框
 */

public class PDFDialog_String extends Dialog implements View.OnClickListener {
    private ImageView bookPicture;  //书图
    private TextView bookName;  //书名
    private TextView remainNum; //剩余书数
    private TextView publishOrg; //出版社
    private TextView authorName; //书作者
    private TextView bookLocation; //书地址
    private Button joinBookself;  //加入书架
    private Button readBook;  //阅读
    private TextView bookIntro; //书籍简介
    private ImageView dialogCancel;  //关闭对话框

    private Context mContext;
    private OnCloseListener listener;
    private String book_pic_url;
    private String book_name;
    private String remain_num;
    private String publish_org;
    private String author_name;
    private String book_location;
    private String book_intro;

    public PDFDialog_String(Context context) {
        super(context);
        this.mContext = context;
    }

    public PDFDialog_String(Context context, int themeResId, String book_pic_url, String book_name, String remain_num, String publish_org, String author_name, String book_location, String book_intro, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.listener = listener;
        this.book_pic_url = book_pic_url;
        this.book_name = book_name;
        this.remain_num = remain_num;
        this.publish_org = publish_org;
        this.author_name = author_name;
        this.book_location = book_location;
        this.book_intro= book_intro;
    }

    protected PDFDialog_String(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_pdf_dialog);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        bookPicture = (ImageView)findViewById(R.id.bookPicture);
        bookPicture.setTag(book_pic_url);
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        ImageLoader.ImageListener listener1 = revoidImageRepeat.getImageListener(bookPicture, R.drawable.loading_on, R.drawable.loading_wrong);
        imageLoader.get(book_pic_url, listener1);
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

        readBook = (Button)findViewById(R.id.readBook);
        readBook.setOnClickListener(this);

        bookIntro = (TextView)findViewById(R.id.bookIntro);
        bookIntro.setText(book_intro);

        dialogCancel = (ImageView) findViewById(R.id.dialogCancel);
        dialogCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialogCancel:
                if(listener != null){
                    listener.onClick(this, false,"cancel");
                }
                this.dismiss();
                break;
            case R.id.joinBookself:
                if(listener != null){
                    listener.onClick(this, true,"joinBookself");
                }
                break;
            case R.id.readBook:
                if(listener != null){
                    listener.onClick(this, true,"readBook");
                }
                break;
        }
    }

    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm, String obj);
    }


}
