package com.android.loushi.loushi.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.alibaba.nb.android.trade.AliTradeSDK;
import com.alibaba.nb.android.trade.callback.AliTradeProcessCallback;
import com.alibaba.nb.android.trade.constants.AliTradeConstants;
import com.alibaba.nb.android.trade.model.AliOpenType;
import com.alibaba.nb.android.trade.model.AliTradeResult;
import com.alibaba.nb.android.trade.model.AliTradeShowParams;
import com.alibaba.nb.android.trade.model.AliTradeTaokeParams;
import com.alibaba.nb.android.trade.uibridge.AliTradeBasePage;
import com.alibaba.nb.android.trade.uibridge.AliTradeDetailPage;
import com.alibaba.nb.android.trade.uibridge.IAliTradeService;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.GoodDetailAdapter;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.event.MainEvent;
import com.android.loushi.loushi.jsonbean.GoodsJson;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.jsonbean.SceneGoodJson;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.ShareSomeThing;
import com.android.loushi.loushi.util.SpaceItemDecoration;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.squareup.picasso.Picasso;


import org.greenrobot.eventbus.EventBus;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/25.
 */
public class GoodDetailActivity extends BaseActivity {
    private LinearLayout show_taobao;
    //下方bar
    private LinearLayout collect_bar;
    private LinearLayout collect;
    private LinearLayout comment;
    private LinearLayout share;
    private ImageButton btn_collect;
    private ImageButton btn_comment;

    private TextView tv_collect_count;
    private TextView tv_comment_count;
    private TextView tv_share_count;

    private RecyclerView recyclerView;
    private ImageView img_good;
    private TextView tv_good_name;
    private TextView tv_introduce;
    private TextView price_symbol;
    private TextView tv_good_price;
    private Toolbar program_toolbar;
    private ImageView back;
    private Button btn_buy;
    private String goodjsonString = "";
    private GoodsJson.BodyBean goodBean;

