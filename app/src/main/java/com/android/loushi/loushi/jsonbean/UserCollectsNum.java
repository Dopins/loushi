package com.android.loushi.loushi.jsonbean;

/**
 * Created by Administrator on 2016/8/1.
 */
public class UserCollectsNum {
    /**
     * goodsNum : 6
     * sceneNum : 4
     * strategyNum : 2
     * topicNum : 3
     */

    private BodyBean body;
    /**
     * body : {"goodsNum":6,"sceneNum":4,"strategyNum":2,"topicNum":3}
     * code : null
     * return_info : null
     * state : true
     */

    private Object code;
    private Object return_info;
    private boolean state;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
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

    public static class BodyBean {
        private int goodsNum;
        private int sceneNum;
        private int strategyNum;
        private int topicNum;

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public int getSceneNum() {
            return sceneNum;
        }

        public void setSceneNum(int sceneNum) {
            this.sceneNum = sceneNum;
        }

        public int getStrategyNum() {
            return strategyNum;
        }

        public void setStrategyNum(int strategyNum) {
            this.strategyNum = strategyNum;
        }

        public int getTopicNum() {
            return topicNum;
        }

        public void setTopicNum(int topicNum) {
            this.topicNum = topicNum;
        }
    }
}
