package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.util.UnderLineEditText;

/**
 * Created by Administrator on 2016/7/19.
 */
public class RegistFragment extends Fragment {
    public static final String BUNDLE_TITLE = "title";

    

    // Content View Elements

    private View view;
    private TextView text_phone;
    private UnderLineEditText regist_edit_phone;
    private TextView text_keyword;
    private UnderLineEditText regist_edit_password;
    private TextView text_cheakword;
    private UnderLineEditText regist_edit_checkword;
    private Button btn_getcheckword;
    private Button btn_finish;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_login, container, false);
            bindViews();
            initView();
            initEvent();
        }
        return view;
    }

    private void initEvent() {
        regist_edit_phone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MyFragment.Gone();
                return false;
            }
        });
        regist_edit_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MyFragment.Gone();
                return false;
            }
        });
        regist_edit_checkword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MyFragment.Gone();
                return false;
            }
        });
    }

    private void initView() {
    }

    private void bindViews() {

        text_phone = (TextView) view.findViewById(R.id.text_phone);
        regist_edit_phone = (UnderLineEditText) view.findViewById(R.id.regist_edit_phone);
        text_keyword = (TextView) view.findViewById(R.id.text_keyword);
        regist_edit_password = (UnderLineEditText) view.findViewById(R.id.regist_edit_password);
        text_cheakword = (TextView) view.findViewById(R.id.text_cheakword);
        regist_edit_checkword = (UnderLineEditText) view.findViewById(R.id.regist_edit_checkword);
        btn_getcheckword = (Button) view.findViewById(R.id.btn_getcheckword);
        btn_finish = (Button) view.findViewById(R.id.btn_finish);
    }


    public static RegistFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(BUNDLE_TITLE, title);
        RegistFragment fragment = new RegistFragment();
        fragment.setArguments(args);
        return fragment;
    }
}