package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import beans.attentionListBean;
import beans.borrowBookListBean;
import nl.neulibrary.LoginActivity;
import nl.neulibrary.R;
import tools.CircleImageView;


public class attentionListAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<attentionListBean> mDatas;
    ViewHolder holder = null;
    attentionListBean bean ;

    public attentionListAdapter(Context context, List<attentionListBean> datas) {
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
            view = mInflater.inflate(R.layout.attention_list, viewGroup, false); //加载布局
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
        userPhoto.setImageResource(bean.getUserImageDrawable());
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
