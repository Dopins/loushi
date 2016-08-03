package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class SearchJson  implements Serializable {
    /**
     * state : 1
     * code : 10000
     * return_info : 错误信息
     * body : [{"id":21,"name":"好枕头","groupID":22,"topicGroup":{"id":22,"name":"枕头"},"collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"imgUrl":"asd"},{"id":221,"name":"坏枕头","groupID":22,"topicGroup":{"id":22,"name":"枕头"},"collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"imgUrl":"asd"}]
     */

    private boolean state;
    private String code;
    private String return_info;
    /**
     * id : 21
     * name : 好枕头
     * groupID : 22
     * topicGroup : {"id":22,"name":"枕头"}
     * collectionNum : 333
     * collected : 0
     * forwordNum : 12
     * commentNum : 6
     * imgUrl : asd
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
        private int pid;
        /**
         * id : 21
         * name : 海贼宿舍
         * label : 海贼王
         * groupID : 22
         * sceneGroup : {"id":22,"name":"动漫风"}
         * collectionNum : 333
         * collected : 0
         * forwordNum : 12
         * commentNum : 6
         * imgUrl : asd
         */

        private SceneJson.BodyBean scene;
        /**
         * id : 22
         * name : 名称
         * collectionNum : 333
         * collected : 0
         * forwordNum : 12
         * commentNum : 6
         * imgUrl : asd
         */

        private GuideJson.BodyBean strategy;
        /**
         * id : 21
         * name : 好枕头
         * groupID : 22
         * topicGroup : {"id":22,"name":"枕头"}
         * collectionNum : 333
         * collected : 0
         * forwordNum : 12
         * commentNum : 6
         * imgUrl : asd
         */

        private GuideJson.BodyBean topic;
        /**
         * id : 21
         * name : 无脸人台灯
         * price : 33.21
         * introduction : 介绍
         * url : 导流链接
         * images : [{"id":21,"sort":0,"url":"图片链接","type":1,"pid":2},{"id":23,"sort":1,"url":"图片链接","type":1,"pid":2}]
         * collectionNum : 333
         * collected : 0
         * forwordNum : 12
         * commentNum : 6
         */

        private GoodsJson.BodyBean goods;

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

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public SceneJson.BodyBean getScene() {
            return scene;
        }

        public void setScene(SceneJson.BodyBean scene) {
            this.scene = scene;
        }

        public GuideJson.BodyBean getStrategy() {
            return strategy;
        }

        public void setStrategy(GuideJson.BodyBean strategy) {
            this.strategy = strategy;
        }

        public GuideJson.BodyBean getTopic() {
            return topic;
        }

        public void setTopic(GuideJson.BodyBean topic) {
            this.topic = topic;
        }

        public GoodsJson.BodyBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsJson.BodyBean goods) {
            this.goods = goods;
        }




    }
}
