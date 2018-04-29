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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.recommendBookListAdapter;
import beans.recommendBookListBean;
import netRequest.OnGetFinishListener;
import netRequest.Urls;
import netRequest.getMethod;
import nl.neulibrary.BookReadActivity;
import nl.neulibrary.HomeActivity;
import nl.neulibrary.R;
import tools.CommomDialog;
import tools.NoPDFDialog;
import tools.NoPDFDialog_String;
import tools.PDFDialog;
import tools.PDFDialog_String;

public class rf_recommend extends Fragment implements AdapterView.OnItemClickListener {
    //整体fragment
    private View view;
    List<recommendBookListBean> mDatas;

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
        initInfos();
    }

    public void initViews(){
        rf_recommendLists = (ListView)view.findViewById(R.id.rf_recommendLists);
        rf_recommendLists.setOnItemClickListener(this);
    }

    public void initInfos(){
        getMethod getRecommend=new getMethod();
        getRecommend.setOnFinishListener(new OnGetFinishListener() {
            @Override
            public void OnGetFinished(String backInfo) {
                try {
                    JSONObject back=new JSONObject(backInfo);
                    Boolean status=back.getBoolean("status");
                    if (status){
                         mDatas= new ArrayList<recommendBookListBean>();
                        JSONArray info = back.getJSONArray("info");
                        for(int i=0;i<info.length();i++){
                            JSONObject real_info=info.getJSONObject(i);
                            recommendBookListBean bean=initDatas(real_info);
                            //将数据装到集合中去
                            mDatas.add(bean);
                        }
                        //为数据绑定适配器
                        itemListViewAdapterWithViewHolder = new recommendBookListAdapter(getActivity(), mDatas);
                        rf_recommendLists.setAdapter(itemListViewAdapterWithViewHolder);
                    }else{
                        String info=back.getString("info");
                        Toast.makeText(getActivity(),info,Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        getRecommend.getInfo(this.getActivity(), Urls.getRecommend);
    }

    public recommendBookListBean initDatas(JSONObject data){
        try {
            return new recommendBookListBean(data.getString("bookId"),data.getString("bookName"),data.getString("bookAuthor"),data.getString("bookInfo"),data.getBoolean("bookstatus"),data.getString("book_url"),data.getBoolean("isPDF"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView bookId = (TextView) view.findViewById(R.id.bookId);
        final TextView bookName = (TextView) view.findViewById(R.id.bookName);
//        TextView bookAuthor = (TextView)view.findViewById(R.id.bookAuthor);
//        TextView bookInfo = (TextView)view.findViewById(R.id.bookInfo);
        LinearLayout isPDF = (LinearLayout)view.findViewById(R.id.isPDF);
        final ImageView bookImage = (ImageView)view.findViewById(R.id.bookImage);
        final String book_id=bookId.getText().toString();
        if (isPDF.getVisibility() == View.VISIBLE){
            getMethod getPDFBookInfo = new getMethod();
            getPDFBookInfo.setOnFinishListener(new OnGetFinishListener() {
                @Override
                public void OnGetFinished(String backInfo) {
                    try {
                        JSONObject back=new JSONObject(backInfo);
                        if (back.getBoolean("status")){
                            JSONObject info = back.getJSONObject("info");
                            showPDFDialog(getActivity(),book_id,bookImage.getTag().toString(), bookName.getText().toString(), info.getInt("remainNum")+"", info.getString("publishOrg"), info.getString("authorName"), info.getString("bookLocation"), info.getString("bookIntro"));
                        }else{
                            String info = back.getString("info");

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            getPDFBookInfo.getInfo(getActivity(),Urls.getPDFBookInfo+"?bookId="+book_id);
        }
        else{
            getMethod getPDFBookInfo = new getMethod();
            getPDFBookInfo.setOnFinishListener(new OnGetFinishListener() {
                @Override
                public void OnGetFinished(String backInfo) {
                    try {
                        JSONObject back=new JSONObject(backInfo);
                        if (back.getBoolean("status")){
                            JSONObject info = back.getJSONObject("info");
                            showNoPDFDialog(getActivity(),book_id,bookImage.getTag().toString(), bookName.getText().toString(), info.getInt("remainNum")+"", info.getString("publishOrg"), info.getString("authorName"), info.getString("bookLocation"), info.getJSONArray("recentBorrowUser"));
                        }else{
                            String info = back.getString("info");

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            getPDFBookInfo.getInfo(getActivity(),Urls.getNoPDFBookInfo+"?bookId="+book_id);
        }
    }

//    public void showNoPDFDialog(final Context context,int book_pic_url,String book_name,String remain_num,String publish_org,String author_name,String book_location,String recent_borrow_user){
//        new NoPDFDialog(context, R.style.dialog,book_pic_url,book_name,remain_num,publish_org,author_name,book_location,recent_borrow_user,new NoPDFDialog.OnCloseListener() {
//            @Override
//            public void onClick(Dialog dialog, boolean confirm) {
//                if (confirm){
//                    new CommomDialog(context, R.style.dialog, "您确定将此书添加到书架？", new CommomDialog.OnCloseListener(){
//                        @Override
//                        public void onClick(Dialog dialog, boolean confirm) {
//                            if(confirm){
//                                //发送添加图书的请求信息
//                                //根据返回信息进行Toast
//                                Toast.makeText(context,"添加成功",Toast.LENGTH_SHORT).show();
//                                dialog.dismiss();
//                            }
//                        }
//                    }).setTitle("提示").show();
//                    dialog.dismiss();
//                }
//            }
//        }).show();
//    }
//
//    public void showPDFDialog(final Context context, int book_pic_url, final String book_name, String remain_num, String publish_org, String author_name, String book_location, String book_intro){
//        new PDFDialog(context, R.style.dialog,book_pic_url,book_name,remain_num,publish_org,author_name,book_location,book_intro,new PDFDialog.OnCloseListener() {
//            @Override
//            public void onClick(Dialog dialog, boolean confirm,String obj) {
//                if (confirm){
//                    if (obj.equals("readBook")){
//                        dialog.dismiss();
//                        Intent toReadBook = new Intent(context, BookReadActivity.class);
//                        toReadBook.putExtra("bookId","12345"); //传bookId
//                        toReadBook.putExtra("bookName",book_name); //传bookName
//                        context.startActivity(toReadBook);
//                    }
//                    else if (obj.equals("joinBookself")){
//                        new CommomDialog(context, R.style.dialog, "您确定将此书添加到书架？", new CommomDialog.OnCloseListener(){
//                            @Override
//                            public void onClick(Dialog dialog, boolean confirm) {
//                                if(confirm){
//                                    //发送添加图书的请求信息
//                                    //根据返回信息进行Toast
//                                    Toast.makeText(context,"添加成功",Toast.LENGTH_SHORT).show();
//                                    dialog.dismiss();
//                                }
//                            }
//                        }).setTitle("提示").show();
//                        dialog.dismiss();
//                    }
//                }
//            }
//        }).show();
//    }
public void showNoPDFDialog(final Context context, final String bookId, String book_pic_url, String book_name, String remain_num, String publish_org, String author_name, String book_location, JSONArray recentBorrowUser){
    new NoPDFDialog_String(context, R.style.dialog,book_pic_url,book_name,remain_num,publish_org,author_name,book_location,recentBorrowUser,new NoPDFDialog_String.OnCloseListener(){
        @Override
        public void onClick(Dialog dialog, boolean confirm) {
            if (confirm){
                new CommomDialog(context, R.style.dialog, "您确定将此书添加到书架？", new CommomDialog.OnCloseListener(){
                    @Override
                    public void onClick(final Dialog dialog, boolean confirm) {
                        if(confirm){
                            //发送添加图书的请求信息
                            //根据返回信息进行Toast
                            getMethod addBookself=new getMethod();
                            addBookself.setOnFinishListener(new OnGetFinishListener() {
                                @Override
                                public void OnGetFinished(String backInfo) {
                                    try {
                                        JSONObject back=new JSONObject(backInfo);
                                        String info = back.getString("info");
                                        Toast.makeText(context,info,Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            addBookself.getInfo(context, Urls.addBookself+"?bookId="+bookId);
                        }
                    }
                }).setTitle("提示").show();
                dialog.dismiss();
            }
        }
    }).show();
}

    public void showPDFDialog(final Context context, final String bookId, String book_pic_url, final String book_name, String remain_num, String publish_org, String author_name, String book_location, String book_intro){
        new PDFDialog_String(context, R.style.dialog,book_pic_url,book_name,remain_num,publish_org,author_name,book_location,book_intro,new PDFDialog_String.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm,String obj) {
                if (confirm){
                    if (obj.equals("readBook")){
                        dialog.dismiss();
                        Intent toReadBook = new Intent(context, BookReadActivity.class);
                        toReadBook.putExtra("bookId",bookId); //传bookId
                        toReadBook.putExtra("bookName",book_name); //传bookName
                        context.startActivity(toReadBook);
                    }
                    else if (obj.equals("joinBookself")){
                        new CommomDialog(context, R.style.dialog, "您确定将此书添加到书架？", new CommomDialog.OnCloseListener(){
                            @Override
                            public void onClick(final Dialog dialog, boolean confirm) {
                                if(confirm){
                                    //发送添加图书的请求信息
                                    //根据返回信息进行Toast
                                    getMethod addBookself=new getMethod();
                                    addBookself.setOnFinishListener(new OnGetFinishListener() {
                                        @Override
                                        public void OnGetFinished(String backInfo) {
                                            try {
                                                JSONObject back=new JSONObject(backInfo);
                                                String info = back.getString("info");
                                                Toast.makeText(context,info,Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                    addBookself.getInfo(context, Urls.addBookself+"?bookId="+bookId);
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
