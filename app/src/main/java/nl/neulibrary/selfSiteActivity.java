package nl.neulibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import tools.removeTitle;

public class selfSiteActivity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{
    //对应控件
    private ImageView back_self; //返回个人界面
    private Switch nightModel; //夜间模式切换
    private TextView largeText; //大号字体
    private TextView middleText; //中号字体
    private TextView smallText; //小号字体
    private ImageView toTextStyle; //进入字体样式按钮
    private LinearLayout readBackgroundGreen; //阅读背景：绿
    private LinearLayout readBackgroundPink; //阅读背景：粉
    private LinearLayout readBackgroundOrange; //阅读背景：橘
    private LinearLayout readBackgroundBlue; //阅读背景：蓝


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        removeTitle.remove(this);
        setContentView(R.layout.activity_self_site);
        initView();
        initClickView();
        initCheckedChangeView();
        updateViewInfo();
    }

    //初始化控件
    public void initView(){
        back_self = (ImageView)findViewById(R.id.back_self);
        nightModel = (Switch)findViewById(R.id.nightModel);
        largeText = (TextView)findViewById(R.id.largeText);
        middleText = (TextView)findViewById(R.id.middleText);
        smallText = (TextView)findViewById(R.id.smallText);
        toTextStyle = (ImageView)findViewById(R.id.toTextStyle);
        readBackgroundGreen = (LinearLayout)findViewById(R.id.readBackgroundGreen);
        readBackgroundPink = (LinearLayout)findViewById(R.id.readBackgroundPink);
        readBackgroundOrange = (LinearLayout)findViewById(R.id.readBackgroundOrange);
        readBackgroundBlue = (LinearLayout)findViewById(R.id.readBackgroundBlue);
    }

    //初始化控件点击监听
    public void initClickView(){
        back_self.setOnClickListener(this);
        largeText.setOnClickListener(this);
        middleText.setOnClickListener(this);
        smallText.setOnClickListener(this);
        toTextStyle.setOnClickListener(this);
        readBackgroundGreen.setOnClickListener(this);
        readBackgroundPink.setOnClickListener(this);
        readBackgroundOrange.setOnClickListener(this);
        readBackgroundBlue.setOnClickListener(this);
    }

    //初始化控件切换监听
    public void initCheckedChangeView(){
        nightModel.setOnCheckedChangeListener(this);
    }

    //初始化控件信息
    public void updateViewInfo(){
        //通过查找sharepreference值确定当前夜间模式是否开启
        //若无sharepreference值则表示未设定，即为关闭状态
        //若有sharepreference值则根据设定的值进行状态的选择
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.back_self:
                finish();
                break;
            case R.id.largeText:
                Toast.makeText(this,"切换至大号字体",Toast.LENGTH_SHORT).show();
                break;
            case R.id.middleText:
                Toast.makeText(this,"切换至中号字体",Toast.LENGTH_SHORT).show();
                break;
            case R.id.smallText:
                Toast.makeText(this,"切换至小号字体",Toast.LENGTH_SHORT).show();
                break;
            case R.id.toTextStyle:
                Intent to_selfSite_wordStyle = new Intent(this,selfSite_wordStyleActivity.class);
                startActivity(to_selfSite_wordStyle);
                break;
            case R.id.readBackgroundGreen:
                Toast.makeText(this,"切换至绿色阅读背景",Toast.LENGTH_SHORT).show();
                break;
            case R.id.readBackgroundPink:
                Toast.makeText(this,"切换至粉色阅读背景",Toast.LENGTH_SHORT).show();
                break;
            case R.id.readBackgroundOrange:
                Toast.makeText(this,"切换至橙色阅读背景",Toast.LENGTH_SHORT).show();
                break;
            case R.id.readBackgroundBlue:
                Toast.makeText(this,"切换至蓝色阅读背景",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b){
            Toast.makeText(this,"开启夜间模式",Toast.LENGTH_SHORT).show();
            //注意：
            //将夜间模式信息记录在sharepreference中
            //待添加...
        }else{
            Toast.makeText(this,"关闭夜间模式",Toast.LENGTH_SHORT).show();
            //注意：
            //将夜间模式信息记录在sharepreference中
            //待添加...
        }
    }
}
