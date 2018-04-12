package tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import nl.neulibrary.R;

/**
 * 推荐=>借阅用户中的用户信息
 */

public class DialogBorrowUserInfoLayout extends LinearLayout {
    private ImageView userPhoto;
    private TextView borrowUserName;
    private TextView borrowTime;

    public DialogBorrowUserInfoLayout(Context context,String userPhotoURL,String userName,String userBorrowTime) {
        super(context);
        initViews(context);
        setUserPhoto(userPhotoURL);
        setUserName(userName);
        setUserBorrowTime(userBorrowTime);
    }

    public void initViews(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.recommend_dialog_borrow_user,this);
        userPhoto = (ImageView)findViewById(R.id.userPhoto);
        borrowUserName = (TextView)findViewById(R.id.borrowUserName);
        borrowTime = (TextView)findViewById(R.id.borrowTime);
    }

    public void setUserPhoto(String userPhotoURL){
        //利用volley设置用户头像
    }

    public void setUserName(String userName){
        borrowUserName.setText(userName);
    }

    public void setUserBorrowTime(String userBorrowTime){
        borrowTime.setText(userBorrowTime);
    }

}
