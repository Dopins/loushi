package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class TopicGroupJson implements Serializable {
    /**
     * state : 1
     * code : 10000
     * return_info : 错误信息
     * body : [{"id":21,"name":"名称","topicNum":52,"imgUrl":"asdad.jpg"}]
     */

    private boolean state;
    private String code;
    private String return_info;
    /**
     * id : 21
     * name : 名称
     * topicNum : 52
     * imgUrl : asdad.jpg
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

    public static class BodyBean implements Serializable  {
        private int id;
        private String name;
        private int topicNum;
        private String imgUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTopicNum() {
            return topicNum;
        }

        public void setTopicNum(int topicNum) {
            this.topicNum = topicNum;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
