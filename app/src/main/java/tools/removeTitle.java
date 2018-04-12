package tools;

import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * 去除窗口标题工具
 */

public class removeTitle {
    public static void remove(AppCompatActivity appCompatActivity){
        if (appCompatActivity.getSupportActionBar() != null){
            appCompatActivity.getSupportActionBar().hide();
        }
        else{
            appCompatActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
    }
}
