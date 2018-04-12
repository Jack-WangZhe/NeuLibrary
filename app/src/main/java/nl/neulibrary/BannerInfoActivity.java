package nl.neulibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tools.removeTitle;

public class BannerInfoActivity extends AppCompatActivity implements View.OnClickListener {
    //对应控件
    private ImageView back_self; //返回操场界面
    private TextView titleName; //标题

    //信息
    private String infoTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        removeTitle.remove(this);
        setContentView(R.layout.activity_banner_info);

        getIntentInfo();

        initView();
        initClickView();
    }

    //初始化控件
    public void initView(){
        back_self = (ImageView)findViewById(R.id.back_self);
        titleName = (TextView)findViewById(R.id.titleName);
        titleName.setText(infoTitle);
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

    public void getIntentInfo(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        infoTitle = bundle.getString("infoTitle");

    }
}
