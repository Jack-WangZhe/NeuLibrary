package nl.neulibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapters.attentionListAdapter;
import adapters.fansListAdapter;
import beans.attentionListBean;
import beans.fansListBean;
import tools.removeTitle;

public class FansActivity extends AppCompatActivity implements View.OnClickListener{
    //对应控件
    private ImageView back_self; //返回个人界面
    private fansListAdapter fansListAdapter;
    private ListView fansList; //关注列表
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeTitle.remove(this);
        setContentView(R.layout.activity_fans);
        initView();
        initClickView();
        firstRequest();
    }


    //初始化控件
    public void initView(){
        back_self = (ImageView)findViewById(R.id.back_self);
        fansList = (ListView)findViewById(R.id.fansList);
    }

    //初始化控件点击监听
    public void initClickView(){
        back_self.setOnClickListener(this);
    }

    public void initReadBooksDatas(String datas){
        //解析字符串
        //以下为模拟数据
        List<fansListBean> mDatas = new ArrayList<fansListBean>();
        //将数据装到集合中去
        fansListBean bean = new fansListBean("000001","挠心猫",R.drawable.user_photo09);
        mDatas.add(bean);
        bean = new fansListBean("000006","兔大萌",R.drawable.user_photo06);
        mDatas.add(bean);
        bean = new fansListBean("000007","送分小仙女□",R.drawable.user_photo07);
        mDatas.add(bean);
        bean = new fansListBean("000008","权萌萌@",R.drawable.user_photo08);
        mDatas.add(bean);
        bean = new fansListBean("000009","老酸奶。",R.drawable.user_photo01);
        mDatas.add(bean);
        bean = new fansListBean("000010","天线宝宝",R.drawable.user_photo10);
        mDatas.add(bean);
        bean = new fansListBean("000011","卡基麻>.<",R.drawable.user_photo11);
        mDatas.add(bean);
        bean = new fansListBean("000002","樱子小孩",R.drawable.user_photo02);
        mDatas.add(bean);
        bean = new fansListBean("000003","咕噜萌妹",R.drawable.user_photo03);
        mDatas.add(bean);
        bean = new fansListBean("000004","花心超人",R.drawable.user_photo04);
        mDatas.add(bean);
        bean = new fansListBean("000005","我爱爽歪歪~",R.drawable.user_photo05);
        mDatas.add(bean);
        //为数据绑定适配器
        fansListAdapter = new fansListAdapter(this, mDatas);
        fansList.setAdapter(fansListAdapter);
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