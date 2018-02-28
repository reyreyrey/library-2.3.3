package com.android.library.ui;

import android.text.TextUtils;
import android.view.View;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityLuckPanelBinding;
import com.android.library.views.luckymonkeypanel.LuckyMonkeyPanelView;
import com.mylhyl.circledialog.CircleDialog;

import java.util.Random;

public class LuckPanelActivity extends UIActivity<ActivityLuckPanelBinding> implements LuckyMonkeyPanelView.OnGameStopListener, Runnable {

    private CircleDialog.Builder builder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_luck_panel;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        else
            tvTitle.setText("手气");
        builder = new CircleDialog.Builder(context);
        databinding.btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!databinding.luckyPanel.isGameRunning()) {
                    databinding.luckyPanel.startGame();
                    handler.postDelayed(LuckPanelActivity.this, 10000);
                }
            }
        });
    }


    @Override
    public void gameStoped(int pos) {
        String result = null;
        switch (pos) {
            case 0:
                result = "今天魅力强劲，得到大家的喜爱，并且他人对你的信任感也有所增强，人脉资源变广。在与人应酬、交谈时要注意言辞，说话要表达清楚才能避免误会，还要注意别过于以自我为中心。";
                break;
            case 1:
                result = "今天可能需要处理一些过去没有彻底处理好的事情，或是要跟旧人见面。要是有出行的话，最好提早准备，以免中途有差错，上班也要提前出门。与人之间可能会有一些小误会，幸好影响不大。";
                break;
            case 2:
                result = "某些人某些事会让你懂得更多的道理，之前你可能有方向迷惑，那么这一周将会有新的领悟。职场和生活中都有贵人出现，自信心十足，对未来有新想法和期待，可能会与某些人会有再一次的接触，想要继续之前的合作。最近个人有一股新的信念推动着你向前走，让你打开局面，有一种冲动要出去闯闯，看看自己的能耐极限。";
                break;
            case 3:
                result = "你与爱人彼此有各自的小秘密和小心思，或者想要体验更多亲密关系的乐趣。但，可遇不可求，用对方式才能够增加感情。本月可能与爱人有资金上的互动，少数人要注意某一人对感情的忠诚度。生活中多关心身体健康，避免感冒发热，还要消除“愤怒的心”，男生避免好斗，女生避免嫉妒，保持一个好心态会增加你的运势。职场动力不足，你会隐藏自身的“攻击性”和“目标性”，可不要树立起敌人，避免小人。钱财方面可能会为他人较多的花销，或者信用卡还款吃力。";
                break;
            case 4:
                result = "你是否会考虑和爱人一同培育彼此的结晶，对于已婚人士来说可能会有这方面的打算，也可能这并不是你的计划，只是突然成为了你的计划和需要面对的事（来得意外），但本月彼此也可以在感情中尝试些“与众不同”来增加感情。如果你单身，娱乐社交类的活动增加，约你的人不少，说不定有机会遇到特别的人，而你骨子里也是怪咖一枚，不需要多解释就心领神会，甚至可以激发你原来的一面。本月一些人会得到来自朋友的消息，或者有机会接触到新朋友和老朋友相聚。";
                break;
            case 5:
                result = "单身的你可能会有“猎奇艳遇”，可能你会被某些人关注到和邀请到，和某人产生愉快的火花。至少本月你愿意尝试更多的新鲜物，拒绝墨守成规。有伴者会在子女身上多花时间，可能打算备孕或提供给子女们良好的教育和愉快的假期。本月出入社交场合的时间增加，生活和工作中可能会拥有更多的创意和有趣感想，活跃度提升。";
                break;
            case 6:
                result = "你需要多协调家庭，家人之间的协调度，可能在某些事上有不同意见，难免引发出争执。某位家人最近脾气不佳，可要多包容。一些人会在本月察觉和揭露某些秘密，或者对外公开一些消息。而公开这个消息或得到这个秘密未必是你一开始所想要的模式，过程并不顺利，或者只是因为你的一时冲动而产生的决定。职场上要注意和上司等有权威的人士沟通，可能你内心对某人有不满，或者将坏情绪带到了家庭和生活中，别让你的个人形象标有“急躁不安”的词汇了。如果将这样的精神放入创业上会不错。";
                break;
            case 7:
                result = "要避免在感情相处中过于地纠结某些小细节，也要排除“我不说，TA也一定能明白我意思的” 想法，这都会你和爱人的距离拉远，甚至产生纠葛。本月可能有钱财上的互动，比如爱人希望你能在资金上资助TA，或者你某一人有出行和情绪不稳，有聚少离多或疏远感。单身人士是，考虑得越多越没用结果。你有时候会非常相信宿命感，相信可以，可不要灰心。";
                break;
            case 8:
                result = "有伴者感情中可能会产生疏离感，不光是身体上遥远的距离（某人外出等），也可能是心的距离变得遥远。如果是后者，那么请从源头找找彼此之间存在的问题。部分人会有异地恋或者对远方的人有想法。本月可能会有出行旅游，或者处理有关于资金上的法律事务。你可能表面上充满了信心，实际上会有些自我独裁，比如你的自信心是通过“内心的强大，无关他己”强壮起来的。";
                break;
        }
        builder
                .setCanceledOnTouchOutside(false)
                .setCancelable(false)
                .setTitle("测试结果")
                .setText(result)
                .setPositive("确定", null);
        builder.show();
    }

    @Override
    public void run() {
        int stayIndex = new Random().nextInt(8);
        databinding.luckyPanel.tryToStop(stayIndex, this);
    }
}
