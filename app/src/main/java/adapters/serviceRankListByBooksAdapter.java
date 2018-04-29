package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.List;

import beans.playgroundListBean;
import beans.serviceRankListByBooksBean;
import nl.neulibrary.R;
import tools.BitmapCache;
import tools.CircleImageView;

/**
 * 根据书排序适配器
 */

public class serviceRankListByBooksAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<serviceRankListByBooksBean> mDatas;
    ViewHolder holder = null;
    serviceRankListByBooksBean bean ;
    Context context;


    //serviceRankListByBooksAdapter，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public serviceRankListByBooksAdapter(Context context, List<serviceRankListByBooksBean> datas) {
        this.context=context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mInflater.inflate(R.layout.service_rank_list, viewGroup, false); //加载布局
            holder = new ViewHolder();
            holder.goldMedal = (ImageView) view.findViewById(R.id.goldMedal);
            holder.silvermedal = (ImageView) view.findViewById(R.id.silvermedal);
            holder.coppermedal = (ImageView) view.findViewById(R.id.coppermedal);
            holder.usersRank = (TextView) view.findViewById(R.id.usersRank);
            holder.usersPhoto = (CircleImageView) view.findViewById(R.id.usersPhoto);
            holder.usersName = (TextView) view.findViewById(R.id.usersName);
            holder.getNumbers = (TextView) view.findViewById(R.id.getNumbers);
            view.setTag(holder);
        } else {
            //else里面说明，view已经被复用了，说明view中已经设置过tag了，即holder
            holder = (ViewHolder) view.getTag();
        }
        holder.goldMedal.setVisibility(View.GONE);
        holder.silvermedal.setVisibility(View.GONE);
        holder.coppermedal.setVisibility(View.GONE);
        holder.usersRank.setVisibility(View.VISIBLE);
        bean = mDatas.get(i);
        holder.usersRank.setText(bean.getRankNumber()+"");
        if (bean.getRankNumber()==1){
            holder.usersRank.setVisibility(View.GONE);
            holder.goldMedal.setVisibility(View.VISIBLE);
        }
        else if(bean.getRankNumber()==2){
            holder.usersRank.setVisibility(View.GONE);
            holder.silvermedal.setVisibility(View.VISIBLE);
        }
        else if(bean.getRankNumber()==3) {
            holder.usersRank.setVisibility(View.GONE);
            holder.coppermedal.setVisibility(View.VISIBLE);
        }
        holder.usersPhoto.setTag(bean.getUserPhotoURL());
        RequestQueue mQueue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.usersPhoto, R.drawable.loading_on, R.drawable.loading_wrong);
        if(((String)holder.usersPhoto.getTag()!=null)&&(holder.usersPhoto.getTag().equals(bean.getUserPhotoURL()))){
            imageLoader.get(bean.getUserPhotoURL(), listener);
        }

        //发送请求获取用户头像资源，并更新
        //holder.usersPhoto.setImageSource();
//        holder.usersPhoto.setImageResource(bean.getUserPhotoImage());
        holder.usersName.setText(bean.getUserName());
        holder.getNumbers.setText(bean.getUserReadBooksNumber());
        return view;
    }

    //这个ViewHolder只能服务于当前这个特定的adapter，因为ViewHolder里会指定item的控件，不同的ListView，item可能不同，所以ViewHolder写成一个私有的类
    private class ViewHolder {
        ImageView goldMedal;
        ImageView silvermedal;
        ImageView coppermedal;
        TextView usersRank;
        CircleImageView usersPhoto;
        TextView usersName;
        TextView getNumbers;
    }
}
