package nl.neulibrary;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import tools.removeTitle;

public class selfSite_wordStyleActivity extends AppCompatActivity implements View.OnClickListener{
    //对应控件
    private ImageView back_self; //返回个人界面

    private RelativeLayout ArialStyle; //宋体 条目
    private RelativeLayout YouYuanStyle; //幼圆体 条目
    private RelativeLayout ZhunYuanStyle; //方正准圆体 条目
    private RelativeLayout XinSongStyle; //华文新宋体 条目
    private RelativeLayout XinWeiStyle; //华文新魏体 条目
    private RelativeLayout XingKaiStyle; //华文行楷体 条目
    private RelativeLayout CaiYunStyle; //华文彩云体 条目

    private TextView ArialWord; //宋体字
    private TextView YouYuanWord; //幼圆字
    private TextView ZhunYuanWord; //方正准圆字
    private TextView XinSongWord; //华文新宋字
    private TextView XinWeiWord; //华文新魏字
    private TextView XingKaiWord; //华文行楷字
    private TextView CaiYunWord; //华文彩云字

    private ImageView ArialChecked; //宋体 选择按钮
    private ImageView YouYuanChecked; //幼圆体 选择按钮
    private ImageView ZhunYuanChecked; //方正准圆体 选择按钮
    private ImageView XinSongChecked; //华文新宋体 选择按钮
    private ImageView XinWeiChecked; //华文新魏体 选择按钮
    private ImageView XingKaiChecked; //华文行楷体 选择按钮
    private ImageView CaiYunChecked; //华文彩云体 选择按钮

