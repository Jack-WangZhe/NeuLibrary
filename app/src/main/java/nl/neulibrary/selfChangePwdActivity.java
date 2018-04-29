package nl.neulibrary;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import netRequest.OnPostFinishListener;
import netRequest.Urls;
import netRequest.postMethod;
import tools.removeTitle;

public class selfChangePwdActivity extends AppCompatActivity implements View.OnClickListener{
    //对应控件
    private ImageView back_self; //返回个人界面
    private EditText userId;
    private EditText userPass;
    private EditText newPass;
    private Button submitBtn;
    String id;
    //获取当前用户学号
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        removeTitle.remove(this);
        setContentView(R.layout.activity_self_change_pwd);
        initView();
        initClickView();
    }

    //初始化控件
    public void initView(){
        back_self = (ImageView)findViewById(R.id.back_self);
        userId=(EditText)findViewById(R.id.userId);
        userPass=(EditText)findViewById(R.id.userPass);
        newPass=(EditText)findViewById(R.id.newPass);
        submitBtn=(Button)findViewById(R.id.submitBtn);
        sharedPreferences=getSharedPreferences("userInfo.txt",MODE_PRIVATE);
        id=sharedPreferences.getString("userId","");
        userId.setText(id);
        userPass.setFocusable(true);
        userPass.setFocusableInTouchMode(true);
        userPass.requestFocus();
        userPass.requestFocusFromTouch();
    }

    //初始化控件点击监听
    public void initClickView(){
        back_self.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_self:
                finish();
                break;
            case R.id.submitBtn:
                if (userId.getText().toString().equals("")||(!userId.getText().toString().equals(id))){
                    Toast.makeText(this,"请填写您的学号",Toast.LENGTH_SHORT).show();
                    userId.setFocusable(true);
                    userId.setFocusableInTouchMode(true);
                    userId.requestFocus();
                    userId.requestFocusFromTouch();
                }
                else if(userPass.getText().toString().equals("")){
                    Toast.makeText(this,"请填写您的旧密码",Toast.LENGTH_SHORT).show();
                    userPass.setFocusable(true);
                    userPass.setFocusableInTouchMode(true);
                    userPass.requestFocus();
                    userPass.requestFocusFromTouch();
                }
                else if (newPass.getText().toString().equals("")){
                    Toast.makeText(this,"请填写您的新密码",Toast.LENGTH_SHORT).show();
                    newPass.setFocusable(true);
                    newPass.setFocusableInTouchMode(true);
                    newPass.requestFocus();
                    newPass.requestFocusFromTouch();
                }
                else if(userPass.getText().toString().equals(newPass.getText().toString())){
                    Toast.makeText(this,"新旧密码一致，请重新输入",Toast.LENGTH_SHORT).show();
                    newPass.setFocusable(true);
                    newPass.setFocusableInTouchMode(true);
                    newPass.requestFocus();
                    newPass.requestFocusFromTouch();
                }
                else {
                    //发送volley请求更改
                    postMethod changePwd=new postMethod();
                    changePwd.setOnFinishListener(new OnPostFinishListener() {
                        @Override
                        public void OnPostFinished(String backinfo) {
                            try {
                                JSONObject back=new JSONObject(backinfo);
                                Boolean status = back.getBoolean("status");
                                if (status){
                                    Toast.makeText(selfChangePwdActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putString("userPwd",userPass.getText().toString());
                                    editor.commit();
                                    finish();
                                }else {
                                    String info = back.getString("info");
                                    Toast.makeText(selfChangePwdActivity.this,info,Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    changePwd.postChangePwd(this,Urls.changePwd,userId.getText().toString(),userPass.getText().toString(),newPass.getText().toString());
                }
                break;
        }
    }
}
