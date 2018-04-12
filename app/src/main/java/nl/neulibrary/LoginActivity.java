package nl.neulibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import tools.removeTitle;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    //对应控件
    private EditText StudentId;
    private EditText StudentPass;
    private ImageView PassSeeable;
    private Button SubmitLogin;

    //填写的学号内容：Id   填写的密码内容:Password
    private String Id;
    private String Password;

    //设置密码最初为不可见
    private int seeable=0;

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
    }

    //获取编辑框中内容
    public void getInfo(){
        Id = StudentId.getText().toString();
        Password = StudentPass.getText().toString();
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
                if (Id.equals("")){
                    Toast.makeText(this,"请输入您的学号",Toast.LENGTH_SHORT).show();
                    StudentId.requestFocus();
                }
                else if (Password.equals("")){
                    Toast.makeText(this,"请输入您的密码",Toast.LENGTH_SHORT).show();
                    StudentPass.requestFocus();
                }
                else{
                    //发送volley请求
                    //String loginSend = "{\"Id\":\""+Id+"\",\"Password\":\""+Password+"\"}";

                    //接收格式模拟:
                    //校验成功返回:
                      String backInfo = "{\"status\":\"True\",\"info\":{\"Id\":\"123456\",\"Name\":\"Jack\"}}";
//                    Toast.makeText(this,backInfo,Toast.LENGTH_SHORT).show();
                    //校验失败返回:
//                    String backInfo = "{\"status\":\"False\",\"info\":\"用户名或密码错误\"}";
//                    Toast.makeText(this,backInfo,Toast.LENGTH_SHORT).show();

                    try {
                        JSONObject back = new JSONObject(backInfo);
                        String status = back.getString("status");
                        if (status.equals("True")){
                            Intent toHome = new Intent(this,HomeActivity.class);
                            startActivity(toHome);
                            finish();
                        }else{
                            Toast.makeText(this,back.getString("info"),Toast.LENGTH_SHORT).show();
                            StudentId.requestFocus();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
