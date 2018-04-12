package nl.neulibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapters.selfDynamicListAdapter;
import beans.selfDynamicListBean;
import tools.removeTitle;

public class selfDynamicActivity extends AppCompatActivity implements View.OnClickListener{
    //对应控件
    private ImageView back_self; //返回个人界面
    private selfDynamicListAdapter selfDynamicListAdapter;
    private ListView selfDynamicList; //我的动态列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        removeTitle.remove(this);
        setContentView(R.layout.activity_self_dynamic);
        initView();
        initClickView();
        firstRequest();
    }

    //初始化控件
    public void initView(){
        back_self = (ImageView)findViewById(R.id.back_self);
        selfDynamicList = (ListView)findViewById(R.id.selfDynamicList);
    }

    //初始化控件点击监听
    public void initClickView(){
        back_self.setOnClickListener(this);
    }

    public void initReadBooksDatas(String datas){
        //解析字符串
        //以下为模拟数据
        List<selfDynamicListBean> mDatas = new ArrayList<selfDynamicListBean>();
        //将数据装到集合中去
        selfDynamicListBean bean = new selfDynamicListBean("","EVA-Sun","1分钟前","","这本书很好值得推荐","10","0");
        mDatas.add(bean);
        bean = new selfDynamicListBean("","EVA-Sun","2分钟前","","这本书特别好，值得推荐","7","3");
        mDatas.add(bean);
        bean = new selfDynamicListBean("","EVA-Sun","3分钟前","","这本书特别好，值得推荐","7","3");
        mDatas.add(bean);
        bean = new selfDynamicListBean("","EVA-Sun","4分钟前","","这本书特别好，值得推荐","7","3");
        mDatas.add(bean);
        bean = new selfDynamicListBean("","EVA-Sun","5分钟前","","这本书特别好，值得推荐","7","3");
        mDatas.add(bean);
        bean = new selfDynamicListBean("","EVA-Sun","6分钟前","","这本书特别好，值得推荐","7","3");
        mDatas.add(bean);
        //为数据绑定适配器
        selfDynamicListAdapter = new selfDynamicListAdapter(this, mDatas);
        selfDynamicList.setAdapter(selfDynamicListAdapter);
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
