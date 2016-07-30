package com.android.loushi.loushi.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
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
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.adapter.PersonCollectTabAdapter;
import com.android.loushi.loushi.adapter.ViewPagerAdapter;
import com.android.loushi.loushi.ui.activity.GoodDetailActivity;
import com.android.loushi.loushi.ui.activity.MyMessageActivity;
import com.android.loushi.loushi.ui.activity.SceneDetailActivity;

import com.android.loushi.loushi.util.RoundImageView;
import com.android.loushi.loushi.util.SlidingTabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

/**
 * Created by Administrator on 2016/7/18.
 */

//个人中心
public class PersonFragment extends BaseFragment {

    private Toolbar mToolbar;
    private TextView mTv_index;
    //private TabLayout mtoorbar_tab;
    private SlidingTabLayout mtoorbar_tab;
    private AppBarLayout appBarLayout;
    private TextView tv_name;
    private RoundImageView img_head;
    private TextView tv_feed;
    private TextView tv_name_small;
    private RoundImageView img_head_small;
    private View rootView;
                            //定义viewPager
    private PersonCollectTabAdapter personCollectTabAdapter;                               //定义adapter
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_cate;
    private List<String> list_count;
    //private CategoryFragment categoryFragment;
    private CollectGoodFragment collectGoodFragment;
    private ViewPager mViewPager;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Button btn_profile;
    private ImageButton btn_my_message;
    private CollapsingToolbarLayoutState state;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        Log.e("Test: "+"PersonFragment", "onActivityCreated");
//        mToolbar=(Toolbar)getView().findViewById(R.id.program_toolbar);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
//        mToolbar.setTitle("");
//        mTv_index=(TextView)mToolbar.findViewById(R.id.toolbar_index);
//        mTv_index.setText("我的");
        initView();
        //mToolbar.setTitle("loushi");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_personal, null);
//
//
//        return inflater.inflate(R.layout.fragment_personal, container, false);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_personal, null);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }


        return rootView;
    }

    public void initView(){


        initToolBar();
        initTablayout();
        initButton();
        initAppBar();
    }

    private void initTablayout() {
        mViewPager = (ViewPager)getView().findViewById(R.id.main_vp_container);
        list_fragment = new ArrayList<>();
        collectGoodFragment=new CollectGoodFragment();
        list_fragment.add(collectGoodFragment);
        collectGoodFragment=new CollectGoodFragment();
        list_fragment.add(collectGoodFragment);
        collectGoodFragment=new CollectGoodFragment();
        list_fragment.add(collectGoodFragment);
        list_cate = new ArrayList<>();
        list_count = new ArrayList<>();
        list_cate.add("场景");
        list_cate.add("指南");
        list_cate.add("单品");
        list_count.add("32");

        list_count.add("32");
        list_count.add("32");

        personCollectTabAdapter = new PersonCollectTabAdapter(getChildFragmentManager(),list_fragment,list_cate,list_count,getContext());
        mViewPager.setAdapter(personCollectTabAdapter);


        mtoorbar_tab.setTabStripWidth(120);


        //mtoorbar_tab.setSelectedIndicatorColors(R.color.colorPrimary);
        mtoorbar_tab.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.rgb(105,184,187);
            }
        });
        mtoorbar_tab.setDistributeEvenly(true);
        mtoorbar_tab.setCustomTabView(R.layout.tab_item_view_collect, R.id.tv_tab_view_count);

        //mtoorbar_tab.setCustomTabView(R.layout.tab_item_view_collect, 0);
        mtoorbar_tab.setViewPager(mViewPager);

       // AppBarLayout app_bar_layout = (AppBarLayout)getView(). findViewById(R.id.app_bar_layout);
//        app_bar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                int[] location = new int[2];
//                img_head.getLocationOnScreen(location);
//                Log.e("vertical", Integer.toString(location[1]));
//                Log.e("verti", Integer.toString(verticalOffset));
//                if (location[1] <0) {
//                    tv_feed.setVisibility(View.GONE);
//                    img_head_small.setVisibility(View.VISIBLE);
//                    tv_name_small.setVisibility(View.VISIBLE);
//                    //mToolbar.setLogo(R.mipmap.ic_launcher);
//                } else {
//                    tv_feed.setVisibility(View.VISIBLE);
//                    img_head_small.setVisibility(View.INVISIBLE);
//                    tv_name_small.setVisibility(View.INVISIBLE);
//                }
//            }
//        });
    }

    public void initToolBar(){
        mToolbar=(Toolbar)getView().findViewById(R.id.program_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.setTitle("");
        mtoorbar_tab=(SlidingTabLayout)getView().findViewById(R.id.toolbar_tab);
        tv_name=(TextView)getView().findViewById(R.id.tv_nickname);
        img_head=(RoundImageView)getView().findViewById(R.id.img_head);
        img_head_small = (RoundImageView)mToolbar.findViewById(R.id.img_head_small);
        tv_feed=(TextView)mToolbar.findViewById(R.id.tv_feed);
        tv_name_small=(TextView)mToolbar.findViewById(R.id.tv_nickname_small);

    }
    private void initButton(){
        btn_profile = (Button)getView().findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GoodDetailActivity.class);
                startActivity(intent);
            }
        });

        btn_my_message= (ImageButton) mToolbar.findViewById(R.id.my_message);
        btn_my_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), MyMessageActivity.class);
                startActivity(intent);
            }
        });

    }
    private void initAppBar(){
        appBarLayout = (AppBarLayout)getView().findViewById(R.id.app_bar_layout);
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


}
