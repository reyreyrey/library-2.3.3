package com.android.library.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityEditUserMessageBinding;
import com.android.library.manager.UserManager;
import com.android.library.models.BaseModel;
import com.android.library.models.UserModel;
import com.android.library.utils.GlideImageLoader;
import com.android.library.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;

import java.io.File;
import java.util.ArrayList;

import static com.android.library.utils.Cons.EDIT_USER_INFO;


/**
 * author: Rea.X
 * date: 2017/12/14.
 */

public class EditUserMessageActivity extends UIActivity<ActivityEditUserMessageBinding> implements View.OnClickListener {
    private ImageItem currentPhoto;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_user_message;
    }

    @Override
    protected void init() {
        tvTitle.setText("资料");
        refresh();
        databinding.ivPhoto.setOnClickListener(this);
        initImagePicker();
    }

    private void refresh() {
        UserModel model = UserManager.getCurrentUser();
        String photo = model.getPhoto();
        if (!TextUtils.isEmpty(photo)) {
            Glide.with(this).load(photo).into(databinding.ivPhoto);
        }
        String emaile = model.getEmail();
        if (!TextUtils.isEmpty(emaile)) {
            databinding.edtEmail.setText(emaile);
        }
        String nickname = model.getNickname();
        if (!TextUtils.isEmpty(nickname)) {
            databinding.edtNickname.setText(nickname);
        }
        String phone = model.getPhone();
        if (!TextUtils.isEmpty(phone)) {
            databinding.edtPhone.setText(phone);
        }
        String sign = model.getSign();
        if (!TextUtils.isEmpty(sign)) {
            databinding.edtSign.setText(sign);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_photo) {
            Intent intent = new Intent(this, ImageGridActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(200);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(200);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(400);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(400);//保存文件的高度。单位像素
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 1) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && images.size() != 0) {
                    currentPhoto = images.get(0);
                    Glide.with(this).load(currentPhoto.path).into(databinding.ivPhoto);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        final String nickname = databinding.edtNickname.getText().toString().trim();
        String email = databinding.edtEmail.getText().toString().trim();
        String phone = databinding.edtPhone.getText().toString().trim();
        String sign = databinding.edtSign.getText().toString().trim();
        PostRequest request = OkGo.<String>post(EDIT_USER_INFO).isMultipart(true);
        request.params("user_id", UserManager.getCurrentUser().getUserid());
        if (!TextUtils.isEmpty(nickname)) {
            request.params("nickname", nickname);
        }
        if (!TextUtils.isEmpty(email)) {
            request.params("email", email);
        }
        if (!TextUtils.isEmpty(phone)) {
            request.params("phone", phone);
        }
        if (!TextUtils.isEmpty(sign)) {
            request.params("sign", sign);
        }
        if (currentPhoto != null) {
            request.params("photo", new File(currentPhoto.path));
        }
        showProgress();
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body();
                dismissProgress();
                BaseModel<UserModel> baseModel = new Gson().fromJson(result, new TypeToken<BaseModel<UserModel>>() {
                }.getType());
                if (baseModel.getSuccess() == 1) {
                    UserManager.saveUser(baseModel.getData());
                    ToastUtils.toastSuccess(context, "修改成功！");
                    refresh();
                } else {
                    ToastUtils.toastSuccess(context, "修改失败！" + response.message());
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissProgress();
                ToastUtils.toastSuccess(context, "修改失败！");
            }
        });
    }
}
