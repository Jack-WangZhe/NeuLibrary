package nl.neulibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.attentionListAdapter;
import beans.attentionListBean;
import netRequest.OnGetFinishListener;
import netRequest.Urls;
import netRequest.getMethod;
import tools.removeTitle;

public class AttentionActivity extends AppCompatActivity implements View.OnClickListener{
    //对应控件
    private ImageView back_self; //返回个人界面
    private attentionListAdapter attentionListAdapter;
    private ListView attentionList; //关注列表
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeTitle.remove(this);
        setContentView(R.layout.activity_attention);
        initView();
        getIntentInfo();
        initClickView();
        firstRequest();
    }

    public void getIntentInfo(){
        Intent infor = getIntent();
        Bundle bundle = infor.getExtras();
        userId = bundle.getString("userId");
    }

    //初始化控件
    public void initView(){
        back_self = (ImageView)findViewById(R.id.back_self);
        attentionList = (ListView)findViewById(R.id.attentionList);
    }

    //初始化控件点击监听
    public void initClickView(){
        back_self.setOnClickListener(this);
    }

//    public void initReadBooksDatas(String datas){
//        //解析字符串
//        //以下为模拟数据
//        List<attentionListBean> mDatas = new ArrayList<attentionListBean>();
//        //将数据装到集合中去
//        attentionListBean bean = new attentionListBean("000001","天线宝宝",R.drawable.user_photo01);
//        mDatas.add(bean);
//        bean = new attentionListBean("000002","樱子小孩",R.drawable.user_photo02);
//        mDatas.add(bean);
//        bean = new attentionListBean("000003","咕噜萌妹",R.drawable.user_photo03);
//        mDatas.add(bean);
//        bean = new attentionListBean("000004","花心超人",R.drawable.user_photo04);
//        mDatas.add(bean);
//        bean = new attentionListBean("000005","我爱爽歪歪~",R.drawable.user_photo05);
//        mDatas.add(bean);
//        bean = new attentionListBean("000006","兔大萌",R.drawable.user_photo06);
//        mDatas.add(bean);
//        bean = new attentionListBean("000007","送分小仙女□",R.drawable.user_photo07);
//        mDatas.add(bean);
//        bean = new attentionListBean("000008","权萌萌@",R.drawable.user_photo08);
//        mDatas.add(bean);
//        bean = new attentionListBean("000009","老酸奶。",R.drawable.user_photo09);
//        mDatas.add(bean);
//        bean = new attentionListBean("000010","挠心猫",R.drawable.user_photo10);
//        mDatas.add(bean);
//        bean = new attentionListBean("000011","卡基麻>.<",R.drawable.user_photo11);
//        mDatas.add(bean);
//        //为数据绑定适配器
//        attentionListAdapter = new attentionListAdapter(this, mDatas);
//        attentionList.setAdapter(attentionListAdapter);
//    }

    public void firstRequest(){
        getMethod getUserAttentions=new getMethod();
        getUserAttentions.setOnFinishListener(new OnGetFinishListener() {
            @Override
            public void OnGetFinished(String backInfo) {
                try {
                    JSONObject back=new JSONObject(backInfo);
                    if(back.getBoolean("status")){
                        JSONArray info = back.getJSONArray("info");
                        List<attentionListBean> mDatas = new ArrayList<attentionListBean>();
                        attentionListBean bean;
                        for(int i=0;i<info.length();i++){
                            JSONObject user_info=info.getJSONObject(i);
                            bean = new attentionListBean(user_info.getString("userId"),user_info.getString("userName"),user_info.getString("userPhotoURL"));
                            mDatas.add(bean);
                        }
                        //为数据绑定适配器
                        attentionListAdapter = new attentionListAdapter(AttentionActivity.this, mDatas);
                        attentionList.setAdapter(attentionListAdapter);
                    }else {
                        String info = back.getString("info");
                        Toast.makeText(AttentionActivity.this,info,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        getUserAttentions.getInfo(AttentionActivity.this, Urls.getUserAttentions+"?userId="+userId);
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
