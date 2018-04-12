package nl.neulibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapters.borrowBookListAdapter;
import adapters.renewBookListAdapter;
import beans.borrowBookListBean;
import beans.renewBookListBean;
import tools.removeTitle;

public class renewBookActivity extends AppCompatActivity implements View.OnClickListener{

    //对应控件
    private ImageView back_self; //返回个人界面
    private ListView borrowBookList;
    private renewBookListAdapter itemListViewAdapterWithViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeTitle.remove(this);
        setContentView(R.layout.activity_renew_book);
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
        List<renewBookListBean> mDatas = new ArrayList<renewBookListBean>();
        //将数据装到集合中去
        renewBookListBean bean = new renewBookListBean("000001","岁月留声","王学泰","人们只有经历了狂风骤雨之后，才懂得安逸自得的可贵。",R.drawable.book12,true,"2018-5-10",false);
        mDatas.add(bean);
        bean = new renewBookListBean("000002","鲁宾逊漂流记","丹尼尔 笛福","该作主要讲述了主人公鲁滨逊出生于一个中产阶级家庭，一生志在遨游四海。",R.drawable.book13,false,"2018-4-30",false);
        mDatas.add(bean);

        //为数据绑定适配器
        itemListViewAdapterWithViewHolder = new renewBookListAdapter(this, mDatas);

        borrowBookList.setAdapter(itemListViewAdapterWithViewHolder);
    }
}
