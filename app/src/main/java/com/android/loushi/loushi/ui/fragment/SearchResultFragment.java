package com.android.loushi.loushi.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.loushi.loushi.R;

/**
 * Created by dopin on 2016/7/21.
 */
public class SearchResultFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search_result, container, false);
        init(view);
        return view;
    }
    private void init(View view){

    }
}
