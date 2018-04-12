package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import beans.playgroundListBean;
import beans.selfBookSelfListBean;
import nl.neulibrary.R;

/**
 * 个人书架适配器
 */

public class selfBookSelfListAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<selfBookSelfListBean> mDatas;
    ViewHolder holder = null;
    selfBookSelfListBean bean ;


    //selfBookSelfListAdapter，通过Context获得Layout.inflater，然后通过inflater加载item的布局
    public selfBookSelfListAdapter(Context context, List<selfBookSelfListBean> datas) {
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
            view = mInflater.inflate(R.layout.self_bookself_lists, viewGroup, false); //加载布局
            holder = new ViewHolder();
            holder.BookPhoto = (ImageView)view.findViewById(R.id.bookPhoto);
            holder.BookId = (TextView)view.findViewById(R.id.bookId);
            holder.BookName = (TextView)view.findViewById(R.id.bookName);
            holder.BookAuthor = (TextView)view.findViewById(R.id.bookAuthor);
            holder.getTime = (TextView)view.findViewById(R.id.getTime);
            view.setTag(holder);

        } else {
            //else里面说明，view已经被复用了，说明view中已经设置过tag了，即holder
            holder = (ViewHolder) view.getTag();
        }
        final ImageView real_bookPhoto = holder.BookPhoto;
        final TextView real_bookId = holder.BookId;
        final TextView real_bookName = holder.BookName;
        final TextView real_bookAuthor = holder.BookAuthor;
        final TextView real_getTime = holder.getTime;

        bean = mDatas.get(i);
        //发送请求获取用户头像资源，并更新
        //holder.BookPhoto.setImageSource();
        holder.BookId.setText(bean.getBookId());
        holder.BookAuthor.setText(bean.getBookAuthor());
        holder.BookName.setText(bean.getBookName());
        holder.getTime.setText(bean.getGetTime());
        return view;
    }

    //这个ViewHolder只能服务于当前这个特定的adapter，因为ViewHolder里会指定item的控件，不同的ListView，item可能不同，所以ViewHolder写成一个私有的类
    private class ViewHolder {
        ImageView BookPhoto;
        TextView BookId;
        TextView BookName;
        TextView BookAuthor;
        TextView getTime;
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
