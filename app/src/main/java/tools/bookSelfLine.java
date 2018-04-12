package tools;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import nl.neulibrary.BookReadActivity;
import nl.neulibrary.R;


public class bookSelfLine extends LinearLayout{
    private Context context;
    public bookSelfLine(Context context) {
        super(context);
        initViews(context);
    }
    public bookSelfLine(Context context, AttributeSet attrs){
        super(context,attrs);
        initViews(context);
    }

    //初始化控件
    public void initViews(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bookself_line,this);
        this.context = context;
    }

}
