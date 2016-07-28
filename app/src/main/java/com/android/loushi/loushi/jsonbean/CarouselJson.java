package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class CarouselJson  implements Serializable {
    @Override
    public String toString() {
        return "CarouselJson{" +
                "state=" + state +
                ", code='" + code + '\'' +
                ", return_info='" + return_info + '\'' +
                ", body=" + body +
                '}';
    }

    /**
     * state : 1
     * code : 10000
     * return_info : 错误信息
     * body : [{"id":21,"type":1,"content":"11","imgUrl":"333"},{"id":21,"type":1,"content":"111","imgUrl":333}]
     */

    private boolean state;
    private String code;
    private String return_info;
    /**
     * id : 21
     * type : 1
     * content : 11
     * imgUrl : 333
     */

    private List<BodyBean> body;

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

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean  implements Serializable {
        private int id;
        private int type;
        private String content;
        private String imgUrl;

        @Override
        public String toString() {
            return "BodyBean{" +
                    "id=" + id +
                    ", type=" + type +
                    ", content='" + content + '\'' +
                    ", imgUrl='" + imgUrl + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
