package nl.neulibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import netRequest.OnGetFinishListener;
import netRequest.Urls;
import netRequest.getMethod;
import tools.HomeBooksLinearLayout;
import tools.bookSelfLine;
import tools.removeTitle;

public class selfBookselfActivity extends AppCompatActivity implements View.OnClickListener{
    //对应控件
    private ImageView back_self; //返回个人界面
    private LinearLayout bookSelf;
    private ImageView addBookSelf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        removeTitle.remove(this);
        setContentView(R.layout.activity_self_bookself);
        initView();
        initClickView();
        firstRequest();
    }

    //初始化控件
    public void initView(){
        back_self = (ImageView)findViewById(R.id.back_self);
        bookSelf = (LinearLayout) findViewById(R.id.bookSelf);
        addBookSelf = (ImageView)findViewById(R.id.addBookSelf);

    }

    //初始化控件点击监听
    public void initClickView(){
        back_self.setOnClickListener(this);
        addBookSelf.setOnClickListener(this);
    }

    public void firstRequest(){
        getMethod getBookselflist = new getMethod();
        getBookselflist.setOnFinishListener(new OnGetFinishListener() {
            @Override
            public void OnGetFinished(String backInfo) {
                try {
                    JSONObject back=new JSONObject(backInfo);
                    if (back.getBoolean("status")){
                        JSONArray info = back.getJSONArray("info");
                        int length = info.length();
                        int times=(int)length/3+1;
                        for(int i=0;i<times;i++){
                            HomeBooksLinearLayout bookline = new HomeBooksLinearLayout(selfBookselfActivity.this);
                            if (length-i*3-3<0){
                                bookline.setBookNum(length-i*3);
                            }
                            for(int j=1;j<=3;j++){
                                if (i*3+j>length){
                                    break;
                                }else{
                                    JSONObject infoList=info.getJSONObject(i*3+j-1);
                                    bookline.setBookInfo(selfBookselfActivity.this,infoList.getString("bookId"),j,infoList.getString("book_url"),infoList.getString("bookName"),infoList.getBoolean("bookStatus"),infoList.getBoolean("bookPDF"));
                                }
                            }
                            bookSelf.addView(bookline);
                            bookSelfLine line = new bookSelfLine(selfBookselfActivity.this);
                            bookSelf.addView(line);
                        }
                    }else{
                        String info = back.getString("info");
                        Toast.makeText(selfBookselfActivity.this,info,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        getBookselflist.getInfo(this, Urls.getBookselfs);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.back_self:
                finish();
                break;
            case R.id.addBookSelf:
                Intent toSearch = new Intent(this,serviceSearchBook.class);
                toSearch.putExtra("SearchInfo","");
                startActivity(toSearch);
                finish();
                break;
        }
    }
}
