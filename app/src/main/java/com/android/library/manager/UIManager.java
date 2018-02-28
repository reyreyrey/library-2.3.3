package com.android.library.manager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.android.library.ui.AboutActivity;
import com.android.library.ui.ArtActivity;
import com.android.library.ui.ChatActivity;
import com.android.library.ui.ChatListActivity;
import com.android.library.ui.CheckUpdateActivity;
import com.android.library.ui.FeedbackActivity;
import com.android.library.ui.FuliActivity;
import com.android.library.ui.GuigushiActivity;
import com.android.library.ui.LoginActivity;
import com.android.library.ui.LuckPanelActivity;
import com.android.library.ui.ManhuaListActivity;
import com.android.library.ui.MineActivity;
import com.android.library.ui.MyAttentionActivity;
import com.android.library.ui.MyFabuActivity;
import com.android.library.ui.MySaveActivity;
import com.android.library.ui.OtherUserActivity;
import com.android.library.ui.PostListActivity;
import com.android.library.ui.RegisterActivity;
import com.android.library.ui.SearchActivity;
import com.android.library.ui.SettingActivity;
import com.android.library.ui.SystemNotifyActivity;
import com.android.library.ui.TrendChartActivity;
import com.android.library.ui.WXMeiwenActivity;
import com.android.library.ui.XiaohuaActivity;
import com.android.library.ui.dialogs.PhotoViewDialog;
import com.android.lotto.lottery.LottoTrendActivity;
import com.android.trend.ui.TrendActivity;

import ticketsystem.ui.LibraryMainActivity;

/**
 * String title = getIntent().getStringExtra("title");
 * if (!TextUtils.isEmpty(title))
 * tvTitle.setText(title);
 * else
 */
public class UIManager {

