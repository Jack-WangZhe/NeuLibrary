package nl.neulibrary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import fragments.playgroundFragment;
import fragments.recommendFragment;
import fragments.selfFragment;
import fragments.surviceFragment;
import netRequest.OnGetFinishListener;
import netRequest.Urls;
import netRequest.getMethod;
import tools.CustomViewPager;
import tools.removeTitle;

public class HomeActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    //对应控件
    private CustomViewPager AllPager;
    private RadioGroup navigate;
    private RadioButton recommend;
    private RadioButton playground;
    private RadioButton survice;
    private RadioButton self;

    //碎片集合
    private ArrayList<Fragment>fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeTitle.remove(this);
        setContentView(R.layout.activity_home);
        initView();
        initPage();
    }

    //初始化控件
    public void initView(){
        AllPager = (CustomViewPager)findViewById(R.id.AllPager);
        AllPager.setNoScroll(false);
        navigate = (RadioGroup)findViewById(R.id.navigate);
        recommend = (RadioButton)findViewById(R.id.recommend);
        playground = (RadioButton)findViewById(R.id.playground);
        survice = (RadioButton)findViewById(R.id.survice);
        self = (RadioButton)findViewById(R.id.self);
        navigate.setOnCheckedChangeListener(this);
    }

    //初始化viewpager
    public void initPage(){
        fragmentList=new ArrayList<Fragment>();
        Fragment recommendFragment=new recommendFragment();
        Fragment playgroundFragment=new playgroundFragment();
        Fragment surviceFragment=new surviceFragment();
        Fragment selfFragment=new selfFragment();
        fragmentList.add(recommendFragment);
        fragmentList.add(playgroundFragment);
        fragmentList.add(surviceFragment);
        fragmentList.add(selfFragment);
        AllPager.setAdapter(new MyAdapter(getSupportFragmentManager(),fragmentList));
        AllPager.addOnPageChangeListener(new MyListener());
        AllPager.setCurrentItem(0);
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
            int current=AllPager.getCurrentItem();
            switch (current){
                case 0:
                    navigate.check(R.id.recommend);
                    break;
                case 1:
                    navigate.check(R.id.playground);
                    break;
                case 2:
                    navigate.check(R.id.survice);
                    break;
                case 3:
                    navigate.check(R.id.self);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int current=0;
        switch (i){
            case R.id.recommend:
                current=0;
                break;
            case R.id.playground:
                current=1;
                break;
            case R.id.survice:
                current=2;
                break;
            case R.id.self:
                current=3;
                break;
        }
        if(AllPager.getCurrentItem()!=current){
            AllPager.setCurrentItem(current);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        getMethod quitRequest=new getMethod();
        quitRequest.getInfo(this, Urls.quit);
        quitRequest.setOnFinishListener(new OnGetFinishListener() {
            @Override
            public void OnGetFinished(String s) {

            }
        });
    }
}
