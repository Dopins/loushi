package com.android.loushi.loushi.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.loushi.loushi.R;
import com.android.loushi.loushi.callback.DialogCallback;
import com.android.loushi.loushi.callback.JsonCallback;
import com.android.loushi.loushi.jsonbean.Area;
import com.android.loushi.loushi.jsonbean.ImageJson;
import com.android.loushi.loushi.jsonbean.School;
import com.android.loushi.loushi.jsonbean.UserInfoJson;
import com.android.loushi.loushi.jsonbean.UserLoginJson;
import com.android.loushi.loushi.util.CurrentAccount;
import com.android.loushi.loushi.util.MaterialSpinner;
import com.android.loushi.loushi.util.MyfragmentEvent;
import com.android.loushi.loushi.util.RoundImageView;
import com.android.loushi.loushi.util.SelectPicPopupWindow;
import com.android.loushi.loushi.util.UnderLineEditText;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;

import okhttp3.Request;
import okhttp3.Response;

public class PersonalInformationActivity extends BaseActivity {
    private String TAG = "PersonalInfoActivity";


    private SelectPicPopupWindow menuWindow;
    public final static int PHOTO_ZOOM = 0;
    public final static int TAKE_PHOTO = 1;
    public final static int PHOTO_RESULT = 2;
    public static final String IMAGE_UNSPECIFIED = "image/*";
    private String imageDir;
    private Uri imageUri;
    private String img_url;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

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
    private EditText edit_phone;
    private Button btn_save;
    private TextView text_exit;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        initDatas();
        test();
    }

    private void test() {

        headImgUrl = CurrentAccount.getHeadImgUrl();
        if (headImgUrl != "null") {
            Log.e(TAG, "bindViews: img_url = " + img_url);
            Picasso.with(this).load(img_url).fit().into(image_circular);
        } else {
            Log.e(TAG, "headImgUrl为空!");
        }
    }

    private void bindViews() {

        toolbar = (Toolbar) findViewById(R.id.Toolbar);
        btn_return = (ImageButton) findViewById(R.id.btn_return);
        image_circular = (RoundImageView) findViewById(R.id.image_circular);
        edit_nickname = (EditText) findViewById(R.id.edit_nickname);
        spinner_sex = (MaterialSpinner) findViewById(R.id.spinner_sex);
        spinner_province = (MaterialSpinner) findViewById(R.id.spinner_province);
        spinner_university = (MaterialSpinner) findViewById(R.id.spinner_university);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        btn_save = (Button) findViewById(R.id.btn_save);
        text_exit = (TextView) findViewById(R.id.Exit);
    }

    private void initDatas() {

        spinner_sex.setItems("男", "女");
        spinner_sex.setDropdownMaxHeight(600);
        spinner_province.setItems("广东省", "广西省", "山东省", "海南省", "安徽省", "四川省");
        spinner_province.setDropdownMaxHeight(600);
        spinner_university.setItems("No.1", "No.2", "No.3", "No.4", "No.5");
        spinner_university.setDropdownMaxHeight(600);
    }

    public void onClickbtn_return(View view) {
        finish();
    }

    public void onClickExit(View view) {
        CurrentAccount.setLoginOrNot(false);
        MobclickAgent.onProfileSignOff();
        finish();
    }

    public void onClickimage_circular(View view) {
        menuWindow = new SelectPicPopupWindow(PersonalInformationActivity.this, itemsOnClick);
        menuWindow.showAtLocation(PersonalInformationActivity.this.findViewById(R.id.personalInFoContainer), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    //改变头像
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.tv_call_camera:
                    imageDir = "temp.jpg";
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File(Environment.getExternalStorageDirectory(), imageDir)));
                    startActivityForResult(intent, TAKE_PHOTO);
                    break;
                case R.id.tv_call_gallery:
                    File outputImage = new File(Environment.getExternalStorageDirectory()
                            , "output_Image.jpg");
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
                            .execute(new DialogCallback<ImageJson>(PersonalInformationActivity.this,ImageJson.class) {
                        @Override
                        public void onResponse(boolean isFromCache, ImageJson imageJson, Request request, @Nullable Response response) {
                            if (imageJson.getState()) {
                                headImgUrl = imageJson.getBody();

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
        sex = spinner_sex.getSelectedIndex() + "";

        Log.e(TAG, user_id);
        Log.e(TAG, nickname);
        Log.e(TAG, headImgUrl);
        Log.e(TAG, schoolName);
        Log.e(TAG, sex);

        OkHttpUtils.post("http://www.loushi666.com/LouShi/user/userinfoAlt.action")
                .params("user_id", user_id)
                .params("nickname", nickname)
                .params("headImgUrl", headImgUrl)
                .params("school.id", schoolName)
                .params("sex", sex)
                .execute(new JsonCallback<UserInfoJson>(UserInfoJson.class) {
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


}
