package netRequest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class getMethod {
    private Context context;
    private OnGetFinishListener onFinishListener;
    public void setOnFinishListener(OnGetFinishListener onFinishListener)
    {
        this.onFinishListener=onFinishListener;
    }
    public void getInfo(final Context context, String url)
    {
        //创建一个请求队列
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        //创建一个请求
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            //正确接收数据回调
            @Override
            public void onResponse(String s) {
                onFinishListener.OnGetFinished(s);
            }
        }, new Response.ErrorListener() {
            //发生异常的监听回调
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context,"加载失败，错误信息:"+volleyError,Toast.LENGTH_SHORT).show();
            }
        });
        //将创建的请求添加到请求队列中
        requestQueue.add(stringRequest);
    }
}