    /**
     * 艺术家列表
     *
     * @param context context
     * @param title   标题栏显示文字
     */
    public static void art(Context context, String title) {
        Intent intent = new Intent(context, ArtActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 艺术列表（使用默认标题）
     *
     * @param context context
     */
    public static void art(Context context) {
        art(context, "");
    }

    /**
     * 笑话列表页面
     *
     * @param context context
     * @param title   标题栏显示文字
     */
    public static void xiaohua(Context context, String title) {
        Intent intent = new Intent(context, XiaohuaActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 笑话列表页面（显示默认标题）
     *
     * @param context context 上下文
     */
    public static void xiaohua(Context context) {
        xiaohua(context, "");
    }

    /**
     * 查看大图页面
     *
     * @param appCompatActivity 当前Activity
     * @param imagePath         图片路径
     */
    public static void showBigImage(AppCompatActivity appCompatActivity, String imagePath) {
        PhotoViewDialog dialog = new PhotoViewDialog();
        dialog.setImage(imagePath);
        dialog.show(appCompatActivity.getSupportFragmentManager(), "1");
    }

    /**
     * 与某个用户聊天
     *
     * @param context  上下文
     * @param userName 用户名（不是用户id）
     */
    public static void chat(Context context, String userName) {
        ChatActivity.chat(context, userName);
    }

    /**
     * 会话列表 页面
     *
     * @param context 上下文
     */
    public static void chatList(Context context) {
        ChatListActivity.chatList(context);
    }

    /**
     * 福利页面（图片瀑布流）
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void fuli(Context context, String title) {
        Intent intent = new Intent(context, FuliActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 福利页面（图片瀑布流）
     *
     * @param context 上下文
     */
    public static void fuli(Context context) {
        fuli(context, "");
    }

    /**
     * 鬼故事页面
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void guigushi(Context context, String title) {
        Intent intent = new Intent(context, GuigushiActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 鬼故事页面
     *
     * @param context 上下文
     */
    public static void guigushi(Context context) {
        guigushi(context, "");
    }

    /**
     * 登录
     *
     * @param context 上下文
     */
    public static void login(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    /**
     * 幸运转盘页面
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void luck(Context context, String title) {
        Intent intent = new Intent(context, LuckPanelActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 鬼故事页面
     *
     * @param context 上下文
     */
    public static void luck(Context context) {
        luck(context, "");
    }

    /**
     * 漫画列表页面
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void manhua(Context context, String title) {
        Intent intent = new Intent(context, ManhuaListActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 漫画列表页面
     *
     * @param context 上下文
     */
    public static void manhua(Context context) {
        manhua(context, "");
    }

    /**
     * 个人中心
     *
     * @param context 上下文
     */
    public static void mine(Context context) {
        mine(context, "");
    }

    /**
     * 个人中心
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void mine(Context context, String title) {
        Intent intent = new Intent(context, MineActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 我关注的人（必须是登录状态）
     *
     * @param context 上下文
     */
    public static void myAttention(Context context) {
        myAttention(context, "");
    }

    /**
     * 我关注的人（必须是登录状态）
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void myAttention(Context context, String title) {
        Intent intent = new Intent(context, MyAttentionActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 我发布的帖子（必须是登录状态）
     *
     * @param context 上下文
     */
    public static void myFabu(Context context) {
        myFabu(context, "");
    }

    /**
     * 我发布的帖子（必须是登录状态）
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void myFabu(Context context, String title) {
        Intent intent = new Intent(context, MyFabuActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 我保存的帖子（必须是登录状态）
     *
     * @param context 上下文
     */
    public static void mySave(Context context) {
        myFabu(context, "");
    }

    /**
     * 我保存的帖子（必须是登录状态）
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void mySave(Context context, String title) {
        Intent intent = new Intent(context, MySaveActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 其他用户的个人中心
     *
     * @param context 上下文
     */
    public static void mySave(Context context, String userid, String username) {
        OtherUserActivity.seeOtherUser(context, userid, username);
    }

    /**
     * 帖子列表
     *
     * @param context 上下文
     */
    public static void postList(Context context) {
        postList(context, "");
    }

    /**
     * 帖子列表
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void postList(Context context, String title) {
        Intent intent = new Intent(context, PostListActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 注册
     *
     * @param context 上下文
     */
    public static void reg(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    /**
     * 搜索用户
     *
     * @param context 上下文
     */
    public static void searchUser(Context context) {
        searchUser(context, "");
    }

    /**
     * 搜索用户
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void searchUser(Context context, String title) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 系统通知
     *
     * @param context 上下文
     */
    public static void systemNotify(Context context) {
        systemNotify(context, "");
    }

    /**
     * 系统通知
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void systemNotify(Context context, String title) {
        Intent intent = new Intent(context, SystemNotifyActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 彩票开奖记录
     *
     * @param context 上下文
     */
    public static void caipiaoHistory(Context context) {
        caipiaoHistory(context, "");
    }

    /**
     * 彩票开奖记录
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void caipiaoHistory(Context context, String title) {
        Intent intent = new Intent(context, TrendChartActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 微信精选页面
     *
     * @param context 上下文
     */
    public static void wx(Context context) {
        wx(context, "");
    }

    /**
     * 微信精选页面
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void wx(Context context, String title) {
        Intent intent = new Intent(context, WXMeiwenActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 走势图
     *
     * @param context 上下文
     */
    public static void lotteryChar(Context context) {
        lotteryChar(context, "");
    }

    /**
     * 走势图
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void lotteryChar(Context context, String title) {
        Intent intent = new Intent(context, LottoTrendActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 走势图2
     *
     * @param context 上下文
     */
    public static void lotteryChar1(Context context) {
        lotteryChar1(context, "");
    }

    /**
     * 走势图2
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void lotteryChar1(Context context, String title) {
        Intent intent = new Intent(context, TrendActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 检查更新页面
     *
     * @param context 上下文
     */
    public static void checkUpdate(Context context) {
        checkUpdate(context, "");
    }

    /**
     * 检查更新页面
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void checkUpdate(Context context, String title) {
        Intent intent = new Intent(context, CheckUpdateActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 设置界面
     *
     * @param context 上下文
     */
    public static void setting(Context context) {
        setting(context, "");
    }

    /**
     * 设置界面
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void setting(Context context, String title) {
        Intent intent = new Intent(context, SettingActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 反馈界面
     *
     * @param context 上下文
     */
    public static void feedback(Context context) {
        feedback(context, "");
    }

    /**
     * 反馈界面
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void feedback(Context context, String title) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 关于我们界面
     *
     * @param context 上下文
     */
    public static void about(Context context) {
        about(context, "");
    }

    /**
     * 关于我们界面
     *
     * @param context 上下文
     * @param title   标题
     */
    public static void about(Context context, String title) {
        Intent intent = new Intent(context, AboutActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 走势图chart
     *
     * @param context 上下文
     */
    public static void caipiaoChatHistory(Context context) {
        Intent intent = new Intent(context, LibraryMainActivity.class);
        context.startActivity(intent);
    }
}
