package nl.neulibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapters.serviceRankListByBooksAdapter;
import adapters.serviceRankListByTimeAdapter;
import beans.serviceRankListByBooksBean;
import beans.serviceRankListByTimeBean;
import tools.CircleImageView;
import tools.removeTitle;

public class serviceRank extends AppCompatActivity implements View.OnClickListener{
    //对应控件
    private ImageView back_self; //返回服务界面
    private LinearLayout readTime;  //阅读时间
    private LinearLayout readTimeDecoration; //阅读时间装饰下划线
    private LinearLayout readBooks;  //阅读书籍
    private LinearLayout readBooksDecoration;  //阅读书籍装饰下划线
    private TextView changeText; //阅读数量和阅读总时长的文字切换
    private CircleImageView selfPhoto; //个人头像
    private TextView rankPosition; //综合名次
    private TextView sumCal; //总计时间
    private ListView rankList; //排名List
    private serviceRankListByTimeAdapter serviceRankListByTimeAdapter;
    private serviceRankListByBooksAdapter serviceRankListByBooksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        removeTitle.remove(this);
        setContentView(R.layout.activity_service_rank);
        initView();
        initClickView();
        firstRequest();
    }

    //初始化控件
    public void initView(){
        back_self = (ImageView)findViewById(R.id.back_self);
        readTime = (LinearLayout)findViewById(R.id.readTime);
        readBooks = (LinearLayout)findViewById(R.id.readBooks);
        readTimeDecoration = (LinearLayout)findViewById(R.id.readTimeDecoration);
        readBooksDecoration = (LinearLayout)findViewById(R.id.readBooksDecoration);
        changeText = (TextView)findViewById(R.id.changeText);
        rankList = (ListView)findViewById(R.id.rankList);
        rankPosition = (TextView)findViewById(R.id.rankPosition);
        sumCal = (TextView)findViewById(R.id.sumCal);
    }

    //初始化控件点击监听
    public void initClickView(){
        back_self.setOnClickListener(this);
        readTime.setOnClickListener(this);
        readBooks.setOnClickListener(this);
    }

    public void initReadTimeDatas(String datas){
        //解析字符串
        //以下为模拟数据
        List<serviceRankListByTimeBean> mDatas = new ArrayList<serviceRankListByTimeBean>();
        //将数据装到集合中去
//        serviceRankListByTimeBean bean = new serviceRankListByTimeBean(1,"1","","SunShine","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(2,"2","","SunShine","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(3,"3","","jack","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(4,"4","","Tom","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(5,"5","","Daming","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(6,"6","","Amy","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(7,"7","","Davi","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(8,"8","","Yamo","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(9,"9","","Sam","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(10,"10","","Dota","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(11,"11","","Helly","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(12,"12","","Opener","200");
//        mDatas.add(bean);
        serviceRankListByTimeBean bean = new serviceRankListByTimeBean(1,"1","SunShine","200",R.drawable.user_photo01);
        mDatas.add(bean);
        bean = new serviceRankListByTimeBean(2,"2","Sun","170",R.drawable.user_photo02);
        mDatas.add(bean);
        bean = new serviceRankListByTimeBean(3,"3","jack","150",R.drawable.user_photo03);
        mDatas.add(bean);
        bean = new serviceRankListByTimeBean(4,"4","Tom","148",R.drawable.user_photo04);
        mDatas.add(bean);
        bean = new serviceRankListByTimeBean(5,"5","Daming","135",R.drawable.user_photo05);
        mDatas.add(bean);
        bean = new serviceRankListByTimeBean(6,"6","Amy","121",R.drawable.user_photo06);
        mDatas.add(bean);
        bean = new serviceRankListByTimeBean(7,"7","Davi","103",R.drawable.user_photo07);
        mDatas.add(bean);
        bean = new serviceRankListByTimeBean(8,"8","Yamo","101",R.drawable.user_photo08);
        mDatas.add(bean);
        bean = new serviceRankListByTimeBean(9,"9","Sam","100",R.drawable.user_photo09);
        mDatas.add(bean);
        bean = new serviceRankListByTimeBean(10,"10","Dota","70",R.drawable.user_photo10);
        mDatas.add(bean);
        bean = new serviceRankListByTimeBean(11,"11","Helly","58",R.drawable.user_photo11);
        mDatas.add(bean);
        bean = new serviceRankListByTimeBean(12,"12","Opener","55",R.drawable.self_photo);
        mDatas.add(bean);
        //为数据绑定适配器
        serviceRankListByTimeAdapter = new serviceRankListByTimeAdapter(this, mDatas);
        rankList.setAdapter(serviceRankListByTimeAdapter);
    }

    public void initReadBooksDatas(String datas){
        //解析字符串
        //以下为模拟数据
        List<serviceRankListByBooksBean> mDatas = new ArrayList<serviceRankListByBooksBean>();
        //将数据装到集合中去
//        serviceRankListByBooksBean bean = new serviceRankListByBooksBean(1,"1","","jack","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(2,"2","","Sam","400");
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(3,"3","","Amy","290");
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(4,"4","","Helly","800");
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(5,"5","","Daming","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(6,"6","","Amy","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(7,"7","","jack","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(8,"8","","Yamo","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(9,"9","","Sam","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(10,"10","","Dota","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(11,"11","","Helly","200");
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(12,"12","","Daming","200");
//        mDatas.add(bean);
        serviceRankListByBooksBean bean = new serviceRankListByBooksBean(1,"1","jack","822",R.drawable.user_photo03);
        mDatas.add(bean);
        bean = new serviceRankListByBooksBean(2,"2","Sam","801",R.drawable.user_photo05);
        mDatas.add(bean);
        bean = new serviceRankListByBooksBean(3,"3","Amy","703",R.drawable.user_photo01);
        mDatas.add(bean);
        bean = new serviceRankListByBooksBean(4,"4","Helly","600",R.drawable.user_photo09);
        mDatas.add(bean);
        bean = new serviceRankListByBooksBean(5,"5","Daming","523",R.drawable.user_photo07);
        mDatas.add(bean);
        bean = new serviceRankListByBooksBean(6,"6","Amy","412",R.drawable.user_photo11);
        mDatas.add(bean);
        bean = new serviceRankListByBooksBean(7,"7","jack","399",R.drawable.user_photo10);
        mDatas.add(bean);
        bean = new serviceRankListByBooksBean(8,"8","Yamo","302",R.drawable.user_photo08);
        mDatas.add(bean);
        bean = new serviceRankListByBooksBean(9,"9","Sam","251",R.drawable.user_photo06);
        mDatas.add(bean);
        bean = new serviceRankListByBooksBean(10,"10","Dota","200",R.drawable.self_photo);
        mDatas.add(bean);
        bean = new serviceRankListByBooksBean(11,"11","Helly","192",R.drawable.user_photo02);
        mDatas.add(bean);
        bean = new serviceRankListByBooksBean(12,"12","Daming","110",R.drawable.user_photo04);
        mDatas.add(bean);
        //为数据绑定适配器
        serviceRankListByBooksAdapter = new serviceRankListByBooksAdapter(this, mDatas);
        rankList.setAdapter(serviceRankListByBooksAdapter);
    }

    //第一次发送请求获取信息
    public void firstRequest(){
        //发送请求数据,获取最近消息
        rankPosition.setText("12");
        sumCal.setText("55");
        initReadTimeDatas("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.back_self:
                finish();
                break;
            case R.id.readTime:
                readTimeDecoration.setVisibility(View.VISIBLE);
                readBooksDecoration.setVisibility(View.INVISIBLE);
                changeText.setText("总计时间(h)");
                //发送请求信息获取阅读时间
                rankPosition.setText("20");
                sumCal.setText("130");
                initReadTimeDatas("");
                break;
            case R.id.readBooks:
                readTimeDecoration.setVisibility(View.INVISIBLE);
                readBooksDecoration.setVisibility(View.VISIBLE);
                changeText.setText("总计阅书(n)");
                //发送请求信息获取阅读书量
                rankPosition.setText("10");
                sumCal.setText("200");
                initReadBooksDatas("");
                break;
        }
    }
}
