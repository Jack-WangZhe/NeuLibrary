package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.List;

import beans.attentionListBean;
import beans.fansListBean;
import nl.neulibrary.R;
import tools.BitmapCache;
import tools.CircleImageView;


public class fansListAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<fansListBean> mDatas;
    ViewHolder holder = null;
    fansListBean bean ;
    Context context;

    public fansListAdapter(Context context, List<fansListBean> datas) {
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
            view = mInflater.inflate(R.layout.fans_list, viewGroup, false); //加载布局
            holder = new ViewHolder();
            holder.userPhoto = (CircleImageView) view.findViewById(R.id.userPhoto);
            holder.userName = (TextView) view.findViewById(R.id.userName);
            holder.userId = (TextView) view.findViewById(R.id.userId);
            view.setTag(holder);

        } else {
            //else里面说明，view已经被复用了，说明view中已经设置过tag了，即holder
            holder = (ViewHolder) view.getTag();
        }

        final CircleImageView userPhoto = holder.userPhoto;
        final TextView userName = holder.userName;
        final TextView userId = holder.userId;

        bean = mDatas.get(i);
        //userPhoto.setImageResource(bean.getUserImageDrawable());
        holder.userPhoto.setTag(bean.getUserImageURL());
        RequestQueue mQueue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.userPhoto, R.drawable.loading_on, R.drawable.loading_wrong);
        imageLoader.get(bean.getUserImageURL(), listener);
        userId.setText(bean.getUserId());
        userName.setText(bean.getUserName());
        return view;
    }

    //这个ViewHolder只能服务于当前这个特定的adapter，因为ViewHolder里会指定item的控件，不同的ListView，item可能不同，所以ViewHolder写成一个私有的类
    private class ViewHolder {
        CircleImageView userPhoto;
        TextView userName;
        TextView userId;
    }
}
