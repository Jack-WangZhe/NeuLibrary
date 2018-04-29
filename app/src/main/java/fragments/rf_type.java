package fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import nl.neulibrary.R;
import tools.HomeBooksLinearLayout;

public class rf_type extends Fragment implements View.OnClickListener{
    //整体fragment
    private View view;

    //布局控件
    private Button electronic_btn; //电子工程系按钮
    private Button business_btn; //商务管理系按钮
    private Button information_btn; //信息管理系按钮
    private Button software_btn; //软件工程系按钮
    private Button japanese_btn; //日语系按钮
    private Button english_btn; //英语系按钮
    private Button russian_btn; //俄语系按钮
    private Button art_btn; //数字艺术系按钮
    private LinearLayout recommendBooks; //推荐的书籍

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rf_type, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initView();
        initClickInfo();
        dy_addView();
    }

    //初始化控件
    public void initView(){
        electronic_btn = (Button)view.findViewById(R.id.electronic_btn);
        business_btn = (Button)view.findViewById(R.id.business_btn);
        information_btn = (Button)view.findViewById(R.id.information_btn);
        software_btn = (Button)view.findViewById(R.id.software_btn);
        japanese_btn = (Button)view.findViewById(R.id.japanese_btn);
        english_btn = (Button)view.findViewById(R.id.english_btn);
        russian_btn = (Button)view.findViewById(R.id.russian_btn);
        art_btn = (Button)view.findViewById(R.id.art_btn);
        recommendBooks = (LinearLayout)view.findViewById(R.id.recommendBooks);
    }

    //初始化点击事件的按钮选项
    public void initClickInfo(){
        electronic_btn.setOnClickListener(this);
        business_btn.setOnClickListener(this);
        information_btn.setOnClickListener(this);
        software_btn.setOnClickListener(this);
        japanese_btn.setOnClickListener(this);
        english_btn.setOnClickListener(this);
        russian_btn.setOnClickListener(this);
        art_btn.setOnClickListener(this);
    }

    //动态添加组件
    public void dy_addView(){
        for (int i = 1;i<=4;i++){
            /*HomeBooksLinearLayout bookline = new HomeBooksLinearLayout(this.getActivity());
            if(i==1){
                bookline.setBookInfo(this.getActivity(),1,R.drawable.book1,"天堂炼狱",true,true);
                bookline.setBookInfo(this.getActivity(),2,R.drawable.book2,"那些年",false,true);
                bookline.setBookInfo(this.getActivity(),3,R.drawable.book3,"Woody Allen",true,false);
            }
            else if(i==2){
                bookline.setBookInfo(this.getActivity(),1,R.drawable.book4,"秘密花园",true,false);
                bookline.setBookInfo(this.getActivity(),2,R.drawable.book5,"The Kama Stra",true,false);
                bookline.setBookInfo(this.getActivity(),3,R.drawable.book6,"Julio Verne",false,true);
            }
            else if(i==3){
                bookline.setBookInfo(this.getActivity(),1,R.drawable.book7,"窗前的花猫",false,false);
                bookline.setBookInfo(this.getActivity(),2,R.drawable.book8,"左腕的猫",true,true);
                bookline.setBookInfo(this.getActivity(),3,R.drawable.book9,"Psychopath",false,true);
            }
            else if(i==4){
                bookline.setBookNum(2);
                bookline.setBookInfo(this.getActivity(),1,R.drawable.book10,"HARUKI",false,false);
                bookline.setBookInfo(this.getActivity(),2,R.drawable.book11,"活出自我",true,true);
            }
            recommendBooks.addView(bookline);*/
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.electronic_btn:
            case R.id.business_btn:
            case R.id.information_btn:
            case R.id.software_btn:
            case R.id.japanese_btn:
            case R.id.english_btn:
            case R.id.russian_btn:
            case R.id.art_btn:
                Button departmentsBtn = (Button)view;
                Toast.makeText(this.getActivity(),departmentsBtn.getText().toString(),Toast.LENGTH_SHORT).show();
                //发送volley请求，获取对应系别的书籍
                //发送格式：
                //接受格式：
                break;
        }
    }
}
