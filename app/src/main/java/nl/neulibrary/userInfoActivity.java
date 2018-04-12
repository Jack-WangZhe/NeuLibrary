package nl.neulibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapters.selfDynamicListAdapter;
import adapters.userInfoDynamicListAdapter;
import beans.selfDynamicListBean;
import tools.removeTitle;

public class userInfoActivity extends AppCompatActivity implements View.OnClickListener{
    //对应控件
    private ImageView back_self; //返回个人界面
    private userInfoDynamicListAdapter selfDynamicListAdapter;
    private ListView userDynamics;
    private TextView userName;
    private Button concernUser;
    private Button concernedUser;
    private TextView userIntegralNum;
    private TextView userRankNum;
    private TextView fansNum;
    private TextView AttentionsNum;
    private TextView readNum;
    private LinearLayout userFans;
    private LinearLayout userAttentions;
    private LinearLayout userRecentlyReads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeTitle.remove(this);
        setContentView(R.layout.activity_user_info);
        initView();
        getIntentInfo();
        initClickView();
        firstRequest();
    }

    public void getIntentInfo(){
        Intent infor = getIntent();
        Bundle bundle = infor.getExtras();
        Boolean isUserSelf = bundle.getBoolean("isUserSelf");
        if (isUserSelf){
            concernUser.setVisibility(View.INVISIBLE);
        }else{

        }
    }

    //初始化控件
    public void initView(){
        back_self = (ImageView)findViewById(R.id.back_self);
        userDynamics = (ListView)findViewById(R.id.userDynamics);
        userName = (TextView)findViewById(R.id.userName);
        concernUser = (Button)findViewById(R.id.concernUser);
        concernedUser = (Button)findViewById(R.id.concernedUser);
        userIntegralNum = (TextView)findViewById(R.id.userIntegralNum);
        userRankNum = (TextView)findViewById(R.id.userRankNum);
        fansNum = (TextView)findViewById(R.id.fansNum);
        AttentionsNum = (TextView)findViewById(R.id.AttentionsNum);
        readNum = (TextView)findViewById(R.id.readNum);
        userFans = (LinearLayout)findViewById(R.id.userFans);
        userAttentions = (LinearLayout)findViewById(R.id.userAttentions);
        userRecentlyReads = (LinearLayout)findViewById(R.id.userRecentlyReads);
    }

    //初始化控件点击监听
    public void initClickView(){
        back_self.setOnClickListener(this);
        userFans.setOnClickListener(this);
        userAttentions.setOnClickListener(this);
        userRecentlyReads.setOnClickListener(this);
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
        selfDynamicListAdapter = new userInfoDynamicListAdapter(this, mDatas);
        userDynamics.setAdapter(selfDynamicListAdapter);
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
            case R.id.userFans:
                Intent toFans = new Intent(this,FansActivity.class);
                startActivity(toFans);
                break;
            case R.id.userAttentions:
                Intent toAttention = new Intent(this,AttentionActivity.class);
                startActivity(toAttention);
                break;
            case R.id.userRecentlyReads:
                break;
        }
    }
}
