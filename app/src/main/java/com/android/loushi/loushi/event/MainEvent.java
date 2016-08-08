package com.android.loushi.loushi.event;

/**
 * Created by Administrator on 2016/8/7.
 */
public class MainEvent {
    int msg;
    public static int UPDATE_USERINFO=5;
    public static int NEED_LOGIN=6;
    public static int UPDATE_COLLECT=7;

    public MainEvent(){
        super();
    }
    public MainEvent(int msg){
        this.msg=msg;
    }
    public int getMsg(){
        return msg;
    }
}
