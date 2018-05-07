package com.trevonn.molecule.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffrey on 2017/12/5.
 */

public class TipView extends FrameLayout {

    /**  动画间隔  */
    private static final int ANIM_DELAYED_MILLIONS = 5 * 1000;
    /**  动画持续时长  */
    private static final int ANIM_DURATION = 1 * 1000;
    /**  默认字体颜色  */
    private static final String DEFAULT_TEXT_COLOR = "#2F4F4F";
    /**  默认字体大小  dp  */
    private static final int DEFAULT_TEXT_SIZE = 16;
    private Animation anim_out, anim_in;
    private TextView tv_tip_out, tv_tip_in ;
    private CardView tip_cardview_in,tip_cardview_out;
    /**  循环播放的消息  */
    private ArrayList tipList;
    /**  当前轮播到的消息索引  */
    private int curTipIndex = 0;
    private long lastTimeMillis ;
    private View view1,view2;


    public TipView(Context context) {
        this(context, null);
    }

    public TipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTipFrame();
        initAnimation();
    }
    private void initTipFrame() {
//        tip_cardview_in = newCardView2();
//        tip_cardview_out = newCardView();

    }

    /**
     *  设置要循环播放的信息
     * @param tipList
     */
    public void setTipList(ArrayList tipList) {
        this.tipList = tipList;
        curTipIndex = 0;
        updateTip(view1);
        updateTipAndPlayAnimation();
    }

    private void initAnimation() {
        anim_out = newAnimation(0, -1);
        anim_in = newAnimation(1, 0);
        anim_in.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updateTipAndPlayAnimationWithCheck();
            }
        });
    }

    private void updateTipAndPlayAnimation() {
        if (curTipIndex % 2 == 0) {
            updateTip(view1);
            tip_cardview_out.startAnimation(anim_in);
            tip_cardview_in.startAnimation(anim_out);

            this.bringChildToFront(tip_cardview_in);

        } else {
            updateTip(view2);
            tip_cardview_in.startAnimation(anim_in);
            tip_cardview_out.startAnimation(anim_out);
            this.bringChildToFront(tip_cardview_out);
        }
    }

    private void updateTipAndPlayAnimationWithCheck() {
        if (System.currentTimeMillis() - lastTimeMillis < 1000 ) {
            return ;
        }
        lastTimeMillis = System.currentTimeMillis();
        updateTipAndPlayAnimation();
    }

    private void updateTip(View v) {

//        CircleImageView circleImageView;
//        TextView nickname,time,address,content,god_name;
//
//        if(curTipIndex % 2 == 0){
//            circleImageView = (CircleImageView) v.findViewById(R.id.head_image1);
//            nickname = (TextView) v.findViewById(R.id.nickname1);
//            time = (TextView) v.findViewById(R.id.time1);
//            address = (TextView) v.findViewById(R.id.address1);
//            content = (TextView) v.findViewById(R.id.content1);
//            god_name = (TextView) v.findViewById(R.id.god_name1);
//        }else{
//            circleImageView = (CircleImageView) v.findViewById(R.id.head_image2);
//            nickname = (TextView) v.findViewById(R.id.nickname2);
//            time = (TextView) v.findViewById(R.id.time2);
//            address = (TextView) v.findViewById(R.id.address2);
//            content = (TextView) v.findViewById(R.id.content2);
//            god_name = (TextView) v.findViewById(R.id.god_name2);
//        }
//        TipsInfo tip = (TipsInfo)getNextTip() ;//tipList.get(curTipIndex);
//        String bgimgurl = tip.getUser().getAvatar();
//        MyApp.getInstance().DonwloadImage( circleImageView, bgimgurl);
//        nickname.setText(tip.getUser().getName());
//        time.setText(tip.getCreated_at());
//        address.setText("在"+tip.getPost().getTitle());
//        god_name.setText("向"+tip.getWishes()+"祈福");
//        content.setText(tip.getContent());

    }

    /**
     *  获取下一条消息
     * @return
     */
//    private TipsInfo getNextTip() {
//        if (isListEmpty(tipList)) return null;
//        return  (TipsInfo) tipList.get(curTipIndex++ % tipList.size());
//    }
//
//    private CardView newCardView(){
//        view1 = inflate(getContext(), R.layout.view_tipview, this);
//        CardView cardView = (CardView) view1.findViewById(R.id.tip_cardview1);
//        return cardView;
//    }
//    private CardView newCardView2() {
//        view2 = inflate(getContext(), R.layout.view_tipview_two, this);
//        CardView cardView = (CardView) view2.findViewById(R.id.tip_cardview2);
//        return cardView;
//    }



    private Animation newAnimation(float fromYValue, float toYValue) {
        Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,fromYValue,Animation.RELATIVE_TO_SELF,toYValue, Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF, 0);
        anim.setDuration(ANIM_DURATION);
        anim.setStartOffset(ANIM_DELAYED_MILLIONS);
        anim.setInterpolator(new DecelerateInterpolator());
        return anim;
    }

    /**
     *  将资源图片转换为Drawable对象
     * @param ResId
     * @return
     */
    private Drawable loadDrawable(int ResId) {
        Drawable drawable = getResources().getDrawable(ResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth() - 10, drawable.getMinimumHeight() - 10);
        return drawable;
    }

    /**
     *  list是否为空
     * @param list
     * @return true 如果是空
     */
    public static boolean isListEmpty(List list) {
        return list == null || list.isEmpty();
    }
}

