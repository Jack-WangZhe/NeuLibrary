package nl.neulibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tools.removeTitle;

public class playgroundSeePerson extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout cancelPlaygroundMessage;
    private TextView finishSetSeePerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题栏
        removeTitle.remove(this);
        setContentView(R.layout.activity_playground_see_person);
        initViews();
    }

    public void initViews(){
        cancelPlaygroundMessage = (RelativeLayout)findViewById(R.id.cancelPlaygroundMessage);
        cancelPlaygroundMessage.setOnClickListener(this);
        finishSetSeePerson = (TextView)findViewById(R.id.finishSetSeePerson);
        finishSetSeePerson.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancelPlaygroundMessage:
                finish();
                break;
            case R.id.finishSetSeePerson:
                finish();
                break;
        }
    }
}
