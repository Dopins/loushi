package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.loushi.loushi.R;

/**
 * Created by Administrator on 2016/7/19.
 */
public class RegistFragment extends Fragment {
    public static final String BUNDLE_TITLE = "title";

    private EditText edit_phone;
    private EditText edit_password;
    private EditText edit_checkword;
    private Button btn_getcheckword;
    private Button btn_finish;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_regist, container, false);

        btn_getcheckword = (Button) view.findViewById(R.id.btn_getcheckword);
        btn_finish = (Button) view.findViewById(R.id.btn_finish);

        edit_phone = (EditText) view.findViewById(R.id.regist_edit_phone);
        edit_phone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MyFragment.Gone();
                return false;
            }

        });

        edit_password = (EditText) view.findViewById(R.id.regist_edit_password);
        edit_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MyFragment.Gone();
                return false;
            }

        });

        edit_checkword = (EditText) view.findViewById(R.id.regist_edit_checkword);
        edit_checkword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                MyFragment.Gone();
                return false;
            }

        });
        return view;
    }

    public static RegistFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(BUNDLE_TITLE, title);
        RegistFragment fragment = new RegistFragment();
        fragment.setArguments(args);
        return fragment;
    }
}