package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.util.UnderLineEditText;

/**
 * Created by Administrator on 2016/7/19.
 */
public class LoginFragment extends Fragment {

    public static final String BUNDLE_TITLE = "title";
    private String mTitle = "DefaultValue";

    public LoginFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        UnderLineEditText editText_phone = (UnderLineEditText) view.findViewById(R.id.phone_edit);
        SpannableString ss1 = new SpannableString("  手机");
        AbsoluteSizeSpan ass1 = new AbsoluteSizeSpan(12,true);
        ss1.setSpan(ass1, 0, ss1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText_phone.setHint(ss1);

        UnderLineEditText editText_password = (UnderLineEditText) view.findViewById(R.id.password_edit);
        SpannableString ss2 = new SpannableString(" 密码");
        AbsoluteSizeSpan ass2 = new AbsoluteSizeSpan(12,true);
        ss2.setSpan(ass2, 0, ss2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText_password.setHint(ss2);

        return view;
    }

    public static LoginFragment newInstance(String title)
    {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