    private GoodDetailAdapter goodDetailAdapter;
    private List<GoodsJson.BodyBean.ImagesBean> list;
    private String good_id = "1";

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
        if (getIntent().getStringExtra("GOOD_ID") != null)
            good_id = getIntent().getStringExtra("GOOD_ID");
        goodjsonString = getIntent().getStringExtra(BaseActivity.GOOD_STRING);
        goodBean = new Gson().fromJson(goodjsonString, GoodsJson.BodyBean.class);
        //透明导航栏
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        bindView();
        init();

    }

    private void bindView() {
        img_good = (ImageView) findViewById(R.id.img_good);
        program_toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.program_toolbar);

        tv_good_name = (TextView) findViewById(R.id.tv_good_name);
        tv_introduce = (TextView) findViewById(R.id.tv_introduce);
        price_symbol = (TextView) findViewById(R.id.price_symbol);
        tv_good_price = (TextView) findViewById(R.id.tv_good_price);
        btn_buy = (Button) findViewById(R.id.btn_buy);
        back = (ImageView) program_toolbar.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bindCollectBarView();


    }

    private void bindCollectBarView() {
        collect_bar = (LinearLayout) findViewById(R.id.collect_bar);
        //collect_bar.setVisibility(View.GONE);
        collect = (LinearLayout) collect_bar.findViewById(R.id.collect_bar_linear_like);
        comment = (LinearLayout) collect_bar.findViewById(R.id.collect_bar_linear_comment);
        share = (LinearLayout) collect_bar.findViewById(R.id.collect_bar_linear_share);
        btn_collect = (ImageButton) collect.findViewById(R.id.collect_bar_btn_like);
        tv_collect_count = (TextView) collect.findViewById(R.id.collect_bar_tv_like);
        tv_comment_count = (TextView) collect_bar.findViewById(R.id.collect_bar_tv_comment);
        tv_share_count = (TextView) collect_bar.findViewById(R.id.collect_bar_tv_share);

    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        list = new ArrayList<GoodsJson.BodyBean.ImagesBean>();
        goodDetailAdapter = new GoodDetailAdapter(this, list);
        getGood();
        //SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(this,5);

        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(goodDetailAdapter);
        //recyclerView.addItemDecoration(spaceItemDecoration);
    }

    private void getGood() {
//        OkHttpUtils.post("http://www.loushi666.com/LouShi/base/goods.action")
//
//                .tag(getApplicationContext()).params("user_id", BaseActivity.user_id).params("good_id", good_id)
//                .execute(new JsonCallback<GoodsJson>(GoodsJson.class) {
//                    @Override
//                    public void onResponse(boolean b, final GoodsJson goodsJson, Request request, Response response) {
//                        Log.e("atg", new Gson().toJson(goodsJson));
//                        if (goodsJson.getState()) {

        //bodyBeanList.addAll(sceneGoodJson.getBody());
        list.addAll(goodBean.getImages());
        list.remove(0);
        Picasso.with(GoodDetailActivity.this).load(goodBean.getImages().get(1).getUrl()).into(img_good);
        tv_good_name.setText(goodBean.getName());
        tv_good_price.setText(goodBean.getPrice() + "");
        tv_introduce.setText(goodBean.getIntroduction());
        goodDetailAdapter.notifyDataSetChanged();


        tv_collect_count.setText(goodBean.getCollectionNum() + "");
        tv_share_count.setText(goodBean.getForwordNum() + "");
        tv_comment_count.setText(goodBean.getForwordNum() + "");
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodDetailActivity.this, CommentActivity.class);
                intent.putExtra(KeyConstant.TYPE, "3");
                intent.putExtra(KeyConstant.PID, goodBean.getId() + "");
                startActivity(intent);
            }
        });
        Log.e("collect1", btn_collect.isSelected() + "");
        initCollect(goodBean.getCollected());
        Log.e("good", goodBean.getUrl());
        initButton(goodBean.getUrl());
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imgurl = goodBean.getImages().get(0).getUrl();
                String title = goodBean.getName();
                String text = goodBean.getIntroduction();
                String url = String.format("%s%s", BaseActivity.url_good_share, good_id);
                //Toast.makeText(this, "click clean ", Toast.LENGTH_SHORT).show();
                ShareSomeThing shareSomeThing = new ShareSomeThing(getApplicationContext(), imgurl, url, text, title, user_id, "3", goodBean.getId() + "");
                shareSomeThing.DoShare();
            }
        });

    }


    private void initButton(final String url) {
        btn_buy = (Button) findViewById(R.id.btn_buy);
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItemDetailPage(show_taobao, url);
            }
        });
    }


    public void showItemDetailPage(View view, String id) {
        AliTradeShowParams aliTradeShowParams = new AliTradeShowParams(AliOpenType.H5, false);
        AliTradeBasePage aliTradeBasePage = new AliTradeDetailPage(id);
        //AliTradeDetailPage tradePage=new AliTradeDetailPage(url);
        IAliTradeService aliTradeService = AliTradeSDK.getService(IAliTradeService.class);
        Map<String, String> exParams = new HashMap<>();
        exParams.put(AliTradeConstants.ISV_CODE, "appisvcode");
        exParams.put("alibaba", "阿里巴巴");

        if (null == aliTradeService) {
            Toast.makeText(GoodDetailActivity.this, "无法获取tradeService",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        AliTradeTaokeParams aliTradeTaokeParams = new AliTradeTaokeParams("mm_114880276_0_0", "", "");
        aliTradeService.show(GoodDetailActivity.this, aliTradeBasePage, aliTradeShowParams, aliTradeTaokeParams, exParams, new AliTradeProcessCallback() {

            @Override
            public void onTradeSuccess(AliTradeResult tradeResult) {
                Toast.makeText(GoodDetailActivity.this, "显示商品详情页成功",
                        Toast.LENGTH_SHORT).show();
                //打开电商组件，用户操作中成功信息回调。tradeResult：成功信息（结果类型：加购，支付；支付结果）
            }

            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(GoodDetailActivity.this, "显示商品详情页失败 " + code + msg,
                        Toast.LENGTH_SHORT).show();
                //打开电商组件，用户操作中错误信息回调。code：错误码；msg：错误信息
            }
        });

    }

    private void initCollect(final boolean Collected) {
        if (Collected)
            btn_collect.setSelected(true);
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userCollect")
                        .params("user_id", user_id).params("type", "3").params("pid", good_id)
                        .tag(this).execute(new JsonCallback<ResponseJson>(ResponseJson.class) {

                    @Override
                    public void onResponse(boolean b, ResponseJson responseJson, Request request, Response response) {
                        if (responseJson.getState()) {
                            if (btn_collect.isSelected()) {
                                int num = Integer.parseInt(tv_collect_count.getText().toString()) - 1;

                                tv_collect_count.setText(num + "");
                                btn_collect.setSelected(false);
                            } else {
                                int num = Integer.parseInt(tv_collect_count.getText().toString()) + 1;
                                tv_collect_count.setText(num + "");
                                btn_collect.setSelected(true);
                            }
                            EventBus.getDefault().post(new MainEvent(MainEvent.UPDATE_COLLECT));
                        }
                    }
                });
            }
        });
    }


}
