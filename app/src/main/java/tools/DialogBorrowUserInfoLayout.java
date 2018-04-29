package tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import nl.neulibrary.R;

/**
 * 推荐=>借阅用户中的用户信息
 */

public class DialogBorrowUserInfoLayout extends LinearLayout {
    private ImageView userPhoto;
    private TextView borrowUserName;
    private TextView borrowTime;
    private Context context;

    public DialogBorrowUserInfoLayout(Context context,String userPhotoURL,String userName,String userBorrowTime) {
        super(context);
        this.context=context;
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
        RequestQueue mQueue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(userPhoto, R.drawable.loading_on, R.drawable.loading_wrong);
        imageLoader.get(userPhotoURL, listener);
    }

    public void setUserName(String userName){
        borrowUserName.setText(userName);
    }

    public void setUserBorrowTime(String userBorrowTime){
        borrowTime.setText(userBorrowTime);
    }

}
