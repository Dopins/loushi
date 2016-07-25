package com.android.loushi.loushi.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class StrategyContentJson   {
    /**
     * state : 1
     * code : 10000
     * return_info : 错误信息
     * body : [{"id":21,"sort":0,"type":2,"content":"neirong","strategyID":22,"strategy":{"id":21,"name":"名称","collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"imgUrl":"asd"}}]
     */

    private int state;
    private String code;
    private String return_info;
    /**
     * id : 21
     * sort : 0
     * type : 2
     * content : neirong
     * strategyID : 22
     * strategy : {"id":21,"name":"名称","collectionNum":333,"collected":0,"forwordNum":12,"commentNum":6,"imgUrl":"asd"}
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

    public static class BodyBean  implements Serializable {
        private int id;
        private int sort;
        private int type;
        private String content;
        private int strategyID;
        /**
         * id : 21
         * name : 名称
         * collectionNum : 333
         * collected : 0
         * forwordNum : 12
         * commentNum : 6
         * imgUrl : asd
         */

        private StrategyBean strategy;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
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

        public int getStrategyID() {
            return strategyID;
        }

        public void setStrategyID(int strategyID) {
            this.strategyID = strategyID;
        }

        public StrategyBean getStrategy() {
            return strategy;
        }

        public void setStrategy(StrategyBean strategy) {
            this.strategy = strategy;
        }

        public static class StrategyBean implements Serializable  {
            private int id;
            private String name;
            private int collectionNum;
            private int collected;
            private int forwordNum;
            private int commentNum;
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

            public int getCollectionNum() {
                return collectionNum;
            }

            public void setCollectionNum(int collectionNum) {
                this.collectionNum = collectionNum;
            }

            public int getCollected() {
                return collected;
            }

            public void setCollected(int collected) {
                this.collected = collected;
            }

            public int getForwordNum() {
                return forwordNum;
            }

            public void setForwordNum(int forwordNum) {
                this.forwordNum = forwordNum;
            }

            public int getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(int commentNum) {
                this.commentNum = commentNum;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }
    }
}
