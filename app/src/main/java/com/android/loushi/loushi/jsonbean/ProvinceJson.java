package com.android.loushi.loushi.jsonbean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/11.
 */
public class ProvinceJson {
    /**
     * body : [{"city":null,"district":null,"id":0,"province":"北京","valid":false},{"city":null,"district":null,"id":0,"province":"上海","valid":false},{"city":null,"district":null,"id":0,"province":"天津","valid":false},{"city":null,"district":null,"id":0,"province":"重庆","valid":false},{"city":null,"district":null,"id":0,"province":"黑龙江","valid":false},{"city":null,"district":null,"id":0,"province":"吉林","valid":false},{"city":null,"district":null,"id":0,"province":"辽宁","valid":false},{"city":null,"district":null,"id":0,"province":"山东","valid":false},{"city":null,"district":null,"id":0,"province":"山西","valid":false},{"city":null,"district":null,"id":0,"province":"陕西","valid":false},{"city":null,"district":null,"id":0,"province":"河北","valid":false},{"city":null,"district":null,"id":0,"province":"河南","valid":false},{"city":null,"district":null,"id":0,"province":"湖北","valid":false},{"city":null,"district":null,"id":0,"province":"湖南","valid":false},{"city":null,"district":null,"id":0,"province":"海南","valid":false},{"city":null,"district":null,"id":0,"province":"江苏","valid":false},{"city":null,"district":null,"id":0,"province":"江西","valid":false},{"city":null,"district":null,"id":0,"province":"广东","valid":false},{"city":null,"district":null,"id":0,"province":"广西","valid":false},{"city":null,"district":null,"id":0,"province":"云南","valid":false},{"city":null,"district":null,"id":0,"province":"贵州","valid":false},{"city":null,"district":null,"id":0,"province":"四川","valid":false},{"city":null,"district":null,"id":0,"province":"内蒙古","valid":false},{"city":null,"district":null,"id":0,"province":"宁夏","valid":false},{"city":null,"district":null,"id":0,"province":"甘肃","valid":false},{"city":null,"district":null,"id":0,"province":"青海","valid":false},{"city":null,"district":null,"id":0,"province":"西藏","valid":false},{"city":null,"district":null,"id":0,"province":"新疆","valid":false},{"city":null,"district":null,"id":0,"province":"安徽","valid":false},{"city":null,"district":null,"id":0,"province":"浙江","valid":false},{"city":null,"district":null,"id":0,"province":"福建","valid":false},{"city":null,"district":null,"id":0,"province":"香港","valid":false},{"city":null,"district":null,"id":0,"province":"澳门","valid":false},{"city":null,"district":null,"id":0,"province":"台湾","valid":false}]
     * code : null
     * return_info : null
     * state : true
     */

    private Object code;
    private Object return_info;
    private boolean state;
    /**
     * city : null
     * district : null
     * id : 0
     * province : 北京
     * valid : false
     */

    private List<BodyBean> body;

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

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        private String city;
        private String district;
        private int id;
        private String province;
        private boolean valid;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Object getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }
    }
}
