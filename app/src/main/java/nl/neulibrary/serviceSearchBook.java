package nl.neulibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapters.selfBookSelfListAdapter;
import beans.selfBookSelfListBean;
import tools.removeTitle;

public class serviceSearchBook extends AppCompatActivity implements View.OnClickListener{
    //对应控件
    private ImageView back_self; //返回服务界面
    private ListView selfBookSelfList; //书架列表
    private selfBookSelfListAdapter selfBookSelfListAdapter;
    private EditText searchBook;
    private ImageView submitSearch;

    String searchInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        removeTitle.remove(this);
        setContentView(R.layout.activity_service_search_book);
        getIntentInfo();
        initView();
        initClickView();
    }

    public void getIntentInfo(){
        Intent getInfo = getIntent();
        Bundle bundle = getInfo.getExtras();
        searchInfo =bundle.getString("searchInfo","");
    }

    //初始化控件
    public void initView(){
        back_self = (ImageView)findViewById(R.id.back_self);
        selfBookSelfList = (ListView)findViewById(R.id.searchBookList);
        searchBook = (EditText)findViewById(R.id.searchBook);
        submitSearch = (ImageView)findViewById(R.id.submitSearch);
        if(!searchInfo.equals("")){
            searchBook.setText(searchInfo);
            firstRequest();
        }
    }

    //初始化控件点击监听
    public void initClickView(){
        back_self.setOnClickListener(this);
    }

    public void initReadBooksDatas(String datas){
        //解析字符串
        //以下为模拟数据
        List<selfBookSelfListBean> mDatas = new ArrayList<selfBookSelfListBean>();
        //将数据装到集合中去
        selfBookSelfListBean bean = new selfBookSelfListBean("#332209","程序员的编程之路","辛俊桥","","2018-4-2");
        mDatas.add(bean);
        bean = new selfBookSelfListBean("#332210","从小工到专家","汪喆","","2018-3-2");
        mDatas.add(bean);
        bean = new selfBookSelfListBean("#332211","人月神话","汪喆","","2018-3-2");
        mDatas.add(bean);
        bean = new selfBookSelfListBean("#332212","疯狂的石头","汪喆","","2018-3-2");
        mDatas.add(bean);
        bean = new selfBookSelfListBean("#332213","马哈顿的秘密","汪喆","","2018-3-2");
        mDatas.add(bean);
        bean = new selfBookSelfListBean("#332214","安迪生童话","汪喆","","2018-3-2");
        mDatas.add(bean);
        bean = new selfBookSelfListBean("#332215","java编程","汪喆","","2018-3-2");
        mDatas.add(bean);
        bean = new selfBookSelfListBean("#332216","安卓开发","汪喆","","2018-3-2");
        mDatas.add(bean);
        bean = new selfBookSelfListBean("#332217","论程序猿的阵亡之路","汪喆","","2018-3-2");
        mDatas.add(bean);
        //为数据绑定适配器
        selfBookSelfListAdapter = new selfBookSelfListAdapter(this, mDatas);
        selfBookSelfList.setAdapter(selfBookSelfListAdapter);
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
        }
    }
}
