package tools;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import nl.neulibrary.R;

public class showReadStatusDialog extends Dialog implements View.OnClickListener {
    private ImageView dialogCancel;  //关闭对话框
    private TextView onlineTime;
    private TextView readBookNum;
    private ImageView onlineFinished;
    private ImageView onlineUnFinished;
    private ImageView isChecked;
    private ImageView isUnChecked;

    private Context mContext;
    private String online_time;
    private String read_book_num;
    private Boolean is_online_finished;
    private Boolean is_checked;

    public showReadStatusDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public showReadStatusDialog(Context context,int themeResId, String online_time, String read_book_num, Boolean is_online_finished,Boolean is_checked) {
        super(context,themeResId);
        this.mContext = context;
        this.online_time = online_time;
        this.read_book_num = read_book_num;
        this.is_online_finished = is_online_finished;
        this.is_checked = is_checked;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_read_status_dialog);
//        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        onlineTime = (TextView)findViewById(R.id.readTime);
        readBookNum = (TextView)findViewById(R.id.bookNum);
        onlineFinished = (ImageView)findViewById(R.id.onlineFinished);
        onlineUnFinished = (ImageView)findViewById(R.id.onlineUnFinish);
        isChecked = (ImageView)findViewById(R.id.isChecked);
        isUnChecked = (ImageView)findViewById(R.id.isUnCheck);

        onlineTime.setText(online_time);
        readBookNum.setText(read_book_num);
        if(is_online_finished){
            onlineFinished.setVisibility(View.VISIBLE);
            onlineUnFinished.setVisibility(View.INVISIBLE);
        }
        if (is_checked){
            isChecked.setVisibility(View.VISIBLE);
            isUnChecked.setVisibility(View.INVISIBLE);
        }

        dialogCancel = (ImageView) findViewById(R.id.dialogCancel);
        dialogCancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialogCancel:
                this.dismiss();
                break;
        }
    }

}
