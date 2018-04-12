package nl.neulibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapters.selfMessageListAdapter;
import beans.selfMessageListBean;
import tools.removeTitle;

public class selfMessageActivity extends AppCompatActivity implements View.OnClickListener{
    //对应控件
    private ImageView back_self; //返回个人界面
    private ListView selfMessageList;
    private selfMessageListAdapter selfMessageListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeTitle.remove(this);
        setContentView(R.layout.activity_self_message);
        initView();
        initClickView();
        firstRequest();
    }

    //初始化控件
    public void initView(){
        back_self = (ImageView)findViewById(R.id.back_self);
        selfMessageList=(ListView)findViewById(R.id.selfMessageList);
    }

    public void initMessageDatas(String datas){
        //解析字符串
        //以下为模拟数据
        List<selfMessageListBean> mDatas = new ArrayList<selfMessageListBean>();
        //将数据装到集合中去
        selfMessageListBean bean = new selfMessageListBean("1234","Jack","","0","评论了您的动态","这是一本什么书？");
        mDatas.add(bean);
        bean = new selfMessageListBean("1235","Sam","","1","赞了您的动态","");
        mDatas.add(bean);
        bean = new selfMessageListBean("1236","Daming","","1","赞了您的动态","");
        mDatas.add(bean);
        bean = new selfMessageListBean("1237","Amy","","0","评论了您的动态","这么晚了还在工作啊！");
        mDatas.add(bean);
        bean = new selfMessageListBean("1238","Sally","","0","评论了您的动态","感觉你都要困死了...");
        mDatas.add(bean);
        bean = new selfMessageListBean("1239","Jerry","","0","评论了您的动态","o my god,最后一个可算弄完了...");
        mDatas.add(bean);
        //为数据绑定适配器
        selfMessageListAdapter = new selfMessageListAdapter(this, mDatas);
        selfMessageList.setAdapter(selfMessageListAdapter);
    }

    public void firstRequest(){
        initMessageDatas("");
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
}
