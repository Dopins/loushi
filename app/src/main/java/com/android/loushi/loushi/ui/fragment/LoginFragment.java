package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.util.UnderLineEditText;

/**
 * Created by Administrator on 2016/7/19.
 */
public class LoginFragment extends Fragment {
    public static final String BUNDLE_TITLE = "title";

    private EditText edit_phone;
    private EditText edit_password;
    private Button btn_login;
    private ImageView btn_xinlang;
    private ImageView btn_weixin;
    private ImageView btn_qq;
    private Button btn_forgetpassword;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_xinlang = (ImageView) view.findViewById(R.id.btn_xinlang);
        btn_weixin = (ImageView) view.findViewById(R.id.btn_weixin);
        btn_qq = (ImageView) view.findViewById(R.id.btn_qq);
        btn_forgetpassword = (Button) view.findViewById(R.id.btn_forgetpassword);

        edit_phone = (UnderLineEditText) view.findViewById(R.id.login_edit_phone);
        SpannableString ss2 = new SpannableString(" 密码");
        AbsoluteSizeSpan ass2 = new AbsoluteSizeSpan(12,true);
        ss2.setSpan(ass2, 0, ss2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        edit_phone.setHint(ss2);
        edit_phone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MyFragment.Gone();
                return false;
            }
        });

        edit_password = (EditText) view.findViewById(R.id.login_edit_password);
        SpannableString ss1 = new SpannableString(" 手机");
        AbsoluteSizeSpan ass1 = new AbsoluteSizeSpan(12,true);
        ss1.setSpan(ass1, 0, ss1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        edit_password.setHint(ss1);
        edit_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MyFragment.Gone();
                return false;
            }

        });


        return view;
    }

    public static LoginFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(BUNDLE_TITLE, title);
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
