package nl.neulibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);  //测试推荐自定义Dialog模块中pdf版本
        //setContentView(R.layout.recommend_pdf_dialog);  //测试推荐自定义Dialog模块中pdf版本
        //setContentView(R.layout.recommend_dialog_borrow_user);  //测试推荐自定义Dialog模块中的借阅用户
        //setContentView(R.layout.recommend_no_pdf_dialog);  //测试推荐自定义Dialog模块
        //setContentView(R.layout.self_dynamic_view);  //测试我的动态模块
    }
}
