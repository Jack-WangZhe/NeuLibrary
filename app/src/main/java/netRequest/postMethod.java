package netRequest;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class postMethod {
    private Context context;
    private OnPostFinishListener onFinishListener;
    public void setOnFinishListener(OnPostFinishListener onFinishListener)
    {
        this.onFinishListener=onFinishListener;
    }
    public void postLogin(final Context context, String url,final String userId,final String userPwd){
        //1.创建一个请求队列
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        //2.创建一个post请求
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //正确接收数据回调
            @Override
            public void onResponse(String s) {
                onFinishListener.OnPostFinished(s);
            }
            }, new Response.ErrorListener() {
                //发生异常的监听回调
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(context,"加载失败，错误信息:"+volleyError,Toast.LENGTH_SHORT).show();
                }
            }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<String, String>();
                //若要传值则在map里面添加
                map.put("userId",userId);
                map.put("userPwd",userPwd);
                return map;
            }
        };
        //3.将post请求添加到队列中
        requestQueue.add(stringRequest);
    }
    public void postChangePwd(final Context context, String url,final String userId,final String userPwd,final String newPwd){
        //1.创建一个请求队列
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        //2.创建一个post请求
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //正确接收数据回调
            @Override
            public void onResponse(String s) {
                onFinishListener.OnPostFinished(s);
            }
        }, new Response.ErrorListener() {
            //发生异常的监听回调
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context,"加载失败，错误信息:"+volleyError,Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<String, String>();
                //若要传值则在map里面添加
                map.put("userId",userId);
                map.put("userPwd",userPwd);
                map.put("newPwd",newPwd);
                return map;
            }
        };
        //3.将post请求添加到队列中
        requestQueue.add(stringRequest);
    }
}
