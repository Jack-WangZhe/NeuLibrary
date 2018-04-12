package fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.recommendBookListAdapter;
import beans.recommendBookListBean;
import nl.neulibrary.BookReadActivity;
import nl.neulibrary.R;
import tools.CommomDialog;
import tools.NoPDFDialog;
import tools.PDFDialog;

public class rf_recommend extends Fragment implements AdapterView.OnItemClickListener {
    //整体fragment
    private View view;

    private ListView rf_recommendLists;
    private recommendBookListAdapter itemListViewAdapterWithViewHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_rf_recommend, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initViews();
        initDatas("");
    }

    public void initViews(){
        rf_recommendLists = (ListView)view.findViewById(R.id.rf_recommendLists);
        rf_recommendLists.setOnItemClickListener(this);
    }

    public void initDatas(String datas){
        //解析字符串
        //以下为模拟数据
        List<recommendBookListBean> mDatas = new ArrayList<recommendBookListBean>();
        //将数据装到集合中去
        recommendBookListBean bean1= new recommendBookListBean("000001","岁月留声","王学泰","人们只有经历了狂风骤雨之后，才懂得安逸自得的可贵。",true,R.drawable.book12,true);
        mDatas.add(bean1);
        recommendBookListBean bean2 = new recommendBookListBean("000002","鲁宾逊漂流记","丹尼尔 笛福","该作主要讲述了主人公鲁滨逊出生于一个中产阶级家庭，一生志在遨游四海。",true,R.drawable.book13,false);
        mDatas.add(bean2);
        recommendBookListBean bean3= new recommendBookListBean("000003","泰山大观","胡志鹏","本书以电视演播词的形式，将泰山人类自然于文华遗产以及相关的风土人情展现的淋漓尽致。",false,R.drawable.book14,true);
        mDatas.add(bean3);
        recommendBookListBean bean4= new recommendBookListBean("000004","设计风尚","劳伦斯 卡宾","该作对广告、多媒体、动漫、影视传媒以及环境艺术服装设计等成功经典案例进行分析。",false,R.drawable.book15,false);
        mDatas.add(bean4);
        recommendBookListBean bean5= new recommendBookListBean("000001","岁月留声","王学泰","人们只有经历了狂风骤雨之后，才懂得安逸自得的可贵。",true,R.drawable.book12,true);
        mDatas.add(bean5);

        //为数据绑定适配器
        itemListViewAdapterWithViewHolder = new recommendBookListAdapter(this.getActivity(), mDatas);
        rf_recommendLists.setAdapter(itemListViewAdapterWithViewHolder);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView bookId = (TextView) view.findViewById(R.id.bookId);
        TextView bookName = (TextView) view.findViewById(R.id.bookName);
        TextView bookAuthor = (TextView)view.findViewById(R.id.bookAuthor);
        TextView bookInfo = (TextView)view.findViewById(R.id.bookInfo);
        LinearLayout isPDF = (LinearLayout)view.findViewById(R.id.isPDF);
        ImageView bookImage = (ImageView)view.findViewById(R.id.bookImage);
        if (isPDF.getVisibility() == View.VISIBLE){
            showPDFDialog(this.getActivity(),(int)bookImage.getTag(), bookName.getText().toString(), "3", "新东方出版社", bookAuthor.getText().toString(), "D33 第三排 433", bookInfo.getText().toString());
        }
        else{
            showNoPDFDialog(this.getActivity(),(int)bookImage.getTag(), bookName.getText().toString(), "3", "新东方出版社", bookAuthor.getText().toString(), "D33 第三排 433", bookInfo.getText().toString());
        }
    }

    public void showNoPDFDialog(final Context context,int book_pic_url,String book_name,String remain_num,String publish_org,String author_name,String book_location,String recent_borrow_user){
        new NoPDFDialog(context, R.style.dialog,book_pic_url,book_name,remain_num,publish_org,author_name,book_location,recent_borrow_user,new NoPDFDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm){
                    new CommomDialog(context, R.style.dialog, "您确定将此书添加到书架？", new CommomDialog.OnCloseListener(){
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if(confirm){
                                //发送添加图书的请求信息
                                //根据返回信息进行Toast
                                Toast.makeText(context,"添加成功",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    }).setTitle("提示").show();
                    dialog.dismiss();
                }
            }
        }).show();
    }

    public void showPDFDialog(final Context context, int book_pic_url, final String book_name, String remain_num, String publish_org, String author_name, String book_location, String book_intro){
        new PDFDialog(context, R.style.dialog,book_pic_url,book_name,remain_num,publish_org,author_name,book_location,book_intro,new PDFDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm,String obj) {
                if (confirm){
                    if (obj.equals("readBook")){
                        dialog.dismiss();
                        Intent toReadBook = new Intent(context, BookReadActivity.class);
                        toReadBook.putExtra("bookId","12345"); //传bookId
                        toReadBook.putExtra("bookName",book_name); //传bookName
                        context.startActivity(toReadBook);
                    }
                    else if (obj.equals("joinBookself")){
                        new CommomDialog(context, R.style.dialog, "您确定将此书添加到书架？", new CommomDialog.OnCloseListener(){
                            @Override
                            public void onClick(Dialog dialog, boolean confirm) {
                                if(confirm){
                                    //发送添加图书的请求信息
                                    //根据返回信息进行Toast
                                    Toast.makeText(context,"添加成功",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        }).setTitle("提示").show();
                        dialog.dismiss();
                    }
                }
            }
        }).show();
    }
}
