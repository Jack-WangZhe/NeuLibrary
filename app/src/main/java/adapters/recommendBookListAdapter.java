package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.List;

import beans.recommendBookListBean;
import nl.neulibrary.R;
import tools.BitmapCache;


public class recommendBookListAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<recommendBookListBean> mDatas;
    ViewHolder holder = null;
    recommendBookListBean bean ;
    Context context;

    public recommendBookListAdapter(Context context, List<recommendBookListBean> datas) {
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
            view = mInflater.inflate(R.layout.rf_book_recommend_list, viewGroup, false); //加载布局
            holder = new ViewHolder();
            holder.bookId = (TextView)view.findViewById(R.id.bookId);
            holder.bookImage = (ImageView)view.findViewById(R.id.bookImage);
            holder.isPDF = (LinearLayout) view.findViewById(R.id.isPDF);
            holder.bookName = (TextView)view.findViewById(R.id.bookName);
            holder.inKU = (TextView)view.findViewById(R.id.inKU);
            holder.bookAuthor = (TextView)view.findViewById(R.id.bookAuthor);
            holder.bookInfo = (TextView)view.findViewById(R.id.bookInfo);

            view.setTag(holder);

        } else {
            //else里面说明，view已经被复用了，说明view中已经设置过tag了，即holder
            holder = (ViewHolder) view.getTag();
        }

        final TextView bookId = holder.bookId;
        final ImageView bookImage = holder.bookImage;
        final LinearLayout isPDF = holder.isPDF;
        final TextView bookName = holder.bookName;
        final TextView inKU = holder.inKU;
        final TextView bookAuthor = holder.bookAuthor;
        final TextView bookInfo = holder.bookInfo;

        bean = mDatas.get(i);

        holder.bookImage.setTag(bean.getBook_url());
        RequestQueue mQueue = Volley.newRequestQueue(context);
        ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holder.bookImage, R.drawable.loading_on, R.drawable.loading_wrong);
        imageLoader.get(bean.getBook_url(), listener);

        holder.bookId.setText(bean.getBookId());
//        holder.bookImage.setImageResource(bean.getBook_image());
//        holder.bookImage.setTag(bean.getBook_image());
        holder.isPDF.setTag(bean.getPDF());
        if (bean.getPDF()){
            holder.isPDF.setVisibility(View.VISIBLE);
        }else{
            holder.isPDF.setVisibility(View.INVISIBLE);
        }
        holder.bookName.setText(bean.getBookName());
        if (bean.getBookstatus()) {
            holder.inKU.setVisibility(View.VISIBLE);
        }
        holder.bookAuthor.setText(bean.getBookAuthor());
        holder.bookInfo.setText(bean.getBookInfo());
        return view;
    }

    //这个ViewHolder只能服务于当前这个特定的adapter，因为ViewHolder里会指定item的控件，不同的ListView，item可能不同，所以ViewHolder写成一个私有的类
    private class ViewHolder {
        TextView bookId;
        ImageView bookImage;
        LinearLayout isPDF;
        TextView bookName;
        TextView inKU;
        TextView bookAuthor;
        TextView bookInfo;
    }
}
