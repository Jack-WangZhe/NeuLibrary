package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import beans.borrowBookListBean;
import beans.rebackBookListBean;
import nl.neulibrary.R;


public class rebackBookListAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<rebackBookListBean> mDatas;
    ViewHolder holder = null;
    rebackBookListBean bean ;

    public rebackBookListAdapter(Context context, List<rebackBookListBean> datas) {
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
            view = mInflater.inflate(R.layout.reback_book_list, viewGroup, false); //加载布局
            holder = new ViewHolder();
            holder.bookId = (TextView)view.findViewById(R.id.bookId);
            holder.bookImage = (ImageView)view.findViewById(R.id.bookImage);
            holder.isPDF = (LinearLayout) view.findViewById(R.id.isPDF);
            holder.bookName = (TextView)view.findViewById(R.id.bookName);
            holder.bookAuthor = (TextView)view.findViewById(R.id.bookAuthor);
            holder.bookInfo = (TextView)view.findViewById(R.id.bookInfo);
            holder.borrow = (Button)view.findViewById(R.id.borrow);
            holder.borrowed = (Button)view.findViewById(R.id.borrowed);
            holder.daoqi = (TextView) view.findViewById(R.id.daoqi);
            holder.overTime = (TextView) view.findViewById(R.id.overTime);
            holder.rebackTimeLayout = (LinearLayout)view.findViewById(R.id.rebackTimeLayout);
            holder.rebackTime = (TextView)view.findViewById(R.id.rebackTime);
            view.setTag(holder);

        } else {
            //else里面说明，view已经被复用了，说明view中已经设置过tag了，即holder
            holder = (ViewHolder) view.getTag();
        }

        final TextView bookId = holder.bookId;
        final ImageView bookImage = holder.bookImage;
        final LinearLayout isPDF = holder.isPDF;
        final TextView bookName = holder.bookName;
        final TextView bookAuthor = holder.bookAuthor;
        final TextView bookInfo = holder.bookInfo;
        final TextView daoqi = holder.daoqi;
        final TextView overTime = holder.overTime;
        final TextView borrow = holder.borrow;
        final TextView borrowed = holder.borrowed;
        final LinearLayout rebackTimeLayout = holder.rebackTimeLayout;
        final TextView rebackTime = holder.rebackTime;


        bean = mDatas.get(i);
        //发送请求获取用户头像资源，并更新

        holder.bookId.setText(bean.getBookId());
        holder.bookImage.setImageResource(bean.getBook_image());
        holder.bookImage.setTag(bean.getBook_image());
        if (bean.getPDF()){
            holder.isPDF.setVisibility(View.VISIBLE);
        }
        holder.overTime.setText(bean.getTimeOver());
        holder.borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送volley请求，返回截止日期
                //返回的数据
                //如果成功
                borrow.setVisibility(View.INVISIBLE);
                borrowed.setVisibility(View.VISIBLE);
                daoqi.setVisibility(View.INVISIBLE);
                overTime.setVisibility(View.INVISIBLE);
                rebackTimeLayout.setVisibility(View.VISIBLE);
                rebackTime.setText("2018-5-20");
                //如果失败

            }
        });
        holder.bookName.setText(bean.getBookName());
        holder.bookAuthor.setText(bean.getBookAuthor());
        holder.bookInfo.setText(bean.getBookInfo());
        if(bean.getRebackStatus()){
            borrow.setVisibility(View.INVISIBLE);
            borrowed.setVisibility(View.VISIBLE);
            daoqi.setVisibility(View.INVISIBLE);
            overTime.setVisibility(View.INVISIBLE);
            rebackTimeLayout.setVisibility(View.VISIBLE);
            rebackTime.setText(bean.getRebackTime());
        }
        return view;
    }

    //这个ViewHolder只能服务于当前这个特定的adapter，因为ViewHolder里会指定item的控件，不同的ListView，item可能不同，所以ViewHolder写成一个私有的类
    private class ViewHolder {
        TextView bookId;
        ImageView bookImage;
        LinearLayout isPDF;
        TextView bookName;
        TextView bookAuthor;
        TextView bookInfo;
        Button borrow;
        Button borrowed;
        TextView daoqi;
        TextView overTime;
        LinearLayout rebackTimeLayout;
        TextView rebackTime;
    }
}
