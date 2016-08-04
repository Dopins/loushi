package com.android.loushi.loushi.event;

/**
 * Created by Administrator on 2016/8/3.
 */
public class ReceiveSmsEvent {
    int msg;
    public ReceiveSmsEvent(){
        super();
    }
    public ReceiveSmsEvent(int msg){
        this.msg=msg;
    }
    public int getMsg(){
        return msg;
    }
}