    private String localFont = "Arial"; //本地字体

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        removeTitle.remove(this);
        setContentView(R.layout.activity_self_site_word_style);
        sharedPreferences=getSharedPreferences("userInfo.txt",MODE_PRIVATE);
        initView();
        initClickView();
        initTextStyle();
        updateTextChecked();
    }

    //初始化控件
    public void initView() {
        back_self = (ImageView) findViewById(R.id.back_self);
        ArialStyle = (RelativeLayout)findViewById(R.id.ArialStyle);
        YouYuanStyle = (RelativeLayout)findViewById(R.id.YouYuanStyle);
        ZhunYuanStyle = (RelativeLayout)findViewById(R.id.ZhunYuanStyle);
        XinSongStyle = (RelativeLayout)findViewById(R.id.XinSongStyle);
        XinWeiStyle = (RelativeLayout)findViewById(R.id.XinWeiStyle);
        XingKaiStyle = (RelativeLayout)findViewById(R.id.XingKaiStyle);
        CaiYunStyle = (RelativeLayout)findViewById(R.id.CaiYunStyle);
        ArialWord = (TextView)findViewById(R.id.ArialWord);
        YouYuanWord = (TextView)findViewById(R.id.YouYuanWord);
        ZhunYuanWord = (TextView)findViewById(R.id.ZhunYuanWord);
        XinSongWord = (TextView)findViewById(R.id.XinSongWord);
        XinWeiWord = (TextView)findViewById(R.id.XinWeiWord);
        XingKaiWord = (TextView)findViewById(R.id.XingKaiWord);
        CaiYunWord = (TextView)findViewById(R.id.CaiYunWord);
        ArialChecked = (ImageView)findViewById(R.id.ArialChecked);
        YouYuanChecked = (ImageView)findViewById(R.id.YouYuanChecked);
        ZhunYuanChecked = (ImageView)findViewById(R.id.ZhunYuanChecked);
        XinSongChecked = (ImageView)findViewById(R.id.XinSongChecked);
        XinWeiChecked = (ImageView)findViewById(R.id.XinWeiChecked);
        XingKaiChecked = (ImageView)findViewById(R.id.XingKaiChecked);
        CaiYunChecked = (ImageView)findViewById(R.id.CaiYunChecked);
    }

    //初始化控件点击监听
    public void initClickView() {
        back_self.setOnClickListener(this);
        ArialStyle.setOnClickListener(this);
        YouYuanStyle.setOnClickListener(this);
        ZhunYuanStyle.setOnClickListener(this);
        XinSongStyle.setOnClickListener(this);
        XinWeiStyle.setOnClickListener(this);
        XingKaiStyle.setOnClickListener(this);
        CaiYunStyle.setOnClickListener(this);
    }

    //初始化控件字体样式
    public void initTextStyle(){
        //将字体文件保存在assets/fonts/目录下，创建Typeface对象
        //使用字体成幼圆
        Typeface tf_to_YouYuanWord = Typeface.createFromAsset(getAssets(),"fonts/youyuan.ttf");
        YouYuanWord.setTypeface(tf_to_YouYuanWord);
        //使用字体成方正准圆
        Typeface tf_to_ZhunYuanWord = Typeface.createFromAsset(getAssets(),"fonts/fz_zhunyuan.TTF");
        ZhunYuanWord.setTypeface(tf_to_ZhunYuanWord);
        //使用字体成华文新宋
        Typeface tf_to_XinSongWord = Typeface.createFromAsset(getAssets(),"fonts/st_xinsong.ttf");
        XinSongWord.setTypeface(tf_to_XinSongWord);
        //使用字体成华文新魏
        Typeface tf_to_XinWeiWord = Typeface.createFromAsset(getAssets(),"fonts/st_xinwei.TTF");
        XinWeiWord.setTypeface(tf_to_XinWeiWord);
        //使用字体成华文行楷
        Typeface tf_to_XingKaiWord = Typeface.createFromAsset(getAssets(),"fonts/st_xingkai.ttf");
        XingKaiWord.setTypeface(tf_to_XingKaiWord);
        //使用字体成华文彩云
        Typeface tf_to_CaiYunWord = Typeface.createFromAsset(getAssets(),"fonts/st_caiyun.TTF");
        CaiYunWord.setTypeface(tf_to_CaiYunWord);
    }

    //更新字体样式的选择
    public void updateTextChecked(){
        //查询sharepreference中字体样式的值，若不存在则不进行处理
        //若存在则将ArialChecked隐藏，并将对应的选择显示
        localFont=sharedPreferences.getString("textStyle","Arial");
        ArialChecked.setVisibility(View.GONE);
        if(localFont.equals("Arial")){
            ArialChecked.setVisibility(View.VISIBLE);
        }
        else if(localFont.equals("YouYuan")){
            YouYuanChecked.setVisibility(View.VISIBLE);
        }
        else if(localFont.equals("ZhunYuan")){
            ZhunYuanChecked.setVisibility(View.VISIBLE);
        }
        else if(localFont.equals("XinSong")){
            XinSongChecked.setVisibility(View.VISIBLE);
        }
        else if(localFont.equals("XinWei")){
            XinWeiChecked.setVisibility(View.VISIBLE);
        }
        else if(localFont.equals("XingKai")){
            XingKaiChecked.setVisibility(View.VISIBLE);
        }
        else if(localFont.equals("CaiYun")){
            CaiYunChecked.setVisibility(View.VISIBLE);
        }
    }

    //更换被选中字体样式
    public void changeChecked(){
        if(localFont.equals("Arial")){
            ArialChecked.setVisibility(View.GONE);
        }
        else if(localFont.equals("YouYuan")){
            YouYuanChecked.setVisibility(View.GONE);
        }
        else if(localFont.equals("ZhunYuan")){
            ZhunYuanChecked.setVisibility(View.GONE);
        }
        else if(localFont.equals("XinSong")){
            XinSongChecked.setVisibility(View.GONE);
        }
        else if(localFont.equals("XinWei")){
            XinWeiChecked.setVisibility(View.GONE);
        }
        else if(localFont.equals("XingKai")){
            XingKaiChecked.setVisibility(View.GONE);
        }
        else if(localFont.equals("CaiYun")){
            CaiYunChecked.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        switch (view.getId()) {
            case R.id.back_self:
                finish();
                break;
            case R.id.ArialStyle:
                Toast.makeText(this,"切换字体样式:宋体",Toast.LENGTH_SHORT).show();
                changeChecked();
                ArialChecked.setVisibility(View.VISIBLE);
                localFont = "Arial";
                //将宋体字体样式的值存储至sharepreference中
                editor.putString("textStyle","Arial");
                break;
            case R.id.YouYuanStyle:
                Toast.makeText(this,"切换字体样式:幼圆",Toast.LENGTH_SHORT).show();
                changeChecked();
                YouYuanChecked.setVisibility(View.VISIBLE);
                localFont = "YouYuan";
                //将幼圆字体样式的值存储至sharepreference中
                editor.putString("textStyle","YouYuan");
                break;
            case R.id.ZhunYuanStyle:
                Toast.makeText(this,"切换字体样式:方正准圆",Toast.LENGTH_SHORT).show();
                changeChecked();
                ZhunYuanChecked.setVisibility(View.VISIBLE);
                localFont = "ZhunYuan";
                //将方正准圆字体样式的值存储至sharepreference中
                editor.putString("textStyle","ZhunYuan");
                break;
            case R.id.XinSongStyle:
                Toast.makeText(this,"切换字体样式:华文新宋",Toast.LENGTH_SHORT).show();
                changeChecked();
                XinSongChecked.setVisibility(View.VISIBLE);
                localFont = "XinSong";
                //将华文新宋字体样式的值存储至sharepreference中
                editor.putString("textStyle","XinSong");
                break;
            case R.id.XinWeiStyle:
                Toast.makeText(this,"切换字体样式:华文新魏",Toast.LENGTH_SHORT).show();
                changeChecked();
                XinWeiChecked.setVisibility(View.VISIBLE);
                localFont = "XinWei";
                //将华文新魏字体样式的值存储至sharepreference中
                editor.putString("textStyle","XinWei");
                break;
            case R.id.XingKaiStyle:
                Toast.makeText(this,"切换字体样式:华文行楷",Toast.LENGTH_SHORT).show();
                changeChecked();
                XingKaiChecked.setVisibility(View.VISIBLE);
                localFont = "XingKai";
                //将华文行楷字体样式的值存储至sharepreference中
                editor.putString("textStyle","XingKai");
                break;
            case R.id.CaiYunStyle:
                Toast.makeText(this,"切换字体样式:华文彩云",Toast.LENGTH_SHORT).show();
                changeChecked();
                CaiYunChecked.setVisibility(View.VISIBLE);
                localFont = "CaiYun";
                //将华文彩云字体样式的值存储至sharepreference中
                editor.putString("textStyle","CaiYun");
                break;
        }
        editor.commit();
    }
}
