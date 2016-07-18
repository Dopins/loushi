package com.android.loushi.loushi.json;

import java.util.List;

/**
 * Created by liaoyt on 16-4-26.
 */
public class Topicjson {
    /**
     * body : [{"collected":true,"collectionNum":9,"commentNum":10,"forwordNum":0,"groupID":1,"id":1,"imgUrl":null,"name":"专题组名称_专题名称1","topicGroup":{"id":1,"imgUrl":null,"name":"专题组名称","topicNum":0}},{"collected":true,"collectionNum":3,"commentNum":0,"forwordNum":0,"groupID":1,"id":2,"imgUrl":null,"name":"专题组名称_专题名称2","topicGroup":{"id":1,"imgUrl":null,"name":"专题组名称","topicNum":0}},{"collected":true,"collectionNum":3,"commentNum":0,"forwordNum":0,"groupID":1,"id":3,"imgUrl":null,"name":"专题组名称_专题名称1","topicGroup":{"id":1,"imgUrl":null,"name":"专题组名称","topicNum":0}},{"collected":true,"collectionNum":2,"commentNum":0,"forwordNum":0,"groupID":1,"id":4,"imgUrl":null,"name":"专题组名称_专题名称2","topicGroup":{"id":1,"imgUrl":null,"name":"专题组名称","topicNum":0}},{"collected":true,"collectionNum":2,"commentNum":0,"forwordNum":0,"groupID":1,"id":5,"imgUrl":null,"name":"专题组名称_专题名称1","topicGroup":{"id":1,"imgUrl":null,"name":"专题组名称","topicNum":0}},{"collected":true,"collectionNum":2,"commentNum":0,"forwordNum":0,"groupID":1,"id":6,"imgUrl":null,"name":"专题组名称_专题名称1","topicGroup":{"id":1,"imgUrl":null,"name":"专题组名称","topicNum":0}}]
     * code : null
     * return_info : 获取成功！
     * state : true
     */

    private Object code;
    private String return_info;
    private boolean state;
    /**
     * collected : true
     * collectionNum : 9
     * commentNum : 10
     * forwordNum : 0
     * groupID : 1
     * id : 1
     * imgUrl : null
     * name : 专题组名称_专题名称1
     * topicGroup : {"id":1,"imgUrl":null,"name":"专题组名称","topicNum":0}
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
        private boolean collected;
        private int collectionNum;
        private int commentNum;
        private int forwordNum;
        private int groupID;
        private int id;
        private String imgUrl;
        private String name;
        /**
         * id : 1
         * imgUrl : null
         * name : 专题组名称
         * topicNum : 0
         */

        private TopicGroupBean topicGroup;

        public boolean isCollected() {
            return collected;
        }

        public void setCollected(boolean collected) {
            this.collected = collected;
        }

        public int getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(int collectionNum) {
            this.collectionNum = collectionNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getForwordNum() {
            return forwordNum;
        }

        public void setForwordNum(int forwordNum) {
            this.forwordNum = forwordNum;
        }

        public int getGroupID() {
            return groupID;
        }

        public void setGroupID(int groupID) {
            this.groupID = groupID;
        }

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

        public TopicGroupBean getTopicGroup() {
            return topicGroup;
        }

        public void setTopicGroup(TopicGroupBean topicGroup) {
            this.topicGroup = topicGroup;
        }

        public static class TopicGroupBean {
            private int id;
            private Object imgUrl;
            private String name;
            private int topicNum;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(Object imgUrl) {
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
}
