package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 */
public class UserAreaJson implements Serializable {
    /**
     * state : 1
     * code : 10000
     * return_info : 错误信息
     * body : [{"id":1,"isValid":true,"province":"广东省","city":"广东省","district":"广东省"},{"id":2,"isValid":true,"province":"广西省","city":"广东省","district":"广东省"}]
     */

    private int state;
    private String code;
    private String return_info;
    /**
     * id : 1
     * isValid : true
     * province : 广东省
     * city : 广东省
     * district : 广东省
     */

    private List<BodyBean> body;

    public int getState() {
        return state;
    }

    public void setState(int state) {
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

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean implements Serializable{
        private int id;
        private boolean isValid;
        private String province;
        private String city;
        private String district;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsValid() {
            return isValid;
        }

        public void setIsValid(boolean isValid) {
            this.isValid = isValid;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }
    }
}
