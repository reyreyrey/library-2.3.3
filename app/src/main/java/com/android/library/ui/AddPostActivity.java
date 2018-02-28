package com.android.library.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityAddPostBinding;
import com.android.library.databinding.ItemImageviewBinding;
import com.android.library.manager.UserManager;
import com.android.library.utils.Cons;
import com.android.library.utils.GlideImageLoader;
import com.android.library.utils.ScreenUtil;
import com.android.library.utils.ToastUtils;
import com.bumptech.glide.Glide;
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

/**
 * author: Rea.X
 * date: 2017/12/13.
 */

public class AddPostActivity extends UIActivity<ActivityAddPostBinding> implements View.OnClickListener {

    private ImagePicker imagePicker;
    private static final int MAX_IMAGE_COUNT = 3;
    private ArrayList<ImageItem> currentImages;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_post;
    }

    @Override
    protected void init() {
        tvTitle.setText("发帖");
        currentImages = new ArrayList<>();
        databinding.ivAddImage.setOnClickListener(this);
        initImagePicker();
    }

    private void initImagePicker() {
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(MAX_IMAGE_COUNT - currentImages.size());    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(200);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(200);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(400);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(400);//保存文件的高度。单位像素
    }

    private void startPick() {
        imagePicker.setSelectLimit(MAX_IMAGE_COUNT - currentImages.size());    //选中数量限制
        Intent intent = new Intent(this, ImageGridActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_add_image) {
            startPick();
        }
    }

    private void send() {
        String title = databinding.edtTitle.getText().toString().trim();
        String content = databinding.edtContent.getText().toString().trim();
        String intro = databinding.edtIntro.getText().toString().trim();
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
            ToastUtils.toastError(context, "标题或内容不能为空！");
            return;
        }
        if (TextUtils.isEmpty(intro) ) {
            ToastUtils.toastError(context, "简介不能为空！");
            return;
        }
        showProgress();
        PostRequest request = OkGo.<String>post(Cons.ADD_POST_URL)
                .isMultipart(true)
                .params("user_id", UserManager.getCurrentUser().getUserid())
                .params("title", title)
                .params("intro", intro)
                .params("content", content);

        if (currentImages != null && currentImages.size() != 0) {
            ImageItem imageItem;
            if (currentImages.size() == 1) {
                imageItem = currentImages.get(0);
                request.params("img1", new File(imageItem.path));
            }
            if (currentImages.size() == 2) {
                imageItem = currentImages.get(0);
                request.params("img1", new File(imageItem.path));
                imageItem = currentImages.get(1);
                request.params("img2", new File(imageItem.path));
            }
            if (currentImages.size() == 3) {
                imageItem = currentImages.get(0);
                request.params("img1", new File(imageItem.path));
                imageItem = currentImages.get(1);
                request.params("img2", new File(imageItem.path));
                imageItem = currentImages.get(2);
                request.params("img3", new File(imageItem.path));
            }
        }

        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissProgress();
                ToastUtils.toastSuccess(context, "发布成功！");
                finish();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissProgress();
                ToastUtils.toastSuccess(context, "发布失败！");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 1) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                addImageView(images);
            }
        }
    }

    private void addImageView(ArrayList<ImageItem> images) {
        currentImages.addAll(images);
        databinding.layoutImages.removeAllViews();
        ItemImageviewBinding itemImageviewBinding;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtil.dp2px(context, 100), ScreenUtil.dp2px(context, 100));
        params.leftMargin = 10;
        params.rightMargin = 10;
        for (final ImageItem item : currentImages) {
            itemImageviewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_imageview, null, false);
            Glide.with(context).load(item.path).into(itemImageviewBinding.ivContent);
            databinding.layoutImages.addView(itemImageviewBinding.getRoot(), 0, params);
            itemImageviewBinding.ivDel.setTag(itemImageviewBinding.getRoot());
            itemImageviewBinding.ivDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View rootView = (View) view.getTag();
                    databinding.layoutImages.removeView(rootView);
                    databinding.ivAddImage.setVisibility(View.VISIBLE);
                    currentImages.remove(item);
                }
            });
        }
        if (currentImages.size() >= MAX_IMAGE_COUNT) {
            databinding.ivAddImage.setVisibility(View.GONE);
        } else {
            databinding.ivAddImage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_send, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.send) {
            send();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
