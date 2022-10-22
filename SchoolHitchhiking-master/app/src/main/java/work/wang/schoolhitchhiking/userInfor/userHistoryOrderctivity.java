package work.wang.schoolhitchhiking.userInfor;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import work.wang.schoolhitchhiking.R;
import work.wang.schoolhitchhiking.adapter.history_df_adapter;
import work.wang.schoolhitchhiking.adapter.history_dg_adapter;
import work.wang.schoolhitchhiking.adapter.history_dn_adapter;
import work.wang.schoolhitchhiking.order_object.df;
import work.wang.schoolhitchhiking.order_object.dg;
import work.wang.schoolhitchhiking.order_object.kd;
import work.wang.schoolhitchhiking.show.userActivity;

public class userHistoryOrderctivity extends AppCompatActivity implements View.OnClickListener {
    private int userID;
    private ImageView back;
    private TextView type;
    private String sort[]={"代购","代拿","代餐"};
    private Handler mHandler;
    private ListView listView;
    private List<dg> dgList;
    private List<kd> kdList;
    private List<df> dfList;
    private int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_history_orderctivity);
        SharedPreferences sp=getSharedPreferences("data",MODE_PRIVATE);
        userID=sp.getInt("userID",1);
        init();
        initEvent();
        attainSendInfor();
        updateUI();
    }
    private void updateUI(){
        mHandler= new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        listView.setAdapter(null);
                        history_dg_adapter dg=new history_dg_adapter(userHistoryOrderctivity.this,dgList);
                        listView.setAdapter(dg);
                        break;
                    case 2:
                        Toast.makeText(userHistoryOrderctivity.this,"没有数据刷新试试",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        listView.setAdapter(null);
                        history_dn_adapter dn=new history_dn_adapter(userHistoryOrderctivity.this,kdList);
                        listView.setAdapter(dn);
                        break;
                    case 4:
                        listView.setAdapter(null);
                        history_df_adapter df=new history_df_adapter(userHistoryOrderctivity.this,dfList);
                        listView.setAdapter(df);
                        break;
                    default:
                        break;
                }
            }
        };
    }
    private void attainSendInfor(){
        if(i==1){
            new Thread(new Runnable() {
                public void run() {
                    OkHttpClient client=new OkHttpClient();
                    RequestBody formBody=new FormBody.Builder()
                            .add("userID",userID+"")
                            .add("type",i+"")
                            .build();
                    Request request=new Request.Builder().url("http://172.17.149.206:8080/hitchhiking/orderHistory")
                            .post(formBody)
                            .build();
                    Response response;
                    try {
                        Gson gson=new Gson();
                        response=client.newCall(request).execute();
                        String jsons=response.body().string();
                        //Json的解析类对象
                        JsonParser parser = new JsonParser();
                        //将JSON的String 转成一个JsonArray对象
                        JsonArray jsonArray = parser.parse(jsons).getAsJsonArray();
                        dgList = new ArrayList<>();
                        for (JsonElement n : jsonArray) {
                            //使用GSON，直接转成Bean对象
                            dg k=gson.fromJson(n,dg.class);
                            dgList.add(k);
                        }
                        if (dgList!=null){
                            Message message = mHandler.obtainMessage();
                            message.what = 1;
                            mHandler.sendMessage(message);
                        }else {
                            Message message = mHandler.obtainMessage();
                            message.what = 2;
                            mHandler.sendMessage(message);
                        }
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        if(i==2){
            new Thread(new Runnable() {
                public void run() {
                    OkHttpClient client=new OkHttpClient();
                    RequestBody formBody=new FormBody.Builder()
                            .add("userID",userID+"")
                            .add("type",i+"")
                            .build();
                    Request request=new Request.Builder().url("http://172.17.149.206:8080/hitchhiking/orderHistory")
                            .post(formBody)
                            .build();
                    Response response;
                    try {
                        Gson gson=new Gson();
                        response=client.newCall(request).execute();
                        String jsons=response.body().string();
                        //Json的解析类对象
                        JsonParser parser = new JsonParser();
                        //将JSON的String 转成一个JsonArray对象
                        JsonArray jsonArray = parser.parse(jsons).getAsJsonArray();
                        kdList = new ArrayList<>();
                        for (JsonElement n : jsonArray) {
                            //使用GSON，直接转成Bean对象
                            kd kd=gson.fromJson(n,kd.class);
                            kdList.add(kd);
                        }
                        if (kdList!=null){
                            Message message = mHandler.obtainMessage();
                            message.what = 3;
                            mHandler.sendMessage(message);
                        }else {
                            Message message = mHandler.obtainMessage();
                            message.what = 2;
                            mHandler.sendMessage(message);
                        }
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        if(i==3){
            new Thread(new Runnable() {
                public void run() {
                    OkHttpClient client=new OkHttpClient();
                    RequestBody formBody=new FormBody.Builder()
                            .add("userID",userID+"")
                            .add("type",i+"")
                            .build();
                    Request request=new Request.Builder().url("http://172.17.149.206:8080/hitchhiking/orderHistory")
                            .post(formBody)
                            .build();
                    Response response;
                    try {
                        Gson gson=new Gson();
                        response=client.newCall(request).execute();
                        String jsons=response.body().string();
                        //Json的解析类对象
                        JsonParser parser = new JsonParser();
                        //将JSON的String 转成一个JsonArray对象
                        JsonArray jsonArray = parser.parse(jsons).getAsJsonArray();
                        dfList = new ArrayList<>();
                        for (JsonElement n : jsonArray) {
                            //使用GSON，直接转成Bean对象
                            df k=gson.fromJson(n,df.class);
                            dfList.add(k);
                        }
                        if (dfList!=null){
                            Message message = mHandler.obtainMessage();
                            message.what = 4;
                            mHandler.sendMessage(message);
                        }else {
                            Message message = mHandler.obtainMessage();
                            message.what = 2;
                            mHandler.sendMessage(message);
                        }
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    private void init(){
        type=(TextView)this.findViewById(R.id.history_type);
        back=(ImageView) this.findViewById(R.id.history_back);
        listView=(ListView)this.findViewById(R.id.history_lv);
        listView.setEmptyView(this.findViewById(R.id.layout_noData));
    }
    private void initEvent(){
        type.setOnClickListener(this);
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.history_type:
                selectType();
                break;
            case R.id.history_back:
                Intent intent=new Intent(this,selectHistoryOrderActivity.class);
                startActivity(intent);
                finish();
        }
    }
    private void selectType(){
        AlertDialog.Builder incomeAlertDialog=new AlertDialog.Builder(this);
        incomeAlertDialog.setSingleChoiceItems(sort, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ListView lw = ((AlertDialog) dialog).getListView();
                // which表示点击的条目
                Object checkedItem = lw.getAdapter().getItem(which);
                // 既然你没有cancel或者ok按钮，所以需要在点击item后使dialog消失
                dialog.dismiss();
                // 更新你的view
                if(((String)checkedItem).equals("代购")){
                    type.setText("类型:代购");
                    i=1;
                    attainSendInfor();
                    updateUI();
                }
                if(((String)checkedItem).equals("代拿")){
                    type.setText("类型:代拿");
                    i=2;
                    attainSendInfor();
                    updateUI();

                }
                if(((String)checkedItem).equals("代餐")){
                    type.setText("类型:代餐");
                    i=3;
                    attainSendInfor();
                    updateUI();

                }
            }
        });
        AlertDialog dialog = incomeAlertDialog.create();
        dialog.show();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            Intent intent=new Intent(this,selectHistoryOrderActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}
