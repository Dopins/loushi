package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.loushi.loushi.R;

/**
 * Created by Administrator on 2016/7/19.
 */
public class RegistFragment extends Fragment {
    public static final String BUNDLE_TITLE = "title";
    private String mTitle = "DefaultValue";

    public RegistFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RegistFragment newInstance(String param1, String param2) {
        RegistFragment fragment = new RegistFragment();
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
        View view = inflater.inflate(R.layout.fragment_regist, container, false);
        return view;
    }

    public static RegistFragment newInstance(String title)
    {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        RegistFragment fragment = new RegistFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}