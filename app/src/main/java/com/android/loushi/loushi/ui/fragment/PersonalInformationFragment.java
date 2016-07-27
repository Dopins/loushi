package com.android.loushi.loushi.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.loushi.loushi.R;
import com.jaredrummler.materialspinner.MaterialSpinner;


public class PersonalInformationFragment extends Fragment {

    public static final String BUNDLE_TITLE = "title";


    public static PersonalInformationFragment newInstance(String title) {
        PersonalInformationFragment fragment = new PersonalInformationFragment();
        Bundle args = new Bundle();
        args.putString(BUNDLE_TITLE, title);
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
        View view =inflater.inflate(R.layout.fragment_personal_information, container, false);


        MaterialSpinner spinner = (MaterialSpinner) view.findViewById(R.id.spinner);
        spinner.setItems("男","女");
        spinner.setDropdownMaxHeight(600);
        MaterialSpinner spinner1 = (MaterialSpinner) view.findViewById(R.id.spinner1);
        spinner1.setItems("广东省","广西省","山东省","海南省","安徽省","四川省");
        spinner1.setDropdownMaxHeight(600);

        MaterialSpinner spinner2 = (MaterialSpinner) view.findViewById(R.id.spinner2);
        spinner2.setItems("大学1","大学2","大学3","大学4","大学5");
        spinner2.setDropdownMaxHeight(600);
        return view;
    }

}
