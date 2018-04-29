package tools;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import netRequest.OnGetFinishListener;
import netRequest.Urls;
import netRequest.getMethod;
import nl.neulibrary.BookReadActivity;
import nl.neulibrary.R;


/**
 * 首页动态布局
 */

public class HomeBooksLinearLayout extends LinearLayout{
    private ImageView bookImage1;
    private ImageView bookImage2;
    private ImageView bookImage3;
    private LinearLayout bookStatus1;
    private LinearLayout bookStatus2;
    private LinearLayout bookStatus3;
    private LinearLayout bookPDF1;
    private LinearLayout bookPDF2;
    private LinearLayout bookPDF3;
    private LinearLayout book1;
    private LinearLayout book2;
    private LinearLayout book3;
    private TextView bookName1;
    private TextView bookName2;
    private TextView bookName3;

    private Context context;
    public HomeBooksLinearLayout(Context context) {
        super(context);
        initViews(context);
    }
    public HomeBooksLinearLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        initViews(context);
    }

    //初始化控件
    public void initViews(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_test_recommend_book,this);
        bookImage1 = (ImageView) findViewById(R.id.bookImage1);
        bookImage2 = (ImageView) findViewById(R.id.bookImage2);
        bookImage3 = (ImageView) findViewById(R.id.bookImage3);
        bookStatus1 = (LinearLayout) findViewById(R.id.bookStatus1);
        bookStatus2 = (LinearLayout) findViewById(R.id.bookStatus2);
        bookStatus3 = (LinearLayout) findViewById(R.id.bookStatus3);
        bookPDF1 = (LinearLayout) findViewById(R.id.bookPDF1);
        bookPDF2 = (LinearLayout) findViewById(R.id.bookPDF2);
        bookPDF3 = (LinearLayout) findViewById(R.id.bookPDF3);
        book1 = (LinearLayout)findViewById(R.id.book1);
        book2 = (LinearLayout)findViewById(R.id.book2);
        book3 = (LinearLayout)findViewById(R.id.book3);
        bookName1 = (TextView)findViewById(R.id.bookName1);
        bookName2 = (TextView)findViewById(R.id.bookName2);
        bookName3 = (TextView)findViewById(R.id.bookName3);
        this.context = context;
    }

    //设置需要展示的书目数
    public void setBookNum(int num){
        switch (num){
            case 1:
                book2.setVisibility(View.INVISIBLE);
                book3.setVisibility(View.INVISIBLE);
                break;
            case 2:
                book3.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
    }

    //设置指定书的相关数据
    public void setBookInfo(final Context context, final String bookId, int bookImageNum, final String bookImageUrl, final String bookName, boolean bookStatus, boolean bookPDF){

        //需要将图片进行获取
        //别忘了加上
        RequestQueue mQueue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        switch (bookImageNum){
            case 1:
                if (!bookStatus){
                    bookStatus1.setVisibility(View.GONE);
                }else{
                    bookStatus1.setVisibility(View.VISIBLE);
                }
                if (!bookPDF){
                    bookPDF1.setVisibility(View.GONE);
                    book1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getMethod getPDFBookInfo = new getMethod();
                            getPDFBookInfo.setOnFinishListener(new OnGetFinishListener() {
                                @Override
                                public void OnGetFinished(String backInfo) {
                                    try {
                                        JSONObject back=new JSONObject(backInfo);
                                        if (back.getBoolean("status")){
                                            JSONObject info = back.getJSONObject("info");
                                            showNoPDFDialog(context,bookId,bookImageUrl, bookName, info.getInt("remainNum")+"", info.getString("publishOrg"), info.getString("authorName"), info.getString("bookLocation"), info.getJSONArray("recentBorrowUser"));
                                        }else{
                                            String info = back.getString("info");

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            getPDFBookInfo.getInfo(context,Urls.getNoPDFBookInfo+"?bookId="+bookId);
                        }
                    });
                }else{
                    bookPDF1.setVisibility(View.VISIBLE);
                    book1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getMethod getPDFBookInfo = new getMethod();
                            getPDFBookInfo.setOnFinishListener(new OnGetFinishListener() {
                                @Override
                                public void OnGetFinished(String backInfo) {
                                    try {
                                        JSONObject back=new JSONObject(backInfo);
                                        if (back.getBoolean("status")){
                                            JSONObject info = back.getJSONObject("info");
                                            showPDFDialog(context,bookId,bookImageUrl, bookName, info.getInt("remainNum")+"", info.getString("publishOrg"), info.getString("authorName"), info.getString("bookLocation"), info.getString("bookIntro"));
                                        }else{
                                            String info = back.getString("info");

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            getPDFBookInfo.getInfo(context,Urls.getPDFBookInfo+"?bookId="+bookId);
                        }
                    });
                }
                bookName1.setText(bookName);
                ImageLoader.ImageListener listener1 = ImageLoader.getImageListener(bookImage1, R.drawable.loading_on, R.drawable.loading_wrong);
                imageLoader.get(bookImageUrl, listener1);
                break;
            case 2:
                if (!bookStatus){
                    bookStatus2.setVisibility(View.GONE);
                }else{
                    bookStatus2.setVisibility(View.VISIBLE);
                }
                if (!bookPDF){
                    bookPDF2.setVisibility(View.GONE);
                    book2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getMethod getPDFBookInfo = new getMethod();
                            getPDFBookInfo.setOnFinishListener(new OnGetFinishListener() {
                                @Override
                                public void OnGetFinished(String backInfo) {
                                    try {
                                        JSONObject back=new JSONObject(backInfo);
                                        if (back.getBoolean("status")){
                                            JSONObject info = back.getJSONObject("info");
                                            showNoPDFDialog(context,bookId,bookImageUrl, bookName, info.getInt("remainNum")+"", info.getString("publishOrg"), info.getString("authorName"), info.getString("bookLocation"), info.getJSONArray("recentBorrowUser"));
                                        }else{
                                            String info = back.getString("info");

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            getPDFBookInfo.getInfo(context,Urls.getNoPDFBookInfo+"?bookId="+bookId);
                        }
                    });
                }else{
                    bookPDF2.setVisibility(View.VISIBLE);
                    book2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getMethod getPDFBookInfo = new getMethod();
                            getPDFBookInfo.setOnFinishListener(new OnGetFinishListener() {
                                @Override
                                public void OnGetFinished(String backInfo) {
                                    try {
                                        JSONObject back=new JSONObject(backInfo);
                                        if (back.getBoolean("status")){
                                            JSONObject info = back.getJSONObject("info");
                                            showPDFDialog(context,bookId,bookImageUrl, bookName, info.getInt("remainNum")+"", info.getString("publishOrg"), info.getString("authorName"), info.getString("bookLocation"), info.getString("bookIntro"));
                                        }else{
                                            String info = back.getString("info");

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            getPDFBookInfo.getInfo(context,Urls.getPDFBookInfo+"?bookId="+bookId);
                        }
                    });
                }
                bookName2.setText(bookName);
                ImageLoader.ImageListener listener2 = ImageLoader.getImageListener(bookImage2, R.drawable.loading_on, R.drawable.loading_wrong);
                imageLoader.get(bookImageUrl, listener2);
                break;
            case 3:
                if (!bookStatus){
                    bookStatus3.setVisibility(View.GONE);
                }else{
                    bookStatus3.setVisibility(View.VISIBLE);
                }
                if (!bookPDF){
                    bookPDF3.setVisibility(View.GONE);
                    book3.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getMethod getPDFBookInfo = new getMethod();
                            getPDFBookInfo.setOnFinishListener(new OnGetFinishListener() {
                                @Override
                                public void OnGetFinished(String backInfo) {
                                    try {
                                        JSONObject back=new JSONObject(backInfo);
                                        if (back.getBoolean("status")){
                                            JSONObject info = back.getJSONObject("info");
                                            showNoPDFDialog(context,bookId,bookImageUrl, bookName, info.getInt("remainNum")+"", info.getString("publishOrg"), info.getString("authorName"), info.getString("bookLocation"), info.getJSONArray("recentBorrowUser"));
                                        }else{
                                            String info = back.getString("info");

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            getPDFBookInfo.getInfo(context,Urls.getNoPDFBookInfo+"?bookId="+bookId);
                        }
                    });
                }else {
                    bookPDF3.setVisibility(View.VISIBLE);
                    book3.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getMethod getPDFBookInfo = new getMethod();
                            getPDFBookInfo.setOnFinishListener(new OnGetFinishListener() {
                                @Override
                                public void OnGetFinished(String backInfo) {
                                    try {
                                        JSONObject back=new JSONObject(backInfo);
                                        if (back.getBoolean("status")){
                                            JSONObject info = back.getJSONObject("info");
                                            showPDFDialog(context,bookId,bookImageUrl, bookName, info.getInt("remainNum")+"", info.getString("publishOrg"), info.getString("authorName"), info.getString("bookLocation"), info.getString("bookIntro"));
                                        }else{
                                            String info = back.getString("info");

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            getPDFBookInfo.getInfo(context,Urls.getPDFBookInfo+"?bookId="+bookId);
                        }
                    });
                }
                bookName3.setText(bookName);
                ImageLoader.ImageListener listener3 = ImageLoader.getImageListener(bookImage3, R.drawable.loading_on, R.drawable.loading_wrong);
                imageLoader.get(bookImageUrl, listener3);
                break;
        }
    }

    public void showNoPDFDialog(final Context context, final String bookId, String book_pic_url, String book_name, String remain_num, String publish_org, String author_name, String book_location, JSONArray recentBorrowUser){
        new NoPDFDialog_String(context, R.style.dialog,book_pic_url,book_name,remain_num,publish_org,author_name,book_location,recentBorrowUser,new NoPDFDialog_String.OnCloseListener(){
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm){
                    new CommomDialog(context, R.style.dialog, "您确定将此书添加到书架？", new CommomDialog.OnCloseListener(){
                        @Override
                        public void onClick(final Dialog dialog, boolean confirm) {
                            if(confirm){
                                //发送添加图书的请求信息
                                //根据返回信息进行Toast
                                getMethod addBookself=new getMethod();
                                addBookself.setOnFinishListener(new OnGetFinishListener() {
                                    @Override
                                    public void OnGetFinished(String backInfo) {
                                        try {
                                            JSONObject back=new JSONObject(backInfo);
                                            String info = back.getString("info");
                                            Toast.makeText(context,info,Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                addBookself.getInfo(context, Urls.addBookself+"?bookId="+bookId);
                            }
                        }
                    }).setTitle("提示").show();
                    dialog.dismiss();
                }
            }
        }).show();
    }

    public void showPDFDialog(final Context context, final String bookId, String book_pic_url, final String book_name, String remain_num, String publish_org, String author_name, String book_location, String book_intro){
        new PDFDialog_String(context, R.style.dialog,book_pic_url,book_name,remain_num,publish_org,author_name,book_location,book_intro,new PDFDialog_String.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm,String obj) {
                if (confirm){
                    if (obj.equals("readBook")){
                        dialog.dismiss();
                        Intent toReadBook = new Intent(context, BookReadActivity.class);
                        toReadBook.putExtra("bookId",bookId); //传bookId
                        toReadBook.putExtra("bookName",book_name); //传bookName
                        context.startActivity(toReadBook);
                    }
                    else if (obj.equals("joinBookself")){
                        new CommomDialog(context, R.style.dialog, "您确定将此书添加到书架？", new CommomDialog.OnCloseListener(){
                            @Override
                            public void onClick(final Dialog dialog, boolean confirm) {
                                if(confirm){
                                    //发送添加图书的请求信息
                                    //根据返回信息进行Toast
                                    getMethod addBookself=new getMethod();
                                    addBookself.setOnFinishListener(new OnGetFinishListener() {
                                        @Override
                                        public void OnGetFinished(String backInfo) {
                                            try {
                                                JSONObject back=new JSONObject(backInfo);
                                                String info = back.getString("info");
                                                Toast.makeText(context,info,Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                    addBookself.getInfo(context, Urls.addBookself+"?bookId="+bookId);
                                }
                            }
                        }).setTitle("提示").show();
                        dialog.dismiss();
                    }
                }
            }
        }).show();
    }
}
