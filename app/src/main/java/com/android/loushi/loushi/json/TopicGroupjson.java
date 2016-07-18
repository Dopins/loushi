package com.android.loushi.loushi.json;

import java.util.List;

/**
 * Created by liaoyt on 16-4-25.
 */
public class TopicGroupjson {
    /**
     * body : [{"id":1,"imgUrl":"http://img0.imgtn.bdimg.com/it/u=3581988992,2491669142&fm=11&gp=0.jpg","name":"专题组名称","topicNum":6},{"id":2,"imgUrl":"http://img0.imgtn.bdimg.com/it/u=3581988992,2491669142&fm=11&gp=0.jpg","name":"专题组名称","topicNum":2},{"id":3,"imgUrl":"http://img0.imgtn.bdimg.com/it/u=3581988992,2491669142&fm=11&gp=0.jpg","name":"专题组名称","topicNum":2}]
     * code : null
     * return_info : 获取成功！
     * state : true
     */

    private Object code;
    private String return_info;
    private boolean state;
    /**
     * id : 1
     * imgUrl : http://img0.imgtn.bdimg.com/it/u=3581988992,2491669142&fm=11&gp=0.jpg
     * name : 专题组名称
     * topicNum : 6
     */

    private List<BodyBean> body;

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getReturn_info() {
        return return_info;
    }

    public void setReturn_info(String return_info) {
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
        private int id;
        private String imgUrl;
        private String name;
        private int topicNum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
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
    }
}
