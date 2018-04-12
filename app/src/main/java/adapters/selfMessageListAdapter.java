package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import beans.playgroundListBean;
import beans.selfMessageListBean;
import nl.neulibrary.R;

/**
 * 我的消息Adapter
 */

public class selfMessageListAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<selfMessageListBean> mDatas;
    ViewHolder holder = null;
    selfMessageListBean bean ;


    //selfMessageListAdapter，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public selfMessageListAdapter(Context context, List<selfMessageListBean> datas) {
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
            view = mInflater.inflate(R.layout.self_message_lists, viewGroup, false); //加载布局
            holder = new ViewHolder();
            holder.userPhoto = (ImageView)view.findViewById(R.id.userPhoto);
            holder.userName = (TextView)view.findViewById(R.id.userName);
            holder.userMessageInfo = (TextView)view.findViewById(R.id.userMessageInfo);
            holder.callback = (LinearLayout)view.findViewById(R.id.callback);
            holder.commentInfo = (LinearLayout)view.findViewById(R.id.commentInfo);
            holder.commentInfoText = (TextView)view.findViewById(R.id.commentInfoText);
            holder.backFrame = (LinearLayout)view.findViewById(R.id.backFrame);
            holder.backMessage = (EditText)view.findViewById(R.id.backMessage);
            holder.closeBack = (Button)view.findViewById(R.id.closeBack);
            holder.submitBack = (Button)view.findViewById(R.id.submitBack);
            view.setTag(holder);

        } else {
            //else里面说明，view已经被复用了，说明view中已经设置过tag了，即holder
            holder = (ViewHolder) view.getTag();
        }

        final ImageView userPhoto = holder.userPhoto;
        final TextView userName = holder.userName;
        final TextView userMessageInfo = holder.userMessageInfo;
        final LinearLayout callback = holder.callback;
        final LinearLayout commentInfo = holder.commentInfo;
        final TextView commentInfoText = holder.commentInfoText;
        final LinearLayout backFrame = holder.backFrame;
        final EditText backMessage = holder.backMessage;
        final Button closeBack = holder.closeBack;
        final Button submitBack = holder.submitBack;

        bean = mDatas.get(i);
        //发送请求获取用户头像资源，并更新
        //holder.userPhoto.setImageSource();
        holder.userName.setText(bean.getUserName());
        holder.userMessageInfo.setText(bean.getUserMessageInfo());
        if(bean.getUserMessageType()=="0"){  //假设0为评论
            callback.setVisibility(View.VISIBLE);
            commentInfo.setVisibility(View.VISIBLE);
            commentInfoText.setText(bean.getUserCommentInfo());
            callback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    backFrame.setVisibility(View.VISIBLE);
                }
            });
            submitBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //发送回复数据
                }
            });
            closeBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    backFrame.setVisibility(View.GONE);
                }
            });
        }
        return view;
    }

    //这个ViewHolder只能服务于当前这个特定的adapter，因为ViewHolder里会指定item的控件，不同的ListView，item可能不同，所以ViewHolder写成一个私有的类
    private class ViewHolder {
        ImageView userPhoto;
        TextView userName;
        TextView userMessageInfo;
        LinearLayout callback;
        LinearLayout commentInfo;
        TextView commentInfoText;
        LinearLayout backFrame;
        EditText backMessage;
        Button closeBack;
        Button submitBack;
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
