package com.android.loushi.loushi.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.DialogCallback;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.ImageJson;
import com.android.loushi.loushi.jsonbean.ProvinceJson;
import com.android.loushi.loushi.jsonbean.ResponseJson;
import com.android.loushi.loushi.jsonbean.SchoolJson;
import com.android.loushi.loushi.jsonbean.UserInfoJson;
import com.android.loushi.loushi.util.CurrentAccount;
import com.android.loushi.loushi.util.KeyConstant;
import com.android.loushi.loushi.util.MaterialSpinner;
import com.android.loushi.loushi.util.RoundImageView;
import com.android.loushi.loushi.util.SelectHeadPicDialog;
import com.android.loushi.loushi.util.UrlConstant;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

public class PersonalInformationActivity extends BaseActivity
        implements MaterialSpinner.OnItemSelectedListener {
    private String TAG = "PersonalInfoActivity";

    private Dialog dialog;
    public final static int PHOTO_ZOOM = 0;
    public final static int TAKE_PHOTO = 1;
    public final static int PHOTO_RESULT = 2;
    public static final String IMAGE_UNSPECIFIED = "image/*";
    private String imageDir;
    private Uri imageUri;

    private String user_id;
    private String nickname;
    private String headImgUrl;
    private String schoolName;
    private String sex;


    // Content View Elements

    private Toolbar toolbar;
    private ImageButton btn_return;
    private RoundImageView image_circular;
    private EditText edit_nickname;
    private MaterialSpinner spinner_sex;
    private MaterialSpinner spinner_province;
    private MaterialSpinner spinner_university;
    private MaterialSpinner spinner_city;
    private EditText edit_phone;
    private Button btn_save;

    private TextView text_exit;
    private List<ProvinceJson.BodyBean> provinceBeanlist;
    private List<SchoolJson.BodyBean> schoolBeanList;
    private List<ProvinceJson.BodyBean> cityBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        initDatas();
        //test();
//        if(!EventBus.getDefault().isRegistered(this))
//            EventBus.getDefault().register(this);

    }

    private void test() {

        headImgUrl = CurrentAccount.getHeadImgUrl();
        if (!headImgUrl.equals("null")) {
            Log.e(TAG, "test : img_url = " + headImgUrl);
            Picasso.with(this).load(headImgUrl).fit().into(image_circular);
        } else {
            Log.e(TAG, "headImgUrl为空!");
        }
    }

    private void bindViews() {

        toolbar = (Toolbar) findViewById(R.id.Toolbar);
        btn_return = (ImageButton) findViewById(R.id.btn_return);
        image_circular = (RoundImageView) findViewById(R.id.image_circular);
        edit_nickname = (EditText) findViewById(R.id.edit_nickname);
        spinner_city = (MaterialSpinner) findViewById(R.id.spinner_city);
        spinner_sex = (MaterialSpinner) findViewById(R.id.spinner_sex);
        spinner_province = (MaterialSpinner) findViewById(R.id.spinner_province);
        spinner_university = (MaterialSpinner) findViewById(R.id.spinner_university);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        btn_save = (Button) findViewById(R.id.btn_save);
        text_exit = (TextView) findViewById(R.id.Exit);
    }

    private void initDatas() {
        if (CurrentAccount.getHeadImgUrl() != null)
            Picasso.with(this).load(CurrentAccount.getHeadImgUrl()).into(image_circular);
        if (CurrentAccount.getNickname() != null)
            edit_nickname.setText(CurrentAccount.getNickname());
        if (CurrentAccount.getMobile_phone() != null)
            edit_phone.setText(CurrentAccount.getMobile_phone());


        spinner_sex.setItems("女", "男");
        spinner_sex.setDropdownMaxHeight(300);
        getProvince();
        spinner_province.setOnItemSelectedListener(this);
        spinner_province.setDropdownMaxHeight(300);
        spinner_city.setOnItemSelectedListener(this);
        if (CurrentAccount.getSex() != null && CurrentAccount.getSex().equals("1")) {
            Log.e(TAG + "sex", CurrentAccount.getSex());
            spinner_sex.setSelectedIndex(1);
        } else spinner_sex.setSelectedIndex(0);

    }

    public void onClickbtn_return(View view) {
        finish();
    }

    public void onClickExit(View view) {
        LogOut();
    }

    public void onClickimage_circular(View view) {

        dialog = new SelectHeadPicDialog(this, itemsOnClick);
        dialog.show();
    }

    public void LogOut() {
        Log.e("personinfo", CurrentAccount.getUser_id());
        OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userLogout").params("user_id", CurrentAccount.getUser_id())
                .execute(new JsonCallback<ResponseJson>(ResponseJson.class) {
                    @Override
                    public void onResponse(boolean b, ResponseJson responseJson, Request request, @Nullable Response response) {
                        Log.e("personinfo", new Gson().toJson(responseJson));
                        if (responseJson.getState()) {
                            CurrentAccount.setLoginOrNot(false);
                            MobclickAgent.onProfileSignOff();
                            finish();
                        }
                    }
                });
    }

    //改变头像
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            dialog.dismiss();
            switch (v.getId()) {
                case R.id.tv_call_camera:
                    imageDir = "temp.jpg";
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File(Environment.getExternalStorageDirectory(), imageDir)));
                    startActivityForResult(intent, TAKE_PHOTO);
                    break;
                case R.id.tv_call_gallery:
                    File outputImage = new File(Environment.getExternalStorageDirectory(), "output_Image.jpg");
                    try {
                        if (outputImage.exists()) {
                            outputImage.delete();
                        }
                        outputImage.createNewFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    imageUri = Uri.fromFile(outputImage);
                    Intent intent2 = new Intent("android.intent.action.PICK");
                    intent2.setType("image/*");
                    intent2.putExtra("crop", true);
                    intent2.putExtra("scale", true);
                    intent2.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                    startActivityForResult(intent2, PHOTO_ZOOM);//参数传TAKE_PHOTO
                    break;
                default:
                    break;
            }


        }

    };

    public void photoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, PHOTO_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PHOTO_ZOOM) {
                photoZoom(data.getData());
            }
            if (requestCode == TAKE_PHOTO) {
                File picture = new File(Environment.getExternalStorageDirectory() + "/" + imageDir);
                photoZoom(Uri.fromFile(picture));
            }

            if (requestCode == PHOTO_RESULT) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    final Bitmap photo = extras.getParcelable("data");
                    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.JPEG, 30, bStream);
                    byte[] bytes = bStream.toByteArray();
                    String ss = Base64.encodeToString(bytes, Base64.DEFAULT);
                    Log.e(TAG, ss);
                    Log.e(TAG, CurrentAccount.getUser_id());

                    OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userHeadImg")
                            .params("img", ss)
                            .params("user_id", CurrentAccount.getUser_id())
                            .params("imgFileName", "1.jpg")
                            .execute(new DialogCallback<ImageJson>(this, ImageJson.class) {
                                @Override
                                public void onResponse(boolean isFromCache, ImageJson imageJson, Request request, @Nullable Response response) {
                                    if (imageJson.getState()) {
                                        headImgUrl = imageJson.getBody();

                                        Log.e(TAG, "上传头像成功！");
                                        Log.e(TAG, headImgUrl);
                                        CurrentAccount.setHeadImgUrl(headImgUrl);
                                        image_circular.setImageBitmap(photo);

                                    }
                                }


                            });
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClick_btn_save(View view) {
        Log.e(TAG, "onClick_btn_save");

        user_id = CurrentAccount.getUser_id();
        nickname = edit_nickname.getText().toString();
        headImgUrl = CurrentAccount.getHeadImgUrl();
        schoolName = spinner_university.getSelectedIndex() + 1 + "";
        String sexBool = "false";
        if (spinner_sex.getSelectedIndex() == 1)
            sexBool = "true";
        //sex = spinner_sex.getSelectedIndex() + "";

        Log.e(TAG, user_id);
        Log.e(TAG, nickname);
        Log.e(TAG, headImgUrl);
        Log.e(TAG, schoolName);
        //Log.e(TAG, sex);

        OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userinfoAlt.action")
                .params("user_id", user_id)
                .params("nickname", nickname)
                .params("headImgUrl", headImgUrl)
                .params("school.id", schoolName)
                .params("sex", sexBool)
                .execute(new DialogCallback<UserInfoJson>(this, UserInfoJson.class) {
                    @Override
                    public void onResponse(boolean isFromCache, UserInfoJson userInfoJson, Request request, @Nullable Response response) {
                        Log.e(TAG, "UserInfoJson.onResponse: " + response.toString());
                        Log.e(TAG, "userInfoJson.getState: " + userInfoJson.isState());
                        if (userInfoJson.isState()) {
                            Log.e(TAG, "onResponse: 资料提交成功 ！");

                            CurrentAccount.storeDatas(nickname, headImgUrl, schoolName, sex);
                            CurrentAccount.setReFresh(true);
                            finish();
                        }
                    }
                });
    }

    private void getSchoolList(String id) {
        getSchoolList(id, 0, 100);
    }

    private void getSchoolList(String area_id, int skip, int take) {
        OkHttpUtils.post(UrlConstant.GETSCHOOLGURL)
                .params(KeyConstant.AREA_ID, area_id)
                .params(KeyConstant.SKIP, skip + "")
                .params(KeyConstant.TAKE, take + "")
                .execute(new JsonCallback<SchoolJson>(SchoolJson.class) {
                    @Override
                    public void onResponse(boolean b, SchoolJson schoolJson, Request request, @Nullable Response response) {
                        if (schoolJson.isState()) {
                            if (schoolJson.getBody() == null)
                                return;
                            schoolBeanList = new ArrayList<SchoolJson.BodyBean>();
                            schoolBeanList.addAll(schoolJson.getBody());
                            int len = schoolBeanList.size();
                            List<String> schools = new ArrayList<String>();
                            for (int i = 0; i < len; i++)
                                schools.add(schoolBeanList.get(i).getName());
                            Log.i("mytest","size=="+schools.size());
                            spinner_university.setItems(schools);
                        } else
                            Toast.makeText(mContext, schoolJson.getReturn_info(), Toast.LENGTH_SHORT).show();

                    }
                });

    }
    public void getProvince() {
        OkHttpUtils.post(UrlConstant.USERAREAGURL)
                .execute(new JsonCallback<ProvinceJson>(ProvinceJson.class) {
            @Override
            public void onResponse(boolean b, ProvinceJson provinceJson, Request request, @Nullable Response response) {
                if (provinceJson.isState()) {
                    provinceBeanlist = new ArrayList<ProvinceJson.BodyBean>();
                    provinceBeanlist.addAll(provinceJson.getBody());
                    int len = provinceBeanlist.size();
                    List<String> provices = new ArrayList<String>();
                    for (int i = 0; i < len; i++)
                        provices.add(provinceBeanlist.get(i).getProvince());
                    spinner_province.setItems(provices);
                   initDefaultCity();
                }
            }
        });
    }


    private void getCityList(String province) {
        OkHttpUtils.post(UrlConstant.USERAREAGURL)
                .params(KeyConstant.PROVINCE, province)
                .execute(new JsonCallback<ProvinceJson>(ProvinceJson.class) {
                    @Override
                    public void onResponse(boolean b, ProvinceJson provinceJson, Request request, @Nullable Response response) {
                        Log.e("cityresponse", new Gson().toJson(provinceJson));
                        if (provinceJson.isState()) {
                            if (provinceJson.getBody() == null)
                                return;
                            cityBeanList = new ArrayList<ProvinceJson.BodyBean>();
                            cityBeanList.addAll(provinceJson.getBody());
                            List<String> citys = new ArrayList<String>();
                            int len = cityBeanList.size();
                            for (int i = 0; i < len; i++) {
                                citys.add(cityBeanList.get(i).getCity());
                            }
                            spinner_city.setItems(citys);
                            initDefaultSchool();
                        }
                    }
                });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().post(new MyfragmentEvent("Transfer PersonalFragment to MyFragment!"));
    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

        switch(view.getId()){
            case R.id.spinner_sex:
                break;
            case R.id.spinner_province:
                getCityList(provinceBeanlist.get(position).getId()+"");
                break;
            case R.id.spinner_city:
                getSchoolList(cityBeanList.get(position).getId() + "");
                break;
            case R.id.spinner_university:
                break;
        }
    }

    private void initDefaultCity(){
        if(cityBeanList==null||cityBeanList.size()==0)
            return;
        getCityList(cityBeanList.get(0).getId() + "");
    }

    private void initDefaultSchool(){
        if(schoolBeanList==null||schoolBeanList.size()==0)
            return;
        getSchoolList(schoolBeanList.get(0).getId() + "");
    }
}
