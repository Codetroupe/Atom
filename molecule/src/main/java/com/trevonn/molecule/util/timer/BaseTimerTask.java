package com.trevonn.molecule.util.timer;

import java.util.TimerTask;

/**
 * Created by Jeffrey on 2018/3/13
 * @function 倒计时
 */


public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener){
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if(mITimerListener != null){
            mITimerListener.onTimer();
        }
    }
}

