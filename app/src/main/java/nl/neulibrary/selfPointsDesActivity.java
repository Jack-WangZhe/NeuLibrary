package nl.neulibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import netRequest.OnGetFinishListener;
import netRequest.Urls;
import netRequest.getMethod;
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
        getMethod getPointsDes=new getMethod();
        getPointsDes.setOnFinishListener(new OnGetFinishListener() {
            @Override
            public void OnGetFinished(String backInfo) {
                try {
                    JSONObject back = new JSONObject(backInfo);
                    if (back.getBoolean("status")){
                        String pointsDes = back.getString("info");
                        pointsDescription.setText(pointsDes);
                    }else{
                        Toast.makeText(selfPointsDesActivity.this,back.getString("info"),Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        getPointsDes.getInfo(this, Urls.getPointsDes);
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
