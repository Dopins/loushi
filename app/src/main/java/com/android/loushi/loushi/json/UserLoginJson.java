package com.android.loushi.loushi.json;

/**
 * Created by Administrator on 2016/4/22.
 */
public class UserLoginJson {
    /**
     * body : 9
     * code : null
     * return_info : null
     * state : true
     */

    private int body;
    private String code;
    private String return_info;
    private boolean state;

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public String  getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String  getReturn_info() {
        return return_info;
    }

    public void setReturn_info(String  return_info) {
        this.return_info = return_info;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
