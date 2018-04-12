package nl.neulibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapters.borrowBookListAdapter;
import adapters.rebackBookListAdapter;
import beans.borrowBookListBean;
import beans.rebackBookListBean;
import tools.removeTitle;

public class rebackBookActivity extends AppCompatActivity implements View.OnClickListener{
    //对应控件
    private ImageView back_self; //返回个人界面
    private ListView borrowBookList;
    private rebackBookListAdapter itemListViewAdapterWithViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeTitle.remove(this);
        setContentView(R.layout.activity_reback_book);
        initView();
        initClickView();
        firstRequest();
    }

    //初始化控件
    public void initView(){
        back_self = (ImageView)findViewById(R.id.back_self);
        borrowBookList = (ListView)findViewById(R.id.borrowBookList);
    }

    //初始化控件点击监听
    public void initClickView(){
        back_self.setOnClickListener(this);
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

    public void firstRequest(){
        //发送请求数据,获取最近消息
        initDatas("");
    }

    public void initDatas(String datas){
        //解析字符串
        //以下为模拟数据
        List<rebackBookListBean> mDatas = new ArrayList<rebackBookListBean>();
        //将数据装到集合中去
        rebackBookListBean bean = new rebackBookListBean("000001","岁月留声","王学泰","人们只有经历了狂风骤雨之后，才懂得安逸自得的可贵。",R.drawable.book12,true,"2018-5-10",false,"2018-5-15");
        mDatas.add(bean);
        bean = new rebackBookListBean("000002","鲁宾逊漂流记","丹尼尔 笛福","该作主要讲述了主人公鲁滨逊出生于一个中产阶级家庭，一生志在遨游四海。",R.drawable.book13,false,"2018-5-3",false,"2018-5-10");
        mDatas.add(bean);

        //为数据绑定适配器
        itemListViewAdapterWithViewHolder = new rebackBookListAdapter(this, mDatas);

        borrowBookList.setAdapter(itemListViewAdapterWithViewHolder);
    }
}
