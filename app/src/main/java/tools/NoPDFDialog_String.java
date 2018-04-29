package tools;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.neulibrary.R;

/**
 * 无pdf书弹出框
 */

public class NoPDFDialog_String extends Dialog implements View.OnClickListener {
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
    private String book_pic_url;
    private String book_name;
    private String remain_num;
    private String publish_org;
    private String author_name;
    private String book_location;
    private JSONArray recent_borrow_user;

    public NoPDFDialog_String(Context context) {
        super(context);
        this.mContext = context;
    }

    public NoPDFDialog_String(Context context, int themeResId, String book_pic_url, String book_name, String remain_num, String publish_org, String author_name, String book_location, JSONArray recent_borrow_user, OnCloseListener listener) {
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

    protected NoPDFDialog_String(Context context, boolean cancelable, OnCancelListener cancelListener) {
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
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        ImageLoader.ImageListener listener1 = ImageLoader.getImageListener(bookPicture, R.drawable.loading_on, R.drawable.loading_wrong);
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

        recentBorrowUser = (LinearLayout) findViewById(R.id.recentBorrowUser);
        addBorrowUser();

        dialogCancel = (ImageView) findViewById(R.id.dialogCancel);
        dialogCancel.setOnClickListener(this);
    }

    public void addBorrowUser(){
        //需要对borrowUser字符串进行解析
        //以下模拟解析结果，加载借阅用户
        try {
        int length=recent_borrow_user.length();
        for(int i=0;i<length;i++){
            JSONObject borrow=recent_borrow_user.getJSONObject(i);
            DialogBorrowUserInfoLayout borrowUser1 = new DialogBorrowUserInfoLayout(mContext,borrow.getString("userImg"),borrow.getString("userName"),borrow.getString("borrowTime"));
            recentBorrowUser.addView(borrowUser1);
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
