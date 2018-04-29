package nl.neulibrary;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import netRequest.OnGetFinishListener;
import netRequest.Urls;
import netRequest.getMethod;
import tools.removeTitle;

public class BookReadActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView bookName;
    private ImageView sectionButton;
    private TextView bookSectionTitle;
    private TextView bookSectionContent;
    private ImageView back;
    private ImageView config;
    private ScrollView TextBackground;

    private String book_name;
    private String book_id;
    private String book_section_title;
    private String book_section_content;

    private String[] section;
    private ListView sectionRadioListView;
    private RadioOnClick radioOnClick = new RadioOnClick(0);
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeTitle.remove(this);
        setContentView(R.layout.activity_book_read);
        sharedPreferences=getSharedPreferences("userInfo.txt",MODE_PRIVATE);
        initViews();
        getIntentInfo();
        refreshSite();
    }

    //获取传过来的数据信息
    public void getIntentInfo() {
        final Intent book = getIntent();
        book_id = book.getStringExtra("bookId");
        book_name = book.getStringExtra("bookName");
        bookName.setText(book_name);
        //发送volley请求，获取id的对应第一章内容
        getMethod bookSectionInfo = new getMethod();
        bookSectionInfo.setOnFinishListener(new OnGetFinishListener() {
            @Override
            public void OnGetFinished(String backInfo) {
                try {
                    JSONObject back = new JSONObject(backInfo);
                    if(back.getBoolean("status")){
                        JSONObject info = back.getJSONObject("info");
                        //模拟得到内容为:
                        book_section_title = info.getString("Title");
                        book_section_content =info.getString("Content");
                        bookSectionTitle.setText(book_section_title);
                        bookSectionContent.setText(book_section_content);
                    }else{
                        String info = back.getString("info");
                        Toast.makeText(BookReadActivity.this,info,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        bookSectionInfo.getInfo(this, Urls.getBookSectionInfo+"?bookId="+book_id+"&sectionNum=1");
    }

    public void initViews() {
        bookName = (TextView) findViewById(R.id.bookName);
        sectionButton = (ImageView) findViewById(R.id.sectionButton);
        sectionButton.setOnClickListener(new RadioClickListener());
        bookSectionTitle = (TextView) findViewById(R.id.bookSectionTitle);
        bookSectionContent = (TextView) findViewById(R.id.bookSectionContent);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        config = (ImageView) findViewById(R.id.config);
        config.setOnClickListener(this);
        TextBackground=(ScrollView)findViewById(R.id.TextBackground);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.config:
                Intent toConfig = new Intent(this, selfSiteActivity.class);
                startActivity(toConfig);
                break;
        }
    }

    /**
     * 单选弹出菜单窗口
     */
    class RadioClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //发送volley请求获取所有章节标题
            //模拟章节标题内容
            getMethod getSetions = new getMethod();
            getSetions.setOnFinishListener(new OnGetFinishListener() {
                @Override
                public void OnGetFinished(String backInfo) {
                    try {
                        JSONObject back = new JSONObject(backInfo);
                        if (back.getBoolean("status")){
                            JSONArray info = back.getJSONArray("info");
                            int length = info.length();
                            section = new String[length];
                            for(int i=0;i<info.length();i++){
                                section[i] = info.get(i).toString();
                            }
                            AlertDialog ad = new AlertDialog.Builder(BookReadActivity.this).setTitle("请选择章节")
                                    .setSingleChoiceItems(section, radioOnClick.getIndex(), radioOnClick).create();
                            sectionRadioListView = ad.getListView();
                            ad.show();
                        }else{
                            Toast.makeText(BookReadActivity.this,back.getString("info"),Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            getSetions.getInfo(BookReadActivity.this,Urls.getSections+"?bookId="+book_id);

        }
    }

    /**
     * 点击单选框事件
     */
    class RadioOnClick implements DialogInterface.OnClickListener {
        private int index;

        public RadioOnClick(int index) {
            this.index = index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public void onClick(final DialogInterface dialog, int whichButton){
            setIndex(whichButton);
            //发送请求获取章节文章内容
            //section[index]
            //Toast.makeText(BookReadActivity.this, "您已经选择了： " + index + ":" + section[index], Toast.LENGTH_LONG).show();
            getMethod bookSectionInfo = new getMethod();
            bookSectionInfo.setOnFinishListener(new OnGetFinishListener() {
                @Override
                public void OnGetFinished(String backInfo) {
                    try {
                        JSONObject back = new JSONObject(backInfo);
                        if(back.getBoolean("status")){
                            JSONObject info = back.getJSONObject("info");
                            //模拟得到内容为:
                            book_section_title = info.getString("Title");
                            book_section_content =info.getString("Content");
                            bookSectionTitle.setText(book_section_title);
                            bookSectionContent.setText(book_section_content);
                            dialog.dismiss();
                        }else{
                            String info = back.getString("info");
                            Toast.makeText(BookReadActivity.this,info,Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            bookSectionInfo.getInfo(BookReadActivity.this, Urls.getBookSectionInfo+"?bookId="+book_id+"&sectionNum="+(index+1));
        }
    }

    public void refreshSite(){
        String mode = sharedPreferences.getString("Mode","simple");
        int textSize = sharedPreferences.getInt("textSize",15);
        String textStyle = sharedPreferences.getString("textStyle","Arial");
        String textBackground=sharedPreferences.getString("textBackground","#ffffff");
        TextBackground.setBackgroundColor(Color.parseColor(textBackground));
        bookSectionTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        bookSectionContent.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        if(textStyle.equals("Arial")){
            bookSectionContent.setTypeface(Typeface.SANS_SERIF);
        }
        else if(textStyle.equals("YouYuan")){
            Typeface tf_to_YouYuanWord = Typeface.createFromAsset(getAssets(),"fonts/youyuan.ttf");
            bookSectionContent.setTypeface(tf_to_YouYuanWord);
        }
        else if(textStyle.equals("ZhunYuan")){
            Typeface tf_to_ZhunYuanWord = Typeface.createFromAsset(getAssets(),"fonts/fz_zhunyuan.TTF");
            bookSectionContent.setTypeface(tf_to_ZhunYuanWord);
        }
        else if(textStyle.equals("XinSong")){
            Typeface tf_to_XinSongWord = Typeface.createFromAsset(getAssets(),"fonts/st_xinsong.ttf");
            bookSectionContent.setTypeface(tf_to_XinSongWord);
        }
        else if(textStyle.equals("XinWei")){
            Typeface tf_to_XinWeiWord = Typeface.createFromAsset(getAssets(),"fonts/st_xinwei.TTF");
            bookSectionContent.setTypeface(tf_to_XinWeiWord);
        }
        else if(textStyle.equals("XingKai")){
            Typeface tf_to_XingKaiWord = Typeface.createFromAsset(getAssets(),"fonts/st_xingkai.ttf");
            bookSectionContent.setTypeface(tf_to_XingKaiWord);
        }
        else if(textStyle.equals("CaiYun")){
            Typeface tf_to_CaiYunWord = Typeface.createFromAsset(getAssets(),"fonts/st_caiyun.TTF");
            bookSectionContent.setTypeface(tf_to_CaiYunWord);
        }
    }
    @Override
    public void onRestart(){
        super.onRestart();
        refreshSite();
    }
}
