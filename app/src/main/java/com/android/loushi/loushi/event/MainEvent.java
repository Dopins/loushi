package com.android.loushi.loushi.event;

/**
 * Created by Administrator on 2016/8/7.
 */
public class MainEvent {
    int msg;
    public final static int UPDATE_USERINFO=5;
    public final static int NEED_LOGIN=6;
    public final static int UPDATE_COLLECT=7;
    public final static int LOGIN_UPDATEINFO=8;
    public final static int STATE_CONNECT_FAIL=9;


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
