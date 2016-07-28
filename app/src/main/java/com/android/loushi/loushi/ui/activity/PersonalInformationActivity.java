package com.android.loushi.loushi.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.util.CircularImageView;
import com.android.loushi.loushi.util.UnderLineEditText;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class PersonalInformationActivity extends AppCompatActivity {


    // Content View Elements

    private Toolbar toolbar;
    private ImageButton btn_return;
    private CircularImageView image_circular;
    private UnderLineEditText edit_nickname;
    private MaterialSpinner spinner_sex;
    private MaterialSpinner spinner_province;
    private MaterialSpinner spinner_university;
    private UnderLineEditText edit_phone;
    private Button btn_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        bindViews();
        initDatas();
    }

    private void initDatas() {

        spinner_sex.setItems("男","女");
        spinner_sex.setDropdownMaxHeight(600);
        spinner_province.setItems("广东省","广西省","山东省","海南省","安徽省","四川省");
        spinner_province.setDropdownMaxHeight(600);
        spinner_university.setItems("大学1","大学2","大学3","大学4","大学5");
        spinner_university.setDropdownMaxHeight(600);
    }


    private void bindViews() {

        toolbar = (Toolbar) findViewById(R.id.Toolbar);
        btn_return = (ImageButton) findViewById(R.id.btn_return);
        image_circular = (CircularImageView) findViewById(R.id.image_circular);
        edit_nickname = (UnderLineEditText) findViewById(R.id.edit_nickname);
        spinner_sex = (MaterialSpinner) findViewById(R.id.spinner_sex);
        spinner_province = (MaterialSpinner) findViewById(R.id.spinner_province);
        spinner_university = (MaterialSpinner) findViewById(R.id.spinner_university);
        edit_phone = (UnderLineEditText) findViewById(R.id.edit_phone);
        btn_save = (Button) findViewById(R.id.btn_save);
    }

}
