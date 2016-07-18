package com.android.loushi.loushi.json;

/**
 * Created by dell-pc on 2016/4/25.
 */
public class UserRegisterJson {
    private int body;
    private Object code;
    private Object return_info;
    private boolean state;

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getReturn_info() {
        return return_info;
    }

    public void setReturn_info(Object return_info) {
        this.return_info = return_info;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
