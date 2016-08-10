package com.android.loushi.loushi.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.PersonCollectTabAdapter;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.event.MainEvent;

import com.android.loushi.loushi.jsonbean.ResponseJson;

import com.android.loushi.loushi.jsonbean.UserCollectsNum;
import com.android.loushi.loushi.ui.activity.BaseActivity;
import com.android.loushi.loushi.ui.activity.FeedActivity;
import com.android.loushi.loushi.ui.activity.GoodDetailActivity;

import com.android.loushi.loushi.ui.activity.MyMessageActivity;
import com.android.loushi.loushi.ui.activity.PersonalInformationActivity;
import com.android.loushi.loushi.ui.activity.SettingActivity;
import com.android.loushi.loushi.util.CircularImageView;
import com.android.loushi.loushi.util.CurrentAccount;
import com.android.loushi.loushi.util.MyfragmentEvent;
import com.android.loushi.loushi.util.RoundImageView;
import com.android.loushi.loushi.util.SlidingTabLayout;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/18.
 */

//个人中心
public class PersonFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG ="PersonFragment";

    private Toolbar mToolbar;
    private TextView mTv_index;
    //private TabLayout mtoorbar_tab;
    private SlidingTabLayout mtoorbar_tab;
    private AppBarLayout appBarLayout;
    private TextView tv_name;
    private CircularImageView img_head;
    private TextView tv_feed;
    private TextView tv_name_small;
    private RoundImageView img_head_small;
    private View rootView;
    //定义viewPager
    private PersonCollectTabAdapter personCollectTabAdapter;                               //定义adapter
    private List<Fragment> list_fragment = new ArrayList<>();                                //定义要装fragment的列表


    private List<String> list_count = new ArrayList<>();
    //private CategoryFragment categoryFragment;
    private CollectGoodFragment collectGoodFragment;
    private ViewPager mViewPager;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Button btn_profile;
    private ImageView btn_my_message;
    private ImageView btn_my_setting;
    private CollapsingToolbarLayoutState state;
    private ImageView iv_message_tips;

    public MyFragment myFragment;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_feed:
                Intent intent = new Intent(getActivity(), FeedActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.my_message:
                intent = new Intent(getContext(), MyMessageActivity.class);
                startActivity(intent);
                iv_message_tips.setVisibility(View.GONE);
                CurrentAccount.setMessageCount(0);
                break;
            case R.id.btn_profile:
                //TODO
                intent = new Intent(getActivity(), PersonalInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.my_settings:
                intent = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(intent);
                break;

        }

    }

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Log.e(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
        initView();
        iniDatas();
       if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        //mToolbar.setTitle("loushi");
    }

    private void iniDatas() {
        tv_name.setText(CurrentAccount.getNickname());
        Picasso.with(getActivity()).load(CurrentAccount.getHeadImgUrl()).into(img_head);
        tv_name_small.setText(CurrentAccount.getNickname());
        Picasso.with(getActivity()).load(CurrentAccount.getHeadImgUrl()).into(img_head_small);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_personal, null);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        Log.i("test", "person fragment onCreateView");

        return rootView;
    }

    public void initView() {


        initToolBar();
        initTablayout();
        initButton();
        initAppBar();
    }

    private void initTablayout() {
        mViewPager = (ViewPager) getView().findViewById(R.id.main_vp_container);
        Log.e("personinit", list_count.size() + "");
        if (list_count.size() == 0) {
            OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userCollectionsNum.action")
                    .params("user_id", BaseActivity.user_id).tag(this).cacheKey("usercollectnums").cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE).
                    execute(new JsonCallback<UserCollectsNum>(UserCollectsNum.class) {
                        @Override
                        public void onResponse(boolean b, UserCollectsNum userCollectsNum, Request request, Response response) {
                            if (userCollectsNum.isState()) {
                                list_count.add(userCollectsNum.getBody().getSceneNum() + "");
                                list_count.add(userCollectsNum.getBody().getTopicNum() + userCollectsNum.getBody().getStrategyNum() + "");
                                list_count.add(userCollectsNum.getBody().getGoodsNum() + "");


                                collectGoodFragment = new CollectGoodFragment();
                                Bundle bundle;
                                bundle = new Bundle();
                                bundle.putString(CollectGoodFragment.TYPE, "0");
                                collectGoodFragment.setArguments(bundle);
                                list_fragment.add(collectGoodFragment);
                                collectGoodFragment = new CollectGoodFragment();
                                bundle = new Bundle();
                                bundle.putString(CollectGoodFragment.TYPE, "1");
                                collectGoodFragment.setArguments(bundle);
                                list_fragment.add(collectGoodFragment);
                                collectGoodFragment = new CollectGoodFragment();
                                bundle = new Bundle();
                                collectGoodFragment.setArguments(bundle);
                                bundle.putString(CollectGoodFragment.TYPE, "3");
                                list_fragment.add(collectGoodFragment);
                                personCollectTabAdapter = new PersonCollectTabAdapter(getChildFragmentManager(), list_fragment, list_count, getContext());
                                mViewPager.setAdapter(personCollectTabAdapter);

                                mtoorbar_tab.setTabStripWidth(120);


                                //mtoorbar_tab.setSelectedIndicatorColors(R.color.colorPrimary);
                                mtoorbar_tab.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                                    @Override
                                    public int getIndicatorColor(int position) {
                                        return Color.rgb(105, 184, 187);
                                    }
                                });

                                mtoorbar_tab.setDistributeEvenly(true);
                                mtoorbar_tab.setCustomTabView(R.layout.tab_item_view_collect, R.id.tv_tab_view_count);


                                //mtoorbar_tab.setCustomTabView(R.layout.tab_item_view_collect, 0);
                                mtoorbar_tab.setViewPager(mViewPager);
                            }
                        }
                    });
        } else {
            mViewPager = (ViewPager) getView().findViewById(R.id.main_vp_container);


//                list_count.add("32");
//
//                list_count.add("44");
//                list_count.add("11");

            personCollectTabAdapter = new PersonCollectTabAdapter(getChildFragmentManager(), list_fragment, list_count, getContext());
            mViewPager.setAdapter(personCollectTabAdapter);


            mtoorbar_tab.setTabStripWidth(120);


            //mtoorbar_tab.setSelectedIndicatorColors(R.color.colorPrimary);
            mtoorbar_tab.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                @Override
                public int getIndicatorColor(int position) {
                    return Color.rgb(105, 184, 187);
                }
            });

            mtoorbar_tab.setDistributeEvenly(true);
            mtoorbar_tab.setCustomTabView(R.layout.tab_item_view_collect, R.id.tv_tab_view_count);


            //mtoorbar_tab.setCustomTabView(R.layout.tab_item_view_collect, 0);
            mtoorbar_tab.setViewPager(mViewPager);
        }

    }

    public void initToolBar() {
        mToolbar = (Toolbar) getView().findViewById(R.id.program_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setTitle("");
        mtoorbar_tab = (SlidingTabLayout) getView().findViewById(R.id.toolbar_tab);
        tv_name = (TextView) getView().findViewById(R.id.tv_nickname);
        img_head = (CircularImageView) getView().findViewById(R.id.img_head);
        img_head_small = (RoundImageView) mToolbar.findViewById(R.id.img_head_small);
        tv_feed = (TextView) mToolbar.findViewById(R.id.tv_feed);
        tv_name_small = (TextView) mToolbar.findViewById(R.id.tv_nickname_small);

    }

    private void initButton() {
        btn_profile = (Button) getView().findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener(this);
        tv_feed.setOnClickListener(this);
        btn_my_message = (ImageView) mToolbar.findViewById(R.id.my_message);
        btn_my_message.setOnClickListener(this);
        btn_my_setting = (ImageView) mToolbar.findViewById(R.id.my_settings);
        btn_my_setting.setOnClickListener(this);

        iv_message_tips = (ImageView) mToolbar.findViewById(R.id.iv_messagetips);
        updataMsgTips();
    }

    /**
     * 刷新小红点显示
     */
    private void updataMsgTips() {
        if (MyMessageActivity.hasNewMessage())
            iv_message_tips.setVisibility(View.VISIBLE);
        else
            iv_message_tips.setVisibility(View.GONE);
    }

    private void initAppBar() {
        appBarLayout = (AppBarLayout) getView().findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        Log.e("coll", "展开");
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开

                        //collapsingToolbarLayout.setTitle("EXPANDED");//设置title为EXPANDED
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        Log.e("coll", "折叠");
                        tv_feed.setVisibility(View.GONE);
                        img_head_small.setVisibility(View.VISIBLE);
                        tv_name_small.setVisibility(View.VISIBLE);
                        //collapsingToolbarLayout.setTitle("");//设置title不显示
                        //playButton.setVisibility(View.VISIBLE);//隐藏播放按钮
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        Log.e("coll", "中间");
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            img_head_small.setVisibility(View.GONE);
                            tv_name_small.setVisibility(View.GONE);
                            tv_feed.setVisibility(View.VISIBLE);
                            //playButton.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮
                        }
                        //collapsingToolbarLayout.setTitle("INTERNEDIATE");//设置title为INTERNEDIATE
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MainEvent event) {
        Log.e("person", "接收消息");

        switch (event.getMsg()) {
            case MainEvent.UPDATE_COLLECT:
                Log.e("person", "接收消息" + MainEvent.UPDATE_COLLECT + "");
                updateCollect();
                break;
            case MainEvent.UPDATE_USERINFO:
                updataMsgTips();
                break;
            case MainEvent.LOGIN_UPDATEINFO:
                Log.e("person","接收消息"+MainEvent.LOGIN_UPDATEINFO+"");
                break;

        }
    }

    @Subscribe
    public void onEventMainThread(MyfragmentEvent event) {

        if (event.getmMsg().equals("Transfer PersonalFragment to MyFragment!"))
            transferToMyFragment();
    }
    private void updateCollect(){
        Log.e("person",list_count.size()+"");
        if (list_count.size() != 0) {
            Log.e("collectnumold",list_count.get(0));
            Log.e("collectnumold",list_count.get(1));
            Log.e("collectnumold",list_count.get(2));
            //personCollectTabAdapter.notifyDataSetChanged();
            OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userCollectionsNum.action")
                    .params("user_id", BaseActivity.user_id).tag(this).execute(new JsonCallback<UserCollectsNum>(UserCollectsNum.class) {
                @Override
                public void onResponse(boolean b, UserCollectsNum userCollectsNum, Request request, Response response) {
                    if (userCollectsNum.isState()) {
                        list_count.set(0, userCollectsNum.getBody().getSceneNum() + "");
                        list_count.set(1, userCollectsNum.getBody().getTopicNum() + userCollectsNum.getBody().getStrategyNum() + "");
                        list_count.set(2, userCollectsNum.getBody().getGoodsNum() + "");
                        Log.e("collectnumnew", list_count.get(0));
                        Log.e("collectnumnew", list_count.get(1));
                        Log.e("collectnumnew", list_count.get(2));
                        personCollectTabAdapter.setListCount(list_count);
                        personCollectTabAdapter.notifyDataSetChanged();
                        mViewPager.setAdapter(personCollectTabAdapter);
                        mtoorbar_tab.setViewPager(mViewPager);

                    }

                }
            });

        }
    }

    public void transferToMyFragment() {
        if (myFragment == null)
            myFragment = new MyFragment();

        getFragmentManager().beginTransaction()
                .replace(R.id.content, myFragment)
                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("person", "destroy");
        rootView = null;
        EventBus.getDefault().unregister(this);//反注册EventBus
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        if(!CurrentAccount.LoginOrNot)transferToMyFragment();
        if(CurrentAccount.isReFresh()){
            Log.e(TAG, "iniDatas");
            iniDatas();
            CurrentAccount.setReFresh(false);
        }
        MobclickAgent.onPageStart(TAG);

    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }

}
