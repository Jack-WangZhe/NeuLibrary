package fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import nl.neulibrary.LoginActivity;
import nl.neulibrary.R;
import nl.neulibrary.selfBookselfActivity;
import nl.neulibrary.selfDynamicActivity;
import nl.neulibrary.selfMessageActivity;
import nl.neulibrary.selfPointsDesActivity;
import nl.neulibrary.selfReadRecordActivity;
import nl.neulibrary.selfSiteActivity;
import nl.neulibrary.userInfoActivity;
import tools.CircleImageView;
import tools.CommomDialog;

public class selfFragment extends Fragment implements View.OnClickListener{
    //整体fragment
    private View view;

    //对应控件
    private TextView userName; // 用户名
    private Button sign_in_btn; //签到按钮
    private Button signed_in_btn; //已签到按钮
    private TextView userAttention; //关注数
    private TextView userFans; //粉丝数
    private TextView userIntegral; //积分数
    private CircleImageView userPhoto; //用户头像
    private LinearLayout selfBookself; //我的书架选项
    private LinearLayout selfMessage; //我的消息选项
    private LinearLayout selfDynamic; //我的动态选项
    private LinearLayout selfReadRecord; //阅读记录选项
    private LinearLayout selfSite; //设置选项
    private LinearLayout selfPointsDes; //积分说明选项
    private LinearLayout outLogin; //退出登录选项

    //用户信息
    private String getUserInfo; //发送的用户信息
    private String userInfo; //用户资料


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_self, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initView();
        initClickViews();
        updateUserInfo();
    }

    //初始化控件
    public void initView(){
        userName = (TextView)view.findViewById(R.id.userName);
        sign_in_btn = (Button)view.findViewById(R.id.sign_in_btn);
        signed_in_btn = (Button)view.findViewById(R.id.signed_in_btn);
        userAttention = (TextView)view.findViewById(R.id.userAttention);
        userFans = (TextView)view.findViewById(R.id.userFans);
        userIntegral = (TextView)view.findViewById(R.id.userIntegral);
        userPhoto = (CircleImageView)view.findViewById(R.id.userPhoto);
        selfBookself = (LinearLayout)view.findViewById(R.id.selfBookself);
        selfMessage = (LinearLayout)view.findViewById(R.id.selfMessage);
        selfDynamic = (LinearLayout)view.findViewById(R.id.selfDynamic);
        selfReadRecord = (LinearLayout)view.findViewById(R.id.selfReadRecord);
        selfSite = (LinearLayout)view.findViewById(R.id.selfSite);
        selfPointsDes = (LinearLayout)view.findViewById(R.id.selfPointsDes);
        outLogin = (LinearLayout)view.findViewById(R.id.outLogin);
    }

    //更新数据
    public void updateUserInfo(){
        //先通过SharePreference获取是否存储用户的基本信息
        //通过网络获取用户信息并更新数据
        //发送数据
            //通过SharePreference获得ID和密码
            //getUserInfo= "{\"Id\":\"21212\",\"Password\":\"1122\"}";
        //接受数据
            //1.校验成功，返回用户资料
                //eg.  {"status":"True,"info":{"userName":"Jack","checkStatus":"True"，"userAttention":1,"userFans":2,"userIntegral":300}}
                //替换数据信息，存储至SharePreference中
            //2.校验失败，提示错误信息(若为密码和用户名不符，则跳转到登录界面)
                //eg.  {"status":"False","info":"用户名与密码不匹配"}\
                //跳转到登录界面
    }

    //初始化点击控件
    public void initClickViews(){
        sign_in_btn.setOnClickListener(this);
        selfBookself.setOnClickListener(this);
        selfMessage.setOnClickListener(this);
        selfDynamic.setOnClickListener(this);
        selfReadRecord.setOnClickListener(this);
        selfSite.setOnClickListener(this);
        selfPointsDes.setOnClickListener(this);
        outLogin.setOnClickListener(this);
        userPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_btn:
                sign_in_btn.setVisibility(View.GONE);
                signed_in_btn.setVisibility(View.VISIBLE);
                break;
            case R.id.selfBookself:
                //Toast.makeText(this.getActivity(),"我的书架",Toast.LENGTH_SHORT).show();
                Intent to_selfBookself = new Intent(getActivity(),selfBookselfActivity.class);
                startActivity(to_selfBookself);
                break;
            case R.id.selfMessage:
                //Toast.makeText(this.getActivity(),"我的消息",Toast.LENGTH_SHORT).show();
                Intent to_selfMessage = new Intent(getActivity(),selfMessageActivity.class);
                startActivity(to_selfMessage);
                break;
            case R.id.selfDynamic:
                //Toast.makeText(this.getActivity(),"我的动态",Toast.LENGTH_SHORT).show();
                Intent to_selfDynamic = new Intent(getActivity(),selfDynamicActivity.class);
                startActivity(to_selfDynamic);
                break;
            case R.id.selfReadRecord:
                //Toast.makeText(this.getActivity(),"阅读记录",Toast.LENGTH_SHORT).show();
                Intent to_selfReadRecord = new Intent(getActivity(),selfReadRecordActivity.class);
                startActivity(to_selfReadRecord);
                break;
            case R.id.selfSite:
                //Toast.makeText(this.getActivity(),"设置",Toast.LENGTH_SHORT).show();
                Intent to_selfSite = new Intent(getActivity(),selfSiteActivity.class);
                startActivity(to_selfSite);
                break;
            case R.id.selfPointsDes:
                //Toast.makeText(this.getActivity(),"积分说明",Toast.LENGTH_SHORT).show();
                Intent to_selfPointsDes = new Intent(getActivity(),selfPointsDesActivity.class);
                startActivity(to_selfPointsDes);
                break;
            case R.id.outLogin:
                new CommomDialog(this.getActivity(), R.style.dialog, "您确定要退出登录？", new CommomDialog.OnCloseListener(){
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if(confirm){
                            //发送请求退出登录
                            Intent to_login = new Intent(getActivity(), LoginActivity.class);
                            startActivity(to_login);
                            dialog.dismiss();
                            getActivity().finish();
                        }
                    }
                }).setTitle("提示").show();
                break;
            case R.id.userPhoto:
                Intent toUserInfo = new Intent(this.getActivity(),userInfoActivity.class);
                toUserInfo.putExtra("isUserSelf",true);
                startActivity(toUserInfo);
                break;
        }
    }
}
