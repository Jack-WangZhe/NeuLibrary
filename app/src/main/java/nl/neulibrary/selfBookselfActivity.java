package nl.neulibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

    public void initReadBooksDatas(String datas){
        for (int i = 1;i<=4;i++){
            HomeBooksLinearLayout bookline = new HomeBooksLinearLayout(this);
            if(i==1){
                bookline.setBookInfo(this,1,R.drawable.book1,"天堂炼狱",true,true);
                bookline.setBookInfo(this,2,R.drawable.book2,"那些年",false,true);
                bookline.setBookInfo(this,3,R.drawable.book3,"Woody Allen",true,false);
            }
            else if(i==2){
                bookline.setBookInfo(this,1,R.drawable.book4,"秘密花园",true,false);
                bookline.setBookInfo(this,2,R.drawable.book5,"The Kama Stra",true,false);
                bookline.setBookInfo(this,3,R.drawable.book6,"Julio Verne",false,true);
            }
            else if(i==3){
                bookline.setBookInfo(this,1,R.drawable.book7,"窗前的花猫",false,false);
                bookline.setBookInfo(this,2,R.drawable.book8,"左腕的猫",true,true);
                bookline.setBookInfo(this,3,R.drawable.book9,"Psychopath",false,true);
            }
            else if(i==4){
                bookline.setBookNum(2);
                bookline.setBookInfo(this,1,R.drawable.book10,"HARUKI",false,false);
                bookline.setBookInfo(this,2,R.drawable.book11,"活出自我",true,true);
            }
            bookSelf.addView(bookline);
            bookSelfLine line = new bookSelfLine(this);
            bookSelf.addView(line);
        }
    }

    public void firstRequest(){
        initReadBooksDatas("");
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
