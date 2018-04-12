package nl.neulibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tools.removeTitle;

public class playgroundMessage extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout cancelPlayground;
    private RelativeLayout seePerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        removeTitle.remove(this);
        setContentView(R.layout.activity_play_ground_message);
        initViews();
    }

    public void initViews(){
        cancelPlayground = (RelativeLayout) findViewById(R.id.cancelPlayground);
        cancelPlayground.setOnClickListener(this);
        seePerson = (RelativeLayout)findViewById(R.id.seePerson);
        seePerson.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancelPlayground:
                //取消当前窗口
                finish();
                break;
            case R.id.seePerson:
                //进入可视人群的选择
                Intent toPlaygroundSeePerson = new Intent(this,playgroundSeePerson.class);
                startActivity(toPlaygroundSeePerson);
                break;
        }
    }
}
