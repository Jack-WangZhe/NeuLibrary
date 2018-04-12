package fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import nl.neulibrary.R;
import nl.neulibrary.borrowBookActivity;
import nl.neulibrary.rebackBookActivity;
import nl.neulibrary.renewBookActivity;
import nl.neulibrary.serviceRank;
import nl.neulibrary.serviceSearchBook;

public class surviceFragment extends Fragment implements View.OnClickListener{
    //整体fragment
    private View view;

    //布局控件
    private LinearLayout borrowbooks; //借书
    private LinearLayout returnbooks; //还书
    private LinearLayout renew; //续借
    private LinearLayout leaderboards; //排行榜
    private LinearLayout search; //查找书籍

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_survice, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initView();
        initClickBtns();
    }

    //初始化控件
    public void initView(){
        borrowbooks = (LinearLayout)view.findViewById(R.id.borrowbooks);
        returnbooks = (LinearLayout)view.findViewById(R.id.returnbooks);
        renew = (LinearLayout)view.findViewById(R.id.renew);
        leaderboards = (LinearLayout)view.findViewById(R.id.leaderboards);
        search = (LinearLayout)view.findViewById(R.id.search);
    }

    //初始化点击控件
    public void initClickBtns(){
        borrowbooks.setOnClickListener(this);
        returnbooks.setOnClickListener(this);
        renew.setOnClickListener(this);
        leaderboards.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.borrowbooks:
                //Toast.makeText(this.getActivity(),"借书",Toast.LENGTH_SHORT).show();
                Intent toBorrowBook = new Intent(this.getActivity(),borrowBookActivity.class);
                startActivity(toBorrowBook);
                break;
            case R.id.returnbooks:
                //Toast.makeText(this.getActivity(),"还书",Toast.LENGTH_SHORT).show();
                Intent toRebackBook = new Intent(this.getActivity(),rebackBookActivity.class);
                startActivity(toRebackBook);
                break;
            case R.id.renew:
                //Toast.makeText(this.getActivity(),"续借",Toast.LENGTH_SHORT).show();
                Intent toRenewBook = new Intent(this.getActivity(),renewBookActivity.class);
                startActivity(toRenewBook);
                break;
            case R.id.leaderboards:
                Intent toRank = new Intent(this.getActivity(),serviceRank.class);
                startActivity(toRank);
                break;
            case R.id.search:
                Intent toSearchBook = new Intent(this.getActivity(),serviceSearchBook.class);
                toSearchBook.putExtra("SearchInfo","");
                startActivity(toSearchBook);
                break;
        }
    }
}
