package com.android.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.android.library.utils.MyAnimationUtils;


/**
 * Created by fei on 2015/9/10.
 */
public class DeleteableEdittext extends AppCompatEditText {
    private Drawable rightDrawable;
    private Drawable[] drawables;
    private boolean isHasFocus;

    public DeleteableEdittext(Context context) {
        super(context);
        init();
    }

    public DeleteableEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DeleteableEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //returns drawables imgages for the  left,top,right,bottom;
        drawables = this.getCompoundDrawables();
        rightDrawable = drawables[2];
        this.addTextChangedListener(new MyTextWatcher());
        setClearDrawableVisible(false);
        setOnFocusChangeListener(new FocusChangeListener());
    }

    private void setClearDrawableVisible(boolean isVisible) {
        Drawable myRightDrawable = isVisible ? rightDrawable : null;
        this.setCompoundDrawables(drawables[0], drawables[1], myRightDrawable, drawables[3]);
    }

    public void setAnimation() {
        MyAnimationUtils.viewShake(this);
    }
    /**
     *判断手指抬起的区域坐标，是否在清除图标内
     * getWidth() 得到控件的宽度
     * event.getX() 点击X坐标
     * getTotalpaddingRight() 获得图标左边缘距离控件右边缘的距离
     * getPaddingRight() 控件左边缘距离图标右边缘的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                boolean isClean =  event.getX() > (getWidth()-getTotalPaddingRight()) && event.getX() < (getWidth() - getPaddingRight());
                if(isClean){
                    setText("");
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private class FocusChangeListener implements OnFocusChangeListener {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            isHasFocus = hasFocus;
            if (isHasFocus) {
                boolean isVisible = getText().toString().length() > 1;
                setClearDrawableVisible(isVisible);
            } else {
                setClearDrawableVisible(false);
            }
        }
    }

    /**
     * 内容编辑监听事件
     */
    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean isVisible = getText().toString().length() > 1;
            setClearDrawableVisible(isVisible);
        }
    }
}
