package adapters;

import android.content.Context;
import android.icu.util.ValueIterator;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import beans.playgroundListBean;
import nl.neulibrary.R;

/**
 * 操场消息适配器
 */

public class playgroundListAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<playgroundListBean> mDatas;
    ViewHolder holder = null;
    playgroundListBean bean ;


    //playgroundListAdapter，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public playgroundListAdapter(Context context, List<playgroundListBean> datas) {
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
            view = mInflater.inflate(R.layout.playground_lists, viewGroup, false); //加载布局
            holder = new ViewHolder();

            holder.userPhoto = (ImageView) view.findViewById(R.id.userPhoto);
            holder.userName = (TextView) view.findViewById(R.id.userName);
            holder.userSendTime = (TextView) view.findViewById(R.id.userSendTime);
            holder.concernUser = (Button) view.findViewById(R.id.concernUser);
            holder.concernedUser = (Button) view.findViewById(R.id.concernedUser);
            holder.imageInfo = (ImageView) view.findViewById(R.id.imageInfo);
            holder.itemDesc = (TextView) view.findViewById(R.id.itemDesc);
            holder.praiseItem = (ImageView) view.findViewById(R.id.praiseItem);
            holder.praiseNum = (TextView) view.findViewById(R.id.praiseNum);
            holder.commentItem = (ImageView) view.findViewById(R.id.commentItem);
            holder.commentNum = (TextView) view.findViewById(R.id.commentNum);

            view.setTag(holder);

        } else {
            //else里面说明，view已经被复用了，说明view中已经设置过tag了，即holder
            holder = (ViewHolder) view.getTag();
        }

        final Button concernUser = holder.concernUser;
        final Button concernedUser = holder.concernedUser;
        final ImageView praiseItem = holder.praiseItem;
        final TextView praiseNum = holder.praiseNum;
        final ImageView commentItem = holder.commentItem;
        final TextView commentNum = holder.commentNum;

        bean = mDatas.get(i);
        //发送请求获取用户头像资源，并更新
        //holder.userPhoto.setImageSource();
        holder.userName.setText(bean.getUserName());
        holder.userSendTime.setText(bean.getUserSendTime());
        if(bean.getUserConcernSatatus()){
            holder.concernUser.setVisibility(View.GONE);
            holder.concernedUser.setVisibility(View.VISIBLE);
        }

        //关注用户
        holder.concernUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //==================
                //发送请求确定进行关注
                //==================

                concernUser.setVisibility(View.GONE);
                concernedUser.setVisibility(View.VISIBLE);
            }
        });
        if (!bean.getImageInfoURL().equals("")){
            //发送请求获取用户头像资源，并更新
            //holder.imageInfo.setImageSource();
        }
        holder.itemDesc.setText(bean.getItemDesc());
        holder.praiseItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //==================
                //发送请求给对应的消息点赞+1
                //==================
                praiseNum.setText(((Integer.parseInt(praiseNum.getText().toString()))+1)+"");
            }
        });
        holder.praiseNum.setText(bean.getPraiseNum());

        holder.commentItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //==================
                //发送请求给对应的消息评论+1
                //==================
            }
        });
        holder.commentNum.setText(bean.getCommentNum());
        return view;
    }

    //这个ViewHolder只能服务于当前这个特定的adapter，因为ViewHolder里会指定item的控件，不同的ListView，item可能不同，所以ViewHolder写成一个私有的类
    private class ViewHolder {
        ImageView userPhoto;
        TextView userName;
        TextView userSendTime;
        Button concernUser;
        Button concernedUser;
        ImageView imageInfo;
        TextView itemDesc;
        ImageView praiseItem;
        TextView praiseNum;
        ImageView commentItem;
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
