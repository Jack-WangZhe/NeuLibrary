package nl.neulibrary;

import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.serviceRankListByBooksAdapter;
import adapters.serviceRankListByTimeAdapter;
import beans.serviceRankListByBooksBean;
import beans.serviceRankListByTimeBean;
import netRequest.OnGetFinishListener;
import netRequest.Urls;
import netRequest.getMethod;
import tools.BitmapCache;
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
        selfPhoto=(CircleImageView)findViewById(R.id.selfPhoto);
    }

    //初始化控件点击监听
    public void initClickView(){
        back_self.setOnClickListener(this);
        readTime.setOnClickListener(this);
        readBooks.setOnClickListener(this);
    }

    public void initReadTimeDatas(String datas){
        try {
            JSONObject back = new JSONObject(datas);
            if (back.getBoolean("status")){
                JSONObject info = back.getJSONObject("info");
                JSONObject self = info.getJSONObject("self");
                rankPosition.setText(self.getString("rankNumber"));
                sumCal.setText(self.getInt("userLearnTime")+"");
                RequestQueue mQueue = Volley.newRequestQueue(serviceRank.this);
                ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
                ImageLoader.ImageListener listener1 = ImageLoader.getImageListener(selfPhoto, R.drawable.loading_on, R.drawable.loading_wrong);
                imageLoader.get(self.getString("userPhotoURL"), listener1);
                JSONArray allPerson=info.getJSONArray("allPerson");
                List<serviceRankListByTimeBean> mDatas = new ArrayList<serviceRankListByTimeBean>();
                serviceRankListByTimeBean bean ;
                for(int i =0;i<allPerson.length();i++){
                    JSONObject person=allPerson.getJSONObject(i);
                    bean = new serviceRankListByTimeBean(person.getInt("rankNumber"),person.getString("userId"),person.getString("userPhotoURL"),person.getString("userName"),person.getInt("userLearnTime")+"");
                    mDatas.add(bean);
                }
                //为数据绑定适配器
                serviceRankListByTimeAdapter = new serviceRankListByTimeAdapter(this, mDatas);
                rankList.setAdapter(serviceRankListByTimeAdapter);
            }else{
                Toast.makeText(serviceRank.this,back.getString("info"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        serviceRankListByTimeBean bean = new serviceRankListByTimeBean(1,"1","SunShine","200",R.drawable.user_photo01);
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(2,"2","Sun","170",R.drawable.user_photo02);
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(3,"3","jack","150",R.drawable.user_photo03);
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(4,"4","Tom","148",R.drawable.user_photo04);
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(5,"5","Daming","135",R.drawable.user_photo05);
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(6,"6","Amy","121",R.drawable.user_photo06);
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(7,"7","Davi","103",R.drawable.user_photo07);
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(8,"8","Yamo","101",R.drawable.user_photo08);
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(9,"9","Sam","100",R.drawable.user_photo09);
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(10,"10","Dota","70",R.drawable.user_photo10);
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(11,"11","Helly","58",R.drawable.user_photo11);
//        mDatas.add(bean);
//        bean = new serviceRankListByTimeBean(12,"12","Opener","55",R.drawable.self_photo);
//        mDatas.add(bean);

    }

    public void initReadBooksDatas(String datas){
        try {
            JSONObject back = new JSONObject(datas);
            if (back.getBoolean("status")){
                JSONObject info = back.getJSONObject("info");
                JSONObject self = info.getJSONObject("self");
                rankPosition.setText(self.getString("rankNumber"));
                sumCal.setText(self.getInt("userReadBooksNumber")+"");
                RequestQueue mQueue = Volley.newRequestQueue(serviceRank.this);
                ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
                ImageLoader.ImageListener listener1 = ImageLoader.getImageListener(selfPhoto, R.drawable.loading_on, R.drawable.loading_wrong);
                imageLoader.get(self.getString("userPhotoURL"), listener1);
                JSONArray allPerson=info.getJSONArray("allPerson");
                List<serviceRankListByBooksBean> mDatas = new ArrayList<serviceRankListByBooksBean>();
                serviceRankListByBooksBean bean ;
                for(int i =0;i<allPerson.length();i++){
                    JSONObject person=allPerson.getJSONObject(i);
                    bean = new serviceRankListByBooksBean(person.getInt("rankNumber"),person.getString("userId"),person.getString("userPhotoURL"),person.getString("userName"),person.getInt("userReadBooksNumber")+"");
                    mDatas.add(bean);
                }
                //为数据绑定适配器
                serviceRankListByBooksAdapter = new serviceRankListByBooksAdapter(this, mDatas);
                rankList.setAdapter(serviceRankListByBooksAdapter);
            }else{
                Toast.makeText(serviceRank.this,back.getString("info"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        serviceRankListByBooksBean bean = new serviceRankListByBooksBean(1,"1","jack","822",R.drawable.user_photo03);
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(2,"2","Sam","801",R.drawable.user_photo05);
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(3,"3","Amy","703",R.drawable.user_photo01);
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(4,"4","Helly","600",R.drawable.user_photo09);
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(5,"5","Daming","523",R.drawable.user_photo07);
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(6,"6","Amy","412",R.drawable.user_photo11);
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(7,"7","jack","399",R.drawable.user_photo10);
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(8,"8","Yamo","302",R.drawable.user_photo08);
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(9,"9","Sam","251",R.drawable.user_photo06);
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(10,"10","Dota","200",R.drawable.self_photo);
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(11,"11","Helly","192",R.drawable.user_photo02);
//        mDatas.add(bean);
//        bean = new serviceRankListByBooksBean(12,"12","Daming","110",R.drawable.user_photo04);
//        mDatas.add(bean);
//        //为数据绑定适配器
//        serviceRankListByBooksAdapter = new serviceRankListByBooksAdapter(this, mDatas);
//        rankList.setAdapter(serviceRankListByBooksAdapter);
    }

    //第一次发送请求获取信息
    public void firstRequest(){
        //发送请求数据,获取最近消息
        getMethod getTimeRank=new getMethod();
        getTimeRank.setOnFinishListener(new OnGetFinishListener() {
            @Override
            public void OnGetFinished(String s) {
                initReadTimeDatas(s);
            }
        });
        getTimeRank.getInfo(this, Urls.getTimeRank);
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
                getMethod getTimeRank=new getMethod();
                getTimeRank.setOnFinishListener(new OnGetFinishListener() {
                    @Override
                    public void OnGetFinished(String s) {
                        initReadTimeDatas(s);
                    }
                });
                getTimeRank.getInfo(this, Urls.getTimeRank);
                break;
            case R.id.readBooks:
                readTimeDecoration.setVisibility(View.INVISIBLE);
                readBooksDecoration.setVisibility(View.VISIBLE);
                changeText.setText("总计阅书(n)");
                //发送请求信息获取阅读书量
                getMethod getReadBookRank=new getMethod();
                getReadBookRank.setOnFinishListener(new OnGetFinishListener() {
                    @Override
                    public void OnGetFinished(String s) {
                        initReadBooksDatas(s);
                    }
                });
                getReadBookRank.getInfo(this, Urls.getReadBookRank);
                break;
        }
    }
}
