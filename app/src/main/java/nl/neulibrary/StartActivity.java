package nl.neulibrary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class StartActivity extends AppCompatActivity {
    public final static int Mode=MODE_PRIVATE;
    public final static String PreferencesFile="userInfo.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences=getSharedPreferences(PreferencesFile,Mode);
        String userId=sharedPreferences.getString("userId",null);
        if(userId==null)
        {
            Intent intent=new Intent();
            intent.setClass(StartActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }else
        {
            Intent intent=new Intent();
            intent.setClass(StartActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}