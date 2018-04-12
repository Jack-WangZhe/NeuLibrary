package fragments;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import nl.neulibrary.R;
import nl.neulibrary.serviceSearchBook;
import tools.HomeBooksLinearLayout;
import tools.showReadStatusDialog;

public class recommendFragment extends Fragment implements View.OnClickListener{
    //整体fragment
    private View view;

    //布局控件
    private EditText searchBook;
    private ImageView submitSearch;
    private ImageView time_clock; //计时器
    private ViewPager rf_fragment; //fragment切换
    private Button recommend_downline;//图书推荐下划线
    private Button type_downline;//图书分类下划线
    private LinearLayout AllRecommend;
    private LinearLayout AllType;
    Fragment rfRecommend;
    Fragment rfType;
    //碎片集合
    private ArrayList<Fragment> fragmentList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recommend, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initView();
        initPage();
    }

    //初始化控件
    public void initView(){
        rf_fragment=(ViewPager)view.findViewById(R.id.rf_fragment);
        AllRecommend = (LinearLayout)view.findViewById(R.id.AllRecommend);
        AllType = (LinearLayout)view.findViewById(R.id.AllType);
        recommend_downline = (Button)view.findViewById(R.id.recommend_downline);
        type_downline = (Button)view.findViewById(R.id.type_downline);
        AllRecommend.setOnClickListener(this);
        AllType.setOnClickListener(this);
        time_clock = (ImageView)view.findViewById(R.id.time_clock);
        time_clock.setOnClickListener(this);
        searchBook = (EditText)view.findViewById(R.id.searchBook);
        submitSearch = (ImageView)view.findViewById(R.id.submitSearch);
        submitSearch.setOnClickListener(this);
    }

    //初始化viewpager
    public void initPage(){
        fragmentList=new ArrayList<Fragment>();
        if(rfRecommend == null ){
            rfRecommend=new rf_recommend();
        }
        if(rfType == null ){
            rfType=new rf_type();
        }
        fragmentList.add(rfRecommend);
        fragmentList.add(rfType);
        rf_fragment.setAdapter(new MyAdapter(getChildFragmentManager(),fragmentList));
        rf_fragment.addOnPageChangeListener(new MyListener());
        rf_fragment.setCurrentItem(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.AllRecommend:
                rf_fragment.setCurrentItem(0);
                break;
            case R.id.AllType:
                rf_fragment.setCurrentItem(1);
                break;
            case R.id.time_clock:
                new showReadStatusDialog(this.getActivity(), R.style.dialog,"123","20",true,false).show();
                break;
            case R.id.submitSearch:
                String searchInfo = searchBook.getText().toString();
                Intent toSearch = new Intent(this.getActivity(),serviceSearchBook.class);
                toSearch.putExtra("searchInfo",searchInfo);
                startActivity(toSearch);
        }
    }

    public class MyAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment>list;
        public MyAdapter(FragmentManager fm, ArrayList<Fragment>list){
            super(fm);
            this.list=list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


    public class MyListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int current=rf_fragment.getCurrentItem();
            switch (current){
                case 0:
                    recommend_downline.setVisibility(View.VISIBLE);
                    type_downline.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    recommend_downline.setVisibility(View.INVISIBLE);
                    type_downline.setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
