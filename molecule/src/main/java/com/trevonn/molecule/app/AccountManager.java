package com.trevonn.molecule.app;


import com.trevonn.molecule.util.storage.AtomPreference;

/**
 * Created by Jeffrey on 2018/3/13
 * @function 用户状态管理
 */


public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

    //    保存用户登录状态，登录后调用
    public static void setSignState(boolean state){
        AtomPreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    private static boolean isSignIn(){
        return AtomPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if(isSignIn()){
            checker.onSignIn();
        }else{
            checker.onNotSignIn();
        }

    }


}

