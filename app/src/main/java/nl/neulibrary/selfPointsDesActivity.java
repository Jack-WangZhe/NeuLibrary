package nl.neulibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import tools.removeTitle;

public class selfPointsDesActivity extends AppCompatActivity implements View.OnClickListener{
    //对应控件
    private ImageView back_self; //返回个人界面
    private TextView pointsDescription; //积分说明信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        removeTitle.remove(this);
        setContentView(R.layout.activity_self_points_des);
        initView();
        initClickView();
        getPointsDes();
    }

    //初始化控件
    public void initView(){
        back_self = (ImageView)findViewById(R.id.back_self);
        pointsDescription = (TextView)findViewById(R.id.pointsDescription);
    }

    //初始化控件点击监听
    public void initClickView(){
        back_self.setOnClickListener(this);
    }

    //更新积分说明
    public void getPointsDes(){
        //发送volley的get请求，获取积分说明
        //请求地址
        //返回信息:
        String backInfo = "{\"PointsDes\":\"积分获取途径：\n" +
                "\n" +
                "1、登录：1/天；\n" +
                "2、连续登录：（连续登录天数）点/天，上线29点；\n" +
                "3、在线时长：1点/小时，每天上线24点\n" +
                "4、校友圈积分：校友圈分享内容：5点/条，每天上线10点。\n" +
                "\n" +
                "积分损耗：\n" +
                "\n" +
                "1、校友圈发布不良信息： 5点/条；\n" +
                "2、推迟归还书籍 ：2点/天；\n" +
                "3、积分兑换：未开启。\"}";
        //注意:从网络中获取的数据需要将\n替换成\\n
        try {
            JSONObject info = new JSONObject(backInfo);
            String pointsDes = info.getString("PointsDes");
            pointsDescription.setText(pointsDes);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
