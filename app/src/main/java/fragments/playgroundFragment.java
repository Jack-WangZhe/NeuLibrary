package fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.oragee.banners.BannerView;

import java.util.ArrayList;
import java.util.List;

import adapters.playgroundListAdapter;
import beans.playgroundListBean;
import nl.neulibrary.BannerInfoActivity;
import nl.neulibrary.R;
import nl.neulibrary.TestActivity;
import nl.neulibrary.playgroundMessage;

public class playgroundFragment extends Fragment implements View.OnClickListener {
    private View view;

    private int[] imgs = {R.drawable.banner_test01,R.drawable.banner_test02,R.drawable.banner_test03,R.drawable.banner_test04};
    private List<View> viewList;
    private BannerView bannerView;
    private LinearLayout latestNews;
    private LinearLayout latestNewsDecorate;
    private LinearLayout hotestNews;
    private LinearLayout hotestNewsDecorate;
    private ListView ItemLists;
    private LinearLayout playgroundAddItems;
    private playgroundListAdapter itemListViewAdapterWithViewHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playground, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initBanner();
        initViews();
        firstRequest();
    }

    public void initBanner(){
        final Context context = this.getContext();
        viewList = new ArrayList<View>();
        for (int i = 0; i < imgs.length; i++) {
            final int j = i;
            ImageView image = new ImageView(this.getActivity());
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置显示格式
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setImageResource(imgs[i]);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(j == 0){
                        Intent toInfo = new Intent(context, BannerInfoActivity.class);
                        String infoTitle = "读书月活动——习主席借我一本书";
                        toInfo.putExtra("infoTitle",infoTitle);
                        startActivity(toInfo);
                    }
                }
            });
            viewList.add(image);
        }
        bannerView = (BannerView)view.findViewById(R.id.playGroundBanner);
        bannerView.startLoop(true);
        bannerView.setViewList(viewList);
        //bannerView.setTransformAnim(true);
    }

    public void initViews(){
        latestNews = (LinearLayout)view.findViewById(R.id.latestNews);
        latestNews.setOnClickListener(this);
        latestNewsDecorate = (LinearLayout)view.findViewById(R.id.latestNewsDecorate);
        hotestNews = (LinearLayout)view.findViewById(R.id.hotestNews);
        hotestNews.setOnClickListener(this);
        hotestNewsDecorate = (LinearLayout)view.findViewById(R.id.hotestNewsDecorate);
        ItemLists = (ListView)view.findViewById(R.id.ItemLists);
        playgroundAddItems = (LinearLayout)view.findViewById(R.id.playgroundAddItems);
        playgroundAddItems.setOnClickListener(this);
    }

    public void initDatas(String datas){
        //解析字符串
        //以下为模拟数据
        List<playgroundListBean>mDatas = new ArrayList<playgroundListBean>();
        //将数据装到集合中去
        playgroundListBean bean = new playgroundListBean("","汪喆","2018-3-3 19:20",false,"","今天看了一本书，感觉特别赞","4","5");
        mDatas.add(bean);
        bean = new playgroundListBean("","辛俊桥","2018-3-6 13:20",false,"","今天看了一本书，感觉真不咋地","2","3");
        mDatas.add(bean);

        bean = new playgroundListBean("","车俊林","2018-4-4 3:20",true,"","看得我晚上都睡不着觉了","3","0");
        mDatas.add(bean);

        bean = new playgroundListBean("","苏凯","2018-5-3 19:20",false,"","今天的书真的是太精彩了","3","5");
        mDatas.add(bean);

        //为数据绑定适配器
        itemListViewAdapterWithViewHolder = new playgroundListAdapter(this.getActivity(), mDatas);

        ItemLists.setAdapter(itemListViewAdapterWithViewHolder);
    }

    public void firstRequest(){
        //发送请求数据,获取最近消息
        initDatas("");
    }
    
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.latestNews:
                latestNewsDecorate.setVisibility(View.VISIBLE);
                hotestNewsDecorate.setVisibility(View.GONE);
                //发送请求数据,获取最近消息
                initDatas("");
                break;
            case R.id.hotestNews:
                latestNewsDecorate.setVisibility(View.GONE);
                hotestNewsDecorate.setVisibility(View.VISIBLE);
                //发送请求数据获取最热消息
                initDatas("");
                break;
            case R.id.playgroundAddItems:
                Intent toPlaygroundMessage = new Intent(getActivity(),playgroundMessage.class);
                startActivity(toPlaygroundMessage);
                break;
        }
    }
}
