package com.android.loushi.loushi.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.trade.TradeService;
import com.alibaba.sdk.android.trade.callback.TradeProcessCallback;
import com.alibaba.sdk.android.trade.model.TradeResult;
import com.alibaba.sdk.android.trade.page.ItemDetailPage;
import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.GoodDetailAdapter;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.GoodsJson;
import com.android.loushi.loushi.jsonbean.SceneGoodJson;
import com.android.loushi.loushi.util.SpaceItemDecoration;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.squareup.picasso.Picasso;
import com.taobao.tae.sdk.model.TaokeParams;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/25.
 */
public class GoodDetailActivity extends  BaseActivity {
    private LinearLayout show_taobao;
    private RecyclerView recyclerView;
    private ImageView img_good;
    private TextView tv_good_name;
    private TextView tv_introduce;
    private TextView price_symbol;
    private TextView tv_good_price;
    private Toolbar program_toolbar;
    private ImageButton back;
    private Button btn_buy;

    private GoodDetailAdapter goodDetailAdapter;
    private List<GoodsJson.BodyBean.ImagesBean> list;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_good_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_detail);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //透明导航栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        bindView();
        init();
        initButton();
    }
    private void bindView(){
        img_good = (ImageView) findViewById(R.id.img_good);
        program_toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.program_toolbar);
        back = (ImageButton) program_toolbar.findViewById(R.id.back);
        tv_good_name = (TextView) findViewById(R.id.tv_good_name);
        tv_introduce = (TextView) findViewById(R.id.tv_introduce);
        price_symbol = (TextView) findViewById(R.id.price_symbol);
        tv_good_price = (TextView) findViewById(R.id.tv_good_price);
        btn_buy = (Button) findViewById(R.id.btn_buy);
    }
    private void init() {
        recyclerView = (RecyclerView)findViewById(R.id.recycleView);
        list = new ArrayList<GoodsJson.BodyBean.ImagesBean>();
        goodDetailAdapter = new GoodDetailAdapter(getApplicationContext(),list);
        getGood();
        //SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(this,5);

        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(goodDetailAdapter);
        //recyclerView.addItemDecoration(spaceItemDecoration);
    }
    private void getGood(){
        OkHttpUtils.post("http://www.loushi666.com/LouShi/base/goods.action")

                .tag(getApplicationContext()).params("user_id", "0").params("good_id", "1")
                .execute(new JsonCallback<GoodsJson>(GoodsJson.class) {
                    @Override
                    public void onResponse(boolean b, GoodsJson goodsJson, Request request, Response response) {
                        Log.e("atg", new Gson().toJson(goodsJson));
                        if (goodsJson.getState()) {
                            //bodyBeanList.addAll(sceneGoodJson.getBody());
                            list.addAll(goodsJson.getBody().getImages());
                            Picasso.with(GoodDetailActivity.this).load(goodsJson.getBody().getImages().get(0).getUrl()).into(img_good);
                            tv_good_name.setText(goodsJson.getBody().getName());
                            tv_good_price.setText(goodsJson.getBody().getPrice() + "");
                            tv_introduce.setText(goodsJson.getBody().getIntroduction());
                            goodDetailAdapter.notifyDataSetChanged();
                        }
                        Log.e("atg", list.size() + "");
                        //sceneDetailGoodAdapter.notifyDataSetChanged();

                    }


                });
    }
    private void initButton(){
        btn_buy=(Button)findViewById(R.id.btn_buy);
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemDetailPage(show_taobao,"520835764780");
            }
        });
    }


    public void showItemDetailPage(View view,String id){
        Log.e("taobaourl",id);
        TradeService tradeService = AlibabaSDK.getService(TradeService.class);

        ItemDetailPage itemDetailPage = new ItemDetailPage(id, null);
        TaokeParams taokeParams = new TaokeParams(); //若非淘客taokeParams设置为null即可
        taokeParams.pid = "mm_114880276_0_0";


        tradeService.show(itemDetailPage, taokeParams, GoodDetailActivity.this, null, new TradeProcessCallback() {

            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(GoodDetailActivity.this, "失败 " + code + msg,
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPaySuccess(TradeResult tradeResult) {
                Toast.makeText(GoodDetailActivity.this, "成功", Toast.LENGTH_SHORT)
                        .show();

            }
        });
    }
}
