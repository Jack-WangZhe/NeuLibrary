package nl.neulibrary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import netRequest.OnPostFinishListener;
import netRequest.Urls;
import netRequest.postMethod;
import tools.removeTitle;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    //对应控件
    private EditText StudentId;
    private EditText StudentPass;
    private ImageView PassSeeable;
    private Button SubmitLogin;

    //设置密码最初为不可见
    private int seeable=0;

    //设置sharePreferences
    SharedPreferences sharedPreferences;

    //填写的学号内容和密码内容
    String userId;
    String userPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        removeTitle.remove(this);
        setContentView(R.layout.activity_login);
        initView();
    }

    //初始化控件
    public void initView(){
        //初始化基本控件
        StudentId = (EditText)findViewById(R.id.StudentId);
        StudentPass = (EditText) findViewById(R.id.StudentPass);
        PassSeeable = (ImageView) findViewById(R.id.PassSeeable);
        SubmitLogin = (Button) findViewById(R.id.SubmitLogin);

        //添加监听事件
        PassSeeable.setOnClickListener(this);
        SubmitLogin.setOnClickListener(this);

        //初始化sharePreferences
        sharedPreferences=getSharedPreferences("userInfo.txt",MODE_PRIVATE);
        userId=sharedPreferences.getString("userId","");
        userPwd=sharedPreferences.getString("userPwd","");
        StudentId.setText(userId);
        StudentPass.setText(userPwd);
    }

    //获取编辑框中内容
    public void getInfo(){
        userId = StudentId.getText().toString();
        userPwd = StudentPass.getText().toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //点击切换密码可视
            case R.id.PassSeeable:
                if(seeable == 0){
                    PassSeeable.setImageDrawable(getResources().getDrawable(R.drawable.loginunseepass));
                    seeable = 1;
                    StudentPass.setInputType(0x90);
                }else{
                    PassSeeable.setImageDrawable(getResources().getDrawable(R.drawable.loginseepass));
                    seeable = 0;
                    StudentPass.setInputType(0x81);
                }
                break;

            //点击登录
            case R.id.SubmitLogin:
                getInfo();
                if (userId.equals("")){
                    Toast.makeText(this,"请输入您的学号",Toast.LENGTH_SHORT).show();
                    StudentId.requestFocus();
                }
                else if (userPwd.equals("")){
                    Toast.makeText(this,"请输入您的密码",Toast.LENGTH_SHORT).show();
                    StudentPass.requestFocus();
                }
                else{
                    final String userId=StudentId.getText().toString();
                    final String userPwd=StudentPass.getText().toString();
                    //发送volley请求
                    //校验成功返回:
                    /*String backInfo = "{\"status\":\"True\",\"info\":\"\"}}";
//                    Toast.makeText(this,backInfo,Toast.LENGTH_SHORT).show();
                      校验失败返回:
//                    String backInfo = "{\"status\":\"False\",\"info\":\"用户名或密码错误\"}";
//                    Toast.makeText(this,backInfo,Toast.LENGTH_SHORT).show();*/
                    postMethod postLogin=new postMethod();
                    postLogin.setOnFinishListener(new OnPostFinishListener() {
                        @Override
                        public void OnPostFinished(String backInfo) {
                            try {
                                JSONObject back = new JSONObject(backInfo);
                                boolean status = back.getBoolean("status");
                                if (status){
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putString("userId",userId);
                                    editor.putString("userPwd",userPwd);
                                    editor.commit();
                                    Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                                    Intent toHome = new Intent(LoginActivity.this,HomeActivity.class);
                                    startActivity(toHome);
                                    finish();
                                }else{
                                    Toast.makeText(LoginActivity.this, back.getString("info"),Toast.LENGTH_SHORT).show();
                                    StudentId.requestFocus();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    postLogin.postLogin(this, Urls.loginInfo,userId,userPwd);
                }
                break;
        }
    }
}
