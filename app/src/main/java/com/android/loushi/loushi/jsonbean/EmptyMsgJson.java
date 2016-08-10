package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/25.
 */
public class EmptyMsgJson implements Serializable {

    /**
     * state : 1
     * code : 10000
     * return_info : 错误信息
     * body : 3
     */

    private boolean state;
    private String code;
    private String return_info;
    private boolean body;

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReturn_info() {
        return return_info;
    }

    public void setReturn_info(String return_info) {
        this.return_info = return_info;
    }

    public  boolean getBody() {
        return body;
    }

    public void setBody(boolean body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "UserLoginJson{" +
                "state=" + state +
                ", code='" + code + '\'' +
                ", return_info='" + return_info + '\'' +
                ", body=" + body +
                '}';
    }
}
