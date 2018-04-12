package adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import beans.selfDynamicListBean;
import nl.neulibrary.R;
import tools.CommomDialog;

/**
 * 操场消息适配器
 */

public class userInfoDynamicListAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<selfDynamicListBean> mDatas;
    ViewHolder holder = null;
    selfDynamicListBean bean ;
    Context context;


    //selfDynamicListAdapter，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public userInfoDynamicListAdapter(Context context, List<selfDynamicListBean> datas) {
        this.context = context;
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
            view = mInflater.inflate(R.layout.user_info_dynamic_list, viewGroup, false); //加载布局
            holder = new ViewHolder();
            holder.userPhoto = (ImageView) view.findViewById(R.id.userPhoto);
            holder.userName = (TextView) view.findViewById(R.id.userName);
            holder.userSendTime = (TextView) view.findViewById(R.id.sendTime);
            holder.bookPhoto = (ImageView) view.findViewById(R.id.bookPhoto);
            holder.itemDesc = (TextView) view.findViewById(R.id.itemDesc);
            holder.praiseNum = (TextView) view.findViewById(R.id.praiseNum);
            holder.commentNum = (TextView) view.findViewById(R.id.commentNum);
            view.setTag(holder);
        } else {
            //else里面说明，view已经被复用了，说明view中已经设置过tag了，即holder
            holder = (ViewHolder) view.getTag();
        }
        bean = mDatas.get(i);
        //发送请求获取用户头像资源，并更新
        //holder.userPhoto.setImageSource();
        //发送请求获取图书资源，并更新
        //holder.bookPhoto.setImageSource();
        holder.userName.setText(bean.getUserName());
        holder.userSendTime.setText(bean.getUserSendTime());
        holder.itemDesc.setText(bean.getItemDesc());
        holder.praiseNum.setText(bean.getPraiseNum());
        holder.commentNum.setText(bean.getCommentNum());

        final TextView userName = holder.userName;
        final ImageView userPhoto = holder.userPhoto;
        final TextView userSendTime = holder.userSendTime;
        final ImageView bookPhoto = holder.bookPhoto;
        final TextView itemDesc = holder.itemDesc;
        final TextView praiseNum = holder.praiseNum;
        final TextView commentNum = holder.commentNum;
        return view;
    }

    //这个ViewHolder只能服务于当前这个特定的adapter，因为ViewHolder里会指定item的控件，不同的ListView，item可能不同，所以ViewHolder写成一个私有的类
    private class ViewHolder {
        ImageView userPhoto;
        TextView userName;
        TextView userSendTime;
        ImageView bookPhoto;
        TextView itemDesc;
        TextView praiseNum;
        TextView commentNum;
    }
    private class concernListener implements View.OnClickListener {
         int mPosition;
         public concernListener(int inPosition){
           mPosition= inPosition;
         }
         @Override
         public void onClick(View v) {

         }
    }
}
